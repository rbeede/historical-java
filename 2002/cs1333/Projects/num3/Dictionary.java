//==============================================================================
//  Dictionary.java -- Rodney Beede -- 10/11/00
//------------------------------------------------------------------------------
//
//  Class Description:  This class loads a dictionary file passed to it and
//	allows access to all the keywords and definitions in that file
//	It also can add new keywords and definitions when one doesn't exist
//
//
//  Public Variables:  none
//
//
//  Public Methods:  findDef -- Returns the string of a definition for a key
//
//
//  Protected Variables:  none
//
//
//  Protected Methods:  none
//
//
//  Private Variables:  oDictEntry -- Vector of DictionaryEntry objects
//
//
//  Private Methods:  loadDictFile -- loads the dictionary file into memory
//			sortDict -- softs the dict into alphabetical order
//
//==============================================================================


import DictionaryEntry;  // Import the DictionaryEntry class
import java.io.*;  //Used in reading the dict file and saving info to it
import structure.Vector;  //Used in storing our DictionaryEntry objects

public class Dictionary {
	private Vector oDictEntry;  //Vector of DictionaryEntry objects

	//**********************************************************************
	// Method:  class constructor
	//
	// Preconditions:  String sFilename -- filename to the dict file
	//
	// Postconditions:  None
	//
	// Description:  Loads the given dict file into a Vector of
	//	DictionaryEntry objects
	//
	//**********************************************************************
	public Dictionary( String sFilename ) {
		//Prep our Vector
		oDictEntry = new Vector();

		//Call a method to open, parse, and store our dict file
		loadDictFile( sFilename );

		//Call a method to sort our dict file data
		sortDict();
	}


	//**********************************************************************
	// Method:  loadDictFile
	//
	// Preconditions:  sFileToLoad -- filename of dict file to open
	//
	// Postconditions:  Loads the file, parses and stores the entries
	//
	// Description:  Reads in the dictionary file and parses all the
	//	entries, storing them in oDictEntry
	//
	//**********************************************************************
	private void loadDictFile( String sFileToLoad ) {
/*  DUE TO MAJOR PROBLEMS AND TIME CONTRAINTS, THIS CODE BELOW IS NOT USED
	I WAS UNABLE TO CORRECTLY PARSE THE DICT FILE USING STREAMTOKENIZER OR
	STRINGTOKENIZER, AND HAD TO SETTLE FOR STATIC DATA IN MY VECTOR

		//Open up strFilename for reading
		FileReader objFileReader = new FileReader( sFileToLoad );
		BufferedReader objBuffReader = new BufferedReader(
		   objFileReader );

		//Create a StringTokenizer object to parse our file, using our
		//keyword and def seperators as tokens, and returning them too
		StreamTokenizer objFileParser = new StreamTokenizer(
			objBuffReader);

		//Setup our delimiter rules for the tokenizer
		objFileParser.resetSyntax();  //Reset all rules
		//Setup our words to include all chars including white space
		objFileParser.wordChars( 0, 126 );
		objFileParser.wordChars( '<', '<' );
		//Setup our main seperators
		objFileParser.whitespaceChars( ':', ':' );
		objFileParser.whitespaceChars( '>', '>' );


		//Allocate our oDictEntry vector for storage
		Vector oDictEntry = new Vector();

		//Setup some temp variables, used to hold parsed data
		String strCurrKey;
		String strCurrDef;

		//Loop through the file, parsing keys and defs as we go
		while( objFileParser.nextToken() != objFileParser.TT_EOF ) {


		//THIS section is old code from proj #2
		//objFileParser should have our first keyword token
			//loaded, so we hold it in strCurrKey
			strCurrKey = objFileParser.sval;

			//Have objFileParser read in the next token, which is
			//our value for this key
			objFileParser.nextToken();
			strCurrValue = objFileParser.sval;

			//Add our new pair to our vector, using the StringPair
			//class to hold our keyword and value
			vecKeyColorPairs.addElement( new StringPair( strCurrKey,
			   strCurrValue ) );


		}

		//Close the file since we're done with it
		objBuffReader.close();
==========================================================
END OF IN-COMPLETE CODE */


//========================================================
//BEGIN OF STATIC CODE
		DictionaryEntry oTempEntry;  //Holds a temp pointer to a dictionary entry


		//Fill in some static values
		oDictEntry.addElement( new DictionaryEntry("cs1333-student") );
		oTempEntry = (DictionaryEntry) oDictEntry.elementAt(oDictEntry.size()-1);
		oTempEntry.addDef("A person who hates parsing a stupid dictionary file");
		oDictEntry.addElement( new DictionaryEntry("tired") );
		oTempEntry = (DictionaryEntry) oDictEntry.elementAt(oDictEntry.size()-1);
		oTempEntry.addDef("The way a person feels after writing a program 6 hours before it is due");
		oDictEntry.addElement( new DictionaryEntry("low-grade") );
		oTempEntry = (DictionaryEntry) oDictEntry.elementAt(oDictEntry.size()-1);
		oTempEntry.addDef("represents this project");

		

	}  //End of loadDictFile method


	//**********************************************************************
	// Method:  findDef
	//
	// Preconditions:  sKeyToFind -- keyword use wants
	//
	// Postconditions:  String -- keyword and definition
	//
	// Description:  Searches for a keyword using a binary search
	//	If found it returns that keyword and definition
	//	If not found, it prompts the user to add a new definition
	//
	//**********************************************************************
	public String findDef( String sKeyToFind ) {
		//This method is an adaptation from the method on page 174

		DictionaryEntry midValue;
		int low=0;
		int high=oDictEntry.size();
		int mid=(low+high)/2;
		while( low < high ) {
			midValue = (DictionaryEntry)oDictEntry.elementAt(mid);
			if( midValue.getKeyword().compareTo(sKeyToFind) < 0) {
				low = mid+1;
			} else {
				high = mid;
			}
			mid = (low+high)/2;
		}

		return ((DictionaryEntry) oDictEntry.elementAt(low)).getDef(0);
	}


	//**********************************************************************
	// Method:  sortDict
	//
	// Preconditions:  None
	//
	// Postconditions:  Returns None
	//
	// Description:  Sorts the oDictEntry Vector based on keyword
	//
	//**********************************************************************
	private void sortDict() {
		//This method comes from an adaptation of the method on page 83 in the book

		int numSorted=1;
		int index;
		int n = oDictEntry.size();

		while( numSorted < n ) {
			String temp = ((DictionaryEntry) oDictEntry.elementAt(numSorted)).getKeyword();
			for( index = numSorted; index > 0; index--) {
				if( temp.compareTo( ( (DictionaryEntry) oDictEntry.elementAt(index-1)).getKeyword() ) < 0 ) {
					oDictEntry.setElementAt( oDictEntry.elementAt(index-1), index);
				}
				else {
					break;
				}
			}

			oDictEntry.setElementAt( new DictionaryEntry(temp),index);
			numSorted++;
		}
	}


}  //End of Dictionary