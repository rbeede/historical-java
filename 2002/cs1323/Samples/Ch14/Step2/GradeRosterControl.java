/*
    GradeRosterControl (Step 2)

    Manages a GradeRoster object.

    Step 2: Implement the newGradeRoster method.

*/

import java.awt.*;
import javabook.*;

class GradeRosterControl
{
/***************************
    Data Members:
 ***************************/
    private Frame               owner;

    private InputBox            inputBox;

    private GradeRoster         gradeRoster;

/***************************
    Constructors:
 ***************************/

    public GradeRosterControl(Frame mainWindow)
    {
        owner           = mainWindow;

        gradeRoster     = null;

        inputBox        = new InputBox(owner);
    }

/***************************
    Public Methods:

            boolean     hasGradeRoster  (               )

            void        newGradeRoster  (               )

            String      getCourseTitle  (               )

 ***************************/

    public String getCourseTitle()
    {
       return gradeRoster.getTitle();
    }
    
    public boolean hasGradeRoster( )
    {
        return (gradeRoster != null);
    }

    public void newGradeRoster( )
    {
        String title, instructor;
        
        title      = inputBox.getString("Enter Course Title:");
        instructor = inputBox.getString("Enter Course Instructor:");

        gradeRoster = new GradeRoster(title, instructor);
    }

}
