package com.rodneybeede.software.socketsniffer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;


/**
 * @author rbeede
 * 
 * Class for managing communication between localhost and destination
 * 
 * Listening socket is only bound to loopback interface for security purposes
 *
 * @throws UnknownHostException if problem occurs with resolving the local loopback interface
 * @throws IllegalArgumentException if destinationAddress or destinationPort are illegal
 * @throws IOException if error occurs listening on the loopback interface on the given listeningPort
 */
public class CommunicationManager {
	private final ServerSocket tcpListeningSocket;
	private final DatagramSocket udpListeningSocket;
	
	private final InetSocketAddress destinationSocketAddress;
	
	public CommunicationManager(final boolean useTCP, final int listeningPort, final String destinationAddress, final int destinationPort) throws UnknownHostException, IOException {
		super();
		
		if(useTCP) {
			// 0 --> default backlog
			// null means give loopback
			this.tcpListeningSocket = new ServerSocket(listeningPort, 0, InetAddress.getByName(null));
			this.udpListeningSocket = null;
		} else {
			this.tcpListeningSocket = null;
			this.udpListeningSocket = new DatagramSocket(listeningPort, InetAddress.getByName(null)); 
		}
		
		this.destinationSocketAddress = new InetSocketAddress(destinationAddress, destinationPort);  // might still be unresolved
	}
	
	
	public boolean isTCP() {
		return null != this.tcpListeningSocket;
	}
	
	
	/**
	 * Blocks and listens for incoming data.
	 * 
	 * <p>
	 * For TCP connections a new thread is spawn to constantly handle the traffic until the connection is closed.
	 * </p>
	 * 
	 * <p>
	 * For UDP connections no new thread is created but the reply response is simply done immediately.
	 * </p>
	 * 
	 * <p>
	 * In either case this method returns almost immediately
	 * </p>
	 * 
	 * @param snifferListener The listener to be notified whenever actual data is sent
	 * 
	 * @return String with address and port of client that connected to sniffer
	 * 
	 * @throws IOException If any socket errors occur
	 */
	public String accept(final SnifferListener snifferListener) throws IOException {
		if(isTCP()) {
			return acceptTCP(snifferListener);
		} else {
			return acceptUDP(snifferListener);
		}
	}
	
	
	private String acceptTCP(final SnifferListener snifferListener) throws IOException {
		final Socket socket = this.tcpListeningSocket.accept();  //blocks
		
		// Spawn a new thread to handle all the relaying
		new Thread(new TCPNetworkRelay(socket, this.destinationSocketAddress, snifferListener)).start();
		
		return socket.getRemoteSocketAddress().toString();
	}
	
	
	private String acceptUDP(final SnifferListener snifferListener) throws IOException {
		final byte[] buffer = new byte[GlobalConstants.BUFFER_SIZE];
		final DatagramPacket clientPacket = new DatagramPacket(buffer, buffer.length);
System.err.println("listening on UDP port " + this.udpListeningSocket.getLocalPort());
		this.udpListeningSocket.receive(clientPacket);  // blocks
		
System.err.println("Got packet from " + clientPacket.getSocketAddress().toString());
		
		if(clientPacket.getLength() < 1) {
			return clientPacket.getSocketAddress().toString();  // No data in payload
		}
		
		if(null != snifferListener) {
			snifferListener.onNetworkTraffic(Arrays.copyOf(buffer, clientPacket.getLength()), true);
		}
		
		// Relay on to server
		final DatagramPacket serverPacket = new DatagramPacket(buffer, 0, clientPacket.getLength(), this.destinationSocketAddress);
		this.udpListeningSocket.send(serverPacket);
		
		
		// Read single response from server to relay back to client
		final DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
		this.udpListeningSocket.receive(responsePacket);
		
		if(responsePacket.getLength() < 1) {
			return clientPacket.getSocketAddress().toString();  // No data in payload
		}
		
		if(null != snifferListener) {
			snifferListener.onNetworkTraffic(Arrays.copyOf(buffer, responsePacket.getLength()), false);
		}
		
		// Relay on to client
		final DatagramPacket serverClientResponsePacket = new DatagramPacket(buffer, 0, responsePacket.getLength(), clientPacket.getSocketAddress());
		this.udpListeningSocket.send(serverClientResponsePacket);

		// Return who connected to us
		return clientPacket.getSocketAddress().toString();
	}
}
