/*

TestEasyIn.java. 

This program prompts for, and reads in a succession of different
primitive data type values. Compile the two classes EasyIn and TestEasyIn and
execute TestEasyIn. 

Provide different values (from within and outside the range of acceptable
values for the particular datatype) to the program and check the ones that
are accepted. Carry out several sets of tests and note your result.

This class is used to test the class EasyIn
1. Save the code for class EasyIn in a file named EasyIn.java
2. Save the code for class TestEasyIn in a separate file named TestEasyIn.java
   (Both files should be in the same directory).
3. Compile the file EasyIn.java
4. Compile the file TestEasyIn.java
5. Execute TestEasyIn.

Try giving different input values for the various primitive types.
What happens when you give an invalid value for a particular input type?


*/     

////////////////////////////////////////////////////////////
// Test the functionality of the class EasyIn. For each
// primitive data type prompt for a value and use the
// appropriate method of EasyIn to read that value. Display
// the value read in on the monitor.  
///////////////////////////////////////////////////////////

import javabook.*;

class TestEasyIn {
  // This method tests the functionality of the class EasyIn
   public static void main (String args[]){

		
       EasyIn easy = new EasyIn();

       System.out.print("enter char: "); System.out.flush();
       System.out.println("You entered: " + easy.readChar() );

       System.out.print("enter String: "); System.out.flush();
       System.out.println("You entered: " + easy.readString() );

       System.out.print("enter boolean: "); System.out.flush();
       System.out.println("You entered: " + easy.readBoolean() );

       System.out.print("enter byte: "); System.out.flush();
       System.out.println("You entered: " + easy.readByte() );

       System.out.print("enter short: "); System.out.flush();
       System.out.println("You entered: " + easy.readShort() );

       System.out.print("enter int: "); System.out.flush();
       System.out.println("You entered: " + easy.readInt() );

       System.out.print("enter long: "); System.out.flush();
       System.out.println("You entered: " + easy.readLong() );

       System.out.print("enter float: "); System.out.flush();
       System.out.println("You entered: " + easy.readFloat() );

       System.out.print("enter double: "); System.out.flush();
       System.out.println("You entered: " + easy.readDouble() );
   }
}

