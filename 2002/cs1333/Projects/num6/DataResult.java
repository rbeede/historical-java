//==============================================================================
//  DataResult.java -- Rodney Beede -- 12/04/00
//------------------------------------------------------------------------------
//
//  Class Description:  This class simply holds and returns test result data
//
//
//  Public Variables:  None
//
//
//  Public Methods:  String getTestName  () - returns tree test type name
//
//		     String getPassNum  () - returns the test pass number
//
//                   long getStartTime  () - returns the start time
//
//                   long getEndTime  () - returns the end time
//
//                   long getTotTime  () - returns the total test time
//
//
//  Protected Variables:  None
//
//
//  Protected Methods:  None
//
//
//  Private Variables:  sTestName - tree test type name
//
//                      iPassNum - test pass number
//
//                      lStartTime - test start time
//
//                      lEndTime - test end time
//
//                      lTotTime - total test time
//
//
//  Private Methods:  None
//
//
//==============================================================================


public class DataResult {
	//Private vars for holding data
	private String sTestName;  //Tree test type
	private int iPassNum;  //Pass number of test
	private long lStartTime;  //Start time of test
	private long lEndTime;  //End time of test
	private long lTotTime;  //Total time of test


	//**********************************************************************
	// Method:  class constructor
	//
	// Preconditions:  sTestName - tree test type name
	//
	//		   iPassNum - current pass number of the test
	//
	//		   lStartTime - start time of test
	//
	//		   lEndTime - end time of test
	//
	//		   lTotTime - total time of test
	//
	// Postconditions:  Sets values of correct private class vars to
	//	passed params
	//
	//**********************************************************************
	public DataResult( String sTestName, int iPassNum, long lStartTime,
	   long lEndTime, long lTotTime) {
		//Set this classes vars to passed params
		this.sTestName = sTestName;
		this.iPassNum = iPassNum;
		this.lStartTime = lStartTime;
		this.lEndTime = lEndTime;
		this.lTotTime = lTotTime;
	}


	//**********************************************************************
	// Method:  getTestName
	//
	// Preconditions:  None
	//
	// Postconditions:  Returns value of class var sTestName
	//
	//**********************************************************************
	public String getTestName() {
		return sTestName;
	}


	//**********************************************************************
	// Method:  getPassNum
	//
	// Preconditions:  None
	//
	// Postconditions:  Returns value of class var iPassNum
	//
	//**********************************************************************
	public int getPassNum() {
		return iPassNum;
	}


	//**********************************************************************
	// Method:  getStartTime
	//
	// Preconditions:  None
	//
	// Postconditions:  Returns value of class var lStartTime
	//
	//**********************************************************************
	public long getStartTime() {
		return lStartTime;
	}


	//**********************************************************************
	// Method:  getEndTime
	//
	// Preconditions:  None
	//
	// Postconditions:  Returns value of class var lEndTime
	//
	//**********************************************************************
	public long getEndTime() {
		return lEndTime;
	}


	//**********************************************************************
	// Method:  getTotTime
	//
	// Preconditions:  None
	//
	// Postconditions:  Returns value of class var lTotTime
	//
	//**********************************************************************
	public long getTotTime() {
		return lTotTime;
	}

}  //End of DataResult