/*
   Program DrawShape
   
   A program to draw the shape (rectangle, circle, or line) selected by the user
   in position, size, and color of the user's specification
   
   Input:   shape
            color
            position
            size
            
   Output:  display the shape drawn in the chosen color at the specified
            position and size.
*/

class DrawShapeMain
{
   public static void main(String args[])
   {
      DrawShape drawShape = new DrawShape();
      drawShape.start();
   }
}                           
