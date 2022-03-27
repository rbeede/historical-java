//==============================================================================
//  GameEngine.java -- Rodney Beede -- 11/13/00
//------------------------------------------------------------------------------
//
//  Class Description:  This is the main game engine.  It creates our binary
//	tree and starts a loop that asks the user questions and attempts to
//	to guess.
//
//
//  Public Variables:  None
//
//
//  Public Methods:  void start  () - Starts the entire game
//
//
//  Protected Variables:  None
//
//
//  Protected Methods:  None
//
//
//  Private Variables:  BinaryTree oGameTree - Binary Tree with all of our game
//	data and logic structure
//
//
//  Private Methods:  void askAboutAnimal  () - Asks user about their animal
//
//		      void askQuestion  () - Asks user current question
//
//                    void checkAnswer  () - Checks computers guess with user
//
//                    void createTree  () - Creates game tree with defaults
//
//                    String currGameData  () - Returns current game data value
//
//                    void debug  () - Dumps contents of game tree to screen
//
//                    String getSTDIN  () - Returns input from user
//
//		      boolean playAgain() - Asks user to play again
//
//
//==============================================================================


// Import GameData class so we can store game data
import GameData;
// Import BinaryTree class for making a tree with
import structure.BinaryTree;
// Import Iterator class for debugging of our tree
import structure.Iterator;
// Import java.io package for reading in input from stdin
import java.io.*;


public class GameEngine {
	//Create a BinaryTree object for use in our game
	private BinaryTree oGameTree;


	//**********************************************************************
	// Method:  class constructor
	//
	// Preconditions:  No params.
	//
	// Postconditions:  Has the binary tree created with sample data
	//
	//**********************************************************************
	public GameEngine() {
		createTree();  //Call method to create tree
	}


	//**********************************************************************
	// Method:  start
	//
	// Preconditions:  No params.
	//
	// Postconditions:  Asks user to answer questions.  Transverses our tree
	//	to guess the user's animal.  It calls methods to process our
	//	tree with and add data to it based on how the game progresses.
	//
	//	The game works in this way:
	//	   1.  Start at root of tree for new game
	//
	//	   2.  Check current node for it's type; for a question ask it.
	//		for a answer state it as the computers guess.
	//
	//	   3.  If node was question and user answered:
	//		"yes" then move cursor to left and move to top of loop
	//		"no" them move cursor to right and move to top of loop
	//	       By moving to top of loop we check the new node type and
	//	       end up asking either a question again or answering it
	//
	//	   4.  If node was an answer:
	//		a) display guess
	//		b) ask user if correct, if yes then say computer won
	//		c) if not correct guess then ask user for the correct
	//		   animal and a question to tell between that animal and
	//		   the one the computer guessed
	//
	//	   5.  Ask user to play again
	//
	//**********************************************************************
	public void start() throws InterruptedException {

		//Loop until user wants to stop playing
		do {
			//Give user instructions
			System.out.println( "" );
			System.out.println( "Imagine some animal, and I will try to"
			   + " guess it!  You have 5 seconds to think of something." );
			System.out.println( "" );

        		//Pause the program for 5 seconds
			Thread.sleep( 5 * 1000 );

			oGameTree.reset();  //Move cursor to root of tree

			//Loop through the tree asking questions until the
			//cursor no longer points to a question
			do {
				askQuestion();  //Ask the user curr question
			} while( ((GameData) oGameTree.value()).getType() ==
			   GameData.DTQUESTION );


			//We have asked all the questions, we should now have
			//an answer, so display it
			System.out.println( "It's a " + currGameData() + "!" );
			System.out.println( "" );

			//Ask if the computer got the guess correct
			//If yes then this method will say computer won
			//If no then this method will ask for the correct answer
			checkAnswer();

		} while( playAgain() );
	}


	//**********************************************************************
	// Method:  askAboutAnimal
	//
	// Preconditions:  No params
	//
	// Postconditions:  Asks user which animal they were thinking of,
	//	a question that distinguishes between that animal & the animal
	//	the computer was thinking of, and adds that information to our
	//	tree
	//
	//**********************************************************************
	private void askAboutAnimal() {
		Object oTempData;  //Placeholder for game data
		String sUserAnimal;  //Holds animal user though of
		String sUserQuestion;  //Holds user's question
		String sUserAnswer;  //Holds answer user would use to question

		//Ask user what animal they were thinking about
		System.out.print( "\nWhat animal were you thinking of?  " );
		sUserAnimal = getSTDIN();

		//Ask for a y/n question to distinguish that animal with
		System.out.println( "\nPlease type in a yes or no question "
		   + "that can distinguish a " + sUserAnimal + " from a " +
		   currGameData() + ":  " );
		sUserQuestion = getSTDIN();

		//Ask what the answer to that question is for the user's animal
		System.out.println( "\nIf the animal was a " + sUserAnimal
		   + " would you answer your question \"y\" or \"n\"?  ");
		sUserAnswer = getSTDIN();


		//The tree cursor is currently on an answer.  We need to replace
		//that answer with the user's question, but we must also
		//preserve that answer and place it below the user's question
		oTempData = oGameTree.value();  //Save current answer
		oGameTree.setValue( new GameData(GameData.DTQUESTION,
		   sUserQuestion) );  //Insert the user's question

		//If the user's animal is chosen when the user's question is
		//answered with yes, then place the user's animal on the left
		//side of the question.  Otherwise it goes on the right
		if( sUserAnswer.equalsIgnoreCase("y") ) {
			//Insert our "yes" response to our question
			oGameTree.moveLeft();  //Left child for YES
			oGameTree.insert(new GameData(GameData.DTANSWER,
			   sUserAnimal));

			//Insert our "no" response to our question (right child)
			oGameTree.moveUp();
			oGameTree.moveRight();  //Right child for NO
			oGameTree.insert(oTempData);
		}
		else {
			//Insert our "yes" response to our question
			oGameTree.moveLeft();  //Left child for YES
			oGameTree.insert(oTempData);

			//Insert our "no" response to our question (right child)
			oGameTree.moveUp();
			oGameTree.moveRight();  //Right child for NO
			oGameTree.insert(new GameData(GameData.DTANSWER,
			   sUserAnimal));
		}
	}


	//**********************************************************************
	// Method:  askQuestion
	//
	// Preconditions:  No params.  The current node in the tree should be
	//	of type question, but is not enforced.
	//
	// Postconditions:  Prints out the current value of the current tree
	//	node and prompts the user for yes or no.  If the user answers
	//	yes then the tree cursor moves to the left; if the user answers
	//	no then the tree cursor moves to the right.
	//
	//**********************************************************************
	private void askQuestion() {
		String sResponse;  //Holds user response to question

		//Ask the question
		System.out.println( "" );
		System.out.print( currGameData() + "? (y/n):  " );

		//Get the user response
		sResponse = getSTDIN();

		//Validate the response
		if( sResponse.equalsIgnoreCase("y") ||
		   sResponse.equalsIgnoreCase("n") ) {
			;  //Do nothing, contine on below
		} else {
			System.out.println( "Invalid Response!!!  Try Again." );
			askQuestion();  //Recursive call
			return;  //Leave now, recursive call should set cursor up
		}


		//Response was valid, set cursor accordingly
		if( sResponse.equalsIgnoreCase("y") ) {
			oGameTree.moveLeft();  //Move to left node
		}
		else {
			oGameTree.moveRight();  //Move to right node
		}
	}


	//**********************************************************************
	// Method:  checkAnswer
	//
	// Preconditions:  No params.
	//
	// Postconditions:  No return.  If computer's guess was correct then the
	//	computer is happy.  If the computer's guess was incorrect then
	//	it will ask what the correct answer was; and get a question
	//	from the user that can distinguish the animal
	//
	//**********************************************************************
	private void checkAnswer() {
		String sResponse;  //Holds user response

		//Ask user if guess was correct
		System.out.print( "Did I guess correctly? (y/n):  " );

		sResponse = getSTDIN();  //Get response

		//See what user said
		if( sResponse.equalsIgnoreCase("y") ) {
			System.out.println( "Man I'm good.\n" );
			return;  //Leave method
		}
		else {
			System.out.println( "You got me!\n" );

			//Call method to ask user about the animal they were
			//thinking about
			askAboutAnimal();
		}
	}


	//**********************************************************************
	// Method:  createTree
	//
	// Preconditions:  No params
	//
	// Postconditions:  Fills in oGameTree with data
	//
	//**********************************************************************
	private void createTree() {
		//Start by instantiating oGameTree
		oGameTree = new BinaryTree();

		//Insert our default question
		oGameTree.insert(new GameData(GameData.DTQUESTION,
		   "Does the animal have legs"));

		//Insert our "yes" response to our question
		oGameTree.moveLeft();  //Left child for YES
		oGameTree.insert(new GameData(GameData.DTANSWER, "dog"));

		//Insert our "no" response to our question (right child)
		oGameTree.moveUp();
		oGameTree.moveRight();  //Right child for NO
		oGameTree.insert(new GameData(GameData.DTANSWER, "snake"));
	}


	//**********************************************************************
	// Method:  currGameData
	//
	// Preconditions:  No params.  Requires that cursor is valid or null
	//	gets returned
	//
	// Postconditions:  Returns String value of GameData value
	//
	//**********************************************************************
	private String currGameData() {
		return ((GameData) oGameTree.value()).getValue();
	}


	//**********************************************************************
	// Method:  debug
	//
	// Preconditions:  None
	//
	// Postconditions:  Doesn't modify anything.  Simply outputs tree
	//	contents to screen.  ONLY FOR DEBUGGING
	//
	//**********************************************************************
	private void debug() {
		Iterator oDebug = oGameTree.preorderElements();

		while( oDebug.hasMoreElements() )
			System.out.println( ((GameData)
			   oDebug.nextElement()).getValue() );
	}


	//**********************************************************************
	// Method:  getSTDIN
	//
	// Preconditions:  No params
	//
	// Postconditions:  Reads in input from keyboard and returns it
	//
	//**********************************************************************
	private String getSTDIN() {
		//Try clause catches any I/O errors
		try {
			//Setup a reader to get input from stdin
			BufferedReader stdin = new BufferedReader( new
			   InputStreamReader(System.in));

			return stdin.readLine();  //Read in value and return it
		}
		catch( IOException e ) {
			System.out.println( "ERROR:  Unable to read input from"
			   + " the keyboard (stdin)!!!\n\n" );

			System.exit(1);  //Kill program
		}


		//This point should never be reached.  The method simply needs a
		//return call that is not inside a try statement
		System.out.println( "getSTDIN has reached an invalid state" );
		return null;
	}


	//**********************************************************************
	// Method:  playAgain
	//
	// Preconditions:  No params
	//
	// Postconditions:  Asks user if they wish to play the game again
	//	Returns true if they say yes or false if they say no
	//
	//**********************************************************************
	private boolean playAgain() {
		String sResponse;  //Response from user

		//Ask user if they wish to play again
		System.out.print( "Do you wish to play again? (y/n):  " );

		//Get the user's response
		sResponse = getSTDIN();

		//Check the user's response and return true or false
		if( sResponse.equalsIgnoreCase("y") ) {
			return true;  //Play again
		}
		else {
			return false;  //Leave game
		}
	}


}  //End of GameEngine