//===================================================================
//  Announcements.java -- 11/18/98 -- Rodney Beede © 1998
//===================================================================
//  Problem:  Write applet to scroll messages accross screen
//  Input:  Reads string message passed from <APPLET> tag
//  Output:  Scrolling message
//  Method:  Use draw text method and change coordinates to move it
//
//  Notes:  This is for Snyder High School's web site
//
//===================================================================

//Tell compilier to include the Java Applet package of the Java API
import java.applet.Applet;

//Tell compilier to include the Java Graphics package of the Java API
import java.awt.*;

public class Announcements extends Applet {
     //Applet width and height
     private final int intAppWidth = 550;
     private final int intAppHeight = 50;

     //Define object used to hold background image
     private Image imgBackground;

     //Define holder for page
     private Graphics page;

     //Define the announcements
     String strMsg;

     //This is called when the applet is first ran
     public void init() {

          //Load the background image up
          imgBackground = getImage( getDocumentBase(),
             "Announcements_Background.gif" );

          setSize (intAppWidth, intAppHeight);  //Set the applet size
          setVisible (true);  //Make the applet visible

          page = getGraphics();  //Setup object to control graphics
     }  //End of init method

     //This is called each time the applet is restarted
     public void start() {
          //Declare horizontal position variable
          int intXCoor = intAppWidth;

          //Read in the message to scroll by
          strMsg = getParameter( "MESSAGE" );

          //Loop forever displaying messages
          while( 1 == 1) {

               //Draw the background image
               //Will also clear out previous text position
               page.drawImage( imgBackground, 0, 0, this );

               //Draw the message on the screen
               page.drawString( strMsg, intXCoor, 30 );

               //Draw the border
               page.drawRect(0, 0, intAppWidth-1, intAppHeight-1);

               //Attempt to do a pause
               try { 
                    Thread.sleep( 100 );  //Pause for 100 millisecs
               }
               catch (InterruptedException e) {  //Catch the error
                    e.printStackTrace();
               }

               intXCoor--;  //Decrement horizontal position

               //Check to see if off screen yet
               if( intXCoor < strMsg.length() * -5 )
                    //Off screen, put back to far right
                    intXCoor = intAppWidth;

          }  //End of infinite while loop
     }  //End of start method

}  //End of class Announcements
