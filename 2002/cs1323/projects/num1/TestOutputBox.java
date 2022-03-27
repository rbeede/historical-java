/**************************************************************************

	Programmer:  		Rodney Beede
	ID:			0000
	Course:      		CS1323
	Lab Section:		#222
	Lab TA:		  	

	Program:		TestOutputBox
	Project#:    		Project #1
	Date Due:		Friday, February 4th

	Description: 		This Program uses an OutputBox object to
               			display the following phrase three times
				on three separate lines:  I Love Java

	Notes:			I declare variables with the first letter
				representing the variable type.
				(i.e. bMyData for a byte variable,
				dHerData for a double, etc...)
**************************************************************************/

import javabook.*;

class TestOutputBox
{
    public static void main (String args[])
    {
	//We will use a loop to print the phrase "I Love Java"
	//Declare a variable for keeping count of a loop
	byte bCountA;
	
	//Declare a constant to set how many times to loop
	//and print the phrase
	final byte bNumPhrase = 3;

	//Declare and initilize a MainWindow object with the title of
	//"CS 1323" in it
	MainWindow mainWin = new MainWindow( "CS 1323" );

	//Declare and initilize a OutputBox object and pass mainWin as
	//the parent (frame) control
	OutputBox outboxMsgBox = new OutputBox( mainWin );

	//Display both objects        	
	mainWin.show();      
	outboxMsgBox.show();

	//Display the following phrase three times, on three separate
	//lines:  "I Love Java"

	//Do this by using a loop to repeat the 1 needed instruction
	//The loop will start at 1 print and go until the maximum number
	//of prints have been done (determined from bNumPhrase)

	for( bCountA = 1; bCountA <= bNumPhrase; bCountA++ )
		outboxMsgBox.printLine( "I Love Java" );

    }  //End of main method

}  //End of TestOutputBox class
