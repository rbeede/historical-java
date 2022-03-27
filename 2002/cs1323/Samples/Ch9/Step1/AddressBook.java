/*
   Class AddressBook (Step 1: skeleton)

   A class to store a collection od Person objects with
   the capability of adding new persons, deleting
   existing persons, and searching for persons
*/   

class AddressBook
{

/*******************
   Data Members
*******************/   

   private static final int DEFAULT_SIZE = 25;
   private Person[] entry;

/*******************
   Constructors
*******************/   
   public AddressBook()
   {
      this(DEFAULT_SIZE);
   }
   
   public AddressBook(int size)
   {
      if (size <= 0) { //invalid default value, use default
         size = DEFAULT_SIZE;
      }
      
      entry = new Person[size];
      
      System.out.println("array of " + size + " is created."); //TEMP
   }

}
