//==============================================================================
//  ERPM.java  -- Rodney Beede -- 10/29/00
//------------------------------------------------------------------------------
//
//  Course:  CS1333-201
//  Project:  #4
//  Instructor's Name:  Scott J. Swindell
//
//  Program Description:  This program functions as an Emergency Room query
//	program.  It processes a query of patients waiting for treatment.
//
//	It does this by looking in a Doctor DB to see what doctor saw the
//	patient last time.  It uses the EmergencyRoom class to handle all of
//	the processing and such.
//
//  Inputs (Command line args):  None
//
//
//
//  Input Requirements:  None
//
//
//
//  Outputs:  Memory dumps on current state of the Patient Query, Patient DB,
//	Doctor Query, and Doctor DB.  This is done at the very beginning, before
//	processing, and once each time a patient is processed
//
//
//
//==============================================================================


//Import EmergencyRoom class
import EmergencyRoom;


public class ERPM {

	//**********************************************************************
	// Method:  main
	//
	// Preconditions:  String args[] - Optional array of command line args
	//	The following files are expected to be found for our queries
	//	and databases:
	//		DoctorDB.txt	DoctorQ.txt	PatientDB.txt
	//		PatientQ.txt
	//	These files aren't checked for here, they are checked for in
	//	the EmergencyRoom class
	//
	// Postconditions:  When the program terminates, the Patient Query will
	//	be empty having been processed.  Also the PatientQ.txt file will
	//	be emptied out, having been processed.
	//
	//	The DoctorDB.txt and PatientDB.txt files will be updated with
	//	any new data.
	//
	// Description:  This method simply creates and starts the EmergencyRoom
	//	class so processing can begin
	//
	//**********************************************************************
	public static void main(String args[]) {
		//Create a EmergencyRoom object
		EmergencyRoom oERWaitingRoom;

		//Display some info about the program
		System.out.println( "Emergency Room Patient Manager (ERPM)" );
		System.out.println( "Written by Rodney Beede\n" );

		//Instantiate oERWaitingRoom for use
		oERWaitingRoom = new EmergencyRoom();

		//Call the start method of oERWaitingRoom to begin processing
		oERWaitingRoom.start();

		//Tell user program is finished
		System.out.println( "Program terminated.\n" );
	}

}  //End of ERPM