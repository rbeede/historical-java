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
      
      void         setFile ( String )
      
***********************/
    
   public void setFile(String filename)
   {
      this.filename = filename;
      System.out.println("Inside setFile. Filename is " + filename); //TEMP
   }
            
}   