import java.io.*;
import java.io.File;


public class TrimSpace {

	//args[0] should be path and filename
	public static void main(String args[]) throws IOException {
		File inFile = new File(args[0]);
		File outFile = new File(inFile.getPath() + ".EML");
		FileReader frIn = new FileReader(inFile);
		FileWriter fwOut = new FileWriter(outFile, true);  //append mode
				
		String sLine[] = new String[500];  //Holds file line by line
		int i;  //For holding position
		int currChar;  //For holding ascii numerical value of current char read

		i = 0;
		currChar = frIn.read();
		sLine[i] = "";  //So it doesn't read "null"
		while( currChar != -1) {
			if( currChar == 13 ) {
				//Trim all head and tail whitespace from line
				sLine[i] = sLine[i].trim() + "\n";
				i++;
				sLine[i] = "";  //Initilize next line
			} else {
				sLine[i] = sLine[i] + (char) currChar;
			}
			

			currChar = frIn.read();  //get the next char
		}
		sLine[i] = sLine[i].trim();  //Trim very last line in file also

		
		//Dump file back out in trimmed format
		System.out.println("Writing " + outFile.getPath() + "...");
		for(int j = 0; j <= i; j++) {
			fwOut.write(sLine[j]);
		}
		fwOut.close();

	}  //end main


}  //End class TrimSpace