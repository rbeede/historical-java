/*
    Program TestBufferedReader

    A test program to load data from a file using the readLine method 
    of BufferedReader for high level String input
*/

import java.io.*;
import javabook.*;

class TestBufferedReader
{
   public static void main (String args[]) throws IOException
   {
      MainWindow mainWindow = new MainWindow();
      OutputBox outputBox = new OutputBox(mainWindow);
      mainWindow.show();
      outputBox.show();

      //setup file and stream
      File           inFile     = new File("sample3.data");
      FileReader     fileReader = new FileReader(inFile);
      BufferedReader bufReader  = new BufferedReader(fileReader);
      String str;

      //get integer
      str = bufReader.readLine();
      int i = Convert.toInt(str);

      //get long
      str = bufReader.readLine();
      long l = Convert.toLong(str);

      //get float
      str = bufReader.readLine();
      float f = Convert.toFloat(str);

      //get double
      str = bufReader.readLine();
      double d = Convert.toDouble(str);

      //get char
      str = bufReader.readLine();
      char c = Convert.toChar(str);

      //get boolean
      str = bufReader.readLine();
      boolean b = Convert.toBoolean(str);

      outputBox.printLine(i);
      outputBox.printLine(l);
      outputBox.printLine(f);
      outputBox.printLine(d);
      outputBox.printLine(c);
      outputBox.printLine(b);

      //input done, so close the stream
      bufReader.close();
   }
}
