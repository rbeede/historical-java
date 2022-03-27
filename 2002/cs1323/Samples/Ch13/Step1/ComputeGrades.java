/*
    class ComputeGrades

    The top level object for managing all other objects in the program

*/

import java.io.*;
import javabook.*;

class ComputeGrades extends MainWindow
{

/***************************
    Data Members:
 ***************************/

   private static final int DEFAULT_SIZE = 25;

   private OutputBox   outputBox;   // for output
    
   private File             inFile;      // for input
   private FileReader       fileReader;
   private BufferedReader   bufReader;
    
   private Student[]   roster;   // for maintaining student info


/***************************
    Constructors:
 ***************************/

   public ComputeGrades()
   {
      this (DEFAULT_SIZE);
   }

   public ComputeGrades(int arraySize)
   {
      super();   // an explicit call to the supercalss constructor
      
      outputBox   = new OutputBox(this);

      roster      = new Student[arraySize];
   }


/***************************
   Public Methods:

      void  processData (  )

***************************/

  public void processData()
  {
     setVisible(true);
     outputBox.setVisible(true);
     
     boolean success = readData();

     if (success) {
        computeGrade();
        printResult();
     }
     else {
        outputBox.printLine("File Input Error");
     }
  }


/***************************
    Privare Methods:

        void        computeGrade    ( int )
        void        printResult     (     )
        boolean     readData        (     )

 ***************************/

   /*
      Method:       computeGrade

      Purpose:      Scan through the roster array and compute
                    the course grades.

      Parameters:   None
      
      Returns:      None
   */

   private void computeGrade()
   {
      outputBox.printLine("Inside computeGrade");  //TEMP
   }

   /*
      Method:       printResult

      Purpose:      Display the result in an outputBox.

      Parameters:   None
      
      Returns:      None
   */

   private void printResult()
   {
      outputBox.printLine("Inside printResult");  //TEMP
   }

   /*
      Method:       readData

      Purpose:      Opens a textfile, read in data, and
                    construct the roster array.

      Parameters:   None
      
      Returns:      boolean [true if successful]
   */

   private boolean readData()
   {
      outputBox.printLine("Inside readData");  //TEMP
      return true;
   }
}
