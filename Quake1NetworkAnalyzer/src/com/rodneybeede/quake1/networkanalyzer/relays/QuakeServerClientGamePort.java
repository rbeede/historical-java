package com.rodneybeede.quake1.networkanalyzer.relays;

import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * @author rbeede
 * 
 * How the UDP port usage goes in a normal connection routine (no relay)
 * Note that port desig of (example) r12345 means that the port was chosen random, later uses of 12345 are that binded port
 * 
 * QClient:r12345 -> connect -> QServer:28000
 * QClient:12345 <- accept, use game channel port r54321 <- QServer:28000
 * 
 * QClient:12345 <- get game setup data <- QServer:54321
 * QClient:12345 -> ack game setup data -> QServer:54321
 * 
 * <hr />
 * 
 * How it works with the relay added in
 * 
 * QClient:r12345 -> Relay:28000 _ Relay:r56789 -> QServer:29000
 *                               _ Relay:56789 <- QServer:29000 (says use new port of 29001)
 * QClient:12345 <- Relay:28000 _  (tells client to use 28001 now
 * QClient:12345 -> Relay:28001 _ Relay:56789 -> QServer:29001
 * QClient:12345 <- Relay:28001 _ Relay:56789 <- QServer:29001
 * 
 */
public class QuakeServerClientGamePort implements Runnable {

	private final UDPRelay clientToServerRelay;
	private final UDPRelay serverToClientRelay;
	
	/**
	 * @param clientToRelayGamePort Port that the client expects the relay (aka server) to be listening on.  Set to 0 to choose dynamically
	 * @param relayToQServerGameAddress Address and port of the Quake server (port isn't 28000 but client connection dedicated port QServer is now listening on)
	 * @param qserverToRelayExpectedPort Port that Quake Server thinks the client is using to receive packets from the QServer (really the relay listening for QServer game packets)
	 * @param relayToQClientGameAddress Address and port that Quake client is expecting game (not server connect 28000) packets from relay (aka fake QServer) 
	 * @throws SocketException If UDP listening couldn't be setup
	 * @see #getRelayListeningPort()
	 */
	public QuakeServerClientGamePort(final int clientToRelayGamePort, final InetSocketAddress relayToQServerGameAddress, final int qserverToRelayExpectedPort, final InetSocketAddress relayToQClientGameAddress) throws SocketException {
		super();
		
		this.clientToServerRelay = new UDPRelay(new InetSocketAddress(clientToRelayGamePort), relayToQServerGameAddress) {
			@Override
			public void sniff(byte[] bytes) {
				System.out.println("Got bytes from client to relay (to server) on game channel");
			}
		};
				
		this.serverToClientRelay = new UDPRelay(new InetSocketAddress(qserverToRelayExpectedPort), relayToQClientGameAddress) {
			@Override
			public void sniff(byte[] bytes) {
				System.out.println("Got bytes from server to relay (to client) on game channel");
			}
		};
	}


	@Override
	public void run() {
		// We need 2 threads:  1 listening for packets for the QServer; 1 listening for packets from the QClient
		// Either could send at either time so we have to relay too
		
		final Thread threadC = new Thread(this.clientToServerRelay);
		final Thread threadS = new Thread(this.serverToClientRelay);
		
		try {
			threadC.join();
			threadS.join();
		} catch(final InterruptedException e) {
			// Just quiting program so no need to worry
		}
	}
	
	
	/**
	 * 
	 * @return Port relay is listening on that client should treat as the QServer client game port
	 */
	public int getRelayListeningPort() {
		return this.clientToServerRelay.getListeningPort();
	}

}
