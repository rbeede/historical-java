/**
 * HTTP_Server
 *
 * @Rodney Beede
 * @version 1.00 2004/02/28
 *
 * Questa programma simplicamente risponde a un riquesito di HTTP
 */


import java.net.*;
import java.io.IOException;

public class HTTP_Server {
	static int iPort = 80;    

    public static void main(String[] args) {
		ServerSocket sckListner = null;
		SocketServerWorker sckWorker = null;
		
		//Prova ascoltare sul iPort
		try{
			System.out.println("Listening on port " + iPort);
			sckListner = new ServerSocket(iPort); 
		} catch (IOException e) {
	    	System.out.println("Could not listen on port " + iPort);
    		System.exit(-1);
  		}


		//Programma accetta le connessione
		while(true){
			try{
				sckWorker = new SocketServerWorker(sckListner.accept());
				System.out.println("Accepted connection");
        		Thread t = new Thread(sckWorker);
        		t.start();
      		} catch (IOException e) {
        		System.out.println("Accept failed: " + iPort);
        		System.exit(-1);
      		}
	    }


    }  //Fine del "main"
}
