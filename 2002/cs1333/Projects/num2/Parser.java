//==============================================================================
//  Parser.java -- Rodney Beede -- 09/20/00
//------------------------------------------------------------------------------
//
//  Class Description:  Parses a java source code file into html format
//	It reads in a keyword/color pair file for formatting codes
//
//
//
//  Public Variables:  none
//
//
//  Public Methods:  boolean parseCodeToHTML  () - Takes the java code file and
//	parses it using a StreamTokenizer into HTML format
//
//
//  Protected Variables:  none
//
//
//  Protected Methods:  none
//
//
//  Private Variables:  String strJavaFilename - holds the filename of the java
//	source file
//			Vector vecKeyColorPairs - holds the keyword/color pairs
//
//
//  Private Methods:  void parseKeys (strFilename) - parses the file named
//	strFilename into keyword/color pairs and stores them in our vector
//
//		      void checkKeyword  (strSearchKey) - searches through our
//	vector looking for a matching keyword to strSearchKey
//
//
//==============================================================================


// Import the java IO package for reading and writing data
import java.io.*;
// Import the StringPair class for storing keyword/color pairs
import StringPair;
// Import the Vector class for storing arrays of objects
import structure.Vector;

public class Parser {

	private String strJavaFilename;  //Filename of java source code
	private Vector vecKeyColorPairs;  //Vector of our key/color pairs


	//**********************************************************************
	// Method:  class constructor
	//
	// Preconditions:  strJavaFilename - filename of java source code to
	//			format into HTML
	//		   strColorsFilename - filename of file with keyword/
	//			color pairs for formatting
	//
	// Postconditions:  returns true if parsing succeeded
	//
	// Description:  This class will first parse the keyword/color pairs
	//	into a vector array, then it will parse the java source code
	//	file into HTML and store the output into a file with the name
	//	of strJavaFilename except with a .HTML extension
	//
	//**********************************************************************
	public Parser( String strJavaFilename, String strColorsFilename )
	   throws IOException {
		//Store strJavaFilename internally
		this.strJavaFilename = strJavaFilename;
		
		//Parse our keyword/color pairs file
		parseKeys( strColorsFilename );
	}


	//**********************************************************************
	// Method:  parseKeys( String strFilename );
	//
	// Preconditions:  strFilename - filename of a file to parse keys from
	//
	// Postconditions:  none
	//
	// Description:  Opens up strFilename, parses each line for
	//	keyword/value pairs, and stores the pairs into the
	//	vecKeyColorPairs vector
	//
	//**********************************************************************
	private void parseKeys( String strFilename ) throws IOException {
		//Open up strFilename for reading
		FileReader objFileReader = new FileReader( strFilename );
		BufferedReader objBuffReader = new BufferedReader(
		   objFileReader );

		//Create a StreamTokenizer object to parse our file
		StreamTokenizer objFileParser = new StreamTokenizer(
			objBuffReader );

		//Allocate our vecKeyColorPairs vector for storage
		vecKeyColorPairs = new Vector();

		//Setup some temp variables, used to hold parsed data
		String strCurrKey;
		String strCurrValue;

		//Loop through the file, parsing keys and colors as we go
		while( objFileParser.nextToken() != objFileParser.TT_EOF ) {
			//objFileParser should have our first keyword token
			//loaded, so we hold it in strCurrKey
			strCurrKey = objFileParser.sval;

			//Have objFileParser read in the next token, which is
			//our value for this key
			objFileParser.nextToken();
			strCurrValue = objFileParser.sval;

			//Add our new pair to our vector, using the StringPair
			//class to hold our keyword and value
			vecKeyColorPairs.addElement( new StringPair( strCurrKey,
			   strCurrValue ) );
		}

		//Close the file since we're done with it
		objBuffReader.close();
	}


	//**********************************************************************
	// Method:  parseCodeToHTML
	//
	// Preconditions:  none
	//
	// Postconditions:  returns true if parse was successful; false if it
	//	was not successful
	//
	// Description:  Opens strJavaFilename and uses a StreamTokenizer to
	//	parse it.  It parses line-by-line and outputs the formatted
	//	code to a HTML file
	//
	//**********************************************************************
	public boolean parseCodeToHTML( ) throws IOException {
		//Setup some objects for reading and writing to the file
		FileReader objFileReader = new FileReader( strJavaFilename );
		BufferedReader objBuffReader = new BufferedReader(
		   objFileReader );
		FileWriter objFileWriter;  //Don't want to open the file yet
		BufferedWriter objBuffWriter;  //(see line right above)

		//Create a StreamTokenizer object to parse our java code file
		StreamTokenizer objFileParser = new StreamTokenizer(
			objBuffReader );

		/* Set the tokenizer rules
		   These are the defaults:
			All byte values 'A' through 'Z', 'a' through 'z', and
			   '\u00A0' through '\u00FF' are considered to be
			   alphabetic.
			All byte values '\u0000' through '\u0020' are considered
			   to be white space.
			'/' is a comment character.
			Single quote '\'' and double quote '"' are string quote
			   characters.
			Numbers are parsed.
			Ends of lines are treated as white space, not as
			   separate tokens.
			C-style and C++-style comments are not recognized.

		   ID's for token data type:
			TT_EOL - end of line
			TT_NUMBER - number
			TT_EOF - end of file
			TT_WORD - word
			" or ' - token is a quoted string
		*/

		//We need to change End of Lines to be tokenized
		objFileParser.eolIsSignificant( true );
		//We want slashSlash (//) comments to show up
		objFileParser.wordChars( '/', '/' );

		//We can't do slashStar comments because the tokenizer will
		//discard all the text between our comments

		//Set our new filename with a .HTML extension
		String strNewFilename = strJavaFilename + ".HTML";

		//Allocate our file writer objects, to shoot out our output
		objFileWriter = new FileWriter( strNewFilename );
		objBuffWriter = new BufferedWriter( objFileWriter );

		//Print our header HTML code to our output file
		objBuffWriter.write( "<HTML>\n<HEAD>\n<TITLE>"
		   + strJavaFilename + "</TITLE>\n</HEAD>\n<BODY>\n<PRE>\n");

		//Now we loop through our file, and identify each token and
		//format it
		while( objFileParser.nextToken() != objFileParser.TT_EOF ) {
			String strCurrKeyValue;  //Used to hold key value

			/* Figure out what kind of token we have.
			   We have these possiblites:
				ttype = TT_WORD -- scan for comments or keywords
				ttype = TT_NUMBER -- underline number
				ttype = " or ' -- bold quote
				ttype = TT_EOL -- add a \n for the next line
			*/
			switch ( objFileParser.ttype ) {
				case objFileParser.TT_WORD:
					//Check the line to see if it starts a
					//slash slash comment
					if( objFileParser.sval.startsWith(
					   "//" ) ) {
						//We have a comment, italize it
						objBuffWriter.write( "<I>"
						   + objFileParser.sval
						   + "</I>");
					}
					//Check for a keyword by first searcing
					//searching for a value for a key
					//matching the current token.  If that
					//search works, the value is saved into
					//strCurrKeyValue and we know we have a
					//token that is a keyword
					else if( (strCurrKeyValue =
					   checkKeyword(objFileParser.sval))
					   != null ) {
						//We have a keyword to color in
						objBuffWriter.write(
						   "<FONT COLOR="
						   + strCurrKeyValue
						   + ">" + objFileParser.sval
						   + "</FONT> " );
					}
					//Just a plain old word
					else {
						//Just shoot it back
						objBuffWriter.write(
						   objFileParser.sval + " " );
					}

					break;
				case objFileParser.TT_NUMBER:
					//Print out the number underlined
					objBuffWriter.write( "<U>"
						+ objFileParser.nval + "</U> ");

					break;
				case (int) '\'':
					//Need to bold quote and reprint the
					// ' character at the end and beginning
					objBuffWriter.write( "<B>\'"
					   + objFileParser.sval + "\'</B> " );

					break;
				case (int) '\"':
					//Need to bold quote and reprint the
					// " character at the end and beginning
					objBuffWriter.write( "<B>\""
					   + objFileParser.sval + "\"</B> " );

					break;
				case objFileParser.TT_EOL:
					//Need to print a \n for the next line
					objBuffWriter.write( "\n" );

					break;
				default:
					//Most likely hit a semi-colon or some
					//other escape character, just echo
					//it right back
					objBuffWriter.write( (char)
					   objFileParser.ttype );

			}  //End of switch
		}  //End of while


		//Print our HTML file footer data
		objBuffWriter.write("\n</PRE>\n</BODY>\n</HTML>");

		//Close our new HTML file
		objBuffWriter.close();

		return true;  //Indicate success
	}


	//**********************************************************************
	// Method:  checkKeyword
	//
	// Preconditions:  String strSearchKey -- keyword to look for
	//
	// Postconditions:  Returns value of keyword if found, if not it
	//	returns null
	//
	// Description:  Loops through our vector looking for a matching keyword
	//	and returns the value of that pair if it is found or null if it
	//	is not found
	//
	//**********************************************************************
	private String checkKeyword( String strSearchKey ) {
		String strKeyValue;  //Value of keyword

		//Loop through vecKeyColorPairs looking for a matching keyword
		for( int j = 0; j < vecKeyColorPairs.size(); j++ ) {
			//Check for a matching keyword
			if( ((StringPair)
			   vecKeyColorPairs.elementAt(j)).getKeyword().equals(
			   strSearchKey)  ) {
				//Found a key, return the value of it
				return ((StringPair)
				   vecKeyColorPairs.elementAt(j)).getValue();
			}
		}

		//If we get to here, we didn't find a match for any keywords
		//Simply return null
		return null;
	}

}  //End of Parser