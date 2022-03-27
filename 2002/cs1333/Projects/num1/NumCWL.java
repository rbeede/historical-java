//==============================================================================
//  NumCWL.java  -- Rodney Beede -- 08/27/00
//------------------------------------------------------------------------------
//
//  Course:  CS1333-201
//  Project:  #1
//  Instructor's Name:  Scott J. Swindell
//
//  Program Description:  This program counts the number of characters, words,
//	and lines in a file.
//
//
//
//  Inputs (Command line args):  args[0] - the path and filename of the file to
//	open.
//
//
//  Input Requirements:  args[0] must not be null, and must have a file that
//	exists.
//
//
//  Outputs:  the number of lines, words, and characters in the file are
//	outputted to STDOUT
//
//
//==============================================================================


//Import the Assert class for doing checks on our input requirements
//Import the IO package to allow access to the passed file
//Import the UTILS package to allow us to parse strings
import structure.*;
import java.io.*;
import java.util.*;


public class NumCWL {

	//**********************************************************************
	// Method:  main
	//
	// Preconditions:  String args[] - Optional array of command line args
	//
	// Postconditions:  FILL THIS IN
	//
	// Description:  WHAT THIS DOES HERE
	//
	//**********************************************************************
	public static void main(String args[]) throws IOException {
		//These variables hold data about the file
		int iCharCount = 0;  //Number of characters in file
		int iWordCount = 0;  //Number of words in file
		int iLineCount = 0;  //Number of lines in file
		String sCurrLine;  //Holds the current line read from the file
		StringTokenizer objParser;  //Parses our file lines into words

		//Check to see if args[0] contains anything at all
		Assert.pre(args.length != 0, "\n\nNo filename was passed\n\n");

		//Create a File object that will load the passed file
		File objPassedFile = new File( args[0] );

		//Check to see if the passed file exists
		Assert.pre( objPassedFile.exists(), "\n\nCouldn't find " +
		   args[0] + "\n\n" );

		//Create a BufferedReader object for reading file lines
		//We take our File object objPassedFile and convert it into a
		//FileReader object.  We like using the File object as an easy
		//way to grab the length of the file
		BufferedReader objPassFileReader = new BufferedReader(
		   new FileReader(objPassedFile) );


		//Get the number of characters in the file
		iCharCount = (int) objPassedFile.length();

		//Loop through the file, reading it in line by line, couting
		//the lines as we go.  Also count the number of words in each
		//line that we read
		sCurrLine = objPassFileReader.readLine();  //Read the 1st line
		while( sCurrLine != null ) {
			//Increment the line count
			iLineCount++;

			//Break the current line into words using objParser
			//It is a StringTokenizer that will break up the line
			//into words based on the following delims: (blank, tab,
			//newline, or return)
			objParser = new StringTokenizer( sCurrLine );
			//Add the number of words found in this line to total
			iWordCount = iWordCount + objParser.countTokens();


			//Read next line
			sCurrLine = objPassFileReader.readLine();
		}

		//We are done with the file, close it
		objPassFileReader.close();


		//Show our output
		System.out.println( "" );  //Blank line
		System.out.println( "# chars\t\t# words\t\t#lines\t\t"
		   + "filename" );	//Header
		System.out.println( "" );  //Blank line
		System.out.println( iCharCount + "\t\t" + iWordCount + "\t\t"
		   + iLineCount + "\t\t" + args[0] );  //The info
		System.out.println( "" );  //Anonther blank line
		
	}

}  //End of CLASSNAME