class TestAddressBook
{
   public static void main(String args[])
   {
      AddressBook myBook;
      Person person;
      
      myBook = new AddressBook(4);
      
      //add four Person objects
      for (int i = 0; i < 4; i++) {
         person = new Person("Ms. X" + i, 10, 'F');
         myBook.add(person);
      }
      
      //add fifth person and see if a new aray is created
      person = new Person("fifth one", 10, 'F');
      myBook.add(person);
   }
}
