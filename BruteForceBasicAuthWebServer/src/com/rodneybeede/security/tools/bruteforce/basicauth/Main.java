package com.rodneybeede.security.tools.bruteforce.basicauth;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

public class Main {
//	private static final int MEBIBYTE = 1048576;  //One MiB (mebibyte) (2^20) [see http://physics.nist.gov/cuu/Units/binary.html] 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(null == args || args.length < 4) {
			printUsage();
			System.exit(0);
		}
		
		final URL url;
		try {
			url = new URL(args[0]);
		} catch (MalformedURLException e) {
			System.err.println("Passed URL was invalid: " + args[0]);
			e.printStackTrace();
			System.exit(255);
			return;  //To avoid compiler warnings
		}
		
		final String remoteAddr = url.getHost();
		//Set the remote port to the default for the protocol if not specified, otherwise use the specified
		final int remotePort = url.getPort() == -1 ? url.getDefaultPort() : url.getPort();
		
		final String userName = args[1];
		
		final int maxPasswordLength;
		try {
			maxPasswordLength = Integer.parseInt(args[2]);
		} catch(NumberFormatException e) {
			System.err.println("Invalid maximum password length: " + args[2]);
			e.printStackTrace();
			System.exit(255);
			return;  //To avoid compiler warnings
		}
		
		final char[] passwordAlphabet;
		if(args[3].equalsIgnoreCase("simple")) {
			passwordAlphabet = buildSimpleCharacterIndex();
		} else {
			passwordAlphabet = buildComplexCharacterIndex();
		}
		
		System.out.println("Preparing run against " + remoteAddr + ":" + remotePort + " with username of " + userName);
		System.out.println();
		
		
		/* Now that we have our command options prepare the process */
		
		// How many passwords total are possible (worst case)?
		final long maxPossiblePasswords = (long) Math.pow(passwordAlphabet.length, maxPasswordLength);
		System.out.println("With a " + maxPasswordLength + " length password there are " + DecimalFormat.getInstance().format(maxPossiblePasswords) + " possible passwords to try");
		System.out.println();
		
		System.out.println("The alphabet length is " + passwordAlphabet.length);
		System.out.println();
		
		// What alphabet was requested
		System.out.println("Using the following alphabet:");
		System.out.println(java.util.Arrays.toString(passwordAlphabet));
		System.out.println();
		
		// How many concurrent attempts should we do based on free memory
		final int maxThreads = Runtime.getRuntime().availableProcessors();
		System.out.println("Using " + maxThreads + " concurrent workers since " + Runtime.getRuntime().availableProcessors() + " processors are available");
		System.out.println();

		final long blockSize = (long) Math.ceil((double) maxPossiblePasswords / (double) maxThreads);
		System.out.println("Each worker will attempt " + DecimalFormat.getInstance().format(blockSize));
		System.out.println();

		

		/* We need to prepare worker threads */
		Runnable[] workers = new Runnable[maxThreads];
		for(int i = 0; i < workers.length; i++) {
			workers[i] = new WorkerRunnable(remoteAddr, remotePort, userName, i, workers.length, maxPossiblePasswords, passwordAlphabet);
		}
		
		//Fire them off
		for(Runnable currWorker : workers) {
			(new Thread(currWorker)).start();
		}
		
		//Threads will continue to run and keep the JVM alive
	}

	
	private static void printUsage() {
		System.out.println("Usage:  java " + Main.class.getCanonicalName() + " http://url username max-password-length simple|complex");
		System.out.println("\tAny path/query string in the url will be ignored");
		System.out.println("\tHTTPS (SSL) not currently supported");
		System.out.println("\tusername for login, pass \"\" to indicate no username");
		System.out.println("\tMaximum password length to try (goes from 0 to max)");
		System.out.println("\tsimple ==> password consisting of a-z, A-Z, or 0-9 only");
		System.out.println("\tcomplex ==> password consisting of ascii decimal codes 32 - 126");
		System.out.println();
		System.out.println("Status is printed every 500 keys attempted");
		System.out.println();
		System.out.println();
	}

	private static char[] buildSimpleCharacterIndex() {
		char[] list = new char[1 + 26 + 26 + 10];  //\0 = 1  +  a-z = 26  +  A-Z = 26  +  0-9 = 10
		
		int cIdx = 0;
		//Analogous to number 0 (zero) when doing base conversion
		//	This will only be used once so no passwords will look like ab\0de for example
		//	It is necessary when converting an integer index value to a password
		list[cIdx] = '\0';
		cIdx++;
		
		for(char cChar = 'a'; cChar <= 'z'; cChar++) {
			list[cIdx] = cChar;
			cIdx++;
		}
		for(char cChar = 'A'; cChar <= 'Z'; cChar++) {
			list[cIdx] = cChar;
			cIdx++;
		}
		for(char cChar = '0'; cChar <= '9'; cChar++) {
			list[cIdx] = cChar;
			cIdx++;
		}

		return list;		
	}
	
	private static char[] buildComplexCharacterIndex() {
		char[] list = new char[127 - 32 + 1];  // + 1 to include \0
		
		list[0] = '\0';  //Analogous to number 0 (zero) when doing base conversion
		
		for(int i = 32; i < 127; i++) {
			list[i - 32] = (char) i;
		}
		
		return list;
	}
}
