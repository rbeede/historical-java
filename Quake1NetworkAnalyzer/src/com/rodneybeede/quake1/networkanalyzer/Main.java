package com.rodneybeede.quake1.networkanalyzer;

import java.net.InetSocketAddress;

import com.rodneybeede.quake1.networkanalyzer.relays.QuakeServerControlPortRelay;

/**
 * Has to listen on all interfaces as Quake client's don't support connecting to only 127.0.0.1.  They automatically resolve it to a public interface other than loopback.
 * At least this is the behavior of JoeQuake (joequake-v0.15b1862-win32) whether running as a server or client
 * 
 * Status messages from this program are sent to STDERR
 * 
 * Status messages about the game network traffic is sent to STDOUT
 * 
 * @author rbeede
 * 
 * Credit to http://www.gamers.org/dEngine/quake/QDP/qnp.html "Unofficial Quake Network Protocol Specs v1.01b" by a.oliver
 * Accessed:  9 Feb 2012
 *
 */
public class Main {
	public static void main(final String[] args) {
		if(null == args || args.length != 3) {
			System.err.println("Insufficent arguments.");
			System.out.println("Usage:  <relayListeningPort> <serverAddress> <serverPort>");
			System.exit(255);
		}
		

		final int relayListeningPort;
		try {
			relayListeningPort = Integer.parseInt(args[0]);
		} catch(final NumberFormatException excep) {
			System.err.println(excep.getMessage());
			System.err.println("Unable to parse relayListeningPort from " + args[0]);
			System.exit(255);
			return;
		}
		
		final InetSocketAddress quakeServerControlAddress;
		{
			final int port;
			try {
				port = Integer.parseInt(args[2]);
			} catch(final NumberFormatException excep) {
				System.err.println(excep.getMessage());
				System.err.println("Unable to parse serverPort from " + args[2]);
				System.exit(255);
				return;
			}
			
			try {
				quakeServerControlAddress = new InetSocketAddress(args[1], port);
			} catch(final IllegalArgumentException excep) {
				System.err.println(excep.getMessage());
				System.err.println("Unable to parse port or host of " + args[1] + ":" + port);
				System.exit(255);
				return;
			}
			
			if(quakeServerControlAddress.isUnresolved()) {
				// Invalid address or hostname
				System.err.println("Unable to resolve server hostname of " + args[1]);
				System.exit(255);
				return;
			}
		}
		
		
		System.err.println("Quake server is at " + quakeServerControlAddress.toString());
		System.err.println("Listening on local (all interfaces) port " + relayListeningPort);  // required due to how Quake handles server IP and hostname connections

		
		// Let this current thread just run the main connection relay code
		final QuakeServerControlPortRelay qscp = new QuakeServerControlPortRelay(quakeServerControlAddress, relayListeningPort);
		qscp.run();
		
		
		System.err.println("Shutting down analyzer");
	}

}
