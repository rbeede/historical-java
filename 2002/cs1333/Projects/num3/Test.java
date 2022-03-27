import java.io.*;
import structure.Vector;
import java.util.*;


class Test {
	public static void main(String args[]) throws IOException {
		//Open up strFilename for reading
		FileReader objFileReader = new FileReader( "rodney.DIC" );
		BufferedReader objBuffReader = new BufferedReader(
		   objFileReader );

		//Create a StringTokenizer object to parse our file, using our
		//keyword and def seperators as tokens, and returning them too
		StreamTokenizer objFileParser = new StreamTokenizer(
			objBuffReader);

		//Setup our delimiter rules for the tokenizer
		objFileParser.resetSyntax();  //Reset all rules
		//Setup our words to include all chars including white space
		objFileParser.wordChars( 0, 126 );
		objFileParser.whitespaceChars( ':', ':' );
		objFileParser.whitespaceChars( '<', '<' );
		objFileParser.whitespaceChars( '>', '>' );



		//Allocate our oDictEntry vector for storage
		Vector oDictEntry = new Vector();

		//Setup some temp variables, used to hold parsed data
		String strCurrKey;
		String strCurrDef;

		//Loop through the file, parsing keys and defs as we go
		while( objFileParser.nextToken() != objFileParser.TT_EOF ) {

System.out.println( "DATA IS: " + objFileParser.toString() );


/*  OLD			//objFileParser should have our first keyword token
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

END OLD*/
		}

		//Close the file since we're done with it
		objBuffReader.close();
	}
}
