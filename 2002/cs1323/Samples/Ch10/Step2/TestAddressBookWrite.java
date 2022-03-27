import java.io.*;

class TestAddressBookWrite
{
   AddressBook         myBook;
   AddressBookStorage  fileManager;

   public static void main (String args[]) throws IOException
   {
      TestAddressBookWrite testWriter = new TestAddressBookWrite();

      testWriter.createAddressBook(15);
      testWriter.createFileManager("book.data");
      testWriter.testWrite();
   }

   public void createAddressBook(int N)
   {
      myBook = new AddressBook(N);

      for (int i = 0; i < N; i++) {
         Person person = new Person("Ms. X" + i, 10, 'F');
         myBook.add(person);
      }
   }

   public void createFileManager(String filename)
   {
      fileManager = new AddressBookStorage(filename);
   }

   public void testWrite()
   {
      try {
         fileManager.write(myBook);
      }
      catch (IOException e) {
         System.out.println("Error: IOException is thrown.");
      }
   }

}