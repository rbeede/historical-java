/*
   Program GradeRosterMainWindow (Step 2)

   This program maintains a class roster of student names and grades.

   Step 2: Define and add GradeRosterControl to the program. Implement the
           New menu choice

*/

import java.awt.*;
import java.awt.event.*;
import javabook.*;


class GradeRosterMainWindow extends MainWindow implements ActionListener
{

/***************************
    Data Members:
 ***************************/
    private static final int SAVE    = ResponseBox.BUTTON1;
    private static final int NO_SAVE = ResponseBox.BUTTON2;
    private static final int CANCEL  = ResponseBox.BUTTON3;

    private MessageBox messageBox;

    private GradeRosterControl  gradeRosterControl;

    ResponseBox saveBox;


/***************************
    Constructors:
 ***************************/

    public GradeRosterMainWindow()
    {
        createMenu();

        messageBox  = new MessageBox(this);

        saveBox = new ResponseBox(this,3);
        saveBox.setLabel(SAVE,"Save");
        saveBox.setLabel(NO_SAVE,"Don't Save");
        saveBox.setLabel(CANCEL,"Cancel");

        gradeRosterControl = new GradeRosterControl(this);

    }


/***************************
    Public Methods:

            void    actionPerformed     ( ActionEvent       )

 ***************************/

    public void actionPerformed(ActionEvent event)
    {
        String menuName;

        menuName = event.getActionCommand();

        if (menuName.equals("New")) {
            newRoster();
        }
        else if (menuName.equals("Open...")) {
            openRoster();
        }
        else if (menuName.equals("Save")) {
            saveRoster();
        }
        else if (menuName.equals("Save As...")) {
            saveAsRoster();
        }
        else if (menuName.equals("Quit")) {
            quitProgram();
        }
        else if (menuName.equals("Test Scores...")) {
            editTestScores();
        }
        else if (menuName.equals("Compute Grades")) {
            computeGrades();
        }
        else if (menuName.equals("Student Name...")) {
            editStudentName();
        }
        else if (menuName.equals("Add New Student...")) {
            addNewStudent();
        }
        else if (menuName.equals("Delete Student...")) {
            deleteStudent();
        }
        else if (menuName.equals("All Students")) {
            showAllStudents();
        }
        else if (menuName.equals("One Student...")) {
            showOneStudent();
        }
    }


/***************************
    Private Methods:


            void    addNewStudent       (   )

            void    computeGrades       (   )

            void    createMenu          (   )
            void    createNewRoster     (   )

            void    deleteStudent       (   )

            void    editStudentName     (   )
            void    editTestScores      (   )
 
            void    newRoster        (   )
            void    openRoster       (   )
            void    quitRoster       (   )

            void    saveRoster       (   )
            void    saveAsRoster     (   )

            void    setNewTitle         (   )

            void    showAllStudents     (   )
            void    showOneStudent      (   )

 ***************************/

    private void addNewStudent( )
    {
        messageBox.show("Inside addNewStudent");
    }

    private void computeGrades( )
    {
        messageBox.show("Inside computeGrades");
    }

    private void createMenu()
    {
        MenuItem    item;
        Menu        menu;
        MenuBar     menuBar;

        menuBar = new MenuBar(); //create menubar first

        menu = new Menu("File");        //File Menu
        menuBar.add(menu);

        item = new MenuItem("New");                 //New
        item.addActionListener( this );
        menu.add( item );

        item = new MenuItem("Open...");             //Open...
        item.addActionListener( this );
        menu.add( item );

        item = new MenuItem("Save");                //Save
        item.addActionListener( this );
        menu.add( item );

        item = new MenuItem("Save As...");          //Save As...
        item.addActionListener( this );
        menu.add( item );

        item = new MenuItem("Quit");                //Quit
        item.addActionListener( this );
        menu.add( item );

        menu = new Menu("Edit");        //Edit Menu
        menuBar.add(menu);

        item = new MenuItem("Test Scores...");      //Test Scores...
        item.addActionListener( this );
        menu.add( item );

        item = new MenuItem("Compute Grades");      //Compute Grades
        item.addActionListener( this );
        menu.add( item );

        item = new MenuItem("Student Name...");     //Student Name...
        item.addActionListener( this );
        menu.add( item );

        item = new MenuItem("Add New Student...");  //Add New Student...
        item.addActionListener( this );
        menu.add( item );

        item = new MenuItem("Delete Student...");   //Delete Student...
        item.addActionListener( this );
        menu.add( item );

        menu = new Menu("Show");        //Show Menu
        menuBar.add(menu);

        item = new MenuItem("All Students");        //All Students
        item.addActionListener( this );
        menu.add( item );

        item = new MenuItem("One Student...");      //One Student...
        item.addActionListener( this );
        menu.add( item );

        setMenuBar(menuBar); //set menubar AFTER all menus are added to it

    }


    private void createNewRoster()
    {
        gradeRosterControl.newGradeRoster();

        setNewTitle();
    }

    private void deleteStudent( )
    {
        messageBox.show("Inside deleteStudent");
    }

    private void editStudentName()
    {
        messageBox.show("Inside editStudentName");
    }

    private void editTestScores()
    {
        messageBox.show("Inside editTestScores");
    }

    private void newRoster()
    {
        if (!gradeRosterControl.hasGradeRoster()) {
            createNewRoster();
        }
        else {
            //save current gradeRoster?
            int answer = saveBox.prompt("Save the current grade roster?");

            switch (answer) {
                case SAVE:
                    saveRoster();
                    createNewRoster();
                    break;

                case NO_SAVE:
                    createNewRoster();
                    break;

                case CANCEL:
                    //do nothing
                    break;
            }
        }
    }

    private void openRoster()
    {
        messageBox.show("Inside openRoster");
    }

    private void quitProgram()
    {
        System.exit(0);
    }

    private void saveRoster()
    {
        messageBox.show("Inside saveRoster");
    }

    private void saveAsRoster()
    {
        messageBox.show("Inside saveAsRoster");
    }

    private void showAllStudents()
    {
        messageBox.show("Inside showAllStudents");
    }

    private void showOneStudent()
    {
        messageBox.show("Inside showOneStudent");
    }

    private void setNewTitle()
    {
        String title = gradeRosterControl.getCourseTitle();
        setTitle("C L A S S    R O S T E R   f o r:  " + title);
    }

}
