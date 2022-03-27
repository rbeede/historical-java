/*
    GradeRosterStorage

    An object to perform file i/o for a GradeRoster object.

*/

import java.awt.*;
import java.io.*;
import javabook.*;

class GradeRosterStorage
{

/***************************
    Data Members:
 ***************************/

    private Frame       owner;
    private String      filename;

/***************************
    Constructors:
 ***************************/

    public GradeRosterStorage(Frame mainWindow)
    {
        owner = mainWindow;
    }


/***************************
    Public Methods:

            GradeRoster open            (               )
            boolean     save            ( GradeRoster   )
            boolean     saveAs          ( GradeRoster   )

 ***************************/

    public GradeRoster open( )
    {
        GradeRoster roster;

        FileDialog fileDialog = new FileDialog(owner,
                                        "Load Grade Roster...",
                                        FileDialog.LOAD);
        fileDialog.show();

        filename = fileDialog.getFile();

        if (filename == null) {
            roster = null;
        }
        else {
            roster = loadData();
        }
        
        return roster;
    }

    public boolean save( GradeRoster roster)
    {
        boolean status;

        if (filename == null) {
            status = saveAs(roster);
        }
        else { //save the data
            status = saveData(roster);
        }
        return status;

    }

    public boolean saveAs( GradeRoster roster )
    {
        boolean status;

        FileDialog fileDialog = new FileDialog(owner,
                                        "Save Grade Roster...",
                                        FileDialog.SAVE);
        fileDialog.show();

        filename = fileDialog.getFile();

        if (filename == null) {
            status = false;
        }
        else {
            status = saveData(roster);
        }
        
        return status;
    }


/***************************
    Private Methods:

            GradeRoster loadData    (               )
            boolean     saveData    ( GradeRoster   )

 ***************************/

    private GradeRoster loadData( )
    {
        GradeRoster roster;

        try {

            //setup file and stream
            File inFile = new File(filename);
            FileInputStream inFileStream = new FileInputStream(inFile);
            ObjectInputStream inObjectStream = new ObjectInputStream(inFileStream);

            roster = (GradeRoster) inObjectStream.readObject();

            inObjectStream.close();
        }
        catch (IOException e) {
            roster = null;
            System.out.println(e);
        }
        catch (ClassNotFoundException e) {
            roster = null;
            System.out.println(e);
        }

        return roster;
    }

    private boolean saveData( GradeRoster roster )
    {
        boolean status = true;

        try {
            //setup the streams
            File outFile = new File(filename);
            FileOutputStream outFileStream = new FileOutputStream(outFile);
            ObjectOutputStream outObjectStream  = new ObjectOutputStream(outFileStream);

            outObjectStream.writeObject(roster);

            outObjectStream.close();
        }
        catch (IOException e) {
            status = false;
            System.out.println(e);
        }

        return status;
    }
}

