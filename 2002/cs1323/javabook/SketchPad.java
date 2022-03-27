package javabook;

import java.awt.*;
import java.awt.event.*;

public class SketchPad extends MainWindow implements MouseListener, MouseMotionListener
{
   private int last_x = 0;
   private int last_y = 0;

   public SketchPad()
   {
      super("SketchPad For Your Doodle Art");
      initialize();
   }

   public SketchPad(String title)
   {
      super(title);
      initialize();
   }

   public void mouseDragged(MouseEvent e)
   {
      if (!e.isMetaDown()) { //don't process right button drag

         int x = e.getX();
         int y = e.getY();

         Graphics g = getGraphics();
         g.drawLine(last_x, last_y, x, y);
         last_x = x;
         last_y = y;
         g.dispose();
      }
   }

   public void mouseMoved(MouseEvent e)
   {
   }

   public void mouseClicked(MouseEvent e)
   {
   }

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

   public void mouseReleased(MouseEvent e)
   {
   }

   public void mouseEntered(MouseEvent e)
   {
   }

   public void mouseExited(MouseEvent e)
   {
   }
    
   private void initialize()
   {
      setSize(400,300);
      moveToCenter();
      addMouseListener(this);
      addMouseMotionListener(this);
   }
}
