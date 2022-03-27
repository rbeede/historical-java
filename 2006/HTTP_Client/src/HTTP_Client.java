/**
 * HTTP_Client
 *
 * @Rodney Beede
 * @version 1.00 2004/03/15
 *
 * Questa programma simplicamente richiede per un file via HTTP
 */


import java.net.*;
import java.io.*;
import java.util.Vector;

public class HTTP_Client {

    public static void main(String[] args) {
		Socket sckClient = null;
		OutputStream myOut = null;
		InputStream myIn = null;
		
		String remoteIP = "f252.mail.yahoo.com";
		String remoteURL = "http://www.google.com/images/logo.gif";
		int remotePort = 80;
		
		byte[] bytResponse = new byte[10000];
		String strRequest;

	
		try {
		sckClient = new Socket(remoteIP, remotePort);
		} catch (Throwable e) {
			System.err.println(e);
		}
		
		try {
			myOut = sckClient.getOutputStream();
		} catch (Throwable e) {
			System.err.println(e);
		}

		try {
			myIn = sckClient.getInputStream();
		} catch (Throwable e) {
			System.err.println(e);
		}

		
//		strRequest = "GET " + remoteURL + " HTTP/1.0\r\n\r\n";
strRequest = "GET http://f252.mail.yahoo.com/ym/login?.rand=7a4ptk9rbafnh HTTP/1.0\r\n" + 
"Accept: image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-gsarcade-launch, application/msword, application/x-shockwave-flash, */*\r\n" + 
"Accept-Language: en-us\r\n" + 
"Cookie: B=3g2ql6d05cisk&b=2; Q=q1=AACAAAAAAAAAcw--&q2=QFZEqg--; F=a=QbmOHZ4svRB1T6aWk2t5vCG5fmOgWDl0C.ncn7_LL2d2cqCSMtJdfuRMas2I&b=3r6N; C=mg=1; Y=v=1&n=cn9tlo4mvqea3&l=he3d4o14434/o&p=m2h27qu013000000&jb=16|47|&r=cf&lg=it&intl=it&np=1; T=z=MvkVABM15VABprj6p5l9WCDNTI1BjQwNjM1MzMwMjU-&a=QAE&sk=DAA2ZLbtpEfcrv&d=c2wBTWpVeUFUTTNNVFF5TkRRM05USS0BYQFRQUUBdGlwAU1VYVNiQgF6egFNdmtWQUJnV0E-\r\n" + 
"User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows 98; Win 9x 4.90; .NET CLR 1.1.4322)\r\n" + 
"Host: f252.mail.yahoo.com\r\n" + 
"\r\n\r\n";
		
		
		try {
			myOut.write(strRequest.getBytes());
		} catch (Throwable e) {
			System.err.println(e);
		}


		try {
			Vector myVec = new Vector();
			int inData = myIn.read();

			while( inData != -1 ) {
				myVec.add(new Integer(inData));
				inData = myIn.read();  //do not cast this to byte, corrupts binary data
			}

			bytResponse = vectorToBytes(myVec);
		} catch(Throwable e) {
			System.err.println(e);
		}


/*
		System.out.println(extractHeader(bytResponse));
		dumpToFile(extractContent(bytResponse), "C:\\TEST.GIF");
*/
dumpToFile(bytResponse, "C:\\ZDATA.DMP");


		try {
			sckClient.close();
		} catch (Throwable e) {
			System.err.println(e);
		}
    }  //Fine del "main"


	//Questo legge un byte[] e ritorna un String con il "header", meno "\r\n\r\n"
	public static String extractHeader(byte[] myData) {
		String myStr = new String(myData);
		
		myStr = myStr.substring(0,myStr.indexOf("\r\n\r\n"));
		
		return myStr;
	}  //Fine del extractHeader


	//Questo legge un byte[] e ritorna un byte[] meno il "header" e "\r\n\r\n"
	public static byte[] extractContent(byte[] myData) {
		String myStr = new String(myData);
		byte[] myContent = null;
		int iPos = 0;
		int i = 0;
		
		iPos = myStr.indexOf("\r\n\r\n") + 4;
		
		myContent = new byte[(myData.length - iPos)];
		for( int j = iPos; j < myData.length; j++ ) {
			myContent[i] = myData[j];
			i++;
		}
		
		
		return myContent;
	}  //Fine del extractContent


	//Questo scrive un byte[] a un file
	public static void dumpToFile(byte[] myData, String strFilename) {
		File outputFile = new File(strFilename);
		FileOutputStream myOut = null;
		
		try {
			myOut = new FileOutputStream(outputFile);
			myOut.write(myData);
			myOut.close();
		} catch (Throwable e) {
			System.out.println("FAILURE in dumpToFile:\t" + e);
		}
	}  //Fine del dumpToFile


	public static byte[] vectorToBytes(Vector myVec) {
		byte[] myData = new byte[myVec.size()];

		for( int i = 0; i < myVec.size(); i++ ) {
			myData[i] = ((Integer)myVec.get(i)).byteValue();
		}

		return myData;
	}

}
