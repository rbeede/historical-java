/**
 * SckAcceptorWorker
 *
 * @Rodney Beede 
 * @version 1.00 2004/02/27
 *
 *	Questa classe leggi da un "socket" la domanda HTTP e ottene l'informazione
 *
 *  
 *
 */
 
import java.net.*;
import java.io.*;


public class SckAcceptorWorker implements Runnable {
	private Socket sckAcceptor;
	
	SckAcceptorWorker(Socket sckAcc) {
		this.sckAcceptor = sckAcc;
	}
	
	//Leggiamo la domanda dal sckAcceptor
	//Ricordiamo al disk HTTP 
	//Andiamo sull'Internet e otteniamo il HTTP
	//Diamo al cliente la risposta di HTTP
	public void run(){
	    String strLineIn = "";
	    BufferedReader in = null;
	    int i = 0;
	    String strRequest[] = new String[500];  //Da cliente a  "Internet"
	    String strResponse[] = null;  //Da "Internet" a cliente
	    byte bytResponse[] = null;

	    try{
			in = new BufferedReader(new InputStreamReader(sckAcceptor.getInputStream()));
		} catch (IOException e) {
			System.out.println("Failure on SckAcceptorWorker  *in*  creation.");
    		System.exit(-1);
		}	
		

		//Leggiamo tutto del riquesto
		try {
			while(strLineIn != null) {  //Se "null" connessione ha smessa
				strLineIn = in.readLine();
				strRequest[i] = strLineIn;
				i++;
				//in.readLine fa "null" solo quando il "socket" sta chiudendo
				//Se riceviamo "" sappiamo che il "header" ha finito
				if(strRequest[i-1].equals("")) {
					//Se riquesito e' tipo "POST" leggiamo prossima linea e basta
					if(strRequest[0].startsWith("POST") ) {
						System.out.print("POST START");
						int iConLen = 0;

						//Dobbiamo conoscrere il "Content-Length: ??"
						for(int j = 0; j < i; j++) {
							if(strRequest[j].startsWith("Content-Length:")){
								iConLen = Integer.parseInt(strRequest[j].substring(16));
								j = i;  //Lascia for addesso
							}
						}
						
						if(iConLen == 0 ) {  //Non e' andata
							System.out.println("Failure on Content Length");
						}

						byte inData[] = new byte[iConLen];
						for(int j = 0; j < iConLen; j++) {
							inData[j] = (byte) in.read();
						}

						strRequest[i] = new String(inData);
					}
					strLineIn = null;  //Marka while di uscire					
				}
			}		
			System.out.println("\tDone reading request");

		} catch (IOException e) {
			System.out.println("Failure on SckAcceptorWorker  *header-read*");
			System.exit(-1);
		} catch (NullPointerException e) {
			System.out.println("Null pointer on i = " + i);
			System.exit(-1);
		}


		//Mette "Request HTTP" dentro il "file"
		String strFileName = HTTP_Dumper.strDumpPath + "\\" + makeFilename(strRequest[0]) + ".dmp";
		writeHTTP(strRequest, strFileName);


		//Manda il riquesito al site e ottenere il risposta
		bytResponse = HTTP_GetWorker.process(strRequest);
		strResponse = (new String(bytResponse)).split("\r\n");

		//Salvare la risposta
		writeHTTP(strResponse, strFileName);
		//Manda la risposta al cliente
		sendToClient(bytResponse);
		
		try {
			sckAcceptor.close();
		} catch(Throwable e) {
			System.out.println("sckAcceptor.close\t" + e);
		}


		System.out.println("Done with SckAcceptorWorker");
	}  //fine di  run


	//Mette dentro file il "Riquesito HTTP".  Chiamato da "run" method
	public void writeHTTP(String strRequest[], String strFileName) {
		File outputFile = new File(strFileName);
		FileWriter fwout = null;
		BufferedWriter out = null;
		int iLen = 0;  //Quanto lungo e' strRequest
		int i = 0;

		try {
			fwout = new FileWriter(outputFile, true);  //append
			out = new BufferedWriter(fwout);
		} catch (IOException e) {
			System.out.println("Failure in writeHTTP on  *out*");
			System.out.println(e);
    		System.exit(-1);
		}

		//Calculare quanto lunga e' strRequest
		try {
			while(strRequest[iLen] != null) {
				iLen++;
			}
		} catch( ArrayIndexOutOfBoundsException e) {
			System.out.println("Overflowed writeHTTP array");
		}

		//Scrive il "file"
		for( i = 0; i < iLen; i++) {
			try {
				out.write(strRequest[i]);
				out.newLine();
			} catch(IOException e) {
				System.out.println("Failure to write:  " + strRequest[i]);
    			System.exit(-1);
			} catch(NullPointerException e) {
				//Era vuoto, fa niente
				;
			}
		}
			
		try {
			out.newLine();
			out.close();
		} catch(IOException e) {
		}

	}  //fine del writeHTTP


	//Qua mandiamo strData al cliente
	public void sendToClient(byte bytData[]) {
		OutputStream out = null;

		try {
			out = sckAcceptor.getOutputStream();

			out.write(bytData);

			//old debug, useful for sepearting the content into a seperate file
			//dumpToFile(extractContent(bytData), HTTP_Dumper.strDumpPath + "\\test.gif");
		} catch (IOException e) {
			System.out.println("Failure on HTTP_SckAcceptorWorker  *sendToClient*\n" + e);
			System.exit(-1);
		} catch (Throwable e) {
			System.out.println("Failure on HTTP_SckAcceptorWorker  *sendToClient*\n" + e);
			System.exit(-1);
		}

		try {
			out.flush();
		} catch (Throwable e) {
			System.out.println("Failure on HTTP_SckAcceptorWorker  *sendToClient-FLUSH*\n" + e);
			System.exit(-1);
		}


	}  //Fine sendToClient
	
	
	//Facciamo un "string" nel formatto corecto per un "filename"
	public String makeFilename(String myStr) {
		//Cambiamo affinche non ci sono "char" invalidi
		myStr = myStr.replace('\\', ' ');
		myStr = myStr.replace('/', '_');
		myStr = myStr.replace(':', ' ');
		myStr = myStr.replace('*', ' ');
		myStr = myStr.replace('?', ' ');
		myStr = myStr.replace('"', ' ');
		myStr = myStr.replace('<', ' ');
		myStr = myStr.replace('>', ' ');
		myStr = myStr.replace('|', ' ');
		

		//Controlla se la lunghezza e' troppa
		if( myStr.length() > 150 ) {
			myStr = myStr.substring(0, 150);
		}

		return myStr;
	}  //Fine del makeFilename


	//Questo legge un byte[] e ritorna un String con il "header", meno "\r\n\r\n"
	public String extractHeader(byte[] myData) {
		String myStr = new String(myData);
		
		myStr = myStr.substring(0,myStr.indexOf("\r\n\r\n"));
		
		return myStr;
	}  //Fine del extractHeader


	//Questo legge un byte[] e ritorna un byte[] meno il "header" e "\r\n\r\n"
	public byte[] extractContent(byte[] myData) {
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
	public void dumpToFile(byte[] myData, String strFilename) {
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

}