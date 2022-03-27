/*
    Program TestDataOutputStream

    A test program to save data to a file using DataOutputStream for
    high level i/o.
*/

import java.io.*;

class TestDataOutputStream
{
   public static void main (String arg[]) throws IOException
   {
      //setup the streams
      File outFile = new File("sample2.data");
      FileOutputStream outFileStream = new FileOutputStream(outFile);
      DataOutputStream outDataStream  = new DataOutputStream(outFileStream);

      //write values of primitive data types to the stream
      outDataStream.writeInt(987654321);
      outDataStream.writeLong(11111111L);
      outDataStream.writeFloat(22222222F);
      outDataStream.writeDouble(3333333D);
      outDataStream.writeChar('A');
      outDataStream.writeBoolean(true);

      //output done, so close the stream
      outDataStream.close();
   }
}
