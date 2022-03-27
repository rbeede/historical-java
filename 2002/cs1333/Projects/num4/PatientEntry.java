//==============================================================================
//  PatientEntry.java -- Rodney Beede -- 10/31/00
//------------------------------------------------------------------------------
//
//  Class Description:  This class holds information on a Patient.  It holds
//	their ID, and a stack of their illness history (using the
//	IllnessHistoryEntry class).
//
//	It allows the user to pull up the entire Illness History of that patient
//
//	It also allows for addition of illness entries
//
//
//  Public Variables:  None
//
//
//  Public Methods:  String getPatientID  () - returns the patient's id
//
//		     void addIllnessEntry  ( String sIllID, String sDate,
//			String sDocID ) - adds a new illness to the patient's
//			history
//
//		     String showIllnessHistory  () - gives entire Illness History
//
//  Protected Variables:  None
//
//
//  Protected Methods:  None
//
//
//  Private Variables:  sID -- String ID of patient
//
//			oIllnesses -- Vector of IllnessHistoryEntries
//
//
//  Private Methods:  None
//
//
//==============================================================================


// Import IllnessHistoryEntry class for storing patient illnesses
import IllnessHistoryEntry;
// Import Vector class for storing multiple IllnessHistoryEntry objects
import structure.Vector;

public class PatientEntry {
	private String sID;  //Patient's ID
	private Vector oIllnesses;  //Patient's illnesses history


	//**********************************************************************
	// Method:  class constructor
	//
	// Preconditions:  Param: String sPatID -- Patient's ID
	//
	// Postconditions:  Sets the value of sID to the param sPatID
	//	Preps oIllnesses Vector for use
	//
	// Description:  See post condition
	//
	//**********************************************************************
	public PatientEntry( String sPatID ) {
		this.sID = sPatID;
		oIllnesses = new Vector();
	}


	//**********************************************************************
	// Method:  getPatientID
	//
	// Preconditions:  No params.  No pre-conds
	//
	// Postconditions:  Returns sID
	//
	// Description:  Returns the value of sID to the user
	//
	//**********************************************************************
	public String getPatientID() {
		return sID;
	}


	//**********************************************************************
	// Method:  addIllnessEntry
	//
	// Preconditions:  Params: String sIllID -- ID of illness
	//			   String sDate -- Date of visit
	//			   String sDocID -- Doctor ID
	//
	// Postconditions:  No returns.  Adds new entry in oIllnesses
	//
	// Description:  See Post Condition
	//
	//**********************************************************************
	public void addIllnessEntry( String sIllID, String sDate, String sDocID ) {
		//Create a new IllnessHistoryEntry
		IllnessHistoryEntry oNewEntry = new IllnessHistoryEntry( sIllID,
		   sDate, sDocID );

		//Add the new object to oIllnesses
		oIllnesses.addElement(oNewEntry);
	}


	//**********************************************************************
	// Method:  showIllnessHistory
	//
	// Preconditions:  Params: none.  oIllnesses has something in it
	//
	// Postconditions:  Returns string with a list of every illness id,
	//	date, and doctor id.  Each entry is on a new line
	//
	// Description:  See Post Condition
	//
	//*********************************************************************
	public String showIllnessHistory() {
		String sAll = "";  //String that holds complete history

		//If oIllnesses is empty, return nothing
		if( oIllnesses.size() <= 0 ) {
			return "";
		}

		//Build the entire illness history for this patient
		for( int j = 0; j < oIllnesses.size(); j++ ) {
			sAll += ((IllnessHistoryEntry) oIllnesses.elementAt(j)).getIllnessID()
			   + "\t" + ((IllnessHistoryEntry) oIllnesses.elementAt(j)).getDate()
			   + "\t\t" + ((IllnessHistoryEntry) oIllnesses.elementAt(j)).getDoctorID()
			   + "\n";
		}

		return sAll;
	}

}  //End of PatientEntry class