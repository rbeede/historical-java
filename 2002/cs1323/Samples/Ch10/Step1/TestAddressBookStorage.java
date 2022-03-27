class TestAddressBookStorage
{
   public static void main (String args[]) 
   {
      AddressBookStorage fileManager;
      
      fileManager = new AddressBookStorage("one.data");
      fileManager.setFile("two.data");
      fileManager.setFile("three.data");
   }
}