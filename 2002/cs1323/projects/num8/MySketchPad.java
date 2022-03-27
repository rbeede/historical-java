/*
    class MySketchPad

    An alternative implementation of the SkethcPad class
    from the javabook package.

    Modified on 4/18/00 with menu items
    PROGRAMMER:		RODNEY BEEDE
    ID NUM:		0000
    SEC:		222
    PROJECT:		8
    DUE:		4-21-2K
*/

import java.awt.*;
import java.awt.event.*;
import javabook.*;

class MySketchPad extends MainWindow implements MouseListener, MouseMotionListener, ActionListener
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

   //Used for controlling created menus
   private Menu fileMenu;
   private Menu editMenu;
   private Menu colorMenu;

   //Define some colors
   private Color objColorRed = new Color(255,0,0);
   private Color objColorGreen = new Color(0,255,0);
   private Color objColorBlue = new Color(0,0,255);
   private Color objColorPink = new Color(255,175,175);
   private Color objColorBlack = new Color(0,0,0);

   //This decides what color we draw with
   private Color objColor;

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

      //Call method to create a File menu
      createFileMenu();
      //Call method to create an Edit menu
      createEditMenu();
      //Call method to create a Color menu
      createColorMenu();

      //Add the menus to the frame's menubar
      MenuBar menuBar = new MenuBar();
      setMenuBar(menuBar);  //Set this menubar to the frame
      menuBar.add(fileMenu);  //Add the File menu
      menuBar.add(editMenu);  //Add the Edit menu
      menuBar.add(colorMenu);  //Add the Color menu

      //Set the default drawing color to black
      objColor = objColorBlack;
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
         g.setColor( objColor );  //Set the correct color
         g.drawLine(last_x, last_y, x, y);
         last_x = x;
         last_y = y;
         g.dispose();
      }
   }

   public void mouseMoved(MouseEvent e) { }


   /*
      Method:    createFileMenu

      Purpose:   Makes a File menu that has a Quit item.  That item allows the user to quit.

      Returns:   None

      Parms:     None
   */
   private void createFileMenu( ) {
      MenuItem item;  //Used for the creation of items for adding to the menu

      fileMenu = new Menu( "File" );  //Create the 'File' menu

      item = new MenuItem( "Quit" );   //Create a 'Quit' item under the File menu
      item.addActionListener( this );   //Allow event handling of the menu
      fileMenu.add( item );  //Put the 'Quit' item on the 'File' menu
   }


   /*
      Method:    createEditMenu

      Purpose:   Makes a Edit menu that has a Erase item.  That item allows the user to erase their drawing.

      Returns:   None

      Parms:     None
   */
   private void createEditMenu( ) {
      MenuItem item;  //Used for the creation of items for adding to the menu

      editMenu = new Menu( "Edit" );  //Create the 'Edit' menu

      item = new MenuItem( "Erase" );   //Create a 'Erase' item under the Edit menu
      item.addActionListener( this );   //Allow event handling of the menu
      editMenu.add( item );  //Put the 'Erase' item on the 'Edit' menu
   }


   /*
      Method:    createColorMenu

      Purpose:   Makes a Color menu that has a these items:
		 Red, Green, Blue, Pink, Black
		 These items change the color of the drawing tool

      Returns:   None

      Parms:     None
   */
   private void createColorMenu( ) {
      MenuItem item;  //Used for the creation of items for adding to the menu

      colorMenu = new Menu( "Color" );  //Create the 'Color' menu

      item = new MenuItem( "Red" );   //Create a 'Red' item under the Color menu
      item.addActionListener( this );   //Allow event handling of the menu
      colorMenu.add( item );  //Put the 'Red' item on the 'Color' menu

      item = new MenuItem( "Green" );   //Create a 'Green' item under the Color menu
      item.addActionListener( this );   //Allow event handling of the menu
      colorMenu.add( item );  //Put the 'Green' item on the 'Color' menu

      item = new MenuItem( "Blue" );   //Create a 'Blue' item under the Color menu
      item.addActionListener( this );   //Allow event handling of the menu
      colorMenu.add( item );  //Put the 'Blue' item on the 'Color' menu

      item = new MenuItem( "Pink" );   //Create a 'Pink' item under the Color menu
      item.addActionListener( this );   //Allow event handling of the menu
      colorMenu.add( item );  //Put the 'Pink' item on the 'Color' menu

      item = new MenuItem( "Black" );   //Create a 'Black' item under the Color menu
      item.addActionListener( this );   //Allow event handling of the menu
      colorMenu.add( item );  //Put the 'Black' item on the 'Color' menu
   }


   /*
      Method:    actionPerformed

      Purpose:   Grabs an action performed by the user.  Primarly a menu selection

      Returns:   None

      Parms:     (ActionEvent) -- The ActionEvent object that contains the occured event
   */
   public void actionPerformed( ActionEvent event ) {
       String menuName;  //Holds the name of the select menu option

       //Get the selection menu action
       menuName = event.getActionCommand();

       //Figure out which menu item was selected
       if( menuName.equals("Quit") ) {		//File menu, Quit item selected
            System.exit(0);  //Terminate program
       }
       else if( menuName.equals("Erase") ) {	//Edit menu, Erase item selected
            //Erase the current drawing
	    Graphics g = getGraphics();   //Get the current graphics board
            Rectangle r = getBounds();  //Set the bounds of a Rectangle to the graphics board size
            g.clearRect(0, 0, r.width, r.height);  //Draw a clear rectangle, clearing out any graphics
            g.dispose();  //Release the memory used to hold the rectangle object
       }
       else if( menuName.equals("Red") ) {	//Color menu, Red item selected
           //Change the color to red for drawing, we do this by simply modifying the
           //global objColor variable
           objColor = objColorRed;
       }
       else if( menuName.equals("Green") ) {	//Color menu, Green item selected
           //Change the color to green for drawing, we do this by simply modifying the
           //global objColor variable
           objColor = objColorGreen;
       }
       else if( menuName.equals("Blue") ) {	//Color menu, Blue item selected
           //Change the color to blue for drawing, we do this by simply modifying the
           //global objColor variable
           objColor = objColorBlue;
       }
       else if( menuName.equals("Pink") ) {	//Color menu, Pink item selected
           //Change the color to pink for drawing, we do this by simply modifying the
           //global objColor variable
           objColor = objColorPink;
       }
       else if( menuName.equals("Black") ) {	//Color menu, Black item selected
           //Change the color to black for drawing, we do this by simply modifying the
           //global objColor variable
           objColor = objColorBlack;
       }
       else {	//Some other action performed
           ;  //Do nothing
       }
   }  //End of actionPerformed method
}  //End of class
