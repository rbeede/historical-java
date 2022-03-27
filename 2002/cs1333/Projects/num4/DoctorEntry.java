//==============================================================================
//  DoctorEntry.java -- Rodney Beede -- 10/31/00
//------------------------------------------------------------------------------
//
//  Class Description:  This class holds information on a Doctor.  It holds
//	their ID, and a stack of their patient visit history (using the
//	VisitHistoryEntry class).
//
//	It allows the user to pull up the entire Patient History of that doctor
//	and see if a patient has been visited by that doctor before
//
//	It also allows for addition of history entries to the doctor
//
//	It is also possible to remove a entry and push it to the top
//
//
//  Public Variables:  None
//
//
//  Public Methods:  String getDoctorID  () - returns the doctor's id
//
//		     void addVisitEntry  ( String sPatID, String sDate ) - adds
//			a new patient visit to the doctor's patient history
//
//		     int findVisit  ( String sPatID ) - finds a patient in the
//			visit history
//
//		     void removeAndPush  ( int iPatientEntryIndex,
//			String sNewDate ) - places a patient entry at the top of
//			the patient visits stack
//
//		     showVisitHistory  () - returns the entire oVisits data
//
//  Protected Variables:  None
//
//
//  Protected Methods:  None
//
//
//  Private Variables:  sID -- String ID of doctor
//
//			oVisits -- Vector of patient visits
//
//
//  Private Methods:  None
//
//
//==============================================================================


// Import VisitHistoryEntry class for storing patient history
import VisitHistoryEntry;
// Import Vector class for storing multiple VisitHistoryEntry objects
import structure.Vector;

public class DoctorEntry {
	private String sID;  //Doctor's ID
	private Vector oVisits;  //Patient visit's history


	//**********************************************************************
	// Method:  class constructor
	//
	// Preconditions:  Param:  String sDocID -- Doctor's ID
	//
	// Postconditions:  Sets the value of sID to the param sDocID
	//	Preps oVisits Vector for use
	//
	// Description:  Sets the value of sID to the param sDocID
	//
	//**********************************************************************
	public DoctorEntry( String sDocID ) {
		this.sID = sDocID;
		oVisits = new Vector();
	}


	//**********************************************************************
	// Method:  getDoctorID
	//
	// Preconditions:  No params.  No pre-conds
	//
	// Postconditions:  Returns sID
	//
	// Description:  Returns the value of sID to the user
	//
	//**********************************************************************
	public String getDoctorID() {
		return sID;
	}


	//**********************************************************************
	// Method:  addVisitEntry
	//
	// Preconditions:  Params: String sPatID -- ID of patient visited
	//			   String sDate -- Date of visit
	//
	// Postconditions:  No returns.  Adds new entry in oVisits
	//
	// Description:  Adds a new VisitHistoryEntry into the oVisits vector
	//
	//**********************************************************************
	public void addVisitEntry( String sPatID, String sDate ) {
		//Create a new VisitHistoryEntry
		VisitHistoryEntry oNewEntry = new VisitHistoryEntry( sPatID,
		   sDate );

		//Add the new object to oVisits
		oVisits.addElement(oNewEntry);
	}


	//**********************************************************************
	// Method:  findVisit
	//
	// Preconditions:  Param: String sPatID -- ID of patient to look for
	//	If oVisits.size() <=0 we have now visit history, so we sure
	//	aren't going to find anything.  We return -1.
	//
	// Postconditions:  Returns the element index of a patient if found,
	//	otherwise it returns -1 if patient wasn't found
	//
	// Description:  Searches through oVisits, looking for a matching
	//	patient ID.  If found, it returns the patient ID so it can
	//	be used later on to access that entry
	//
	//	If not found, it simply returns a flag (-1) to indicate so
	//
	//**********************************************************************
	public int findVisit( String sPatID ) {
		//Check to see if oVisits has any entries
		if( oVisits.size() <= 0 ) {
			return -1;  //No entry to find, return -1 flag
		}

		//Loop through the entire oVisits vector looking for a match
		//We start at the end and work to the beginning, because the
		//latest dated entries should be at the end of the vector
		for( int j = oVisits.size()-1; j >= 0; j-- ) {
			if( ((VisitHistoryEntry)
			   oVisits.elementAt(j)).getPatientID() == sPatID ) {
				//Found a match, return it
				return j;
			}
		}

		//Never found a match, return -1 flag
		return -1;
	}


	//**********************************************************************
	// Method:  removeAndPush
	//
	// Preconditions:  int iPatientEntryIndex -- Index in oVisits of the
	//	patient to remove and push to the top
	//
	//		   String sNewDate -- Date to set visit entry to
	//
	//	iPatientEntryIndex needs to fall into the size of oVisits
	//
	// Postconditions:  Returns: none.  See Description for what this does
	//
	// Description:  Takes the element at iPatientEntryIndex in the oVisits
	//	vector and removes it from it's current position and to the end
	//	of the vector, which is considered the top of the Stack.  This
	//	index can be obtained for a particular patient via the findVisit
	//	method.  It also resets the date to sNewDate to reflect the new
	//	visit date
	//
	//**********************************************************************
	public void removeAndPush( int iPatientEntryIndex, String sNewDate ) {
		//Create a temp VisitHistory Entry for holding the entry to move
		VisitHistoryEntry oMovingEntry = (VisitHistoryEntry)
		   oVisits.elementAt(iPatientEntryIndex);

		//Remove the element from it's current position
		oVisits.removeElementAt(iPatientEntryIndex);

		//Modify the date of our temp VisitHistoryEntry
		oMovingEntry.setLastVisitDate( sNewDate );

		//Place it on the end of vector (which is top of stack)
		oVisits.addElement( oMovingEntry );
	}


	//**********************************************************************
	// Method:  showVisitHistory
	//
	// Preconditions:  Params: none.  oVisits has something in it
	//
	// Postconditions:  Returns string with a list of every patient id &
	//	date.  Each entry is on a new line
	//
	// Description:  See Post Condition
	//
	//*********************************************************************
	public String showVisitHistory() {
		String sAll = "";  //String that holds complete history

		//If oVisits is empty, return nothing
		if( oVisits.size() <= 0 ) {
			return "";
		}

		//Build the entire visits history for this doctor
		for( int j = 0; j < oVisits.size(); j++ ) {
			sAll += ((VisitHistoryEntry) oVisits.elementAt(j)).getPatientID()
			   + "\t" + ((VisitHistoryEntry) oVisits.elementAt(j)).getLastVisitDate()
			   + "\n";
		}

		return sAll;
	}

}  //End of DoctorEntry class