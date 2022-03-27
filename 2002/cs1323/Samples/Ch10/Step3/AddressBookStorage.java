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
      AddressBook  read    ( )
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
            
   
   public AddressBook read() throws IOException
   {
      AddressBook book;

      //first create an ObjectInputStream
      File inFile = new File(filename);
      FileInputStream inFileStream = 
               new FileInputStream(inFile);
      ObjectInputStream inObjectStream = 
               new ObjectInputStream(inFileStream);

      try {
         //read the data from it
         book = (AddressBook) inObjectStream.readObject();
      }
      catch (ClassNotFoundException e) {
         book = null;
         System.out.println("Error: AddressBook class not found");
      }

      //and close it
      inObjectStream.close();

      //and return the object
      return book;
   }

   public void setFile(String filename)
   {
      this.filename = filename;
      System.out.println("Inside setFile. Filename is " + filename); //TEMP
   }
            
}   