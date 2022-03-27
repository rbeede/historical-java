import java.io.*;

class AddressBookStorage
{

/***********************
   Data Members
***********************/

   private String filename;
      
/***********************
   Constructor
***********************/            

   public AddressBookStorage ( String filename )
   {
      setFile(filename);
   }
      
/***********************
   Public Methods
      
      void         write   ( AddressBook )
      void         setFile ( String )
      
***********************/
    
   public void write(AddressBook book) throws IOException
   {
      //first create an ObjectOutputStream
      File outFile = new File(filename);
      FileOutputStream outFileStream = 
               new FileOutputStream(outFile);
      ObjectOutputStream outObjectStream = 
               new ObjectOutputStream(outFileStream);

      //save the data to it
      outObjectStream.writeObject(book);

      //and close it
      outObjectStream.close();
   }
            
   public void setFile(String filename)
   {
      this.filename = filename;
      System.out.println("Inside setFile. Filename is " + filename); //TEMP
   }
            
}   