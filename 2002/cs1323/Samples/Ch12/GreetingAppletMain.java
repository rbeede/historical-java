/*
   Program GreetingAppletMain

   Run GreetingApplet as an application
*/

import java.awt.*;

class GreetingAppletMain
{

   public static void main(String args[])
   {
      Frame myFrame= new Frame("Greeting Applet in Frame");
      myFrame.setSize(300, 270);
      myFrame.addWindowListener(new ProgramTerminator());
      myFrame.add(new GreetingApplet());
      myFrame.setVisible(true);
   }

}
