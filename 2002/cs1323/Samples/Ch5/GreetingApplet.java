/*
   Program GreetingApplet

   An applet that accepts the user's name and
   displays a personalized greeting.
*/

import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class GreetingApplet extends Applet implements ActionListener
{

   /******************
      Data Members
   ******************/
   
   private Label      prompt;
   private Label      greeting;
   private TextField  inputLine;

   /******************
      Constructor
   ******************/
   
   public GreetingApplet ()
   {
      // Create GUI objects
      prompt = new Label("Please enter your name:");
      greeting = new Label();
      inputLine = new TextField(15);
      
      // add GUI objects to the applet
      add(prompt);
      add(inputLine);
      add(greeting);  
      
      // add this applet as an action listener
      inputLine.addActionListener(this);

   }

   /******************
      Public methods
         void actionPerformed( ActionEvent )
   ******************/
   
   /* Method:     actionPerformed
   
      Purpose:    Implements the abstract method defined in the interface
                  ActionListener. The method retrieves the text from the
                  TextField object inputLine and displays the personalized
                  greeting using the Label object greeting
       
      Parameters: ActionEvent object which is unsed.
      
      Returns:    none
      
   */
   
   public void actionPerformed(ActionEvent event)
   {
      greeting.setText("Nice to meet you, " + 
                       inputLine.getText() + ".");
      add(greeting);
      doLayout();
   }

}
