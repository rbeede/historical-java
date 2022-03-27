//==============================================================================
//  IllnessHistoryEntry.java -- Rodney Beede -- 10/31/00
//------------------------------------------------------------------------------
//
//  Class Description:  This class simply holds data on a patient.  That data is
//	illness id, date of diagnosis, & doctor id
//
//
//
//  Public Variables:  None
//
//
//  Public Methods:  String getIllnessID  () - Returns sIllID
//
//		     String getDate  () - Returns sDate
//
//		     String getDoctorID  () - Returns sDocID
//
//
//  Protected Variables:  None
//
//
//  Protected Methods:  None
//
//
//  Private Variables:  sIllID -- Illness ID
//			sDate -- Date of diagnosis
//			sDocID -- Doctor ID
//
//  Private Methods:  None
//
//
//==============================================================================


public class IllnessHistoryEntry {
	//Some global class variables for holding patient visit info
	private String sIllID;  //Illness ID number
	private String sDate;  //Date of diagnosis
	private String sDocID;  //Doctor's ID


	//**********************************************************************
	// Method:  class constructor
	//
	// Preconditions:  String sIllID -- ID of illness patient has
	//
	//		   String sDate -- Date of diagnosis
	//
	//		   String sDocID -- Diagnosising Doctor's ID
	//
	// Postconditions:  Sets value of sIllID, sDate, & sDocID to passed
	//	values
	//
	// Description:  Simply sets values for the entry to use.
	//
	//**********************************************************************
	public IllnessHistoryEntry( String sIllID, String sDate, String sDocID ) {
		this.sIllID = sIllID;
		this.sDate = sDate;
		this.sDocID = sDocID;
	}


	//**********************************************************************
	// Method:  getIllnessID
	//
	// Preconditions:  No params.  No pre-conds
	//
	// Postconditions:  Returns value of sIllID
	//
	// Description:  See post condition
	//
	//**********************************************************************
	public String getIllnessID() {
		return sIllID;
	}


	//**********************************************************************
	// Method:  getDate
	//
	// Preconditions:  No params.  No pre-conds
	//
	// Postconditions:  Returns value of sDate
	//
	// Description:  See post condition
	//
	//**********************************************************************
	public String getDate() {
		return sDate;
	}


	//**********************************************************************
	// Method:  getDoctorID
	//
	// Preconditions:  No params.  No pre-conds
	//
	// Postconditions:  Returns value of sDocID
	//
	// Description:  See post condition
	//
	//**********************************************************************
	public String getDoctorID() {
		return sDocID;
	}

}  //End of IllnessHistoryEntry class