//==============================================================================
//  Engine.java -- Rodney Beede -- 12/03/00
//------------------------------------------------------------------------------
//
//  Class Description:  This class controls all of the benchmarking of the
//	search trees.  It creates the trees, times them, and then returns the
//	results to the user.
//
//
//  Public Variables:  none
//
//
//  Public Methods:  void startTests  () - begins testing of how fast a search
//	is done with BST and ST
//
//
//  Protected Variables:  none
//
//
//  Protected Methods:  none
//
//
//  Private Variables:  long lAmountData - amount of data to be in trees
//
//			int iNumTests - number of passes for each test
//
//			BinarySearchTree oRandomBST - binary search tree with
//			   random data filled into it
//
//			BinarySearchTree oSortedBST - binary search tree with
//			   sorted data filled into it
//
//			SplayTree oRandomST - splay tree with random data filled
//			   into it
//
//			SplayTree oSortedST - splay tree with sorted data filled
//			   into it
//
//
//  Private Methods:  void fillInTrees  () - has trees filled in with data
//
//		      void testTrees  (int iPassNo) - has trees searched and
//		         timed using Benchmark class
//
//
//==============================================================================


//Import needed classes
import structure.BinarySearchTree;
import structure.SplayTree;
import Benchmark;
import DataGenerator;


public class Engine {
	private long lAmountData;  //Holds amount of data to be placed in trees
	private int iNumTests;  //Holds number of tests to be performed

	private BinarySearchTree oRandomBST;  //BST with random data
	private BinarySearchTree oSortedBST;  //BST with sorted data
	private SplayTree oRandomST;  //ST with random data
	private SplayTree oSortedST;  //ST with random data

	private Benchmark oBenchmarker;  //Does benchmarking and has results


	//**********************************************************************
	// Method:  class constructor
	//
	// Preconditions:  lAmountData -- amount of data to place in tree
	//
	//		   lNumTests -- number of tests to perform
	//
	// Postconditions:  Sets the values from the passed params of
	//	lAmountData & iNumTests in this class
	//
	//**********************************************************************
	public Engine( long lAmountData, int iNumTests ) {
		//Save our passed params
		this.lAmountData = lAmountData;
		this.iNumTests = iNumTests;
	}


	//**********************************************************************
	// Method:  startTests
	//
	// Preconditions:  None
	//
	// Postconditions:  Puts data into the trees and benchmarks the time it
	//	takes for each one to search for some random value
	//
	//**********************************************************************
	public void startTests() {
		//Initialize oBenchmarker
		oBenchmarker = new Benchmark(iNumTests);


		//Start a for loop for the number of times to run each test
		for( int j = 0; j < iNumTests; j++ ) {
			//Tell user what pass test is on
			System.out.println( "\n\nBeginning test #" + (j+1) );
			System.out.println( "==================" );
			System.out.println( "" );

			//Call method to fill in trees with data
			fillInTrees();

			//Call method that will have the trees tested
			testTrees(j);
		}

		//Have oBenchmarker print out all the results
		oBenchmarker.printResults();
	}


	//**********************************************************************
	// Method:  fillInTrees
	//
	// Preconditions:  None
	//
	// Postconditions:  Inserts data into our trees notifying the user as
	//	we start filling in each tree
	//
	//**********************************************************************
	private void fillInTrees() {
		long lRandomData[];

		//Generate random data, also tell user we are doing so
		System.out.println( "Generating random data..." );
		lRandomData = DataGenerator.getUniqueRandoms( lAmountData );

		//Use DataGenerator class to fill in our trees
		//Also tell user what tree is being filled in
		System.out.println( "Filling in random data to Binary"
		   + " Search Tree A..." );
		oRandomBST = DataGenerator.GenRanTree(lRandomData);
		System.out.println( "Filling in sorted data to Binary"
		   + " Search Tree B..." );
		oSortedBST = DataGenerator.GenSortTree(oRandomBST);

		System.out.println( "Filling in random data to Splay"
		   + " Search Tree A..." );
			oRandomST = DataGenerator.GenRanSplayTree(lRandomData);
		System.out.println( "Filling in sorted data to Splay"
		   + " Search Tree B..." );
			oSortedST = DataGenerator.GenSortSplayTree(oRandomST);
	}


	//**********************************************************************
	// Method:  testTrees
	//
	// Preconditions:  int iPassNo -- Current pass number of this test
	//
	// Postconditions:  Uses oBenchmarker to benchmark each tree
	//
	//**********************************************************************
	private void testTrees( int iPassNo ) {
		long lRanValue;  //Random value to search for


		//Get a random value to search for
		lRanValue = (long) (Math.random() * lAmountData) + 1;

		//Make sure not over the maximum possible
		if( lRanValue > lAmountData ) {
			lRanValue = lAmountData;
		}

		//Tell user test has begun
		System.out.print( "Testing trees..." );

		//Run tests on trees
		oBenchmarker.runTest("BST Random", oRandomBST, lRanValue, iPassNo);
		oBenchmarker.runTest("BST Sorted", oSortedBST, lRanValue, iPassNo);
		oBenchmarker.runTest("ST Random", oRandomST, lRanValue, iPassNo);
		oBenchmarker.runTest("ST Sorted", oSortedST, lRanValue, iPassNo);

		//Tell user testing is done
		System.out.println( "DONE" );
	}

}  //End of Engine