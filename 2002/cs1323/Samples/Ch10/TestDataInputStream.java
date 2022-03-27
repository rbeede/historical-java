/*
    Program TestDataInputStream

    A test program to load data from a file using DataInputStream for
    high level i/o.
*/

import java.io.*;
import javabook.*;

class TestDataInputStream
{
   public static void main (String arg[]) throws IOException
   {
      MainWindow mainWindow = new MainWindow();
      OutputBox outputBox = new OutputBox(mainWindow);
      mainWindow.show();
      outputBox.show();

      //setup file and stream
      File inFile = new File("sample2.data");
      FileInputStream inFileStream = new FileInputStream(inFile);
      DataInputStream inDataStream = new DataInputStream(inFileStream);

      //read values back from the stream and display them
      outputBox.printLine(inDataStream.readInt());
      outputBox.printLine(inDataStream.readLong());
      outputBox.printLine(inDataStream.readFloat());
      outputBox.printLine(inDataStream.readDouble());
      outputBox.printLine(inDataStream.readChar());
      outputBox.printLine(inDataStream.readBoolean());

      //input done, so close the stream
      inDataStream.close();
   }
}
