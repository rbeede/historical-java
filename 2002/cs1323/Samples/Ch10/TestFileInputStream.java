/*
    Program TestFileInputStream

    A test program to read data from a file using FileInputStream
*/

import javabook.*;
import java.io.*;

class TestFileInputStream
{
   public static void main (String arg[]) throws IOException
   {
      MainWindow mainWindow   = new MainWindow();
      OutputBox outputBox     = new OutputBox(mainWindow);
      mainWindow.show();
      outputBox.show();

      //setup file and stream
      File inFile = new File("sample1.data");
      FileInputStream inStream = new FileInputStream(inFile);

      //set up an array to read data in
      int fileSize = (int)inFile.length();
      byte byteArray[] = new byte[fileSize];

      //read data in and display them
      inStream.read(byteArray);
      for (int i = 0; i < fileSize; i++) {
         outputBox.printLine(byteArray[i]);
      }

      inStream.close();
   }
}
