import java.io.*;

class TestAddressBookRead
{
   AddressBook         myBook;
   AddressBookStorage  fileManager;

   public static void main (String args[]) throws IOException
   {
      TestAddressBookRead testReader = new TestAddressBookRead();

      testReader.createFileManager("book.data");
      testReader.testRead();
   }

   public void createFileManager(String filename)
   {
      fileManager = new AddressBookStorage(filename);
   }

   public void testRead()
   {
      try {
         myBook = fileManager.read();
         printout();
      }
      catch (IOException e) {
         System.out.println("Error: IOException is thrown.");
      }
   }

   public void printout()
   {
      Person person;

      person = myBook.search("Ms. X5");

      if (person != null) {
         System.out.println(person.getName());
         System.out.println(person.getAge());
         System.out.println(person.getGender());
      }
      else {
         System.out.println("Error: object not found");
      }
   }

}