abstract class Student
{

/***************************
    Data Members:
 ***************************/
   protected   final static int NUM_OF_TESTS = 3;
   protected   String           name;
   protected   int[]            test;
   protected   String           courseGrade;


/***************************
    Constructors:
 ***************************/

   public Student( )
   {
      this("No Name");
   }

   public Student(String studentName)
   {
      name = studentName;
      test = new int[NUM_OF_TESTS];
      courseGrade = "****";
   }

/***************************
    Public Methods:

        abstract    void        computeCourseGrade  (           )

                    String      getCourseGrade      (           )
                    String      getName             (           )
                    int         getTestScore        ( int       )

                    void        setName             ( String    )
                    void        setTestScore        ( int, int  )

 ***************************/

   public abstract void computeCourseGrade();

   public String getCourseGrade( )
   {
      return courseGrade;
   }

   public String getName( )
   {
      return name;
   }

   public int getTestScore(int testNumber)
   {
      return test[testNumber-1];
   }

   public void setName(String newName)
   {
      name = newName;
   }

   public void setTestScore(int testNumber, int testScore)
   {
      test[testNumber-1] = testScore;
   }

}
