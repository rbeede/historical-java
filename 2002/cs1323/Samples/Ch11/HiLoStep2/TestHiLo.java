class TestHiLo
{
   public static void main(String args[])
   {
      //create a HiLo object
      HiLo hiLo;
      
      hiLo = new HiLo(200, 500);
      for (int i = 0; i < 100; i++) {
         hiLo.newGame();
      }
   }
}   
