package javabook;

import java.awt.*;
import java.awt.event.*;

//JDK 1.1 Generic Dialog: Abstract superclass of other JavaBook dialogs

public abstract class JavaBookDialog extends Dialog implements WindowListener
{

/***************************
    Data Members:
 ***************************/

   protected Font   font = new Font("Helvetica",Font.PLAIN, 12);
   protected boolean wasMoved = false;

/***************************
    Constructors:
 ***************************/
//why do I have to declare contructors for an abstract class?
    public JavaBookDialog(Frame owner)
    {
        super(owner, true);
        addWindowListener(this);
    }

    public JavaBookDialog(Frame owner, boolean modal)
    {
        super(owner,modal);
        addWindowListener(this);
    }

/***************************
    Public Methods:

            public void windowOpened(WindowEvent e) 
            public void windowClosing(WindowEvent e)
            public void windowClosed(WindowEvent e)
            public void windowIconified(WindowEvent e)
            public void windowDeiconified(WindowEvent e)
            public void windowActivated(WindowEvent e)
            public void windowDeactivated(WindowEvent e)

            boolean handleEvent ( Event ) Note: Do not call this method
            void    show        (       )

 ***************************/

    public void windowOpened(WindowEvent e) 
    {
    }

    public void windowClosing(WindowEvent e)
    {
        dispose();
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

    public void setVisible(boolean view)
    {
        if (view) {
          adjustSize();
          if (!wasMoved) {
          	moveToCenter();
          	wasMoved = false;
          }
        }

        super.setVisible(view);
    }

/***************************
    Protected Methods:

            void    adjustSize  (   )
            void    moveToCenter(   )

 ***************************/

   //Adjust the dialog dimension based on the components the dialog contains
   abstract protected void adjustSize();


   protected void moveToCenter()
   {
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       Rectangle selfBounds = getBounds();

       setLocation((screenSize.width - selfBounds.width) / 2,
                   (screenSize.height - selfBounds.height) / 2);
   }

}