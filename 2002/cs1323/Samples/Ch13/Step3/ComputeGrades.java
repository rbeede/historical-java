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
   
   private int studentCount;


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

//      messageBox  = new MessageBox(this);
      roster      = new Student[arraySize];
      studentCount = 0;
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
      for (int i = 0; i < studentCount; i++) {
         roster[i].computeCourseGrade();
      }
   }

   /*
      Method:       printResult

      Purpose:      Display the result in an outputBox.

      Parameters:   None
      
      Returns:      None
   */

   private void printResult()
   {
      for (int i = 0; i < studentCount; i++) {
         //print one student
         outputBox.print(Format.leftAlign(15, roster[i].getName()));

         for (int testNum = 1; testNum <= Student.NUM_OF_TESTS; testNum++) {
            outputBox.print(Format.centerAlign(8, roster[i].getTestScore(testNum)));
         }

         outputBox.printLine(Format.rightAlign(8, roster[i].getCourseGrade()));
      }
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
      
      for (int i = 0; i < 15; i++) {
         roster[i] = new UndergraduateStudent();
         roster[i].setName("Undergrad # " + i);
         
         roster[i].setTestScore(1, 70 + i);
         roster[i].setTestScore(2, 80 + i);
         roster[i].setTestScore(3, 90 + i);
         
      }

      for (int i = 15; i < DEFAULT_SIZE; i++) {
         roster[i] = new GraduateStudent();
         roster[i].setName("Grad # " + i);
         
         roster[i].setTestScore(1, 80 + i);
         roster[i].setTestScore(2, 85 + i);
         roster[i].setTestScore(3, 90 + i);
         
      }

      studentCount = DEFAULT_SIZE;

      return true;
    }
}
