/*

    Program MyFirstApplication

    This program displays a window on the screen.  The window is
    positioned at the center of the screen, and the size of the
    window is almost as big as the screen.
*/

import javabook.*;

class MyFirstApplication
{
    public static void main (String args[])
    {
        MainWindow mainWindow;
        mainWindow = new MainWindow( );      //create and
        mainWindow.show();         //display a window
    }

}
