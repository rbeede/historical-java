/*

    Program MyFirstApplicationX

    A variation on MyFirstApplication.
*/

import javabook.*;

class MyFirstApplicationX
{
    public static void main (String args[])
    {
        MainWindow mainWindow;
        mainWindow = new MainWindow("Let's Experiment!!");

        mainWindow.setSize(300,200);

        //can you guess what is this method accomplishing?
        mainWindow.setLocation(150,150);

        mainWindow.setResizable(false);
        mainWindow.show();
    }

}
