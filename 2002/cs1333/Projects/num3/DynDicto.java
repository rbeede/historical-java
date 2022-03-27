//==============================================================================
//  DynDicto.java  -- Rodney Beede -- 10/11/00
//------------------------------------------------------------------------------
//
//  Course:  CS1333-201
//  Project:  #3
//  Instructor's Name:  Scott J. Swindell
//
//  Program Description:  This program reads in a dictionary file and allows
//	the user to display entries or add new ones
//
//  Inputs (Command line args):  No command line arguments are used
//	Instead, the program grabs the username from the OS and reads in a
//	dictionary file based on that name
//
//  Input Requirements:  That a file with the filename of username.DIC exist
//
//  Outputs:  Dictionary entries to the screen
//
//  SPECIAL NOTE:  I'm using my "get-out-of-jail free"
//
//==============================================================================


//Import the Dictionary class
import Dictionary;
import java.io.*;

public class DynDicto {

	//**********************************************************************
	// Method:  main
	//
	// Preconditions:  String args[] - Optional array of command line args
	//
	// Postconditions:  Displays request definitions to the screen
	//
	// Description:  This method gets the username, loads the Dictionary
	//	object with that data in that file with the name of username.DIC
	//	and starts the user interface to listen for commands
	//
	//**********************************************************************
	public static void main(String args[]) throws IOException {
		//Holds what command the user issued
		String sUserCommand;

		//Get the username from the OS and use it for our filename
		String sFilename = System.getProperty( "user.name" );

		//Declare a Dictionary object for use with our dictionary file
		Dictionary oMyDictionary;

		//Format the username with a .DIC extension
		sFilename += ".DIC";
		//Instansiate oMyDictionary with the filename to use.  It will
		//open up that file when it is created and load all the entries
		oMyDictionary = new Dictionary( sFilename );

		//Ask the user for a command, looping until the user quits
		sUserCommand = getCommand();
		while( sUserCommand != "-KILL" ) {
			//Have oMyDictionary search for the given keyword
			//Printing out whatever is returned
			System.out.println(
			   oMyDictionary.findDef(sUserCommand) );

			//Ask the user again for a command
			sUserCommand = getCommand();			
		}  //End of while loop
		
		//Print thankyou for using program
		System.out.println( "Thank you for using this program." );
	}


	//**********************************************************************
	// Method:  getCommand
	//
	// Preconditions:  None
	//
	// Postconditions:  Returns command user selected.  Possibles are:
	//	LOOKUP - lookup a word; KILL - end the program
	//
	// Description:  This method simply gets the user's input for a command
	//
	//**********************************************************************
	public static String getCommand() throws IOException {
		//Reader for grabbing input from stdin
		BufferedReader stdin = new BufferedReader
		   (new InputStreamReader(System.in));
		//Holds what the user typed in, so we can check what we're doing
		String sUserInput;

		//Prompt the user for a keyword or to quit
		System.out.println( "\n" );
		System.out.println( "Enter in the keyword you wish to search " +
		   "for.\nIf you are adding a new one, simply type it in.\n" +
		   "\nIf you wish to end the program, type in -quit, -q, " +
		   "-exit, or -x.\n\n");
		System.out.print( "=======>  " );

		//Get the input
		sUserInput = stdin.readLine();

		//See if the user wanted to quit
		if( sUserInput.equalsIgnoreCase("-quit") ||
		    sUserInput.equalsIgnoreCase("-q")    ||
		    sUserInput.equalsIgnoreCase("-exit") ||
		    sUserInput.equalsIgnoreCase("-x") ) {
			return "-KILL";
		}
		else {  //User didn't want to quit, gave a keyword
			return sUserInput;
		}
	}  //End of getCommand method

}  //End of DynDicto