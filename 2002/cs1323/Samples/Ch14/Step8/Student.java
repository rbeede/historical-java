import javabook.*;
import java.io.*;

abstract class Student implements Serializable
{

/***************************
    Data Members:
 ***************************/
    public      final static int NUM_OF_TESTS = 3;
    protected   String           name;
    protected   int[]            test;
    protected   String           courseGrade;


/***************************
    Constructors:
 ***************************/

    public Student( )
    {
        this("Unnamed");
    }

    public Student(String studentName)
    {
        name = studentName;
        test = new int[NUM_OF_TESTS];
        courseGrade = "****";
    }


/***************************
    Public Methods:


        static      int         numberOfValues      (           )
        abstract    void        computeCourseGrade  (           )

                    String      getCourseGrade      (           )
                    String      getName             (           )
                    int         getTestScore        ( int       )
                    String[]    getValues           (           )


                    void        setName             ( String    )
                    void        setTestScore        ( int, int  )
                    void        setTestScores       ( int[]     )

 ***************************/

    public static int numberOfValues()
    {
        return NUM_OF_TESTS + 2;
    }


    abstract void computeCourseGrade();


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

    public String[] getValues( )
    {
        String value[] = new String[NUM_OF_TESTS + 2];

        value[0] = name;

        for (int i = 0; i < test.length; i++) {
            value[i+1] = Convert.toString(test[i]);
        }

        value[value.length - 1] = courseGrade;

        return value;
    }

    public void setName(String newName)
    {
        name = newName;
    }

    public void setTestScore(int testNumber, int testScore)
    {
        test[testNumber-1] = testScore;
    }

    public void setTestScores(int[] score)
    {
        for (int i = 0; i < test.length; i++) {
            test[i] = score[i];
        }
    }
}
