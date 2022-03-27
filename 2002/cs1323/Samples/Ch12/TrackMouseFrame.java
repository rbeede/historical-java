/*
    class TrackMouseFrame

    This program tracks the mouse click events. When a mouse button (any
    mouse button supported by the platform) is clicked, the
    location where the mouse button is clicked is displayed in an outputBox.
*/

import java.awt.*;
import java.awt.event.*;
import javabook.*;

class TrackMouseFrame extends Frame implements MouseListener
{

/******************
   Data Members
******************/

   private static final int FRAME_WIDTH    = 450;
   private static final int FRAME_HEIGHT   = 300;
   private static final int FRAME_X_ORIGIN = 50;
   private static final int FRAME_Y_ORIGIN = 50;

   private OutputBox outputBox;

/******************
   Constructor
******************/

   public TrackMouseFrame()
   {
      //set frame properties
      setTitle     ("TrackMouseFrame");
      setSize      (FRAME_WIDTH, FRAME_HEIGHT);
      setResizable (false);
      setLocation  (FRAME_X_ORIGIN, FRAME_Y_ORIGIN);

      //create an OutputBox for printing out
      //the mouse click points
      outputBox = new OutputBox(this);

      //register self as a mouse event listener and
      //a ProgramTerminator as a window event listener
      addMouseListener( this );
      addWindowListener( new ProgramTerminator() );
   }

   public void start()
   {
      setVisible(true);
      outputBox.setVisible(true);
   }

/******************************
    Mouse Event Handling
*******************************/

   public void mouseClicked(MouseEvent event)
   {
      if (event.getClickCount() == 2) {
         //erase the contents of outputBox
         outputBox.clear();
      }
      else {
         int x, y;

         x = event.getX(); //get the x and y coordinates of
         y = event.getY(); //the mouse click point

         outputBox.printLine("[" + x + "," + y + "]");
      }
   }

   public void mouseEntered   ( MouseEvent event ) { }
   public void mouseExited    ( MouseEvent event ) { }
   public void mousePressed   ( MouseEvent event ) { }
   public void mouseReleased  ( MouseEvent event ) { }

/******************************
    Main Method
*******************************/

	public static void main (String args[])
    {
		TrackMouseFrame mainWindow = new TrackMouseFrame();
    	mainWindow.show();
    }
}
