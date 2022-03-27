//==============================================================================
//  DictionaryEntry.java -- Rodney Beede -- 10/11/00
//------------------------------------------------------------------------------
//
//  Class Description:  This class holds a keyword and the definitions for a
//	dictionary entry.  It also has methods to add new definitions to a
//	single entry
//
//
//  Public Variables:  None
//
//
//  Public Methods:	void  addDef  (String sNewDef) -- Adds a new definition
//	to the entry
//
//			String getKeyword  () -- Returns the keyword for the
//	current entry
//
//			String getDef  (int iDefNum) -- Returns the requested
//	definition, if iDefNum <= 0, all definitions are returned each seperated
//	by new line characters
//
//  Protected Variables:  None
//
//
//  Protected Methods:  None
//
//
//  Private Variables:  sKeyword -- holds the definition entries keyword
//			oDefinitions -- holds all of our definitions
//
//
//  Private Methods:  None
//
//
//==============================================================================


// Import the Vector class
import structure.Vector;

public class DictionaryEntry {
	//These hold our entry data internally
	private String sKeyword;
	private Vector oDefinitions;


	//**********************************************************************
	// Method:  class constructor
	//
	// Preconditions:  String sKeyword -- The keyword for this entry
	//
	// Postconditions:  None
	//
	// Description:  Simply creates a new entry, and preps our Vector of
	//	entry definitions
	//
	//**********************************************************************
	public DictionaryEntry( String sKeyword ) {
		//Set our passed keyword to our private, interal sKeyword
		this.sKeyword = sKeyword;
		//Prep our oDefintions Vector for use
		oDefinitions = new Vector(1);
	}


	//**********************************************************************
	// Method:  addDef
	//
	// Preconditions:  String sNewDef -- new definition to add
	//
	// Postconditions:  Adds definition to entry
	//
	// Description:  Simply adds a definition to this entry in memory
	//	Does not save the definition to the disk
	//
	//**********************************************************************
	public void addDef( String sNewDef) {
		//Simply add the definition to our oDefinitions Vector
		oDefinitions.addElement( sNewDef );
	}


	//**********************************************************************
	// Method:  getKeyword
	//
	// Preconditions:  None
	//
	// Postconditions:  Returns the keyword for the entry
	//
	// Description:  Returns sKeyword back as a String
	//
	//**********************************************************************
	public String getKeyword() {
		return this.sKeyword;  //Return the keyword
	}


	//**********************************************************************
	// Method:  getDef
	//
	// Preconditions:  iDefNum -- Number of the definition to return
	//	If this is -1, then return all of them
	//	If the number is invalid, send back "Invalid definition number"
	//
	// Postconditions:  The definition entry in the oDefinitions Vector
	//	that was selected from iDefNum
	//
	// Description:  Returns a definition entry stored in the oDefinitions
	//	Vector that is at the position  iDefNum - 1
	//	If iDefNum <= 0, then return all the entries with \n chars
	//
	//**********************************************************************
	public String getDef( int iDefNum ) {
		String sReturnDef = "";  //Holds our definition we are returning

		//Verify that iDefNum exists in oDefinitions
		if( (iDefNum >= oDefinitions.size()) ||
		   (oDefinitions.isEmpty()) ){
			return "Invalid definition number, or keyword has no " +
			   " definition entries.";
		}

		//Check to see if we are passing back every entry
		if( iDefNum < 1 ) {
			//We need to pass back every entry, loop through
			//oDefinitions adding each definition to sReturnDef and
			//appending a \n at the end
			for(int iCnt = 0; iCnt < oDefinitions.size(); iCnt++){
				//Grab the current def from Vector and convert
				//it to a String
				sReturnDef += (String)
				   oDefinitions.elementAt(iCnt);
				//Append a \n to the end of the current def list
				sReturnDef += "\n";
			}
		}
		else {		//We are simply returning 1 definition
			//Set our sReturnDef value to the correct definition
			//from oDefinitions
			sReturnDef = (String) oDefinitions.elementAt(iDefNum-1);
		}  //End of if( iDefNum < 1 )
		

		return sReturnDef;  //Return the definition(s)
	}  //End of getDef method

}  //End of DictionaryEntry