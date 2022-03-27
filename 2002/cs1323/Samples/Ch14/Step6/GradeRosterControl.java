/*
    GradeRosterControl (Step 6)

    Manages a GradeRoster object.

    Step 6: Implement the editTestScores and computeGrades methods.

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

    private StudentNameDialog   editNameBox;
    private TestScoreDialog     editScoreBox;

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

        editNameBox     = new StudentNameDialog(owner);
        editScoreBox    = new TestScoreDialog(owner,Student.NUM_OF_TESTS);
    }

/***************************
    Public Methods:

            void        addStudent      (               )
            void        deleteStudent   (               )

            void        computeGrades   (               )

            void        editStudentName (               )
            void        editTestScores  (               )

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

    public void deleteStudent(  )
    {

        String studentName = inputBox.getString("Enter the name of student to delete:");

        Student student = gradeRoster.getStudent(studentName);

        if (student == null) {
            messageBox.show("Cannot delete: Student not found.");
        }
        else {
            gradeRoster.deleteStudent(student);
        }
    }

    public void computeGrades( )
    {
        gradeRoster.computeGrades();
    }

    public void editStudentName()
    {
        editNameBox.setVisible(true);

        if (!editNameBox.isCanceled()) {
            String currentName = editNameBox.getCurrentName();
            String newName     = editNameBox.getNewName();

            Student student = gradeRoster.getStudent(currentName);
            Student duplicate = gradeRoster.getStudent(newName);

            if (student == null) {
                messageBox.show("Cannot locate " + currentName);
            }
            else if (duplicate != null) {
                messageBox.show("There is already a student named " +
                                newName + " in the roster.");
            }
            else {
                student.setName(newName);
            }
        }
    }

    public void editTestScores()
    {
        String name = inputBox.getString("Enter Student Name:");

        Student student = gradeRoster.getStudent(name);

        if (student == null) {
            messageBox.show("Cannot locate " + name);
        }
        else {

            editScoreBox.show(student);

            if (!editScoreBox.isCanceled()) {

                int[] score = editScoreBox.getTestScores();

                student.setTestScores(score);
            }
        }
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
