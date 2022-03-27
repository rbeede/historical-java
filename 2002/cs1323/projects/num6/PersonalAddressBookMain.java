//=========================================================================
//	PersonalAddressBookMain.java
//=========================================================================
//	Programmer:		Rodney Beede
//	ID:			0000
//	Course:			CS1323
//	Lab Section:		222
//	Lab TA:			Lalitha
//
//	Project#:		6
//	Due Date:		March 31st, 2000
//
//	Description:		This class declare and instantiate a
//				PersonalAddressBook object and call a
//				public void start( ) method
//
//	Inputs:			String[] args -- Not used
//
//	Outputs:		N/A
//
//	Public Methods:		main
//
//	Private Methods:	None
//
//=========================================================================

import PersonalAddressBook;  //Load the PersonalAddressBook class

class PersonalAddressBookMain {
	public static void main( String[] args ) {
		//Create a PersonalAddressBook object
		PersonalAddressBook objPAB = new PersonalAddressBook();

		//Get the program rolling by calling PersonalAddressBook's
		//start() method
		objPAB.start();		

		//Make sure program ends
		System.exit(0);
	}  //End of main method
}  //End of class PersonalAddressBookMain