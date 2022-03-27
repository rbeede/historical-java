class TestAddressBook
{
   AddressBook myBook;
   Person person;

   public static void main(String args[])
   {
      TestAddressBook tester = new TestAddressBook();
      
      tester.setupArray(5);
      tester.testSearch();
   }
   
   private void setupArray(int N)
   {      
      myBook = new AddressBook(N);
      
      //add N Person objects
      for (int i = 0; i < N; i++) {
         person = new Person("Ms. X" + i, 10, 'F');
         myBook.add(person);
      }
   }
   
   private void testSearch()
   {
      //test for the end case
      person = myBook.search("Ms. X0");
      
      if (person == null) {
         System.out.println("Error: didn't find the person it should");
      }
      else {
         System.out.println(person.getName() + " was found okay.");
      }      
   }

}
