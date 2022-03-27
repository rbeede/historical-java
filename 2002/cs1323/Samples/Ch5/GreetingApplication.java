/*
   Program GreetingApplication

   Run GreetingApplet as an application
*/

import javabook.*;

public class GreetingApplication
{

   public static void main(String args[])
   {
      MainWindow      mainWindow;
      GreetingApplet  greetingApplet;

      mainWindow = new MainWindow("My Applet Runner");
      greetingApplet = new GreetingApplet();

      greetingApplet.init();  // make sure you initialize it

      // add greetingApplet to mainWindow and show the window
      mainWindow.add(greetingApplet);
      mainWindow.show();
   }

}
