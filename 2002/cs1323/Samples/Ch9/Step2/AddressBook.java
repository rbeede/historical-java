/*
   Class AddressBook (Step2: implementing add)

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
   
   private int count;  //number of elements in the aray, it is 
                       //also the position to add the next Person

/*******************
   Constructors
*******************/   
   public AddressBook()
   {
      this(DEFAULT_SIZE);
   }
   
   public AddressBook(int size)
   {
      count = 0;

      if (size <= 0) { //invalid default value, use default
         size = DEFAULT_SIZE;
      }
      
      entry = new Person[size];
      
      System.out.println("array of " + size + " is created."); //TEMP
   }
   
/*******************
   Public Methods
   
      void add        ( Person )
      
*******************/   

   /*
      Method:       add

      Purpose:      Add a new person to the address book . The address
                    will automatically grow to accomodate entries
                    beyond its initial capacity

      Parameters:   the person to be added

      Returns:      None
   */
      
   public void add(Person newPerson) 
   {
      if (count == entry.length) { // no more space left
         enlarge();                // create a new larger array
      }

      //at this point entry refers to the new larger array
      //if it was needed
      entry[count] = newPerson;
      
      count++;
   }

/*******************
   Private Methods
   
      void enlarge    (        )
      
*******************/   

   /*
      Method:       enlarge

      Purpose:      expand the size of the address book by 150%

      Parameters:   None

      Returns:      None
   */
      
   private void enlarge() 
   {
      //create a new array whose size is 150% larger
      //than the current array
      int newLength = (int) (1.5f * entry.length);
      Person[] temp = new Person[newLength];
      
      //now copy the data to the new array
      for (int i = 0; i < entry.length; i++) {
         temp[i] = entry[i];
      }
      
      //finally set the variable entry to point to the new array
      entry = temp;
      
      System.out.println("Inside the method enlarge"); //TEMP
      System.out.println("Size of new array: " +
                         entry.length);                //TEMP
   }
}
