//==============================================================================
//  DataGenerator.java -- Rodney Beede -- 12/03/00
//------------------------------------------------------------------------------
//
//  Class Description:  This class fills in random or sorted data into a
//	Binary Search Tree or Splay Tree.
//
//
//  Public Variables:  None
//
//
//  Public Methods:  BinarySearchTree GenRanTree  ( long lAmountData ) - this
//	generates a BST with random ordered numbers in it based on lAmountData
//
//		     BinarySearchTree GenSortTree( BinarySearchTree oTree ) -
//	this generates a BST with sorted order numbers in it based on the size
//	of oTree
//
//		     SplayTree GenRanSplayTree  ( BinarySearchTree oTree ) -
//	this generates a SplayTree with the same randomized order data as
//	the passed BST oTree
//
//		     SplayTree GenSortSplayTree  ( SplayTree oTree ) - this
//	generates a sorted tree based on the passed ST oTree
//
//
//  Protected Variables:  None
//
//
//  Protected Methods:  None
//
//
//  Private Variables:  None
//
//
//  Private Methods:  long getUniqueRandoms[]  ( long lAmountData ) - generates
//	an array of unique random numbers
//
//==============================================================================


//Import needed classes
import structure.BinarySearchTree;
import structure.SplayTree;
import structure.Iterator;


public class DataGenerator {

	//**********************************************************************
	// Method:  class constructor
	//
	// Preconditions:  None
	//
	// Postconditions:  None
	//
	//**********************************************************************
	public DataGenerator() {
		;  //Nothing needed here
	}


	//**********************************************************************
	// Method:  GenRanTree
	//
	// Preconditions:  lAmountData - amount of data to put in tree
	//
	// Postconditions:  Returns a BST that contains a set of random ordered
	//	numbers from 0 to lAmountData
	//
	//**********************************************************************
	public static BinarySearchTree GenRanTree( long lRanData[] ) {
		//Setup a temporary BST to store data in
		BinarySearchTree oGrowingTree = new BinarySearchTree();


		//Loop until we have added lAmountData elements
		for( long j=0; j < lRanData.length; j++ ) {
			//Add the random numbers to our tree
			oGrowingTree.add( new Long(lRanData[(int) j]) );
		}

		return oGrowingTree;
	}


	//**********************************************************************
	// Method:  getUniqueRandoms
	//
	// Preconditions:  lAmountData - number of numbers to place in array
	//
	// Postconditions:  Returns a unique array of random numbers
	//
	//**********************************************************************
	public static long[] getUniqueRandoms( long lAmountData ) {
		//Create an array that holds numbers we have already picked
		long lUsedNums[] = new long[(int) lAmountData];

		boolean bolDone;  //Flag to see if ran number is unique
		long lRanNum;  //Holds our random number
		int k = 0;


		//Loop until we fill up lUsedNums
		do {
			//Get a random number
			lRanNum = (long) (Math.random() * lUsedNums.length) + 1;

			//Assume we have a valid number
			bolDone = true;

			//See if the number is in lUsedNums
			for( int j=0; j < lUsedNums.length; j++ ) {
				if( lRanNum == lUsedNums[j] ) {
					//Duplicate, mark flag to try again
					bolDone = false;
					break;  //Leave for loop
				}
			}

			//See if number exceeds max allowed size
			if( lRanNum > lUsedNums.length ) {
				bolDone = false;
			}


			if( bolDone != false ) {
				//Add random number to unique numbers list
				lUsedNums[(int) k] = lRanNum;
				k++;
			}

			if( k < lAmountData ) {
				bolDone = false;  //Need to keep adding
			}
       		} while( bolDone == false);


		//Return the random numbers
		return lUsedNums;
	}


	//**********************************************************************
	// Method:  GenSortTree
	//
	// Preconditions:  oTree - tree to get values from to sort
	//
	// Postconditions:  Returns a BST that contains a set of sort ordered
	//	numbers from 1 to oTree.size()
	//
	//	This tree is returned as a new tree.  This method doesn't
	//	modify the original passed tree
	//
	//**********************************************************************
	public static BinarySearchTree GenSortTree( BinarySearchTree oTree ) {
		//Setup a temporary BST to store data in
		BinarySearchTree oGrowingTree = new BinarySearchTree();


		//We simply need to loop to the size of oTree since we are
		//simply adding numbers.  oTree should contain every number
		//from 1 to oTree.size(), they are just in random order.
		//Since we want sorted order to only have to add the numbers
		//1 to oTree.size() in a for loop
		for( long j=1; j <= oTree.size(); j++ ) {
			//Add the current position index number to our tree
			oGrowingTree.add( new Long(j) );
		}

		return oGrowingTree;
	}


	//**********************************************************************
	// Method:  GenRanSplayTree
	//
	// Preconditions:  lRanData - array of random numbers
	//
	// Postconditions:  Returns a ST that contains a set of random ordered
	//	numbers from 0 to oTree.size()
	//
	//**********************************************************************
	public static SplayTree GenRanSplayTree( long lRanData[] ) {
		//Setup a temporary ST to store data in
		SplayTree oGrowingTree = new SplayTree();


		//Loop until we have added all of lRanData to oGrowingTree
		for( int j=0; j < lRanData.length; j++ ) {
			//Add the current element to our splay tree
			oGrowingTree.add( new Long(lRanData[j]) );
		}

		return oGrowingTree;
	}



	//**********************************************************************
	// Method:  GenSortSplayTree
	//
	// Preconditions:  oTree - tree to get values from
	//
	// Postconditions:  Returns a ST that contains a set of sort ordered
	//	numbers from 0 to oTree.size()
	//
	//	This tree is returned as a new tree.  This method doesn't
	//	modify the original passed tree
	//
	//**********************************************************************
	public static SplayTree GenSortSplayTree( SplayTree oTree ) {
		//Setup a temporary ST to store data in
		SplayTree oGrowingTree = new SplayTree();


		//We simply need to loop to the size of oTree since we are
		//simply adding numbers.  oTree should contain every number
		//from 1 to oTree.size(), they are just in random order.
		//Since we want sorted order to only have to add the numbers
		//1 to oTree.size() in a for loop
		for( long j=1; j <= oTree.size(); j++ ) {
			//Add the current position index number to our tree
			oGrowingTree.add( new Long(j) );
		}

		return oGrowingTree;
	}

}  //End of DataGenerator