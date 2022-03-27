/*
    Program MyFirstApplet

    An applet that displays the text "I Love Java"
    and a rectangle around the text.
*/

import java.applet.*;
import java.awt.*;

public class MyFirstApplet extends Applet
{
   public void paint(Graphics g)
    {
       g.drawString("I Love Java",70,70);
       g.drawRect(50,50,100,30);
    }

}
