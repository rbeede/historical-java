//==============================================================================
//  JtoHTML.java  -- Rodney Beede -- 09/20/00
//------------------------------------------------------------------------------
//
//  Course:  CS1333-201
//  Project:  #2
//  Instructor's Name:  Scott J. Swindell
//
//  Program Description:  This program converts a java source file into html
//	formatting keywords, comments, quoted strings, and numbers
//
//
//
//  Inputs (Command line args):  args[0] - filename of java file
//				 args[1] - filename of keyword/color pair file
//
//
//
//  Input Requirements:  args[0] and args[1] must contain a filename
//
//
//
//  Outputs:  html file of the formatted code with the same filename as args[0]
//	except with a .HTML extension
//
//
//==============================================================================


//Import Parser class for use in parsing data
import Parser;
//Import Java.IO package for storing data to a file
import java.io.*;


public class JtoHTML {

	//**********************************************************************
	// Method:  main
	//
	// Preconditions:  String args[] - Array of command line args (see top
	//	header for info)
	//
	// Postconditions:  Output is an HTML file of the formatted code
	//
	// Description:  This method uses Parser objects to break up keyword/
	//	color pairs and format java code to html.
	//
	//	It then saves the formatted code to a html file.
	//
	//**********************************************************************
	public static void main(String args[]) throws IOException {
		//Check that we have the required params
		if( (args == null) || (args.length < 2) ) {
			System.out.println( "You failed to correctly pass " +
			   "your paramaters.\nCorrect usage is:\n\tJtoHTML " +
			   "javafile.java color.txt\n" );
			return;  //End program
		}


		//Create a parser object and pass it the java source code
		//filename and keyword colors file.  It will automatically parse
		//our keyword/color pairs
		Parser objCodeParser = new Parser( args[0], args[1] );

		//Ask objCodeParser to parse the html.  It returns true if
		//successful or false if something failed
		if( objCodeParser.parseCodeToHTML() == false ) {
			//The parsing failed, tell user and quit
			System.out.println( "Parsing of " + args[0] + " has " +
			   "failed.\n" );
			return;  //End program
		}
		else {
			//Tell the user parsing was successful
			System.out.println( "Parsing of " + args[0] + " was " +
			   "successful.\n" );
		}
	}  //End of main method

}  //End of JtoHTML