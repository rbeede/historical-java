package javabook;

import java.awt.*;
import java.awt.event.*;

public class MainWindow extends Frame implements WindowListener
{

 /***************************
    Data Members:
 ***************************/

    Dimension screenSize;


/***************************
    Constructors:
 ***************************/

    public MainWindow()
    {
        super("Sample Java Application");
        initialize();
    }

    public MainWindow(String title)
    {
        super(title);
        initialize();
    }


/***************************
    Public Methods:

            public void windowOpened(WindowEvent e) Note: Do not call this method
            public void windowClosing(WindowEvent e) Note: Do not call this method
            public void windowClosed(WindowEvent e) Note: Do not call this method
            public void windowIconified(WindowEvent e) Note: Do not call this method
            public void windowDeiconified(WindowEvent e) Note: Do not call this method
            public void windowActivated(WindowEvent e) Note: Do not call this method
            public void windowDeactivated(WindowEvent e) Note: Do not call this method

 ***************************/

    public void windowOpened(WindowEvent e) 
    {
    }

    public void windowClosing(WindowEvent e)
    {
         System.exit(0);
    }

    public void windowClosed(WindowEvent e)
    {
    }

    public void windowIconified(WindowEvent e)
    {
    }

    public void windowDeiconified(WindowEvent e)
    {
    }

    public void windowActivated(WindowEvent e)
    {
    }

    public void windowDeactivated(WindowEvent e)
    {
    }

 /***************************
    Protected Methods:

            void moveToCenter (   )

 ***************************/

    protected void moveToCenter()
    {
         Dimension selfBounds = getSize();
         setLocation((screenSize.width - selfBounds.width) / 2,
                     (screenSize.height - selfBounds.height) / 2);
    }

 /***************************
    Private Methods:

            void initialize   (   )

 ***************************/

    private void initialize()
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        screenSize      = toolkit.getScreenSize();

        setSize(screenSize.width-20,screenSize.height-50);
        setBackground(Color.white);
        moveToCenter();
        addWindowListener(this);
    }

}
