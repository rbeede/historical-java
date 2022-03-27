/**
 * HTTP_GetWorker
 *
 * @Rodney Beede 
 * @version 1.00 2004/02/27
 *
 *	Questa classe ottene un "file" via HTTP
 *	Leggiamo il "HTTP Header" nel formatto di "proxy" per informazione
 *
 */
 
 import java.net.*;
 import java.io.DataInputStream;
 import java.io.*;
 import java.util.Vector;
 
 public class HTTP_GetWorker {
	public static byte[] process(String strRequest[]) {
		Socket sckGet = null;
		String strHost = "";
		int iPort = 0;

		int iLen = 0;  //Lunggezza di strRequest
		int i = 0;
		
		PrintWriter out = null;
		InputStream in = null;
		Vector inData = new Vector();

		//Calculare quanto lunga e' strRequest
		while(strRequest[iLen] != null) {
			iLen++;
		}
		
		//Cercare il "host" nel strRequest
		for( i = 0; i < iLen; i++) {
			if(strRequest[i].indexOf("Host:") != -1 ) {
				//Abbiamo trovato il "host"
				strHost = strRequest[i].substring(strRequest[i].indexOf("Host:") + 6);
			}
		}
		
		if( strHost.equals("") ) {
			System.out.println("Failure on HTTP_GetWorker  *strHost*");
		}

		//Cercare il "port"
		if(strHost.lastIndexOf(":") == -1 ) {
			iPort = 80;
		} else {
			i = strHost.lastIndexOf(':');
			iPort = Integer.parseInt(strHost.substring(i + 1));
			strHost = strHost.substring(1,i);
		}
		

		//Apriamo un "socket"
		try {
			sckGet = new Socket(strHost, iPort);
		} catch (IOException e) {
			System.out.println("Failure on HTTP_GetWorker  *open-socket*\n\t" + e);
    		System.exit(-1);
		}


		//Spedire il nostro "header"
		try {
			out = new PrintWriter(sckGet.getOutputStream(), true);
			in = sckGet.getInputStream();
		} catch (IOException e) {
			System.out.println("Failure on HTTP_GetWorker  *out-in creation*");
    		System.exit(-1);
		}

		for( i = 0; i < iLen; i++) {
			//Non fa "Proxy-Connection:"
			if( !(strRequest[i].startsWith("Proxy-Connection:")) ) {
				out.println(strRequest[i]);
			} else {
				;
			}
	
		}
		
		
		//Leggi la risposta
		try {
			int dataIn = in.read();
			byte currStat = 0;

			System.out.println("HTTP_GetWorker started read");
			//Dobbiamo leggere fino inData - header = Content-length
			while(currStat == 0) {  //0 = ancora da ottenere
				if(dataIn != -1) {
					inData.add(new Integer(dataIn));
				} else {
					currStat = gotItAll(inData);
				}

				dataIn = in.read();
				
				//Se currStat == 2 dobbiamo controllare
				if( currStat == 2 && dataIn == -1 ) {
					//Non c'era un "Content-length" nella risposta, ma quando
					//abbiamo chrIn == -1 and il "socket" chiuso abbiamo finito
					currStat = 2;  //Abbiamo finito sicuramente
				} else if(currStat != 1) {  //1 = Content-length gai fatto
					currStat = 0;  //Da fare ancora
				}
			}
		} catch (EOFException e) {
			//Abbiamo letto tutto
			System.out.println("EOF Reached");
			;
		} catch (IOException e) {
			System.out.println("Failure on HTTP_GetWorker  *response-read*\n\t" + e);
			System.exit(-1);
		}


		System.out.println("Done with HTTP_GetWorker");

		//Ridare la risposta
		return vectorToByteArray(inData);
		
	}  //Fine del "process"
	

	//Converts vector to ByteArray
	public static byte[] vectorToByteArray(Vector myVec) {
		byte myData[] = new byte[myVec.size()];
		
		for( int i = 0; i < myVec.size(); i++) {
			myData[i] = ((Integer)myVec.get(i)).byteValue();
		}

		return myData;
	}  //Fine vectorToByteArray


	//Questo controlla se il HTTP dentro objData e' tutto la risposta dal "server"
	//Ritorniamo:	0	se non e' finito
	//				1	se e' finito
	//				2	se forse finito, ma dobbiamo avere "chiuso" dal "socket"
	//					(perche non c'e "Content-length")
	public static byte gotItAll(Vector objData) {
		byte myData[] = null;
		String strData = null;
		int iConLen = 0;
		int iPos = 0;
		int iPos2 = 0;
	
		myData = vectorToByteArray(objData);

		strData = new String(myData);
		

		//Controlliamo se il "header" c'e'
		if(strData.indexOf("\r\n\r\n") == -1 ) {
			return 0;  //Non e' finito
		}

		//Cerciamo per un "Content-length"
		if(strData.indexOf("Content-length") == -1 ) {
			return 2;  //Non possiamo sapere qua, devi controllare il "socket"
		}

		//Dev'essere un "Content-length", cerciamo
		iPos = strData.indexOf("Content-length") + 16;
		iPos2 = strData.indexOf("\n", iPos) - 1;

		iConLen = Integer.parseInt(strData.substring(iPos,iPos2));
		
		if(iConLen == 0) {
			//Non e' andata, dobbiamo mangiare un insecto
			System.out.println("FAILURE on iConLen in gotItAll in HTTP_GetWorker\n\n");
			System.exit(-1);
		}


		//Addesso dobbiamo calculare quanto abbiamo ricevuto della risposta
		iPos = strData.indexOf("\r\n\r\n") + 4;  //fine del header
		iPos2 = objData.size();

		if( iPos2 - iPos == iConLen ) {
			return 1;  //E' finito
		} else {
			return 0;  //Non e' finito
		}
	}  //Fine del gotItAll
}
