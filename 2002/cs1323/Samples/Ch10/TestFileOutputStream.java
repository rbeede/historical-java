/*
    Program TestFileOutputStream

    A test program to save data to a file using FileOutputStream
*/

import java.io.*;

class TestFileOutputStream
{
   public static void main (String arg[]) throws IOException
   {
      //setup file and stream
      File outFile = new File("sample1.data");
      FileOutputStream outStream = new FileOutputStream(outFile);

      //data to output
      byte byteArray[] = {10, 20, 30, 40, 50, 60, 70, 80};

      //write data to the stream
      outStream.write(byteArray);

      //output done, so close the stream
      outStream.close();
   }
}
