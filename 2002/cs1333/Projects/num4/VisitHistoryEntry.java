//==============================================================================
//  VisitHistoryEntry.java -- Rodney Beede -- 10/31/00
//------------------------------------------------------------------------------
//
//  Class Description:  This class simply holds data for a doctor on what
//	patients have seen that doctor and the last visit with that doctor
//
//
//
//  Public Variables:  None
//
//
//  Public Methods:  String getPatientID  () - Returns sPatID
//
//		     String getLastVisitDate  () - Returns sLastVisDate
//
//		     void setLastVisitDate  (String sDate) - Sets sLastVisDate
//			to sDate
//
//
//  Protected Variables:  None
//
//
//  Protected Methods:  None
//
//
//  Private Variables:  sPatID -- Patient ID
//			sLastVisDate -- Date of last visit by patient
//
//
//  Private Methods:  None
//
//
//==============================================================================


public class VisitHistoryEntry {
	//Some global class variables for holding patient visit info
	private String sPatID;  //Patient ID number
	private String sLastVisDate;  //Last date of visit


	//**********************************************************************
	// Method:  class constructor
	//
	// Preconditions:  String sPatientID -- ID of patient that visited doc
	//
	//		   String sDate -- Date of last visit
	//
	// Postconditions:  Sets value of sPatID and sLastVisitDate to passed
	//	values
	//
	// Description:  Simply sets sPatID and sLastVisitDate for the new
	//	object to use.
	//
	//**********************************************************************
	public VisitHistoryEntry( String sPatientID, String sDate ) {
		this.sPatID = sPatientID;
		this.sLastVisDate = sDate;
	}


	//**********************************************************************
	// Method:  getPatientID
	//
	// Preconditions:  No params.  No pre-conds
	//
	// Postconditions:  Returns value of sPatID
	//
	// Description:  Returns String value of sPatID
	//
	//**********************************************************************
	public String getPatientID() {
		return sPatID;
	}


	//**********************************************************************
	// Method:  getLastVisitDate
	//
	// Preconditions:  No params.  No pre-conds
	//
	// Postconditions:  Returns value of sLastVisDate
	//
	// Description:  Returns String value of sLastVisDate
	//
	//**********************************************************************
	public String getLastVisitDate() {
		return sLastVisDate;
	}


	//**********************************************************************
	// Method:  setLastVisitDate
	//
	// Preconditions:  Param: String sDate -- Date of visit
	//
	// Postconditions:  No returns.  Updates sLastVisDate to sDate param
	//
	// Description:  Updates sLastVisDate to value of sDate param
	//
	//**********************************************************************
	public void setLastVisitDate( String sDate ) {
		sLastVisDate = sDate;
	}

}  //End of VisitHistoryEntry class