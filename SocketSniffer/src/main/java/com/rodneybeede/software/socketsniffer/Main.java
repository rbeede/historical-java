package com.rodneybeede.software.socketsniffer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


//TODO in the future support SSL as well along with generated SSL certificate for easy import into client

/**
 * @author rbeede
 * 
 * Just listens for a connection and forwards all traffic along to a specified server
 * 
 * Connection with destination is made when a client connects.  Same for close.
 * 
 * Dumps network output to specified output file and info messages to STDOUT
 * 
 * Never terminates unless user kills process
 *
 */
public class Main {
	public static void main(final String[] args) {
		if(null == args || args.length != 5) {
			System.err.println("Insufficent arguments.");
			System.out.println("Usage:  java " + Main.class.getName() + " <localListeningPort> <destinationAddress> <destinationPort> <TCP|UDP> </path/for/output.file>");
			System.exit(255);
		}

		final int localListeningPort;
		try {
			localListeningPort = Integer.parseInt(args[0]);
		} catch(final NumberFormatException excep) {
			System.err.println(excep.getMessage());
			System.err.println("Unable to parse localListeningPort from " + args[0]);
			System.exit(255);
			return;
		}
		
		final String destinationAddress = args[1];
		
		final int destinationPort;
		try {
			destinationPort = Integer.parseInt(args[2]);
		} catch(final NumberFormatException excep) {
			System.err.println(excep.getMessage());
			System.err.println("Unable to parse destinationPort from " + args[2]);
			System.exit(255);
			return;
		}
		
		final boolean useTCP = "TCP".equalsIgnoreCase(args[3]);
		
		final File outputFile = new File(args[4]);
		
		
		System.out.println("Destination server is at " + destinationAddress + ":" + destinationPort + " and is of type " + (useTCP ? "TCP" : "UDP"));
		System.out.println("Listening on local (loopback interface only) port " + localListeningPort);
		System.out.println("Network traffic output stored in " + outputFile.getAbsolutePath());
		
		
		final CommunicationManager commMan;
		try {
			commMan = new CommunicationManager(useTCP, localListeningPort, destinationAddress, destinationPort);
		} catch(final UnknownHostException excep) {
			// Loopback interface not available (bad kernel config or loopback device disabled in OS)
			System.err.println("Unable to find local loopback interface");
			excep.printStackTrace();
			System.exit(255);
			return;
		} catch(final IOException excep) {
			// Failed to listen on port, taken already?
			System.err.println("Unable to listen (bind) on local loopback interface on port " + localListeningPort);
			System.err.println(excep.getMessage());
			excep.printStackTrace();
			System.exit(255);
			return;
		}  
		
		
		// We need a listener that will take action on the data given us
		final SnifferListener snifferListener;
		try {
			snifferListener = new SnifferListenerToFile(outputFile);
		} catch (final FileNotFoundException ex) {
			System.err.println("Unable to setup write to base location " + outputFile.getAbsolutePath());
			ex.printStackTrace();
			System.exit(255);
			return;
		}

		
		while(true) {
			String connectMessage = "";
			
			try {
				connectMessage = commMan.accept(snifferListener);
			} catch (final IOException e) {
				e.printStackTrace();
				continue;
			}
			
			System.out.println("Connection from " + connectMessage);
		}
	}

}
