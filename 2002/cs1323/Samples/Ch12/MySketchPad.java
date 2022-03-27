/*
    class MySketchPad

    An alternative implementation of the SkethcPad class
    from the javabook package.
*/

import java.awt.*;
import java.awt.event.*;
import javabook.*;

class MySketchPad extends MainWindow implements MouseListener, MouseMotionListener
{

/******************
   Data Members
******************/

   private static final int FRAME_WIDTH    = 450;
   private static final int FRAME_HEIGHT   = 300;
   private static final int FRAME_X_ORIGIN = 50;
   private static final int FRAME_Y_ORIGIN = 50;

   private int last_x;
   private int last_y;

/******************
   Constructor
******************/

   public MySketchPad()
   {
      //set frame properties
      setTitle     ("SketchPad For YOur Doodle Art");
      setSize      (FRAME_WIDTH, FRAME_HEIGHT);
      setResizable (false);
      setLocation  (FRAME_X_ORIGIN, FRAME_Y_ORIGIN);

      last_x = last_y = 0;

      addMouseListener( this );
      addMouseMotionListener( this );
   }

/******************************
    Mouse Event Handling
*******************************/

   public void mousePressed(MouseEvent e)
   {
      if (e.isMetaDown()) {
         //erase the content if it is a rightbutton
         Graphics g = getGraphics();
         Rectangle r = getBounds();
         g.clearRect(0, 0, r.width, r.height);
         g.dispose();
      }
      else {
         //reset for a new mouse drag
         last_x = e.getX();
         last_y = e.getY();
      }
   }

   public void mouseReleased(MouseEvent e) { }
   public void mouseClicked(MouseEvent e) { }
   public void mouseEntered(MouseEvent e) { }
   public void mouseExited(MouseEvent e) { }
    
/******************************
    Mouse Motion Event Handling
*******************************/

   public void mouseDragged(MouseEvent e)
   {
      //process the close box event
      int x = e.getX();
      int y = e.getY();

      if (!e.isMetaDown()) { //don't process right button drag
//      if (!e.isPopupTrigger()) { //don't process right button drag
         Graphics g = getGraphics();
         g.drawLine(last_x, last_y, x, y);
         last_x = x;
         last_y = y;
         g.dispose();
      }
   }

   public void mouseMoved(MouseEvent e) { }

}
