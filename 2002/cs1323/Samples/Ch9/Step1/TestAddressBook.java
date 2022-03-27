class TestAddressBook
{
   public static void main(String args[])
   {
      AddressBook myBook;
      
      myBook = new AddressBook(-10); //Error
      myBook = new AddressBook(0);   //Error
      myBook = new AddressBook(4);
   }
}
