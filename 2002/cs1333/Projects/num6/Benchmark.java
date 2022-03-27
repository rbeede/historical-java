//==============================================================================
//  Benchmark.java -- Rodney Beede -- 12/04/00
//------------------------------------------------------------------------------
//
//  Class Description:  This class times how long a search takes with a
//	particular tree and records that time
//
//
//  Public Variables:  None
//
//
//  Public Methods:  void runTest  ( String sTestName, BinarySearchTree oTree,
//	long lSearchValue, int iPassNum)  -- runs a test on the passed tree
//
//                   void calculateAverage  () -- calculates the average run
//	time for each test
//
//                   void printResults  () -- prints out results
//
//
//  Protected Variables:  None
//
//
//  Protected Methods:  None
//
//
//  Private Variables:  DataResult oResults[][] -- holds results from tests
//
//			int iNumPasses -- number of passes to make
//
//			double dRanBSTAvg -- avg of random BST test
//
//                      double dSortBSTAvg -- avg of sorted BST test
//
//                      double dRanSTAvg -- avg of random ST test
//
//                      double dSortSTAvg -- avg of sorted ST test
//
//
//  Private Methods:  long arrayAverage  (DataResult oArray[]) -- calculates
//	average value of time from array of passes for a test (oArray)
//
//
//==============================================================================


//Import needed classes
import DataResult;
import structure.BinarySearchTree;


public class Benchmark {
	private DataResult oResults[][];  //Array of results

	private int iNumPasses;  //Number of passes that are being made
	//Variables for overall average search time for each tree
	private double dRanBSTAvg;  //random binary search tree
	private double dSortBSTAvg;  //sorted binary search tree
	private double dRanSTAvg;  //random splay tree
	private double dSortSTAvg;  //sorted splay tree


	//**********************************************************************
	// Method:  class constructor
	//
	// Preconditions:  int iNumPasses - number of passes that are going to
	//	be made.  This allows us to store results correctly
	//
	// Postconditions:  Sets value of iNumPasses class variable.  Setups
	//	an array of DataResults so we can store test results
	//
	//**********************************************************************
	public Benchmark( int iNumPasses ) {
		this.iNumPasses = iNumPasses;

		//Initialize oResults as a 4 x iNumPasses
		oResults = new DataResult[4][iNumPasses];
	}


	//**********************************************************************
	// Method:  runTest
	//
	// Preconditions:  sTestName - String value of test name
	//
	//		   oTree - tree to search, BST or Splay
	//
	//		   lSearchValue - number to search for
	//
	//		   iPassNum - number of current pass on tests
	//
	// Postconditions:  Runs tests on oTree and saves result to oResults
	//	In the event the value wasn't found, the total search time is
	//	made a negative value.  We expect to always find the value we
	//	are searching for.  Time is calculated in milliseconds
	//
	//**********************************************************************
	public void runTest( String sTestName, BinarySearchTree oTree,
	   long lSearchValue, int iPassNum ) {
		//Create some variables for holding the start, end,
		//and total time
		long lStartTime;
		long lEndTime;
		long lTotTime;

		boolean bolFoundValue;  //Indicates if value was ever found
		byte bytTestPos = 0;  //Position in oResults to save this test in

		
		//Grab the current time (starting time)
		lStartTime = System.currentTimeMillis();

		//Search for lSearchValue, recording if we found it
		bolFoundValue = oTree.contains( new Long(lSearchValue) );

		//Grab the end time
		lEndTime = System.currentTimeMillis();

		//Calculate the total time
		lTotTime = lEndTime - lStartTime;

		//If the value was never actually found, our total time
		//is made a negative value
		if( bolFoundValue == false ) {
			lTotTime = lTotTime * -1;
		}


		//Figure out which tree test this is, so we can save it in
		//the correct spot in oResults
		if( sTestName.equals("BST Random") ) {
			bytTestPos = 0;
		}
		else if( sTestName.equals("BST Sorted") ) {
			bytTestPos = 1;
		}
		else if( sTestName.equals("ST Random") ) {
			bytTestPos = 2;
		}
		else if( sTestName.equals("ST Sorted") ) {
			bytTestPos = 3;
		}


		//Save our result to oResults
		oResults[bytTestPos][iPassNum] = new DataResult(sTestName,
		   iPassNum, lStartTime, lEndTime, lTotTime);
	}  //End of runTest method


	//**********************************************************************
	// Method:  calculateAverage
	//
	// Preconditions:  None
	//
	// Postconditions:  Calculates the average value of all the passes for
	//	each test and stores those in some private class variables
	//
	//**********************************************************************
	public void calculateAverage() {
		dRanBSTAvg = arrayAverage( oResults[0] );
		dSortBSTAvg = arrayAverage( oResults[1] );		
		dRanSTAvg = arrayAverage( oResults[2] );
		dSortSTAvg = arrayAverage( oResults[3] );
	}


	//**********************************************************************
	// Method:  arrayAverage
	//
	// Preconditions:  oArray - array to average, accessing DataResult
	//	objects inside the array
	//
	// Postconditions:  Calculates average value of all the numbers in a
	//	one-diminsional array
	//
	//**********************************************************************
	private double arrayAverage( DataResult oArray[]) {
		double dTemp = 0;  //Holds current calculation


		//Loop through all the passes in this test
		for( int j = 0; j < oArray.length; j++ ) {
			dTemp += (double) oArray[j].getTotTime();
		}

		return (double) (dTemp / oArray.length);
	}


	//**********************************************************************
	// Method:  printResults
	//
	// Preconditions:  None
	//
	// Postconditions:  Outputs the results in csv (comma seperated values)
	//	form to the screen.
	//
	//**********************************************************************
	public void printResults() {
		String sOutMsg;  //Holds string to printout


		//Call method to calculate the results
		calculateAverage();

		sOutMsg = "\n\nResults:\n";
		sOutMsg += "==========\n\n";
	
		//Header line, useful for columns in Excel
		sOutMsg += "Tree Type,Pass Number,Start Time,End Time,"
		   + "Total Time\n";

		//Loop through our results, printing them out
		for( int j=0; j < oResults.length; j++ ) {  //Tests
			for( int k=0; k < oResults[j].length; k++ ) {  //Passes
				sOutMsg += oResults[j][k].getTestName() + ",";
				sOutMsg += oResults[j][k].getPassNum() + ",";
				sOutMsg += oResults[j][k].getStartTime() + ",";
				sOutMsg += oResults[j][k].getEndTime() + ",";
				sOutMsg += oResults[j][k].getTotTime();
				sOutMsg += "\n";  //Start new line
			}  //End of pass loop
		}  //End of test loop

		sOutMsg += "\n\n";  //Blank lines

		//Printout the overall average for each tree test
		sOutMsg += "Tree Type,Average Search Time\n";
		sOutMsg += "BST Random," + dRanBSTAvg + "\n";
		sOutMsg += "BST Sorted," + dSortBSTAvg + "\n";
		sOutMsg += "ST Random," + dRanSTAvg + "\n";
		sOutMsg += "ST Sorted," + dSortSTAvg + "\n";
		sOutMsg += "\n\n\n";  //Blank lines


		//Now show the results
		System.out.println( sOutMsg );
	}

}  //End of Benchmark