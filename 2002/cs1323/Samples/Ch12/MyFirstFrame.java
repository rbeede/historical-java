/*
   MyFirstFrame class

   A sample frame to illustrate the placing of GUI objects and event handling
*/

import java.awt.*;
import java.awt.event.*;

class MyFirstFrame extends Frame implements ActionListener
{

/******************
   Data Members
******************/

   private static final int FRAME_WIDTH    = 300;
   private static final int FRAME_HEIGHT   = 200;
   private static final int FRAME_X_ORIGIN = 150;
   private static final int FRAME_Y_ORIGIN = 250;

   private static final int BUTTON_WIDTH  = 60;
   private static final int BUTTON_HEIGHT = 30;

   Button cancelButton, okButton;

/******************
   Constructor
******************/

   public MyFirstFrame()
   {
      //set the frame properties
      setSize      (FRAME_WIDTH, FRAME_HEIGHT);
      setResizable (false);
      setTitle     ("Program MyFirstFrame");
      setLocation  (FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
      setLayout    (null);
      
      //create and place two buttons on the frame
      okButton = new Button("OK");
      okButton.setBounds(100, 150, BUTTON_WIDTH, BUTTON_HEIGHT);
      add(okButton);
      
      cancelButton = new Button("CANCEL");
      cancelButton.setBounds(170, 150, BUTTON_WIDTH, BUTTON_HEIGHT);
      add(cancelButton);

      //register this frame as an action listener of the two buttons
      cancelButton.addActionListener(this);
      okButton.addActionListener(this);
      
      //register a ProgramTerminator object as the window listener of this frame
      addWindowListener(new ProgramTerminator());
   }
   
/*************************
   Action Event Handling
*************************/

   public void actionPerformed(ActionEvent event) 
   {
      Button clickedButton = (Button) event.getSource();
      
      if (clickedButton == cancelButton) {
         setTitle("You clicked CANCEL");
      }
      else { //the event source must be okButton
         setTitle("You clicked OK");
      }
   }
   
}
