//=========================================================================
//	InventoryMain.java 
//=========================================================================
//	Programmer:  		Rodney Beede 
//	ID:			0000 
//	Course:      		CS1323 
//	Lab Section:		222 
//	Lab TA:			Lalitha
//
//	Project#:    		4		
//	Due Date:		3-03-2000	 
//
//	Description:		This program uses a Inventory object
//				(loaded from the Inventory class) to report
//				a vechile series based on data entered
//				about that data
//
//	Inputs:			Vechile Price, Number of Doors,
//				Transmission Type
//				If Series = G, Horsepower of Vehicle
//
//	Outputs:		Vehicle Series Type (A-G), If Series = G,
//				Trim Level (1-4)
//
//	Public Methods:		main( String[] args ) -- Starts program
//
//	Private Methods:	displayInfo( ) -- Displays info about prog
//
//				displayResults( ) -- Displays results of
//				data entered by user
//				
//=========================================================================

import javabook.*;  //Load the javabook class
import Inventory;  //Load the Inventory class

class InventoryMain {
	//Declare and create a MainWindow object
	private static MainWindow objMainWin = new MainWindow( "CS1323 " +
	   "-- Inventory, Project #4" );

	//Declare and create an OutputBox object
	private static OutputBox objMsgBox = new OutputBox( objMainWin,
	   "Info Box" );


	public static void main( String[] args ) {

		//Declare (but don't create) an Inventory object
		Inventory objInven;

		//Make objMainWin and objMsgBox visible
		objMainWin.show();
		objMsgBox.show();

		//Display information about this program
		displayInfo();

		//Make objInven a Inventory object
		//This will also start getting inputs and figure out
		//all the information about the vechile in question
		objInven = new Inventory( objMainWin );

		//Give the user the results of their input
		displayResults( objInven );
	}  //End of main method


	/*
	  Method:	displayInfo

	  Purpose:	To display information about this program

	  Parameters:	None

	  Returns:	None
	*/
	private static void displayInfo( ) {
		//Shoot out some info to the user
		objMsgBox.printLine( "Inventory" );
		objMsgBox.printLine( "=========" );
		objMsgBox.skipLine( 2 );
		objMsgBox.printLine( "This program takes the Price, " +
		   "Number of Doors, and Transmission Type of a vechile" );
		objMsgBox.printLine( "and outputs the vechile Series." );
		objMsgBox.skipLine( 1 );
		objMsgBox.printLine( "If the Series is 'G' then the " +
		   "program also asks for the Horsepower and outputs the" +
		   " Trim Level." );
		objMsgBox.skipLine( 1 );
		objMsgBox.printLine( "Valid values for the Price are " +
		   "from $11,250 to $29,200." );
		objMsgBox.printLine( "Valid values for the Number of " +
		   "Doors are from 2 to 4." );
		objMsgBox.printLine( "Valid values for the Transmission " +
		   "Type are A for automatic, M for manual." );
		objMsgBox.printLine( "Valid values for Horse Power are " +
		   "from 200 to 310." );
		objMsgBox.printLine( "For all values entered, use only " +
		   "numbers; no commas or dollar signs." );
	}


	/*
	  Method:	displayResults

	  Purpose:	Displays results of user input (the vechile series
			and Trim Level if appropriate)

	  Parameters:	objInven -- Object reference so data can be
			read from Inventory class

	  Returns:	None
	*/
	private static void displayResults( Inventory objInven ) {
		//Echo back what the user entered as input
		objMsgBox.skipLine( 2 );
		objMsgBox.printLine( "Vechile Price:  $" + objInven.shrtPrice );
		objMsgBox.printLine( "Number of Doors:  " + objInven.bytDoors );

		//Figure out the transmission type and print 'Automatic'
		//for a value of 'A' and 'Manual' for a value of 'M'
		if( objInven.chrTransmission == 'A' )
			objMsgBox.printLine( "Transmission Type:  " +
			   "Automatic" );
		else
			objMsgBox.printLine( "Transmission Type:  " +
			   "Manual" );

		//If vechile series was 'G' then also show what horse
		//power was entered in by the user
		if( objInven.chrSeries == 'G' )
			objMsgBox.printLine( "Horse Power:  " +
			   objInven.shrtHorsePower );

		//Reveal the vechile series (and trim level is appliable)
		objMsgBox.skipLine( 2 );
		objMsgBox.printLine( "Vechile Series:  " +
		   objInven.chrSeries );
		if( objInven.chrSeries == 'G' )
		   objMsgBox.printLine( "Vechile Trim Level:  " +
		      objInven.bytTrimLvl );

		//If the series was unknown (due to bad user data) tell the
		//user why the Vechile Series indicates a '?'
		if( objInven.chrSeries == '?' ) {
			//See if user entered a HP incase it was bad, that
			//way the user can see the bad entry
			if( objInven.shrtHorsePower > 0 )
				objMsgBox.printLine( "Horse Power:  " +
				   objInven.shrtHorsePower );

			objMsgBox.skipLine( 3 );
			objMsgBox.printLine( "The series could not be " +
			   "determined due to the fact that the data " +
			   "entered did not match anything in the " +
			   "database." );
		}
	}
}  //End of class InventoryMain



