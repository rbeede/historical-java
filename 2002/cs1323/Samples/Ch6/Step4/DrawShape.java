/*
    Class DrawShape (Step 4)

    The top level object for managing all other objects in the program
*/

import javabook.*;

class DrawShape
{

/*********************
   Data Members
*********************/

   private MainWindow  mainWindow;
   private OutputBox   outputBox;
   
   private final int   LINE        = 0,
                       CIRCLE      = 1,
                       RECTANGLE   = 2;

   private ListBox     shapeListBox;

   private int         selectedShape;
   
   private final int   MAGENTA     = 0,
                       CYAN        = 1,
                       RED         = 2,
                       BLUE        = 3,
                       GREEN       = 4;
   
   private ListBox     colorListBox;

   private int         selectedColor;
   
   private InputBox    inputBox;
   private MessageBox  messageBox;
   
   private boolean     canDraw;  //false if there an input error

   private int    originX,
                  originY,
                  endX,
                  endY,
                  radius,
                  width,
                  height;
   
      
/*********************
   Constructor
*********************/

   public DrawShape()
   {
      mainWindow  = new MainWindow("Drawing Shape");
      outputBox   = new OutputBox(mainWindow);

      shapeListBox = new ListBox(mainWindow, "Select Shape:");
      shapeListBox.addItem("Line");
      shapeListBox.addItem("Circle");
      shapeListBox.addItem("Rectangle");
      
      colorListBox = new ListBox(mainWindow, "Select Color:");
      colorListBox.addItem("Magenta");
      colorListBox.addItem("Cyan");
      colorListBox.addItem("Red");
      colorListBox.addItem("Blue");
      colorListBox.addItem("Green");
      
      inputBox   = new InputBox(mainWindow);
      messageBox = new MessageBox(mainWindow);

      mainWindow.show();
      outputBox.show();
   }

/*********************
   Public Methods
   
      void start ( )
      
*********************/

   public void start()
   {
      describeProgram();   //tell the user what the program does
      selectShape();       //let the user select a shape
      selectColor();       //let the user select a color
      selectDimension();   //let the user set the position & size
      draw();              //draw the selected shape
   }

/*********************
   Private Methods
   
      void describeProgram ( )
      void selectShape     ( )
      void selectColor     ( )
      void selectDimension ( )
      void draw            ( )
      
      void getCircleDimension     ( )
      void getLineDimension       ( )
      void getRectangleDimension  ( )
      
*********************/

   /*  Method:       describeProgram
   
       Purpose:      Describe waht the program does

       Parameters:   None
                      
       Returns:      None
   */
   
   private void describeProgram()
   {
      outputBox.printLine("Inside describeProgram");
   }

   /*  Method:       draw
   
       Purpose:      Draw the selected shape in the chosen color, position
                     size.

       Parameters:   None
                      
       Returns:      None
   */
   
   private void draw()
   {
      outputBox.printLine("Inside draw");
   }

   /*  Method:       selectColor
   
       Purpose:      Let the user select the color from a ListBox
                     size.

       Parameters:   None
                      
       Returns:      None
   */
   
   private void selectColor()
   {
      outputBox.printLine("Inside selectColor");  //TEMP
      selectedColor = colorListBox.getSelectedIndex();
      outputBox.printLine("Selected Color: " + selectedColor);  //TEMP
   }

   /*  Method:       selectDimension
   
       Purpose:      Let the user select the size and position 
                     using InputBox

       Parameters:   None
                      
       Returns:      None
   */
   
   private void selectDimension()
   {
      switch (selectedShape) {
         
         case ListBox.CANCEL:  // user canceled, so do nothing
               canDraw = false;
               break;

         case ListBox.NO_SELECTION:  // no shape selected
               canDraw = false;
               break;

         case LINE:
               getLineDimension();
               break;
               
         case CIRCLE:
               getCircleDimension();
               break;
               
         case RECTANGLE:
               getRectangleDimension();
               break;
               
         default:
               messageBox.show("ListBox error");
               canDraw = false;
               break;
      }
   }

   /*  Method:       selectShape
   
       Purpose:      Let the user select the shape from the ListBox

       Parameters:   None
                      
       Returns:      None
   */
   
   private void selectShape()
   {
      outputBox.printLine("Inside selectShape");  //TEMP
      selectedShape = shapeListBox.getSelectedIndex();
      outputBox.printLine("Selected Shape: " + selectedShape);  //TEMP
   }

   /*  Method:       getCircleDimension
   
       Purpose:      Let the user enter the circle's center point 
                     and radius

       Parameters:   None
                      
       Returns:      None
   */
   
   private void getCircleDimension()
   {
      originX  = inputBox.getInteger("X-coord of the center");
      originY  = inputBox.getInteger("Y-coord of the center");
      radius   = inputBox.getInteger("Radius of the circle");

      //make sure everything is positive
      if (originX < 0 || originY < 0 || radius < 0) {
         messageBox.show("Negative number is entered. " +
                         "Cannot draw a circle.");
         canDraw = false;
      }
      else { //input okay
         canDraw = true;
         outputBox.printLine("circle dimension okay");  //TEMP
      }
   }
   
   /*  Method:       getLineDimension
   
       Purpose:      Let the user enter the end points of the line

       Parameters:   None
                      
       Returns:      None
   */

   private void getLineDimension()
   {
      originX = inputBox.getInteger("X-coord of starting point");
      originY = inputBox.getInteger("Y-coord of starting point");
      endX    = inputBox.getInteger("X-coord of ending point");
      endY    = inputBox.getInteger("Y-coord of ending point");

      //make sure everything is positive
      if (originX < 0 || originY < 0 || endX < 0 || endY < 0) {
         messageBox.show("Negative number is entered. " +
                         "Cannot draw a line.");
         canDraw = false;
      }
      else { //input okay
         canDraw = true;
         outputBox.printLine("line dimension okay");  //TEMP
      }
   }
   
   /*  Method:       getRectangleDimension
   
       Purpose:      Let the user enter the upper left corner, width
                     and height of the rectangle

       Parameters:   None
                      
       Returns:      None
   */

   private void getRectangleDimension()
   {
      originX  = inputBox.getInteger("X-coord of origin");
      originY  = inputBox.getInteger("Y-coord of origin");
      width    = inputBox.getInteger("Rectangle width");
      height   = inputBox.getInteger("Rectangle height");

      //make sure everything is positive
      if (originX < 0 || originY < 0 || width < 0 || height < 0) {
         messageBox.show("Negative number is entered. " +
                         "Cannot draw a rectangle.");
         canDraw = false;
      }
      else { //input okay
         canDraw = true;
         outputBox.printLine("rectangle dimension okay");  //TEMP
      }
   }
}


