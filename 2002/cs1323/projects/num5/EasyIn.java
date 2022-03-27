/*

Save the code for the following class in a file called EasyIn.java. This
class provides a very simple way to read values from the keyboard for all
primitive data types. The primitive data types are boolean, char, byte,
short, int, long, float, double. Don't worry if you do not understand the
code in this class. An example of use of this class is shown in the
following code snippet:

	EasyIn easy = new EasyIn;
	int maxCount = easy.readInt(); // reads an int from System.in
	float weight = easy.readFloat();    // reads a a float from System.in

Use the class TestEasyIn to test this EasyIn class.  

Note that this program prompts for, and reads in a succession of different
primitive data type values. Compile the two classes EasyIn and TestEasyIn and
execute TestEasyIn. 

Provide different values (from within and outside the range of acceptable
values for the particular datatype) to the program and check the ones that
are accepted. Carry out several sets of tests and note your result.

*/
///////////////////////////////////////////////////////////////////
// Simple input from the keyboard for all primitive types. ver 1.0
// Copyright (c) Peter van der Linden,  May 5 1997.
// corrected error message 11/21/97  
///////////////////////////////////////////////////////////////////
import java.io.*;
import java.util.*;

class EasyIn {
    static InputStreamReader is = new InputStreamReader( System.in );
    static BufferedReader br = new BufferedReader( is );
    StringTokenizer st;

    StringTokenizer getToken() throws IOException {
       String s = br.readLine();
       return new StringTokenizer(s);
    }

    boolean readBoolean() {
       try {
          st = getToken();
          return new Boolean(st.nextToken()).booleanValue();
       } catch (IOException ioe) {
          System.err.println("IO Exception in EasyIn.readBoolean");
          return false;
       }
    }

    byte readByte() {
       try {
         st = getToken();
         return Byte.parseByte(st.nextToken());
       } catch (IOException ioe) {
          System.err.println("IO Exception in EasyIn.readByte");
          return 0;
       }
    }

    short readShort() {
       try {
         st = getToken();
         return Short.parseShort(st.nextToken());
       } catch (IOException ioe) {
          System.err.println("IO Exception in EasyIn.readShort");
          return 0;
       }
    }

    int readInt() {
       try {
         st = getToken();
         return Integer.parseInt(st.nextToken());
       } catch (IOException ioe) {
          System.err.println("IO Exception in EasyIn.readInt");
          return 0;
       }
    }

    long readLong() {
       try {
         st = getToken();
         return Long.parseLong(st.nextToken());
       } catch (IOException ioe) {
          System.err.println("IO Exception in EasyIn.readLong");
          return 0L;
       }
    }

    float readFloat() {
       try {
         st = getToken();
         return new Float(st.nextToken()).floatValue();
       } catch (IOException ioe) {
          System.err.println("IO Exception in EasyIn.readFloat");
          return 0.0F;
       }
    }

    double readDouble() {
       try {
         st = getToken();
         return new Double(st.nextToken()).doubleValue();
       } catch (IOException ioe) {
          System.err.println("IO Exception in EasyIn.readDouble");
          return 0.0;
       }
    }

    char readChar() {
       try {
         String s = br.readLine();
         return s.charAt(0);  
       } catch (IOException ioe) {
          System.err.println("IO Exception in EasyIn.readChar");
          return 0;
       }
    }

    String readString() {
       try {
         return br.readLine(); 
       } catch (IOException ioe) {
          System.err.println("IO Exception in EasyIn.readString");
          return "";
       }
    }
}
