package com.rodneybeede.quake1.networkanalyzer.relays;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Arrays;

import com.rodneybeede.quake1.networkanalyzer.QuakePacketViewerUtility;
import com.rodneybeede.quake1.networkanalyzer.quakepacket.QuakeControlPacketConnectionRequestReplyAccept;

/**
 * @author rbeede
 * 
 * Handles Quake 1 connection handoff between client to relay to server
 * 
 * Client sends connect request, server will reply with accept message along with new UDP listening port on the server dedicated to that client connection
 * 
 * This means this class has to do the hand-off, intercept and modify the port value, and start a new listener for the client
 *
 */
public class QuakeServerControlPortRelay implements Runnable {
	private final InetSocketAddress quakeServerSocketAddress;
	/**
	 * Control (connect) requests from client to server where relay is intercepting server and relaying it onward
	 */
	private final int relayListeningPort;
	
	
	
	public QuakeServerControlPortRelay(final InetSocketAddress quakeServerSocketAddress, final int localListeningPort) {
		super();
		
		this.quakeServerSocketAddress = quakeServerSocketAddress;
		this.relayListeningPort = localListeningPort;
	}



	@Override
	public void run() {
		final DatagramSocket relayListeningSocket;
		try {
			relayListeningSocket = new DatagramSocket(this.relayListeningPort);  // Must listen on all interfaces as Quake client&server force use of loopback to another interface instead
		} catch(final SocketException e) {
			System.err.println(this.getClass().getSimpleName() + " unable to listen on port " + this.relayListeningPort);
			e.printStackTrace();
			return;
		}
		
		
		// So at this point client to relay and relay to client communication all happen for Control (connect) packets via this DatagramSocket
		
		
		// We need a socket for server to relay communication that can timeout (in case the Quake server isn't accessible over the network) to prevent blocking forever
		final DatagramSocket quakeServerSocket;
		try {
			quakeServerSocket = new DatagramSocket();
			quakeServerSocket.setSoTimeout(1 * 1000);
		} catch(final SocketException excep) {
			// System won't allow basic use of sockets
			System.err.println("Unable to setup " + DatagramSocket.class.getSimpleName() + " for communication to Quake server.  Basic network support failure.");
			excep.printStackTrace();
			return;  // fatal
		}
		
		
		// Quake 1 packet has packet length represented by 2 bytes (including quake packet header) which means the maximum length is 2^16 - 1 = 65535
		// This happens to match IPv4 UDP length as well.  IPv6 Jumbogram isn't supported in Quake although if it were the length maximum could be much bigger (see wikipedia UDP)
		// This buffer is reused for performance
		final byte[] buffer = new byte[65535];
		
		
		while(true) {
			// Listen for client to relay connect request packet
			final DatagramPacket clientToRelayDatagram = new DatagramPacket(buffer, buffer.length);

			try {
				relayListeningSocket.receive(clientToRelayDatagram);
			} catch(final IOException e) {
				// Probably an interrupt, just go on
				System.err.println("WARNING: " + e.getMessage());
				continue;
			}
			
			// We need to remember the Quake client's IP address and remote port because the Quake Server changes its port later but expects a client to be from that tuple
			// of ip:port
			
			final SocketAddress quakeClientSocketAddress = clientToRelayDatagram.getSocketAddress();
			
			
			QuakePacketViewerUtility.display(buffer, clientToRelayDatagram.getLength(), true, quakeClientSocketAddress);
			
			//TODO	finish work here of fixing up hadnling of source addresses and stuff
			// Forward on to Quake server
			final DatagramPacket relayToServerDatagram;
			try {
				relayToServerDatagram = new DatagramPacket(buffer, clientToRelayDatagram.getLength(), this.quakeServerSocketAddress);
			} catch(final SocketException e) {
				System.err.println("Failed to find address of Quake server at " + this.quakeServerSocketAddress.toString());
				e.printStackTrace();
				continue;
			}
			
			try {
				quakeServerSocket.send(relayToServerDatagram);
			} catch(final IOException e) {
				// Not really sure why this could occur except for the OS to drop its networking stack?
				e.printStackTrace();
				continue;
			}  
			
			
			// Wait (with timeout) for reply from Quake server
			// We could have simply resume the loop, but the Quake server isn't going to communicate for long with this client on this port
			//	It hands out a dedicated port to the client for in-game use so we just need to grab that Accept packet with the port info
			//	so we can intercept and start a relay there too
			// Could have pushed to separate thread to speed up multiple clients connecting, but Quake only allows 16 anyway so not that important
			final DatagramPacket serverToRelayDatagram = new DatagramPacket(buffer, buffer.length);
			try {
				quakeServerSocket.receive(serverToRelayDatagram);
			} catch(final SocketTimeoutException e) {
				System.err.println("Timeout waiting for reply from Quake server at " + this.quakeServerSocketAddress.toString());
				continue;
			} catch(final IOException e) {
				// Probably an interrupt, just go on
				System.err.println("WARNING: " + e.getMessage());
				continue;
			}
			

			QuakePacketViewerUtility.display(buffer, serverToRelayDatagram.getLength(), false, serverToRelayDatagram.getSocketAddress());
			
			
			// Modify with custom port for relay and spawn dedicated client channel to intercept game channel communication
			byte[] modifiedConnectionAccept;
			try {
				modifiedConnectionAccept = interceptServerClientCommChannel(buffer, serverToRelayDatagram.getLength(), quakeServerSocket.getLocalPort(), (InetSocketAddress) clientToRelayDatagram.getSocketAddress());
			} catch (final SocketException e) {
				// Probably an interrupt, just go on
				System.err.println("WARNING: " + e.getMessage());
				continue;
			}
			
			if(null != modifiedConnectionAccept) {
				System.out.println("Modified Quake Accept connection packet:");
				QuakePacketViewerUtility.display(modifiedConnectionAccept, modifiedConnectionAccept.length, false, serverToRelayDatagram.getSocketAddress());
			} else {
				modifiedConnectionAccept = Arrays.copyOf(buffer, serverToRelayDatagram.getLength());  // Restore original so it can be passed unmodified
			}
				
			
			// Forward modified server's reply on to Quake client
			DatagramPacket relayToClientDatagram;
			try {
				relayToClientDatagram = new DatagramPacket(modifiedConnectionAccept, modifiedConnectionAccept.length, clientToRelayDatagram.getSocketAddress());
			} catch (final SocketException excep) {
				System.err.println("Failed to find address of Quake client (that relay just talked with) at " + clientToRelayDatagram.getSocketAddress().toString());
				excep.printStackTrace();
				continue;
			}
			
			try {
				relayListeningSocket.send(relayToClientDatagram);
			} catch(final IOException e) {
				// Not really sure why this could occur except for the OS to drop its networking stack?
				e.printStackTrace();
				continue;
			}  
		}
	}
	
	
	/**
	 * @param originalPacket
	 * @param origLength
	 * @return null if error occurred.  Modified packet bytes otherwise.
	 * @throws SocketException 
	 */
	private byte[] interceptServerClientCommChannel(final byte[] originalPacket, final int origLength, final int listPortForCommFromQServerToRelay, final InetSocketAddress relayToQClientAddress) throws SocketException {
		final QuakeControlPacketConnectionRequestReplyAccept qcpcrra = new QuakeControlPacketConnectionRequestReplyAccept(originalPacket, origLength);
		
		if(!qcpcrra.isValid()) {
			// Probably a CCREP_SERVER_INFO or some other chatter, ignore
			return null;  // signals to skip modification
		}
		
		// The port the Quake server has dedicated to listen on for this client's game connection
		// We will use this for the second relay between in-game client and Quake server
		final int quakeServerChannelPort = qcpcrra.getUDPPort();
		
		final InetSocketAddress quakeServerGameChannelAddress = new InetSocketAddress(this.quakeServerSocketAddress.getAddress(), quakeServerChannelPort);
		
		// We need a new listening relay for the client to server in-game connection
		final QuakeServerClientGamePort qscgp = new QuakeServerClientGamePort(0, quakeServerGameChannelAddress, listPortForCommFromQServerToRelay, relayToQClientAddress);
		
		// Get the randomly chosen port that the relay is now listening on for QClient -> QServer communication
		final int fakeQServerChannelPort = qscgp.getRelayListeningPort();
		
		// Return a modified Quake packet with the new (little-endian) port
		final byte[] modifiedPacketBytes = Arrays.copyOf(originalPacket, origLength);
		
		// Modify bytes 6 & 7 (the UDP port the QServer sent to the QClient (captured by relay)
		modifiedPacketBytes[5] = (byte)(fakeQServerChannelPort);  // 32-bit integer to 8-bit byte taking last 8 bits (from left to right, or those on the right side)
		modifiedPacketBytes[6] = (byte)(fakeQServerChannelPort >> 8);  // 3rd set of 8-bits (from left to right) (or bits 17 - 24)
		
		return modifiedPacketBytes;
	}
}
