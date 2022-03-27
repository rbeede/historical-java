//=========================================================================
//	PersonalAddressBook.java
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
//	Description:		This class allows a user to create a
//				personal address book that can hold
//				entries, delete them, and search them
//
//	Public Methods:		PersonalAddressBook -- Class constructor
//
//				start -- 1. Call a method to fill an array
//				of AddressBook objects.
//				2. Call a method to get the user's choice
//				as to which address book they would like
//				to work on).
//				3. While the user wishes to continue, call
//				a method to process the selected address
//				book and work on it for as long as they
//				wish to continue.
//				4. Terminate the program when the user
//				desires to quit
//
//	Private Methods:        addEntry -- Adds a entry
//
//				deleteEntry -- Delete a entry
//
//				fillArray -- Creates address books
//
//				getMainMenuChoice -- Asks user for a
//				address book or to quit
//
//				getSubMenuChoice -- Prompt the user to
//				choose either to add a new entry, delete an
//				entry, search for an entry, or close the
//				current address book
//
//				processAddressBook -- Call a method to get
//				the user's choice to add, delete, search,
//				or close the address book
//
//				processEntries -- Figures out the users
//				choice of what to do with a entry
//
//				searchEntry -- Searches for a entry
//
//
//=========================================================================

import javabook.*;  //Load the javabook package
import AddressBook;  //Load the AddressBook class
import Person;  //Load the Person class

class PersonalAddressBook {
	private AddressBook objAddressBooks[];  //Array of address books
	//Address book user is working with (user selected this one)
	private AddressBook objCurrentAddressBook;
	private InputBox objInput;  //Used to get single data
	private ListBox objList;  //Used to display list of choices
	private Person objPerson;  //Person object
	private MainWindow objMainWin;  //Used for other controls
	private MessageBox objMsgBox;  //Used to display messages to user
	private MultiInputBox objMultiInput;  //Used to get multi data

	private	byte bytSubMenuChoice;  //Holds what to do with an address
					//book's entries

	
	/*
	  Method:	PersonalAddressBook

	  Purpose:	Instantiate objects

	  Parameters:	None

	  Returns:	None
	*/
	public PersonalAddressBook( ) {
		//Instantiate some needed objects for input and output
		objMainWin = new MainWindow( "CS1323 -- Project #6" );

		objInput = new InputBox( objMainWin );
		objMsgBox = new MessageBox( objMainWin );

		//objCurrentAddressBook is set in getMainMenuChoice
		//objList is created as needed in any methods
		//objMultiInput is created as needed in each method
		//objPerson is created as needed in any methods
	}


	/*
	  Method:	addEntry

	  Purpose:	1. Using a MultiInputBox object, prompt the user to
			enter their name, age, and gender.
			2. Instantiate a Person object with the user input.
			3. Add this person to the current address book
			using a public method from the AddressBook class.

	  Parameters:	None

	  Returns:	None
	*/
        private void addEntry( ) {
		String strData[];  //Used to hold returned data
		//Used in the MultiInputBox
		String strChoices[] = { "Name", "Age", "Gender" };

		//Used for making a new Person with the entry data
		Person objNewPerson;

		//Setup objMultiInput to have the proper labels
		objMultiInput = new MultiInputBox( objMainWin,
		   strChoices );

		strData = objMultiInput.getInputs( );  //Get data

		//Check to see if the user canceled
		if( objMultiInput.isCanceled( ) )
			return;  //User doesn't want to add, leave

		//Create a new Person object for holding the entered
		//data in the correct format
		objNewPerson = new Person( strData[0],
		   Integer.parseInt(strData[1]), strData[2].charAt(0) );

		//Add the new person to the address book
		objCurrentAddressBook.add( objNewPerson );
	}


        /*
	  Method:	deleteEntry

	  Purpose:	1. Prompt the user to enter the name they would
			like to delete.
			2. If found, delete the specified entry using a
			public method from the AddressBook class.  Use the
			same procedure shown in the testDelete( ) method
			that can be found in the program called
			TestAddressBook.java.

	  Parameters:	None

	  Returns:	None
	*/
	private void deleteEntry( ) {
		String strName;  //Holds name from user to delete
		boolean bolSuccess;  //Tells if delete worked or not		

		//Ask the user for the name
		strName = objInput.getString( "Enter the name to delete" );

		//Look for the name in the address book
		if (objCurrentAddressBook.search( strName ) == null) {
			//Name not found, tell user
			objMsgBox.show( "The name wasn't found." );

			//Nothing left to do, leave
			return;
		}

		bolSuccess = objCurrentAddressBook.delete( strName );

		//Check to see if the delete worked
		if( bolSuccess && (objCurrentAddressBook.search(strName) == null))
			objMsgBox.show( "Deletion worked" );
		else
			objMsgBox.show( "Deletion failed" );

	}


	/*
	  Method:	fillArray

	  Purpose:	1. Prompt the user for the number of Address Books
			they would like to create
			2. Fill the AddressBook array, instantiating as
			many AddressBook objects as the user has requested

	  Parameters:	None

	  Returns:	None
	*/
        private void fillArray( ) {
		int intNumBooks;  //Holds number of address books to create

		intNumBooks = objInput.getInteger( "Enter in the number " +
		   "of address books you wish to create" );

		//Instantiate the AddressBooks array and fill it in
		objAddressBooks = new AddressBook[intNumBooks];

		//Fill in the array with AddressBook objects
		for( int i = 0; i < intNumBooks; i++ )
			objAddressBooks[i] = new AddressBook();
	}


	/*
	  Method:	getMainMenuChoice

	  Purpose:	1. Prompt the user to choose an address book or to
			choose quit.
			2. If the user choose to quit, return false,
			otherwise return true.
			3. Set objCurrentAddressBook to the selected
			address book

	  Parameters:	None

	  Returns:	boolean -- returns if user quitted or not
	*/
	private boolean getMainMenuChoice( ) {
		//Create a ListBox object for input
		objList = new ListBox( objMainWin,
		   "Select an address book", true );

		byte bytSelection;  //Holds address book selection

		//Fill in the list box with the address books
		for( int i = 0; i < objAddressBooks.length; i++ )
		   objList.addItem( "Address Book #" + i );

		//Give the user a way to quit
		objList.addItem( "Quit" );

		//Loop until the user either cancels, quits, or selects
		//an address book
		do {
			//Ask the user for the address book
			bytSelection = (byte) objList.getSelectedIndex();

			//Check to see what the user did
			if( bytSelection == objList.CANCEL ) {
				//User cancelled
				return false;  //Send back 'quit' signal
			}
			else if( bytSelection == objList.NO_SELECTION ) {
				//User didn't select anything
				//Tell the user to select something
				objMsgBox.show( "You must select a " +
				   "option or cancel." );
			}
			else if( objList.getItemFromIndex(bytSelection)
			   == "Quit" ) {
				//User choose to quit
				return false;  //Send 'quit' signal back
			}
			else {
				//User selected a address book
				//Set the current address book
				objCurrentAddressBook =
				   objAddressBooks[bytSelection];
				return true;  //Tell program to not quit
			}
		} while( true );
	}


	/*
	  Method:	getSubMenuChoice

	  Purpose:	1. Prompt the user to choose either to add a new
			entry, delete an entry, search for an entry, or
			close the current address book.
			2. If the user choose to close the current address
			book, return false, otherwise return true

	  Parameters:	None

	  Returns:	boolean -- Returns if the user choose to close the
				   current address book
	*/
	private boolean getSubMenuChoice( ) {
		//Make a new list box for the menu choices
		objList = new ListBox( objMainWin, "Select a command",
		   true );

		//Add the choices to the listbox
		objList.addItem( "Add New Entry" );
		objList.addItem( "Delete an Entry" );
		objList.addItem( "Search for an Entry" );
		objList.addItem( "Close Current Address Book" );

		//Get the user's choice
		bytSubMenuChoice = (byte) objList.getSelectedIndex();

		//Figure out if the user selected anything
		switch( bytSubMenuChoice ) {
			case 3:		//User wants to close the book
				return false;
			case objList.CANCEL:	//User cancelled, close
				return false;
			case objList.NO_SELECTION:	//User did nothing
				return false;
			default:	//User picked something to do
				return true;
		}
	}


	/*
	  Method:	processAddressBook

	  Purpose:	1. Call a method to get the user's choice to add,
			delete, search,or close the address book.
			2. While the user wishes to continue, call a method
			to process the entries in the current address book
			and work on it for as long as they wish to continue.

	  Parameters:	AddressBook book -- reference to the current
			address book

	  Returns:	None
	*/
	private void processAddressBook(AddressBook book) {
		//Flag to see if current address book is to be closed or modified
		boolean bolModifyBook;

		//Loop until the user closes this address book
		do {
			//Call getSubMenuChoice to figure out what the user wants
			//to do with the current address book (the entries in it)
			bolModifyBook = getSubMenuChoice();

			//Check to see if the user wanted to close the book
			if( bolModifyBook == false ) {
				//User wants to close this book
				return;  //Leave this method
			}

			//Process the command the user wants to do with the book
			//This will either be add, delete, or search an entry
			processEntries();
		} while( bolModifyBook == true );
	}

	/*
	  Method:	processEntries

	  Purpose:	Depending on the user's choice, call one of these
			methods:	addEntry( )
					deleteEntry( )
					searchEntry( )
			The choice was made in getSubMenuChoice, we look
			at bytSubMenuChoice to figure out what was selected

	  Parameters:	None

	  Returns:	None
	*/
	private void processEntries( ) {
		switch( bytSubMenuChoice ) {
			case 0:			//Add an entry
				addEntry();
				break;
			case 1:			//Delete an entry
				deleteEntry();
				break;
			case 2:			//Search for an entry
				searchEntry();
				break;
			case 3:			//Close the book
				break;
			default:		//Not suppose to happen
				System.out.println( "Error in entry " +
				   "processing." );
				break;
		}
	}


	/*
	  Method:	searchEntry

	  Purpose:	1. Prompt the user to enter the name they would
			like to search.
			2. If found, notify the user of the success and
			display the name, age, and gender for this entry.
			3. If not found, display "not found" to the user.

	  Parameters:	None

	  Returns:	None
	*/
	private void searchEntry( ) {
		String strSearchName;  //Holds name of entry to search for

		//Used to show the found entry
		OutputBox objOutBox = new OutputBox( objMainWin,
		   "Person Found" );

		//Ask the user for a name
		strSearchName = objInput.getString(
		   "Enter in the name of the person." );

		//Check to see if name entered was blank
		if( strSearchName.equals( "" ) )
			return;  //Blank name, abort search

		//Look for the person
		objPerson = objCurrentAddressBook.search( strSearchName );

		//Check to see if the person was found
		if( objPerson != null ) {
			//Display the person's name, gender, & age
			objOutBox.print( "Name:  " );
			objOutBox.printLine( objPerson.getName() );
			objOutBox.print( "Gender:  " );
			objOutBox.printLine( objPerson.getGender() );
			objOutBox.print( "Age:  " );
			objOutBox.printLine( objPerson.getAge() );
			objOutBox.waitUntilClose();
		}
		else {  //No entry found
			objMsgBox.show( strSearchName +
			   " was not found." );
		}
	}


	/*
	  Method:	start

	  Purpose:	1. Call a method to fill an array of AddressBook
			objects.
			2. Call a method to get the user's choice as to
			which address book they would like to work on).
			3. While the user wishes to continue, call a
			method to process the selected address book and
			work on it for as long as they wish to continue.
			4. Terminate the program when the user desires to
			quit.

	  Parameters:	None

	  Returns:	None
	*/
	public void start( ) {
		//Fill in the AddressBooks array
		fillArray();

		//Loop until the user decides to stop processing any
		//address books
		while( getMainMenuChoice() ) {
			//Process the selected address book
			processAddressBook( objCurrentAddressBook );
		}

		//Once this point is reached, program is done
	}
}  //End of class PersonalAddressBook
