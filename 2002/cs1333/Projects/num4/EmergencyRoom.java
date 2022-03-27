//==============================================================================
//  EmergencyRoom.java -- Rodney Beede -- 10/29/00
//------------------------------------------------------------------------------
//
//  Class Description:  This class processes ER patient queries.  It loads the
//	Doctor & Patient Queries from files, and processes them.  It also reads
//	in data from databases for Doctors and Patients.
//
//	As each patient is processed, that databases are updated.  When the
//	program has finished processing, it will save the new data to disk
//
//
//
//  Public Variables:  None
//
//
//  Public Methods:  void start  () - starts the program off
//
//
//  Protected Variables:  None
//
//
//  Protected Methods:  None
//
//
//  Private Variables:  QueueList oDoctorQuery - holds doctor query
//			QueueList oPatientQuery - holds patient query
//			DoublyLinkedList oDoctorDB - holds doctor database
//			DoublyLinkedList oPatientDB - holds patient database
//
//			String:
//				sDoctorDBFile - filename of doctor database
//				sPatientDBFile - filename of patient database
//				sDoctorQFile - filename of doctor query
//				sPatientQFile - filename of patient query
//
//
//  Private Methods:  String readDocQueryFile  () - reads the doctor query file
//			into memory
//
//		      String readPatQueryFile  () - reads the patient query file
//			into memory
//
//		      String readDocDBFile  () - reads the doctor database file
//			into memory
//
//		      String readPatDBFile  () - reads the patient database file
//			into memory
//
//		      void memDump  () - dumps the memory contents of both DB
//			and queries to the screen
//
//==============================================================================


// Import DoublyLinkedList package for creating lists with
import structure.DoublyLinkedList;
// Import QueryList package for creating queries with
import structure.QueueList;
// Import the Java IO package for reading and writing files
import java.io.*;
// Import the Java Util StringTokenizer class for parsing our files
import java.util.StringTokenizer;
// Import the DoctorEntry class for making entires for our DoctorDB
import DoctorEntry;
// Import the Iterator package for accessing elements in DBes
import structure.Iterator;


public class EmergencyRoom {
	//Define some query objects for use
	private QueueList oDoctorQuery;
	private QueueList oPatientQuery;

	//Define some list objects for use
	private DoublyLinkedList oDoctorDB;
	private DoublyLinkedList oPatientDB;

	//Create some constants that point to filenames
	//In the future, this might just be a paramater for instantiating
	//this class
	private final String sDoctorDBFile = "DoctorDB.txt";
	private final String sPatientDBFile = "PatientDB.txt";
	private final String sDoctorQFile = "DoctorQ.txt";
	private final String sPatientQFile = "PatientQ.txt";


	//**********************************************************************
	// Method:  class constructor
	//
	// Preconditions:  None
	//
	// Postconditions:  Instantiates our query and list objects
	//
	// Description:  This method will instantiate our query	objects for
	// later use and also our DoublyLinkedList database objects
	//
	//**********************************************************************
	public EmergencyRoom() {
		oDoctorQuery = new QueueList();
		oPatientQuery = new QueueList();
		oDoctorDB = new DoublyLinkedList();
		oPatientDB = new DoublyLinkedList();
	}


	//**********************************************************************
	// Method:  start
	//
	// Preconditions:  No params.  Class does require that the DB files and
	//	Query files exist
	//
	// Postconditions:  Memory dumps of the DBes and Queries are outputted
	//	to the screen.  The Patient Query will be empty by the end of
	//	execution.  Both DB will be updated with any new info to disk,
	//	and the PatientQ.txt will be emptied out.
	//
	// Description:  Calls various methods to read in Query info from files,
	//	read in database info from files, drops out current state of
	//	DBes & Queries in memory, and processes each patient in the
	//	Patient Query.  This method basically kicks off everything else
	//
	//**********************************************************************
	public void start() {
		//Holds return value of a method, to tell if it was successful
		String sMethodFailure;

		//Start things off by reading in our Doctor Query by calling the
		//readDocQueryFile method.  If it fails the method will return a
		//error message.  If it does leave this method (which returns
		//control to calling class)
		sMethodFailure = readDocQueryFile();

		if( !(sMethodFailure.equals("")) ) {
			//There was some kind of problem, print it out to user
			System.out.println( "There was an error during the " +
			   "attempt to read the Doctor Query.  Error was:\n" +
			   sMethodFailure );

			//Leave this method
			return;
		}

		//Read in the Patient Query.  If an error occurs, do the same as
		//what happens with an error in the Doctor Query
		sMethodFailure = readPatQueryFile();

		if( !(sMethodFailure.equals("")) ) {
			//There was some kind of problem, print it out to user
			System.out.println( "There was an error during the " +
			   "attempt to read the Patient Query.  Error was:" +
			   "\n" + sMethodFailure );

			//Leave this method
			return;
		}

		//Read in the Doctor Database.  If an error occurs, tell the
		//user and leave this class
		sMethodFailure = readDocDBFile();

		if( !(sMethodFailure.equals("")) ) {
			//There was some kind of problem, print it out to user
			System.out.println( "There was an error during the " +
			   "attempt to read the Doctor Database.  Error was:" +
			   "\n" + sMethodFailure );

			//Leave this method
			return;
		}

		//Read in the Patient Database.  If an error occurs, tell the
		//user and leave this class
		sMethodFailure = readPatDBFile();

		if( !(sMethodFailure.equals("")) ) {
			//There was some kind of problem, print it out to user
			System.out.println( "There was an error during the " +
			   "attempt to read the Patient Database.  Error was:" +
			   "\n" + sMethodFailure );

			//Leave this method
			return;
		}


		//Dump the current state of our memory, before processing
		System.out.println("Before processing memory dump\n");
		dumpMem();


		//REST OF PROGRAM GOES HERE

	}  //End of start() method


	//**********************************************************************
	// Method:  readDocQueryFile
	//
	// Preconditions:  No params.  Requires that the file specified by
	//	sDoctorQFile exists.
	//
	// Postconditions:  Returns a String object that contains any errors
	//	that may have occured.
	//	If no errors occured a blank string is returned and the
	//	oDoctorQuery object is filled in with the Doctor Query data in
	//	the Doctor Query file
	//
	// Description:  Attempts to populate the oDoctorQuery with the Query
	//	data in the sDoctorQFile file.
	//
	//**********************************************************************
	private String readDocQueryFile() {
		String sCurrLine;  //Holds current line read from file

		//Setup our Doctory Query File for reading
		try {  //Incase of IOException errors
			File oInputFile = new File(sDoctorQFile);
			FileReader oQueryFileReader =
			   new FileReader(oInputFile);
			BufferedReader oQueryBuffFR =
			   new BufferedReader(oQueryFileReader);

			//Read in the first line of the file
			sCurrLine = oQueryBuffFR.readLine();


			//Add in each line of the file, reading each line as we
			//go until the end of the file is reached
			while( sCurrLine != null) {
				oDoctorQuery.enqueue( sCurrLine );  //Add entry

				//Read in the next line
				sCurrLine = oQueryBuffFR.readLine();
			}

			oQueryBuffFR.close();  //Close the file
		}
		catch (IOException e) {
			//An I/O error occured trying to open the file
			//Return back the error
			return "Error during access of " + sDoctorQFile +
			   "\nError Description:  " + e.getMessage();
		}


		//We've made it this far, so we have succeeded in reading
		//in the data.  Return an empty string to indicate success
		return "";

	}  //End of readDocQueryFile() method


	//**********************************************************************
	// Method:  readPatQueryFile
	//
	// Preconditions:  No params.  Requires that the file specified by
	//	sPatientQFile exists.
	//
	// Postconditions:  Returns a String object that contains any errors
	//	that may have occured.
	//	If no errors occured a blank string is returned and the
	//	oPatientQuery object is filled in with the Patient Query data in
	//	the Patient Query file
	//
	// Description:  Attempts to populate the oPatientQuery with the Query
	//	data in the sPatientQFile file.
	//
	//**********************************************************************
	private String readPatQueryFile() {
		String sCurrLine;  //Holds current line read from file

		//Setup our Patient Query File for reading
		try {  //Incase of IOException errors
			File oInputFile = new File(sPatientQFile);
			FileReader oQueryFileReader =
			   new FileReader(oInputFile);
			BufferedReader oQueryBuffFR =
			   new BufferedReader(oQueryFileReader);

			//Read in the first line of the file
			sCurrLine = oQueryBuffFR.readLine();


			//Add in each line of the file, reading each line as we
			//go until the end of the file is reached
			while( sCurrLine != null) {
				oPatientQuery.enqueue( sCurrLine );  //Add entry

				//Read in the next line
				sCurrLine = oQueryBuffFR.readLine();
			}

			oQueryBuffFR.close();  //Close the file
		}
		catch (IOException e) {
			//An I/O error occured trying to open the file
			//Return back the error
			return "Error during access of " + sPatientQFile +
			   "\nError Description:  " + e.getMessage();
		}


		//We've made it this far, so we have succeeded in reading
		//in the data.  Return an empty string to indicate success
		return "";

	}  //End of readPatQueryFile() method


	//**********************************************************************
	// Method:  readDocDBFile
	//
	// Preconditions:  No params.  Requires that the file specified by
	//	sDoctorDBFile exists.
	//
	// Postconditions:  Returns a String object that contains any errors
	//	that may have occured.
	//	If no errors occured a blank string is returned and the
	//	oDoctorDB object is filled in with the Doctor DB data in
	//	the Doctor Database file
	//
	// Description:  Attempts to populate the oDoctorDB with the Database
	//	data in the sDoctorDBFile file.
	//
	//**********************************************************************
	private String readDocDBFile() {
		StringTokenizer oParser;  //Parses our file for us
		String sCurrLine;  //Holds current line in file
		String sCurrToken;  //Holds the current token data
		//Holds ID of a patient that belongs to a doctor
		String sNewPatHisID;
		//Holds date of visit of a patient that belongs to a doctor
		String sNewPatHisDate;
		DoctorEntry oCurrDoc;  //Holds reference to current doctor


		//Setup our Doctory DB File for reading
		try {  //Incase of IOException errors
			File oInputFile = new File(sDoctorDBFile);
			FileReader oQueryFileReader =
			   new FileReader(oInputFile);
			BufferedReader oQueryBuffFR =
			   new BufferedReader(oQueryFileReader);

			//Read in each line of the file, and parse it into
			//it's different parts
			while( (sCurrLine=oQueryBuffFR.readLine()) != null ) {
				//Parse the current line
				oParser = new StringTokenizer( sCurrLine );

				//Grab the 1st token, if there is one
				if( oParser.hasMoreTokens() == true ) {
					sCurrToken = oParser.nextToken();
				}
				else {
					//Make sure we don't have stale data
					//saying that there was a token
					sCurrToken = "";
				}

				//Check to see if the token had a doctor id
				if( sCurrToken.startsWith("dr") ) {
					//Found a doctor ID, create a new Doctor
					//Entry in our oDoctorDB with this ID
					oDoctorDB.add(new DoctorEntry(sCurrToken));

					//The very next line in the file must
					//contain our patient history list, so
					//read in that line and break it up into
					//tokens
					sCurrLine = oQueryBuffFR.readLine();
					oParser = new StringTokenizer(
					   sCurrLine );
					//Loop through all of our tokens
					while(oParser.hasMoreTokens() == true) {
						//Grab a token
						sCurrToken = oParser.nextToken();

						//The first token should be a
						//patient id
						if(sCurrToken.startsWith("pt")){
							//Make a reference to the last added doctor
							oCurrDoc = (DoctorEntry) oDoctorDB.peek();

							//Store the patient ID
							sNewPatHisID = sCurrToken;
							//The next token should contain the patient date
							sNewPatHisDate = oParser.nextToken();

							//Store the new patient history
							oCurrDoc.addVisitEntry( sNewPatHisID, sNewPatHisDate );
						}
					}
				}	

			}

			oQueryBuffFR.close();  //Close the file
		}
		catch (IOException e) {
			//An I/O error occured trying to open the file
			//Return back the error
			return "Error during access of " + sDoctorDBFile +
			   "\nError Description:  " + e.getMessage();
		}


		//We've made it this far, so we have succeeded in reading
		//in the data.  Return an empty string to indicate success
		return "";

	}  //End of readDocDBFile() method


	//**********************************************************************
	// Method:  readPatDBFile
	//
	// Preconditions:  No params.  Requires that the file specified by
	//	sPatientDBFile exists.
	//
	// Postconditions:  Returns a String object that contains any errors
	//	that may have occured.
	//	If no errors occured a blank string is returned and the
	//	oPatientDB object is filled in with the Patient DB data in
	//	the Patient Database file
	//
	// Description:  Attempts to populate the oPatientDB with the Database
	//	data in the sPatientDBFile file.
	//
	//**********************************************************************
	private String readPatDBFile() {
		StringTokenizer oParser;  //Parses our file for us
		String sCurrLine;  //Holds current line in file
		String sCurrToken;  //Holds the current token data

		//Holds Illness data read from current line of file
		String sNewIllHisID;
		String sNewIllHisDate;
		String sNewIllHisDocID;

		PatientEntry oCurrPat;  //Holds reference to current patient


		//Setup our Patient DB File for reading
		try {  //Incase of IOException errors
			File oInputFile = new File(sPatientDBFile);
			FileReader oQueryFileReader =
			   new FileReader(oInputFile);
			BufferedReader oQueryBuffFR =
			   new BufferedReader(oQueryFileReader);

			//Read in each line of the file, and parse it into
			//it's different parts
			while( (sCurrLine=oQueryBuffFR.readLine()) != null ) {
				//Parse the current line
				oParser = new StringTokenizer( sCurrLine );

				//Grab the 1st token, if there is one
				if( oParser.hasMoreTokens() == true ) {
					sCurrToken = oParser.nextToken();
				}
				else {
					//Make sure we don't have stale data
					//saying that there was a token
					sCurrToken = "";
				}

				//Check to see if the token had a patient id
				if( sCurrToken.startsWith("pt") ) {
					//Found a patient ID, create a new Patient
					//Entry in our oPatientDB with this ID
					oPatientDB.add(new PatientEntry(sCurrToken));

					//The very next line in the file must
					//contain our patient illness list, so
					//read in that line and break it up into
					//tokens
					sCurrLine = oQueryBuffFR.readLine();
					oParser = new StringTokenizer(
					   sCurrLine );
					//Loop through all of our tokens
					while(oParser.hasMoreTokens() == true) {
						//Grab a token
						sCurrToken = oParser.nextToken();

						//The first token should be a
						//illness id
						if(sCurrToken.startsWith("il")){
							//Make a reference to the last added patient
							oCurrPat = (PatientEntry) oPatientDB.peek();

							//Store the illness ID
							sNewIllHisID = sCurrToken;
							//The next token should contain the date
							sNewIllHisDate = oParser.nextToken();
							//The next token should contain the doc id
							sNewIllHisDocID = oParser.nextToken();

							//Store the new illness history
							oCurrPat.addIllnessEntry( sNewIllHisID, sNewIllHisDate, sNewIllHisDocID );
						}
					}
				}	

			}

			oQueryBuffFR.close();  //Close the file
		}
		catch (IOException e) {
			//An I/O error occured trying to open the file
			//Return back the error
			return "Error during access of " + sPatientDBFile +
			   "\nError Description:  " + e.getMessage();
		}


		//We've made it this far, so we have succeeded in reading
		//in the data.  Return an empty string to indicate success
		return "";

	}  //End of readPatDBFile() method


	//**********************************************************************
	// Method:  dumpMem
	//
	// Preconditions:  None.  No pre-cond
	//
	// Postconditions:  Prints out the complete mem contents
	//	of oDoctorDB, oPatientDB, oDoctorQuery, & oPatientQuery
	//
	// Description:  See Post Cond
	//
	//**********************************************************************
	private void dumpMem() {
		Iterator oListor;  //Allows us to grab elements in DB objects

		//Print out that we are dumping the DoctorDB
		System.out.println("DoctorDB:\n");
		oListor = oDoctorDB.elements();  //Grab oDoctorDB Iterator
		//Loop through oDoctorDB elements, shooting out there values
		while (oListor.hasMoreElements()) {
			System.out.println( "Doctor ID:  " + ((DoctorEntry)
			   oListor.value()).getDoctorID());
			System.out.println( "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			System.out.println( "\tpatient history\n" );

			System.out.println( "ID\t\tVisit Date\n----------------------------" );
			System.out.println( ((DoctorEntry) oListor.value()).showVisitHistory() );
			System.out.println( "==============================\n\n" );

			oListor.nextElement();  //Next entry
		}


		//Print out that we are dumping the PatientDB
		System.out.println("PatientDB:\n");
		oListor = oPatientDB.elements();  //Grab oPatientDB Iterator
		//Loop through oPatientDB elements, shooting out there values
		while (oListor.hasMoreElements()) {
			System.out.println( "Patient ID:  " + ((PatientEntry)
			   oListor.value()).getPatientID() );
			System.out.println( "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			System.out.println( "illness history\n" );

			System.out.println( "Illness ID\tDiag Date\tDoctor ID\n----------------------------" );
			System.out.println( ((PatientEntry) oListor.value()).showIllnessHistory() );
			System.out.println( "==============================\n\n" );

			oListor.nextElement();  //Next entry
		}


		//Print out that we are dumping the DoctorQ
		System.out.println("DoctorQ:");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		while( !(oDoctorQuery.isEmpty()) ) {
			System.out.println(oDoctorQuery.dequeue());
		}

		//Print out that we are dumping the PatientQ
		System.out.println("\n\nPatientQ:");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		while( !(oPatientQuery.isEmpty()) ) {
			System.out.println(oPatientQuery.dequeue());
		}
	}










/*


	//**********************************************************************
	// Method:  NAME
	//
	// Preconditions:  PARAMS HERE
	//
	// Postconditions:  RETURN VALUE HERE
	//
	// Description:  What this does here
	//
	//**********************************************************************
	public type METHODNAME( params ) {

	}

*/

}  //End of EmergencyRoom class