/**
 * HTTP_Dumper
 *
 * @Rodney Beede 
 * @version 1.00 2004/02/27
 *
 *	Questa programma si pretendeva essere un "proxy" per HTTP
 *  e fa una raccolta di tutti dei "HTTP Header & Body"
 *
 */
 
import java.net.*;
import java.io.*;


public class HTTP_Dumper {
    

	//Da fare, metti questi dentro un "file"
	static String strDumpPath = "R:\\JFiles\\HTTP_Dumper\\DUMP";
	static int iLisPort = 8080;  //Porto di Ascoltare


    public static void main(String[] args) {
		ServerSocket sckListner = null;
		SckAcceptorWorker objSockWorker = null;

		//Prova ascoltare sul  iLisPort
		try{
			System.out.println("Listening on port " + iLisPort);
			sckListner = new ServerSocket(iLisPort); 
		} catch (IOException e) {
	    	System.out.println("Could not listen on port " + iLisPort);
    		System.exit(-1);
  		}


		//Programma accetta le connessione
		while(true){
			try{
				objSockWorker = new SckAcceptorWorker(sckListner.accept());
				System.out.println("Accepted connection");
        		Thread t = new Thread(objSockWorker);
        		t.start();
      		} catch (IOException e) {
        		System.out.println("Accept failed: " + iLisPort);
        		System.exit(-1);
      		}
	    }


		//Chiude tutto		
/*		try {
			sckListner.close();
		} catch (IOException e) {
			System.out.println("Close sockets failed!");
    		System.exit(-1);			
		}
*/
    }  //Fine del main
}
