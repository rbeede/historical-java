/*
    Class DrawShape (Step 3)

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
      outputBox.printLine("Inside selectDimension");
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

}


