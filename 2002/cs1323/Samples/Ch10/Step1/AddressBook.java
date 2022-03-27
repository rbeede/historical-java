/*
   Class AddressBook
   
   A class to store a collection od Person objects with
   the capability of adding new persons, deleting
   existing persons, and searching for persons
*/   

import java.io.*;

class AddressBook implements Serializable
{

/*******************
   Data Members
*******************/   

   private static final int DEFAULT_SIZE = 25;
   private static final int NOT_FOUND    = -1;
   
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
      
//      System.out.println("array of " + size + " is created."); //TEMP
   }
   
   
/*******************
   Public Methods
   
      void add        ( Person )
      boolean delete  ( String )
      Person search   ( String )
      
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
   
   /*
      Method:       delete

      Purpose:      delete a person from the address book

      Parameters:   The name of the person to be deleted

      Returns:      true if deletion was successful, 
                    false otherwise
   */
      
   public boolean delete(String searchName)
   {
      boolean status;
      int loc;
      
      loc = findIndex(searchName);
      
      if (loc == NOT_FOUND) {
         status = false;
      }
      else { //found, so pack the hole
      
         entry[loc] = entry[count - 1];
         
         status = true;
         count--;     //decrement count, since we now
                      //have one less element
      }
      
      return status;
      
   }
   
   /*
      Method:       search

      Purpose:      search for a person in the address book

      Parameters:   The name of the person to be located

      Returns:      The Person with the matching name, or 
                    null if the person can not be found
   */
      
   public Person search(String searchName)
   {
      Person foundPerson;
      int loc = 0;
      
      while ( loc < count && 
              !searchName.equals(entry[loc].getName()) ) {
         loc++;
      }
      
      if (loc == count) {
         foundPerson = null;
      }
      else {
         foundPerson = entry[loc];
      }

      return foundPerson;      
   }


/*******************
   Private Methods
   
      void enlarge    (        )
      int findIndex   ( String )
      
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
      
//      System.out.println("Inside the method enlarge"); //TEMP
//      System.out.println("Size of new array: " +
//                         entry.length);                //TEMP
   }

   /*
      Method:       findIndex

      Purpose:      find the array index of the named person

      Parameters:   The name of the person to find

      Returns:      The index of the named person if found,
                    NOT_FOUND otherwise
   */
      
   public int findIndex(String searchName)
   {
      int loc = 0;
      
      while ( loc < count && 
              !searchName.equals(entry[loc].getName()) ) {
         loc++;
      }
      
      if (loc == count) {
         loc = NOT_FOUND;
      }

      return loc;      
   }

}
