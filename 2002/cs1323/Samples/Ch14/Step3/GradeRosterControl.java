/*
    GradeRosterControl (Step 3)

    Manages a GradeRoster object.

    Step 3: Implement the addStudent methods.

*/

import java.awt.*;
import javabook.*;

class GradeRosterControl
{
/***************************
    Data Members:
 ***************************/
    private static final int GRAD_STUDENT       = ResponseBox.BUTTON1;
    private static final int UNDERGRAD_STUDENT  = ResponseBox.BUTTON2;

    private Frame               owner;

    private ResponseBox         selectionBox;
    private InputBox            inputBox;
    private MessageBox          messageBox;

    private GradeRoster         gradeRoster;


/***************************
    Constructors:
 ***************************/

    public GradeRosterControl(Frame mainWindow)
    {
        owner           = mainWindow;

        gradeRoster     = null;

        selectionBox    = new ResponseBox(owner);
        selectionBox.setLabel(UNDERGRAD_STUDENT,"Undergraduate");
        selectionBox.setLabel(GRAD_STUDENT,"  Graduate   ");

        inputBox        = new InputBox(owner);
        messageBox      = new MessageBox(owner);

    }

/***************************
    Public Methods:

            void        addStudent      (               )

            boolean     hasGradeRoster  (               )

            void        newGradeRoster  (               )

            String      getCourseTitle  (               )

 ***************************/

    public void addStudent( )
    {
        //input student information
        int answer = selectionBox.prompt("Select the student type.");

        if (answer == GRAD_STUDENT) {
            addGraduateStudent();
        }
        else if (answer == UNDERGRAD_STUDENT) {
            addUndergraduateStudent();
        }
        //else canceled, so do nothing
    }

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

/***************************
    Private Methods:

            void    addGraduateStudent      (           )
            void    addUndergraduateStudent (           )

 ***************************/

    private void addGraduateStudent( )
    {
        String studentName = inputBox.getString("Enter Graduate Student Name:");

        Student student = gradeRoster.getStudent(studentName);

        if (student == null) {
            GraduateStudent newGradStudent = new GraduateStudent(studentName);
            gradeRoster.addStudent(newGradStudent);
        }
        else {
            messageBox.show("Duplicate name exists." +
                            " No new student is added.");
        }
    }

    private void addUndergraduateStudent( )
    {
        String studentName = inputBox.getString("Enter Undergraduate Student Name:");

        Student student = gradeRoster.getStudent(studentName);

        if (student == null) {
            UndergraduateStudent newUndergradStudent = new UndergraduateStudent(studentName);
            gradeRoster.addStudent(newUndergradStudent);
        }
        else {
            messageBox.show("Duplicate name exists." +
                            " No new student is added.");
        }
    }

}
