//==============================================================================
//  StringPair.java -- Rodney Beede -- 09/20/00
//------------------------------------------------------------------------------
//
//  Class Description:  This class contains two strings, one which represents
//	a keyword; the other which represents a value.  Together these make
//	a collective pair
//
//
//
//  Public Variables:  none
//
//
//  Public Methods:  StringPair  (keyword, value) - Class contructor, creates
//			a new keyword, value pair that is immutable
//
//		     getKeyword () - returns strKeyword
//		     getValue () - returns strValue
//
//
//  Protected Variables:  none
//
//
//  Protected Methods:  none
//
//
//  Private Variables:  String strKeyword - string containing the
//			 keyword for this pair
//
//		        String strValue - string containing the value
//			 for this pair
//
//
//  Private Methods:  none
//
//
//==============================================================================



public class StringPair {
	private String strKeyword;
	private String strValue;


	//**********************************************************************
	// Method:  class constructor
	//
	// Preconditions:  String keyword, String value -- keyword and it's
	//	value
	//
	// Postconditions:  None
	//
	// Description:  Sets the variables strKeyword and strValue with their
	//	respective values
	//
	//**********************************************************************
	public StringPair( String strKeyword, String strValue ) {
		//Set the values
		this.strKeyword = strKeyword;
		this.strValue = strValue;
	}


	//**********************************************************************
	// Method:  getKeyword
	//
	// Preconditions:  none
	//
	// Postconditions:  returns value of strKeyword
	//
	// Description:  Simply returns value of strKeyword
	//
	//**********************************************************************
	public String getKeyword( ) {
		return strKeyword;
	}


	//**********************************************************************
	// Method:  getValue
	//
	// Preconditions:  none
	//
	// Postconditions:  returns value of strValue
	//
	// Description:  Simply returns value of strValue
	//
	//**********************************************************************
	public String getValue( ) {
		return strValue;
	}
}  //End of StringPair