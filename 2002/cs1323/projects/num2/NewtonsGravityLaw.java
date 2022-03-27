//=========================================================================
//	NewtonsGravityLaw.java 
//=========================================================================
//	Programmer:  		Rodney Beede 
//	ID:			0000 
//	Course:      		CS1323 
//	Lab Section:		222 
//
//	Project#:		2    		
//	Due Date:		2/11/2000	 
//
//	Description:		This program uses Newton's universal law of
//				gravitation to compute the force between
//				two bodies.  It will prompt the user for
//				the the mass of two bodies (in grams) and
//				the distance between the two (in
//				centimeters).  It will then output the
//				force between the two bodies.
//				
//=========================================================================

import javabook.*;  //Load the javabook package

class NewtonsGravityLaw {
	public static void main( String[] args ) {
		//Create a new window with an appropriate title
		MainWindow objMainWin = new MainWindow(
		   "Newton's universal law of gravitation" );

		//For displaying back the calculations to the user
		OutputBox objOutBoxInfo = new OutputBox( objMainWin,
		   "Information" );

		//For getting input from the user
		InputBox objInBoxData = new InputBox( objMainWin,
		   "Data Input" );

		//Setup any known constant values
		final double GRAVCONST = 6.67E-8;  //Gravitational constant

		float fltBody1Mass;  //For the mass of body one
		float fltBody2Mass;  //For the mass of body two
		float fltDistance;  //For the distance between the bodies
		double dblForce;  //For the force between the two bodies
		
		//Show objMainWin and objOutBoxInfo
		objMainWin.show();
		objOutBoxInfo.show();

		//Give the user some info about this program
		objOutBoxInfo.printLine( "This program computes the " +
		   "force between two bodies" );
		objOutBoxInfo.printLine( "by using Newton's universal" +
		   " law of gravitation:" );
		objOutBoxInfo.printLine( "F = k * ( M1 * M2 / d^2 )" );
		objOutBoxInfo.printLine( "" );
		objOutBoxInfo.printLine( "F is the force between two " +
		   "bodies" );
		objOutBoxInfo.printLine( "k is the gravitational " +
		   "constant (6.67E-8 dyne cm^2 / gm^2)" );
		objOutBoxInfo.printLine( "M1 is the first body, M2 is " +
		   "second body" );
		objOutBoxInfo.printLine( "d is the distance between the " +
		   "two bodies" );
		objOutBoxInfo.printLine( "" );
		objOutBoxInfo.printLine( "You may use the notation " +
		   "#.###E-## to enter in large numbers." );
		objOutBoxInfo.printLine( "Decimals are allowed as " +
		   "any of the inputs." ); 	
		objOutBoxInfo.printLine( "" );

		//Ask the user for the mass of body number 1
		fltBody1Mass = objInBoxData.getFloat( "Enter the mass " +
		   "of body 1 in grams." );

		//Ask the user for the mass of body number 2
		fltBody2Mass = objInBoxData.getFloat( "Enter the mass " +
		   "of body 2 in grams." );

		//Ask the user for the distance between the two bodies
		fltDistance = objInBoxData.getFloat( "Enter the " +
		   "distance between the two bodies in centimeters." );

		//Calculate the force from the given data
		dblForce = GRAVCONST * ( (double) fltBody1Mass * fltBody2Mass
		   / Math.pow( fltDistance, 2.0 ) );

		//Repeat back what the user entered and display the result
		objOutBoxInfo.printLine( "Mass of body 1:  " +
		   fltBody1Mass );
		objOutBoxInfo.printLine( "Mass of body 2:  " +
		   fltBody2Mass );
		objOutBoxInfo.printLine( "Distance between body 1 and " +
		   "body 2:  " + fltDistance );
		objOutBoxInfo.printLine( "" );
		objOutBoxInfo.printLine( "The force between the two " +
		   "bodies is " + dblForce );

	}  //End of main method
}  //End of class NewtonsGravityLaw
