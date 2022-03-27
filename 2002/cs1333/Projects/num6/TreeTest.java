//==============================================================================
//  TreeTest.java  -- Rodney Beede -- 12/03/00
//------------------------------------------------------------------------------
//
//  Course:  CS1333-201
//  Project:  #6
//  Instructor's Name:  Scott J. Swindell
//
//  Program Description:  This program runs a benchmark of how fast a Binary
//	Search Tree and a Splay Tree run.  It uses random and presorted
//	data to determine the average run time.
//
//
//  Inputs (Command line args):
//	args[0] -- optional, max number to add into the tree; if not specified
//	   then DEFAULT_AMOUNT_DATA is used
//
//	args[1] -- optional, number of times to run the test on each tree.
//	   If not specified then DEFAULT_NUM_TESTS is used
//
//
//  Input Requirements:  The passed arguments must be numbers no greater than
//	the max value of a long data type
//
//
//  Outputs:  Displays the results of the benchmark
//
//
//
//==============================================================================


//Import Engine class
import Engine;


public class TreeTest {
	//Set some default values for the tests
	final static long DEFAULT_AMOUNT_DATA = 100;
	final static int DEFAULT_NUM_TESTS = 4;


	//**********************************************************************
	// Method:  main
	//
	// Preconditions:  String args[] - Optional array of command line args
	//	args[0] - amount of data to use
	//	args[1] - number of tests to run
	//
	// Postconditions:  Runs the benchmark class and gets test results on
	//	the trees.  The results are outputted to the screen.
	//
	//**********************************************************************
	public static void main(String args[]) {
		Engine oBenchEngine;  //Used to control testing

		//Have some info about this program displayed
		displayProgInfo();

		//Determine if we were given any arguments for the amount of
		//data to use and the number of tests to run
		if( (args.length > 0) && args[0] != "" && args[1] != "" ) {
			//Create engine with the passed values
			oBenchEngine = new Engine(new Long(args[0]).longValue(), new Integer(args[1]).intValue());
		}
		else {
			//Start the engine with the default values
			oBenchEngine = new Engine(DEFAULT_AMOUNT_DATA,
			   DEFAULT_NUM_TESTS );
		}

		//Signal the engine to start
		oBenchEngine.startTests();
	}


	//**********************************************************************
	// Method:  displayProgInfo
	//
	// Preconditions:  None
	//
	// Postconditions:  Prints info about program to screen
	//
	//**********************************************************************
	public static void displayProgInfo() {
		System.out.println( "Welcome to TreeTest" );
		System.out.println( "===================" );
		System.out.println( "" );
		System.out.println( "Program written by Rodney Beede for"
		   + " CS1333-201" );
		System.out.println( "" );
	}

}  //End of TreeTest