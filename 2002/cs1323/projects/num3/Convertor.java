//=========================================================================
//	Convertor.java 
//=========================================================================
//	Programmer:  		Rodney Beede 
//	ID:			0000 
//	Course:      		CS1323 
//	Lab Section:		222 
//	Lab TA:			Lalitha
//
//	Project#:    		#3
//	Due Date:		February 25th, 2000	 
//
//	Description:		This program calls on the Unit class to
//				convert centimeters to feet, inches, yards,
//				and miles.
//
//				It uses the methods toInches, toFeet,
//				toYards, and toMiles from the Unit class
//
//=========================================================================

import javabook.*;  //Load the javabook package
import java.awt.*;  //Used for the Frame class

import Unit;  //My custom made class for converting units

class Convertor {
	public static void main( String[] args ) {
		//Declare a frame object, it is used to simply give a
		//placeholder for the OutputBox and InputBox objects
		//instead of using MainWindow
		Frame objFrameHolder = new Frame();

		//Declare an OutputBox object to display output to user
		OutputBox objMsgBox = new OutputBox( objFrameHolder, 
		   "CS1323 -- Convertor" );

		//Declare a InputBox object to get input from user
		InputBox objInputBox = new InputBox( objFrameHolder,
		   "Input Required" );

		//Show the output box
		objMsgBox.show();

		//Describe this program in objMsgBox
		objMsgBox.printLine( "This program converts centimeters " +
		   "to inches, feet, yards, and miles." );
		objMsgBox.skipLine( 2 );

		//Create a Unit object to be used to convert units
		//Also ask the user for the number of centimeters and pass
		//that along to the object
		Unit objUnitCalc = new Unit( objInputBox.getFloat(
		   "Enter in the number of centimeters.  Decimals " +
		   "Allowed.") );

		//Convert centimeters to inches, feet, yards, and miles
		//Then display the result to the user
		objMsgBox.printLine( objUnitCalc.fltCentMtr +
		   " centimeters equals " + objUnitCalc.toInches() +
		   " inches" );
		objMsgBox.printLine( objUnitCalc.fltCentMtr +
		   " centimeters equals " + objUnitCalc.toFeet() +
		   " feet" );
		objMsgBox.printLine( objUnitCalc.fltCentMtr +
		   " centimeters equals " + objUnitCalc.toYards() +
		   " yards" );
		objMsgBox.printLine( objUnitCalc.fltCentMtr +
		   " centimeters equals " + objUnitCalc.toMiles() +
		   " miles" );

		//Have the program wait to terminate until after the user
		//closes the OutputBox
		objMsgBox.waitUntilClose();

		//Unload the frame holder and kill the program
		objFrameHolder.dispose();
	}  //End of main method
}  //End of class Convertor
