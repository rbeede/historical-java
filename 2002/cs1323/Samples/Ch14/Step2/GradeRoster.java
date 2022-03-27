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

        System.out.println("Inside GradeRoster constructor");    //TEMP
        System.out.println(courseTitle);                         //TEMP
        System.out.println(courseInstructor);                    //TEMP
        System.out.println("roster length: " + roster.length);   //TEMP
    }


/***************************
    Public Methods:

            String      getTitle        (           )

 ***************************/

    public String getTitle( )
    {
        return courseTitle;
    }

}
