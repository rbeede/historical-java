/*
    GradeRosterDisplay

    An object to display the grade roster using OutputBox.
*/

import javabook.*;
import java.awt.*;

class GradeRosterDisplay
{

/***************************
    Data Members:
 ***************************/

    private int         widthForColumn[];    //field size for each column
    private String      headingForColumn[];  //column heading for each column
    private OutputBox   outputBox;
    private InputBox    inputBox;

/***************************
    Constructors:
 ***************************/

    public GradeRosterDisplay( Frame owner,
                               int columnCnt,
                               String[] heading,
                               int[] width)
    {
        outputBox        = new OutputBox(owner);
        inputBox         = new InputBox(owner);
        headingForColumn = new String[columnCnt];
        widthForColumn   = new int[columnCnt];

        for (int i = 0; i < columnCnt; i++) {
            headingForColumn[i] = heading[i];
            widthForColumn[i] = width[i];
        }
    }


/***************************
    Public Methods:

            void    displayAll          ( GradeRoster )
            void    displayOne          ( Student     )

 ***************************/

    public void displayAll( GradeRoster roster )
    {
        Student[] student = roster.getAllStudents();
        
        displayHeading();

        for (int i = 0; i < student.length; i++) {
            displayOneRow(student[i]);
        }

        outputBox.show();
    }

    public void displayOne( GradeRoster roster )
    {
       String name;
       Student student;

       name = inputBox.getString("Enter Student Name:");
       student = roster.getStudent(name);

       if (student == null) {
         outputBox.printLine("No student with the given name" +
                            " is in the roster");
       }
       else {
           displayHeading();
           displayOneRow(student);
       }

       outputBox.show();
    }

/***************************
    Private Methods:


            void    displayHeading  (           )
            void    displayOneRow   ( Student   )


 ***************************/


    private void displayHeading()
    {
        for (int i = 0; i < headingForColumn.length; i++) {
            outputBox.print(Format.centerAlign(widthForColumn[i],
                                               headingForColumn[i]));
        }

        outputBox.skipLine(1);
    }

    private void displayOneRow( Student student )
    {
        String oneRow[] = student.getValues();

        for (int i = 0; i < oneRow.length; i++) {
            outputBox.print(Format.centerAlign(widthForColumn[i],
                                               oneRow[i]));
        }

        outputBox.skipLine(1);
    }

}
