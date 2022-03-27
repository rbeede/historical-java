/* 
  Program TestMyFirstDialog

  The main class for testing MyFirstDialog
*/

import javabook.*;

class TestMyFirstDialog
{
   public static void main (String arg[])
   {
      MainWindow frame = new MainWindow();
      MyFirstDialog dialog = new MyFirstDialog(frame, false);
      frame.setVisible(true);
      dialog.setVisible(true);
   }
}
