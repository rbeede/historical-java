/*
 * MyLDSMail_Downloader.java -- Anziano Rodney Beede
 *	Missione Italiana di Roma -- 07 Dec 2003
 *
 *
 *  NOTE:   This tool uses a hacked SSL entry method for accessing the
 *			server and does NOT provide safe SSL encryption when downloading
 *			e-mail.
 *	Also of note is that this causes any msgs marked as Unread to be marked Read
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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;


public class MyLDSMail_Downloader {
    //Used by all methods
    static GetMethod httpget;  //Used to create URLs
    static HttpClient httpclient;  //Our client worker

    static String urlBASE = "https://signin1.ldsmail.net";  //Base url used after login
    static int maxMSG = 500;  //Max number of e-mails this program can save

    static String CRLF = (char) 10 + "\n";  //Carriage return line feed for file writing

    public static void main(String[] args) throws Exception {
                
                //Quite up logging, otherwise get lots of WARNING:  Content-length unknown
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog"); 
		System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "false"); 
		System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "fatal"); 
		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "fatal"); 

                
		String responseBody;  //Stores server response
		String currURL;  //URL we are formatting to use
		String folderURL[] = new String[maxMSG];  //Array of folder URLs
		String msgURL[] = new String[maxMSG];  //Array of e-mail link URLs
		int arySize;  //holds number of actual items in msgURL

		//User login credintals
		String userName = args[0];
		String password = args[1];

		httpclient = new HttpClient();  //Setup our client worker


		//Lets make EasySSL the default SSL protocal which accepts anything
		Protocol.registerProtocol("https", new Protocol("https", new EasySSLProtocolSocketFactory(), 443));
                //Inform user that no security is promised
                System.out.println("This program accepts any SSL connection.  No security promised.");

		//Try the Login page to see if connection is possible
		System.out.print("Trying Login page...");
		httpget = new GetMethod("https://secure.lds.org/ldsm/signin/0,14544,3821-1,00.html");
		httpclient.executeMethod(httpget);
	
		//Any errors that occur showup now
		System.out.print("DONE\n");  //Done trying LOGIN page


		//Login the user to MyLDSMail.net
		System.out.print("Logging in " + userName + "...");
		responseBody = getPage("/f?username=" + userName + "&user=" + userName + "@myldsmail.net&password=" + password + "&Submit=Submit", "001 - LOGIN of " + userName + ".html");
		
		//Any errors that occur showup now
		System.out.print("DONE\n");  //Done submitting login


		//Look for the "Folders" URL
		currURL = getURLS(responseBody, "href=","Folders</FONT>")[0];

		//Follow the Folders URL
		System.out.print("Opening Folders page...");
		responseBody = getPage(currURL,"002 - List of Folders.html");

		//Any errors that occur showup now
		System.out.print("DONE\n");  //Done


		//Grab all the Folder URLs.  Note that first grab is Rules link (see Folder URL Fallouts.txt)
		folderURL = getURLS(responseBody, "HREF=", "TARGET=\"_top\"");


		//Follow the 1 = INBOX folder
		//======================================================
		System.out.print("Opening Inbox Folder...");
		responseBody = getPage(folderURL[1],".\\DUMP\\FOLDERS\\INBOX\\" ,"004 - Inbox Folder.html");

		//Any errors that occur showup now
		System.out.print("DONE\n");  //Done

		//Grab all the msg URLs
		System.out.print("Grabbing URLs...");
		msgURL = getURLS(responseBody, "href=", "ead Message\":");
		arySize = detLng(msgURL);
		System.out.print("DONE\n");  //Done

		//Start storing all the msgs in the folder
		//Note that to save the SOURCE we must first follow the Normal View on each message individually
		System.out.print("Saving messages (" + (arySize+1) + ") in INBOX folder...");
		saveEmails(msgURL, "INBOX");
		System.out.print("DONE\n");  //Done
		//======================================================


		//Follow the 2 = RECEIVED folder
		//======================================================
		System.out.print("Opening Received Folder...");
		responseBody = getPage(folderURL[2],".\\DUMP\\FOLDERS\\RECEIVED\\" ,"004 - Received Folder.html");

		//Any errors that occur showup now
		System.out.print("DONE\n");  //Done

		//Grab all the msg URLs
		System.out.print("Grabbing URLs...");
		msgURL = getURLS(responseBody, "href=", "ead Message\":");
		arySize = detLng(msgURL);
		System.out.print("DONE\n");  //Done

		//Start storing all the msgs in the folder
		//Note that to save the SOURCE we must first follow the Normal View on each message individually
		System.out.print("Saving messages (" + (arySize+1) + ") in RECEIVED folder...");
		saveEmails(msgURL, "RECEIVED");
		System.out.print("DONE\n");  //Done
		//======================================================



		//Follow the 3 = Sent_Items folder
		//======================================================
		System.out.print("Opening SENT_ITEMS Folder...");
		responseBody = getPage(folderURL[3],".\\DUMP\\FOLDERS\\SENT_ITEMS\\" ,"006 - SENT_ITEMS Folder.html");

		//Any errors that occur showup now
		System.out.print("DONE\n");  //Done

		//Grab all the msg URLs
		msgURL = getURLS(responseBody, "href=", "ead Message\":");
		arySize = detLng(msgURL);
		
		//Start storing all the msgs in the folder
		//Note that to save the SOURCE we must first follow the Normal View on each message individually
		System.out.print("Saving messages (" + (arySize+1) + ") in SENT_ITEMS folder...");
		saveEmails(msgURL, "SENT_ITEMS");
		System.out.print("DONE\n");  //Done
		//======================================================




		httpget.releaseConnection();  //Close connection cleanly

    }  //End main



    //This method parses out a list of URLs.
    // sContent is the html content
    // sStrTag is beginning of where URL starts (HREF=") 
    // sEndTag is tail end of content where URL ends
    //  and ending of URL is .J
    // This method is recursive in that it searches over pages
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

	//Check for the next page button
	if(sContent.indexOf("Next Page") != -1) {  //found next page button
		String sndURLS[];
		String newContent;

		//extract the URL and call this method on that URL
		iPos = sContent.indexOf("Next Page");
		iPos = sContent.lastIndexOf("href=", iPos) + 6;  //+6 for the href=" part
		iPosEnd = sContent.indexOf(".J\"", iPos) + 2;  //+2 to include .J

		System.out.print("page.");
		newContent = getPage(sContent.substring(iPos, iPosEnd),".\\DUMP\\FOLDERS\\", "MultiPage.html");
		sndURLS = getURLS(newContent, "href=", "ead Message\":");
		
		//copy these second URLs into srcURL
		iPos = 0;  //for sndURLS
		iPosEnd = iNumURL;  //for srcURL
		while(sndURLS[iPos] != null) {
			srcURL[iPosEnd] = sndURLS[iPos];
			iPos++;
			iPosEnd++;
		}
	}

	return srcURL;  //return the array of URLs
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
	
	httpget = new GetMethod(urlBASE + sURL);
	try {  //sometimes connection resets, so we try 2 times
		httpclient.executeMethod(httpget);
	} catch(Throwable e) {
		httpclient.executeMethod(httpget);
	}

	//Read in the page
	responseBody = new String(httpget.getResponseBody());

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

	//some msgs don't have Date: entries, we should do it in the following way
	if(sContent.indexOf("<BR>Date:") == -1) {
		iPos = sContent.indexOf("NetWare;<BR>") + 12;
		iPosEnd = sContent.indexOf("<BR>",iPos);
	}

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
	sDate = sDate + "-";
	if(myDate.getMinutes() < 10 )  //Make leading 0 for minutes less than 10
		sDate = sDate + "0" + (myDate.getMinutes());
	else
		sDate = sDate + (myDate.getMinutes());
	sDate = sDate + "-";
	if(myDate.getSeconds() < 10 )  //Make leading 0 for seconds less than 10
		sDate = sDate + "0" + (myDate.getSeconds());
	else
		sDate = sDate + (myDate.getSeconds());


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
	sSub = sSub.replace('*', ' ');

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


    //Returns index of last item in array that wasn't null
    public static int detLng(String myArray[]) {
	int i = 0;
	while(myArray[i] != null)  i++;
	return i - 1;
    }

}  //End class
