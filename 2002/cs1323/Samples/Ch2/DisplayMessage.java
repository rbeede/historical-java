/*
   Program DisplayMessage

   The program displays the text "I Love Java". The program uses a
   MessageBox object from the javabook package to display the text.
   
*/

import javabook.*;

class DisplayMessage
{
   public static void main(String args[])
   {
      //declare two objects
      MainWindow mainWindow;
      MessageBox messageBox;

      //create two objects
      mainWindow = new MainWindow("Display Message");
      messageBox = new MessageBox(mainWindow);

      //display two objects: first the frame and then the dialog
      mainWindow.show();
      messageBox.show("I Love Java");

   }
}
