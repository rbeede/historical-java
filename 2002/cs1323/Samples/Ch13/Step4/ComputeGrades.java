/*
    class ComputeGrades

    The top level object for managing all other objects in the program

*/

import java.io.*;
import java.awt.*;
import java.util.*;   // for StringTokenizer
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
    
//   private MessageBox  messageBox;
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

        Student  newStudentWithData ( String, StringTokenizer );
        Student  createStudent      ( String )
        boolean  buildRoster        ( String )

        void     computeGrade       ( )

        void     printResult        ( )
        boolean  readData           ( )
 
 ***************************/

   /*
      Method:       newStudentWithData

      Purpose:      Create a new student of the specified type by reading
                    data from the parser provided.

      Parameters:   String          [type of student to create]
                    StringTokenizer [input line parser]
      
      Returns:      Student  [null if input line is invalid]
   */

   private Student newStudentWithData(String type, StringTokenizer parser)
   {
       Student student;
       
       try {
          if (type.equals("U")) {
             student = new UndergraduateStudent();
          }
          else {
             student = new GraduateStudent();
          }
          
          //set the student name
          String  firstName   = parser.nextToken();
          String  lastName    = parser.nextToken();

          student.setName(firstName + " " + lastName);

          //set the student test scores
          for (int testNum = 1; testNum <= Student.NUM_OF_TESTS; testNum++) {
             student.setTestScore( testNum, Convert.toInt(parser.nextToken()));
          }
       }
       catch (Exception e) { //either parser.nextToken() or
                             //Convert.toInt(...)  thrown exception
          student = null;
       }
       
       return student;

    }

   /*
      Method:       createStudent

      Purpose:      Called when one of the five buttons is clicked
                    Perform the specified calculation and display
                    the result.

      Parameters:   String [input line]
      
      Returns:      Student [null if input line is not valid]
   */
   
   private Student createStudent(String line) 
   {
      Student          student;
      StringTokenizer  parser = new StringTokenizer(line);
      String           type;
      
      try {
         type = parser.nextToken();
         
         if (type.equals("U") || type.equals("G")) {
            student = newStudentWithData(type, parser);
         }
         else {
            student = null;
         }
      }
      catch (NoSuchElementException e) { //no token
         student = null;
      }
      
      return student;
   }
         

   /*
      Method:       buildRoster

      Purpose:      Build a student roster from the specified file.

      Parameters:   String [input file name]
      
      Returns:      boolean [true if successful]
   */

   private boolean buildRoster(String filename)
   {
      String  inputLine;
      Student student;

      boolean status   = true;
      boolean done     = false;

      try {
         inFile = new File(filename);
         fileReader = new FileReader(inFile);
         bufReader = new BufferedReader(fileReader);

         while ( !done ) {

            inputLine = bufReader.readLine(); //read one line
            
            if (inputLine.equalsIgnoreCase("END")) {
               done = true;
            }
            else {
               student = createStudent(inputLine);
               
               if (student != null) {
                  roster[studentCount] = student;
                  studentCount++;
               }
            }
         } // while
         
         bufReader.close();
      }
      catch (IOException e) {
          status = false;
      }

      return status;
   }

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
      //get file to open
      FileDialog fileBox = new FileDialog(this, "Open File", FileDialog.LOAD);
      
      fileBox.setVisible(true);
      String filename = fileBox.getDirectory() + fileBox.getFile();
      
      if (filename != null) {
         return buildRoster(filename);
      }
      else {
         return false;
      }
    }
}
