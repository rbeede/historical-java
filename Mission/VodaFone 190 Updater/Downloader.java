/*
 * Downloader.java -- Anziano Rodney Beede
 *	Missione Italiana di Roma -- 23 Dec 2003
 *
 *
 *  NOTE:   This tool uses a hacked SSL entry method for accessing the server
 *	 and does NOT provide safe SSL encryption when downloading
 *
 *
 *
 * Variation of URLReader.java, see original info below
 * ====================================================
 * @(#)URLReader.java	1.3 01/05/10
 *
 * Copyright 1995-2002 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 * -Redistributions of source code must retain the above copyright  
 * notice, this  list of conditions and the following disclaimer.
 * 
 * -Redistribution in binary form must reproduct the above copyright 
 * notice, this list of conditions and the following disclaimer in 
 * the documentation and/or other materials provided with the 
 * distribution.
 * 
 * Neither the name of Sun Microsystems, Inc. or the names of 
 * contributors may be used to endorse or promote products derived 
 * from this software without specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any 
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND 
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY 
 * EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY 
 * DAMAGES OR LIABILITIES  SUFFERED BY LICENSEE AS A RESULT OF  OR 
 * RELATING TO USE, MODIFICATION OR DISTRIBUTION OF THE SOFTWARE OR 
 * ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE 
 * FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, 
 * SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER 
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF 
 * THE USE OF OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * You acknowledge that Software is not designed, licensed or 
 * intended for use in the design, construction, operation or 
 * maintenance of any nuclear facility. 
 */

/* 
 *	Pass as main params your username and password
 *	You must be using the FAST template in order for this to work
 *  No msgs from your INBOX are read as that changes the state of them
 */

/* backup copy of import statements
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.contrib.ssl.*;
import org.apache.commons.httpclient.protocol.*;
import java.io.File;
 */

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.contrib.ssl.*;
import org.apache.commons.httpclient.protocol.*;
import org.apache.commons.httpclient.Header;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;


public class Downloader {
    //Used by all methods
    static GetMethod httpget;  //Used to create URLs
    static HttpClient httpclient;  //Our client worker

    static int maxMSG = 500;
    static String urlBASE = "https://www.areaaziende.190.it";  //Base url used after login

    static String CRLF = (char) 10 + "\n";  //Carriage return line feed for file writing

    public static void main(String[] args) throws Exception {
                
                //Quite up logging, otherwise get lots of WARNING:  Content-length unknown
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog"); 
		System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "false"); 
		System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "fatal"); 
		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "fatal"); 

                
		String responseBody;  //Stores server response
		String currURL;  //URL we are formatting to use
                String msgURL[] = new String[maxMSG];  //Array of e-mail link URLs


		httpclient = new HttpClient();  //Setup our client worker, we do this after turning off logging


		//Lets make EasySSL the default SSL protocal which accepts anything
		Protocol.registerProtocol("https", new Protocol("https", new EasySSLProtocolSocketFactory(), 443));
                //Inform user that no security is promised
                System.out.println("This program accepts any SSL connection.  No security promised.");


		//Login the user
		System.out.print("Logging in...");
		responseBody = getPage("http://www.190.it/190/trilogy/jsp/login.do?username=&password=", "001 - LOGIN.html");
		//Any errors that occur showup now
		System.out.print("DONE\n");  //Done submitting login


		//Go to the  "Gestione servizi Aziendali" link
		System.out.print("Gestione servizi Aziendali...");
		responseBody = getPage("http://www.areaaziende.190.it/190/trilogy/jsp/channelView.do?pageTypeId=9606&channelPage=/jsp/content/ty_editorialBody.jsp&channelId=-18041&tk=9606,l&precPage=Top+Fai+da+te", "002 - Gestione servizi Aziendali.html");
		//Any errors that occur showup now
		System.out.print("DONE\n");  //Done 


		//Go to the  "Conto telefonico e controllo dei Costi" link
		System.out.print("Conto telefonico e controllo dei Costi...");
		responseBody = getPage("http://www.areaaziende.190.it/190/trilogy/jsp/channelView.do?channelPage=/jsp/content/ty_editorialBody.jsp&channelId=-18082&pageTypeId=9606&tk=9606,l&precPage=Conto+telefonico+e+controllo+dei+Costi", "003 - Conto telefonico e controllo dei Costi.html");
		//Any errors that occur showup now
		System.out.print("DONE\n");  //Done


		//Go to the  "Infotraffico" link
		System.out.print("Infotraffico Link...");
		responseBody = getPage("http://www.areaaziende.190.it/190/trilogy/jsp/programView.do?programPage=%2FfdtDisp.do%3Fachk%3DA2%26apth%3D%2Fjita.do%26actx%3D%2Fjipr%26jitadesturl%3DAZ%26ServiceName%3DPRCCService%26TemplateName%3D190Online%2FAuthentication%2FPRCCMain4JITA.htm%26jitatrace%3Dtrue%26FUNCTIONCODE%3D89&channelId=-18082&programId=10931&pageTypeId=9606&tk=9606,l&precPage=Conto+telefonico+e+controllo+dei+Costi", "004 - Infotraffico Link Page.html");
//PRCCService=0A1813772025192423251E251D7A247824; Path=/; Domain=.190.it
//Set-Cookie: frte_lbf_PRCCService=040A0L0Q0o2g4; Path=/; Domain=.190.it

		//Any errors that occur showup now
		System.out.print("DONE\n");  //Done 


		//Go to the  "Infotraffico" search page
		System.out.print("Infotraffico Page...");
String sURL = "https://www.areaaziende.190.it/190/prcc/SearchEngineViewerAction.do?desc=Infotraffico&searchType=5&custcode=6.327022&functionCode=89&isBilling=false&isRep=false&destUrl=https%3A//www.areaaziende.190.it/190/jipr/jita.do%3Fjitadesturl%3DAZ%26ServiceName%3DPRCCService%26TemplateName%3D/190Online/MotoreDiRicerca/MdRSIMPBXNV.htm%26jitatrace%3Dtrue%26channelId%3D-18082%26pageTypeId%3D9606%26programId%3D10931%26FUNCTIONCODE%3D89%26tk%3D9606%2Cl%26JITA_USER%3DMISSIONMOBILE%26JITA_MSISDN%3D3480027021%26JITA_EMAIL%3DMISSION@JENETEK.COM%26JITA_TYPEID%3D5%26JITA_CUSTCODE%3D6.327022%26CUSTCODE%3D6.327022%26REMOTE_USER%3DMISSIONMOBILE%26TIP%3DREF%26MSISDN%3D3480027021%26REPARTO%3DNOR%26code%3D6.327022%26HotLine%3DFALSE%26prcccode%3D89%26GoBack%3DTYHPREF%26DigimonLevel%3DPR%26PageLevel%3DSIM";
	httpget = new GetMethod(sURL);
	httpget.setRequestHeader("PRCCService", "0A1813772025192423251E251D7A247824");
	httpget.setRequestHeader("frte_lbf_PRCCService", "040A0L0Q0o2g4");
	httpclient.executeMethod(httpget);


	//Read in the page
	responseBody = new String(httpget.getResponseBody());

	//Dump out HTTP header data for debugging
	Header daHeaders[] = httpget.getResponseHeaders();
	dumpInfo(sURL + "\n" + httpget.getStatusCode() + " | " + httpget.getStatusText() + "\n",".\\DUMP\\","HTTP-Headers.dmp");
	for(int i=0; i < daHeaders.length; i++) {
		dumpInfo(daHeaders[i].toString(),".\\DUMP\\","HTTP-Headers.dmp");
	}
	dumpInfo("\n\n",".\\DUMP\\","HTTP-Headers.dmp");  //Seperation for the next one

	//If sDumpName isn't empty Dump the page to a file
	if( responseBody.compareTo("") != 0 ) {
		dumpInfo(responseBody, ".\\DUMP\\","005 - Infotraffico Search Page.html");
	}
	


//		responseBody = getPage("https://www.areaaziende.190.it/190/prcc/SearchEngineViewerAction.do?desc=Infotraffico&searchType=5&custcode=6.327022&functionCode=89&isBilling=false&isRep=false&destUrl=https%3A//www.areaaziende.190.it/190/jipr/jita.do%3Fjitadesturl%3DAZ%26ServiceName%3DPRCCService%26TemplateName%3D/190Online/MotoreDiRicerca/MdRSIMPBXNV.htm%26jitatrace%3Dtrue%26channelId%3D-18082%26pageTypeId%3D9606%26programId%3D10931%26FUNCTIONCODE%3D89%26tk%3D9606%2Cl%26JITA_USER%3DMISSIONMOBILE%26JITA_MSISDN%3D3480027021%26JITA_EMAIL%3DMISSION@JENETEK.COM%26JITA_TYPEID%3D5%26JITA_CUSTCODE%3D6.327022%26CUSTCODE%3D6.327022%26REMOTE_USER%3DMISSIONMOBILE%26TIP%3DREF%26MSISDN%3D3480027021%26REPARTO%3DNOR%26code%3D6.327022%26HotLine%3DFALSE%26prcccode%3D89%26GoBack%3DTYHPREF%26DigimonLevel%3DPR%26PageLevel%3DSIM", "005 - Infotraffico Search Page.html");
		//Any errors that occur showup now
		System.out.print("DONE\n");  //Done 


		//Start submitting our phone numbers
		System.out.print("Searching 3480027038...");
		responseBody = getPage("https://www.areaaziende.190.it/190/prcc/SearchEngineAction.do?entity=SIM&entityNum=3480027038", "3480027038 - Search.html");
		//Any errors that occur showup now
		System.out.print("DONE\n");  //Done 



		httpget.releaseConnection();  //Close connection cleanly

    }  //End main



    //This method parses out a list of URLs.
    // sContent is the html content
    // sStrTag is beginning of where URL starts (HREF=") 
    // sEndTag is tail end of content where URL ends
    //  and ending of URL is .J
    public static String[] getURLS(String sContent, String sStrTag, String sEndTag) throws java.io.IOException {
	int iPos;  //For position searching
	int iPosEnd;  //ditto
	int iPosDoc;  //For holding where to start looking for next URL
	String srcURL[] = new String[maxMSG];  //Holds resulting URLs
	int iNumURL = 0;  //Number of URLs we've found

	iPosDoc = 0;  //Starting from beginning
	while( sContent.indexOf(sEndTag,iPosDoc) != -1 ) {
		//Look for the HTML EndTag
		iPos = sContent.indexOf(sEndTag, iPosDoc);
		iPosDoc = iPos + 1;  //Record where we are for the next time around
		//Grab the URL start
		iPos = sContent.lastIndexOf(sStrTag, iPos) + 6;  //+6 for the href=" part
		//Grab the URL end
		iPosEnd = sContent.indexOf(".J", iPos) + 2;  //+2 to include .J
		//Store the URL
		srcURL[iNumURL] = sContent.substring(iPos, iPosEnd);
		iNumURL++;  //Add one to count
	}  //end while


	//Dump out the URLs for debugging uses
	dumpInfo("URLs for " + sEndTag + "\n", ".\\DUMP\\", "_getURLS.dmp");
	for(iPos = 0; iPos < iNumURL; iPos++) {
		dumpInfo(srcURL[iPos] + "\n", ".\\DUMP\\", "_getURLS.dmp");
	}

	return srcURL;
    }  //end getURLS()


    //This method simply opens the passed URL, reads the content, and saves it to the DUMP directory
    //If sDumpName is blank then the URL is not saved
    public static String getPage(String sURL, String sDumpName) throws java.io.IOException {
	//No directory specified, pass default of DUMP
	return getPage(sURL, ".\\DUMP\\", sDumpName);
    }  //end getPage


    //Overloaded that allows specification of directory, see above
    // If sDir doesn't exist then dumpInfo will create it
    public static String getPage(String sURL, String sDir, String sDumpName) throws java.io.IOException {
	String responseBody;
	String redirURL;
	Header hdrResponse;
	int responseCode;

	
	httpget = new GetMethod(sURL);
	httpclient.executeMethod(httpget);

	//Check to see if we've been passed a redirection
	responseCode = httpget.getStatusCode();
	while( responseCode == 302 ) {
		//Loop until we're done with the redirection

		System.out.print("redirection.");
		hdrResponse = httpget.getResponseHeader("Location");
		redirURL = hdrResponse.getValue();
		httpget = new GetMethod(redirURL);
		httpclient.executeMethod(httpget);
		responseCode = httpget.getStatusCode();
	}
	


	//Read in the page
	responseBody = new String(httpget.getResponseBody());

	//Dump out HTTP header data for debugging
	Header daHeaders[] = httpget.getResponseHeaders();
	dumpInfo(sURL + "\n" + httpget.getStatusCode() + " | " + httpget.getStatusText() + "\n",sDir,"HTTP-Headers.dmp");
	for(int i=0; i < daHeaders.length; i++) {
		dumpInfo(daHeaders[i].toString(),sDir,"HTTP-Headers.dmp");
	}
	dumpInfo("\n\n",sDir,"HTTP-Headers.dmp");  //Seperation for the next one

	//If sDumpName isn't empty Dump the page to a file
	if( sDumpName.compareTo("") != 0 ) {
		dumpInfo(responseBody, sDir, sDumpName);
	}
	
	return responseBody;
    }  //end getPage


    //This method dumps the sData to sFileName in sDir
    // if sDir does not exist it is created, using nested directories is ok
    // sDir most have trailing \\, remember to escape your \ with \\
    public static void dumpInfo(String sData, String sDir, String sFileName) throws java.io.IOException {
	File myFile = new File(sDir + sFileName);;
	FileWriter myWriter;


	//Make sDir incase it doesn't exist
	new File(sDir).mkdirs();

	//Make the file
	myFile.createNewFile();

	//Write the contents to the file
	myWriter = new FileWriter(myFile, true);  //append mode
	myWriter.write(sData);
	myWriter.close();  //Saves file to disk and close it

    }  //end dumpInfo


    //This method reads a Source View e-mail html page and returns a usable filename to save the file with
    // using the date/time received and subject information
    // Subject is assumed to be enclosed in <STRONG> </STRONG> tags
    // Date is assumed to be enclosed by <BR>Date:  <BR> tags    ex. Tue, 27 May 2003 09:34:16 +0000
    public static String makeName(String sContent) {
	int iPos;
	int iPosEnd;
	String sSub;  //subject
	String sDate;  //date

	//Get date starting position
	iPos = sContent.indexOf("<BR>Date:") + 9;
	iPosEnd = sContent.indexOf("<BR>",iPos);
	sDate = sContent.substring(iPos,iPosEnd);


	Date myDate = new Date(sDate);

	sDate = (myDate.getYear() + 1900) + "-";
	if((myDate.getMonth()+1) < 10 )  //Make leading 0 for months less than 10
		sDate = sDate + "0" + (myDate.getMonth()+1);
	else
		sDate = sDate + (myDate.getMonth()+1);
	sDate = sDate + "-";
	if(myDate.getDate() < 10 )  //Make leading 0 for days less than 10
		sDate = sDate + "0" + (myDate.getDate());
	else
		sDate = sDate + (myDate.getDate());
	sDate = sDate + "--";
	if(myDate.getHours() < 10 )  //Make leading 0 for hours less than 10
		sDate = sDate + "0" + (myDate.getHours());
	else
		sDate = sDate + (myDate.getHours());
	sDate = sDate + "-" + myDate.getMinutes() + "-" + myDate.getSeconds();


	//Get Subject
	iPos = sContent.indexOf("<STRONG>") + 8;
	iPosEnd = sContent.indexOf("</STRONG>", iPos);
	sSub = sContent.substring(iPos,iPosEnd);
	//Remove invalid chars for filenames
	sSub = sSub.replace(':', ' ');
	sSub = sSub.replace('/', ' ');
	sSub = sSub.replace('\\', ' ');
	sSub = sSub.replace(',', ' ');
	sSub = sSub.replace(';', ' ');
	sSub = sSub.replace('?', ' ');

	return sDate + " --- " + sSub + ".html";
    }  //end makeName


    //Saves the SOURCE VIEW of all the email msgs in the sMSGURLs list
    // sDir is the directory to save to
    public static void saveEmails(String sMsgUrls[], String sDir) throws IOException {
		int i = 0;  //for positioning
		String responseBody;  //For holding pages we get back
		String srcURL;  //Holds URL to Source View page

		while (sMsgUrls[i] != null) {
			System.out.print(i + ".");  //Print which msg we're on
			//This will get us the page in normal view, we will want SOURCE view to save attachments
			responseBody = getPage(sMsgUrls[i],".\\DUMP\\FOLDERS\\" + sDir + "\\" ,"MSG-" + i + ".html");

			//Grab the source view URL
			srcURL = getURLS(responseBody, "href=", "View Source")[0];
			//Follow the srcURL and save the FULL e-mail with attachments
			responseBody = getPage(srcURL,"");  //don't save yet, need to create filename still
			//Save the page with the created filename
			dumpInfo(responseBody, ".\\DUMP\\FOLDERS\\" + sDir + "\\", makeName(responseBody));
			i++;
		}
    }  //end saveEmails


}  //End class
