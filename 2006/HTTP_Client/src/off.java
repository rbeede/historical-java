/**
 * SocketServerWorker
 *
 * @Rodney Beede 
 * @version 1.00 2004/02/27
 *
 *	Questa classe leggi da un "socket" la domanda HTTP e rida l'informazione
 *
 *  
 *
 */
 
import java.net.Socket;
import java.io.*;
import java.util.Vector;


public class SocketServerWorker implements Runnable {
	private Socket sckCurrent;
	
	SocketServerWorker(Socket sckCurrent) {
		this.sckCurrent = sckCurrent;
	}
	
	public void run() {
		byte bytHTTP[] = null;
		Vector datIn = new Vector();
		InputStream in = null;
		OutputStream out = null;
		byte bytIn = 0;
		

		try {
			in = sckCurrent.getInputStream();
		} catch (IOException e) {
			System.out.println(e);
			System.exit(-1);
		}


		//Fa l'iterazione fino abbiamo ricevuto tutto del HTTP
		System.out.println("Reading HTTP Request");
		while(completeHTTP(datIn) == false) {
			try {
				bytIn = (byte) in.read();
			} catch(IOException e) {
				System.err.println(e);
				System.exit(-1);
			}

			datIn.add(new Byte(bytIn));
		}  //Fine del while


		//Ritorna HTTP al cliente
		try {
			out = sckCurrent.getOutputStream();
		} catch (IOException e) {
			System.out.println(e);
			System.exit(-1);
		}
		
		String strResponse = "HTTP/1.0 200 OK\r\n\r\n";
		
		strResponse = strResponse + "<PRE>";
		strResponse = strResponse + new String(vectorToByteArray(datIn));

		
		try {
			System.out.println("Sending Response");
			out.write(strResponse.getBytes("US-ASCII"));
			out.close();
		} catch (Throwable e) {
			System.out.println(e);
			System.exit(-1);
		}
	}  //Fine del "run"
	
	
	public boolean completeHTTP(Vector objData) {
		byte myData[] = null;
		String strData = null;
	
		myData = vectorToByteArray(objData);

		strData = new String(myData);
		
		if( strData.startsWith("GET")) {  //Fine sara' con \r\n\r\n
			if(strData.indexOf("\r\n\r\n") != -1 ) {
				return true;
			}
		} else if(strData.startsWith("POST")) {  //Fine sara' secondo Content-Length
			
		}

		return false;  //Non abbiamo trovato la fine
	}  //Fine completeHTTP


	public byte[] vectorToByteArray(Vector myVec) {
		byte myData[] = new byte[myVec.size()];
		
		for( int i = 0; i < myVec.size(); i++) {
			myData[i] = ((Byte)myVec.get(i)).byteValue();
		}

		return myData;
	}  //Fine vectorToByteArray
}
	