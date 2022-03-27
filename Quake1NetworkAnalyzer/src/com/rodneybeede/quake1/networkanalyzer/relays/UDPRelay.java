package com.rodneybeede.quake1.networkanalyzer.relays;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Arrays;

/**
 * Listen for packets on the specified interface and port and relay them out to the specified address and port.
 * 
 * Thread safe.
 * 
 * No socket timeout (may block while receiving until forcefully interrupted or terminated
 *
 * 
 * @author rbeede
 *
 */
public abstract class UDPRelay implements Runnable {
	// Quake 1 packet has packet length represented by 2 bytes (including quake packet header) which means the maximum length is 2^16 - 1 = 65535
	// This happens to match IPv4 UDP length as well.  IPv6 Jumbogram isn't supported in Quake although if it were the length maximum could be much bigger (see wikipedia UDP)
	// This buffer is reused for performance
	private final byte[] buffer = new byte[65535];
	
	private final InetSocketAddress listeningAddress;
	private final InetSocketAddress destinationAddress;
	
	private final DatagramSocket listeningSocket;
	
	
	/**
	 * @param listeningAddress Address (and port) to listen on.  May have port value of 0 which will choose a random one.
	 * @param destinationAddress Address (and port) of where to send the data
	 * @throws SocketException if the requested address and port could not be listened on for listeningAddress or destinationAddress is unresolved
	 */
	public UDPRelay(final InetSocketAddress listeningAddress, final InetSocketAddress destinationAddress) throws SocketException {
		this.listeningAddress = listeningAddress;
		this.destinationAddress = destinationAddress;
		
		if(this.destinationAddress.isUnresolved()) {
			throw new SocketException("Unable to resolve " + this.destinationAddress.toString());
		}
		
		this.listeningSocket = new DatagramSocket(this.listeningAddress);
	}
	
	/**
	 * Any I/O exceptions are ignored and resumes listening.
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		final DatagramPacket incomingPacket = new DatagramPacket(buffer, buffer.length);
		final DatagramPacket outgoingPacket;
		try {
			// Just have to update length each time before sending
			outgoingPacket = new DatagramPacket(buffer, 0, this.destinationAddress);
		} catch (final SocketException e) {
			// Something wrong with this.destinationAddress
			// This should have already been handled/thrown in the constructor
			throw new RuntimeException(e);
		}  
		
		while(this.listeningSocket.isBound()) {
			try {
				listeningSocket.receive(incomingPacket);  // blocks
			} catch (final IOException e) {
				// just swallow
				//FIXME consider calling an abstract method that sends along the error and allows to terminate or resume?
			}
			
			if(incomingPacket.getLength() > -1) {
				// may be expensive on memory so consider different method API to pass buffer?
				// just ask the user to not modify buffer ?
				sniff(Arrays.copyOf(buffer, incomingPacket.getLength()));
			} else {
				// Invalid so skip
				//FIXME consider calling an abstract method that sends along the error and allows to terminate or resume?
				
				continue;
			}
			
			// Relay it on (it will appear to have come from the relay and not the client)
			outgoingPacket.setLength(incomingPacket.getLength());  // proper length of actual data
			
			try {
				this.listeningSocket.send(outgoingPacket);
			} catch(final IOException e) {
				// just swallow
				//FIXME consider calling an abstract method that sends along the error and allows to terminate or resume?
			}
			
		}  // while - repeat listen/sniff/relay cycle
	}
	
	
	/**
	 * @return Port being listened on which may have been dynamically chosen based on {@link #listeningAddress}
	 */
	public int getListeningPort() {
		return this.listeningSocket.getLocalPort();
	}
	
	
	/**
	 * @param bytes Never null.  Copy of the bytes received.  bytes.length is exact length of received bytes.
	 */
	public abstract void sniff(final byte[] bytes);
}
