/*
    GradeRoster

    Manages an array (roster) of Student objects.

*/

import java.io.*;

class GradeRoster implements Serializable
{

/***************************
    Data Members:
 ***************************/

    private static final int DEFAULT_LENGTH = 25;
    private static final float EXPAND_FACTOR = 1.5f;

    private String      courseTitle;
    private String      courseInstructor;
    private Student[]   roster;
    private int         rosterSize; //number of Students in roster


/***************************
    Constructors:
 ***************************/

    public GradeRoster(String title, String instructor)
    {
        this(title, instructor, DEFAULT_LENGTH);
    }

    public GradeRoster(String title, String instructor, int length)
    {
        courseTitle      = title;
        courseInstructor = instructor;
        roster           = new Student[length];
        rosterSize       = 0;
    }


/***************************
    Public Methods:

            void        addStudent      ( Student   )

            Student     getStudent      ( String    )
            String      getTitle        (           )

 ***************************/

    public void addStudent( Student student)
    {
         //if there's no more space to add the student
         //make the roster larger
         if (rosterSize == roster.length) {
            expandRoster();
         }
         roster[rosterSize] = student;
         rosterSize++;

         System.out.println("Inside addStudent");                  //TEMP
         System.out.println("Position added: " + (rosterSize - 1) );  //TEMP
    }

    public Student getStudent(String studentName)
    {
        int     loc     = 0;

        while (loc < rosterSize   //still more elements
               &&
               !studentName.equals(roster[loc].getName()))
                                   //not a duplicate name
        {
            loc++;
        }

        if (loc < rosterSize) {
           return roster[loc];
        }
        else {
           return null;
        }
    }

    public String getTitle( )
    {
        return courseTitle;
    }

/***************************
    Private Methods:

            void        expandRoster    (           )

 ***************************/

    private void expandRoster( )
    {
        //expand the roster by 50%

        int newSize = (int) (roster.length * EXPAND_FACTOR);

        Student newArray[] = new Student[newSize];

        for (int i = 0; i < roster.length; i++) {
            newArray[i] = roster[i];
        }
        
        roster = newArray;

        System.out.println("Inside expandRoster");           //TEMP
        System.out.println("New length: " + roster.length);  //TEMP
    }

}
