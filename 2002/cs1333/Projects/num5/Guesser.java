//==============================================================================
//  Guesser.java  -- Rodney Beede -- 11/13/00
//------------------------------------------------------------------------------
//
//  Course:  CS1333-201
//  Project:  #5
//  Instructor's Name:  Scott J. Swindell
//
//  Program Description:  This program is a game that tried to guess an animal
//	that the user it thinking about.  It uses yes/no questions that are in
//	a binary tree to guess at the animal.
//
//
//
//
//  Inputs (Command line args):  No command line arguments.  Program will ask
//	for user input on STDIN (console) for answers to questions.
//
//
//
//  Input Requirements:  Yes or No answers are required.
//
//
//
//  Outputs:  Questions to help solve what kind of animal and guesses at
//	that animal.  This info come from a binary search tree.
//
//
//
//==============================================================================


//Import GameEngine so we can play the game
import GameEngine;


public class Guesser {

	//**********************************************************************
	// Method:  main
	//
	// Preconditions:  String args[] - Optional array of command line args
	//
	// Postconditions:  GameEngine is ran, after it has finished the program
	//	ends
	//
	// Description:  Shows some info about the game and starts the
	//	GameEngine class to run the game.
	//
	//**********************************************************************
	public static void main(String args[]) throws InterruptedException {
		GameEngine oGuessEngine = new GameEngine();  //Create the engine

		//Tell the user about the game
		System.out.println( "" );
		System.out.println( "Welcome to Guesser" );
		System.out.println( "------------------" );

		//Start the engine
		oGuessEngine.start();


		//Game has ended, tell user goodbye
		System.out.println( "Thanks for playing!!!" );
		System.out.println( "" );
	}
}  //End of Guesser