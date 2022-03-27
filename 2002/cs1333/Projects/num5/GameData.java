//==============================================================================
//  GameData.java -- Rodney Beede -- 11/13/00
//------------------------------------------------------------------------------
//
//  Class Description:  This class holds game data for the guesser program.  It
//	may contain a question or an answer for the game.  This data is held
//	in a tree node during the game.
//
//
//  Public Variables:  static final byte DTQUESTION - This is a constant that
//	is used to represent a "question" gamedata type
//
//		       static final byte DTANSWER - This is a constant that is
//	used to represent a "answer" gamedata type
//
//
//  Public Methods:  byte getType  () - Returns the data type, either DTQUESTION
//	or DTANSWER
//
//		     String getValue  () - Returns the data value, either a
//	a question or an answer
//
//
//  Protected Variables:  None
//
//
//  Protected Methods:  None
//
//
//  Private Variables:  byte dDataType - Holds value of the gamedata type
//
//			String sDateValue - Holds the gamedata value.  This is
//	either a question or an animal (a.k.a. an answer)
//
//
//  Private Methods:  None
//
//
//==============================================================================


public class GameData {
	//Some public constants for the data type
	public static final byte DTQUESTION = 1;
	public static final byte DTANSWER = 2;

	//Some private variables for holding game data
	private byte bDataType;
	private String sDataValue;


	//**********************************************************************
	// Method:  class constructor
	//
	// Preconditions:  bType -- Data type, either DTQUESTION for a question
	//	or DTANSWER for an answer
	//
	//		   sValue -- Value of gamedata.  Should contain a string
	//	with either a question or an animal name for an answer
	//
	// Postconditions:  Value of bDataType and sDataValue are set to passed
	//	paramaters.
	//
	// Description:  See Postcondition
	//
	//**********************************************************************
	public GameData( byte bType, String sValue ) {
		this.bDataType = bType;
		this.sDataValue = sValue;
	}


	//**********************************************************************
	// Method:  getType
	//
	// Preconditions:  No params.
	//
	// Postconditions:  Returns value in bDataType
	//
	//**********************************************************************
	public byte getType() {
		return bDataType;
	}


	//**********************************************************************
	// Method:  getValue
	//
	// Preconditions:  None
	//
	// Postconditions:  Returns value in sDataValue
	//
	//**********************************************************************
	public String getValue() {
		return sDataValue;
	}

}  //End of GameData