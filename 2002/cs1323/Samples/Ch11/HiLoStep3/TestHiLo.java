class TestHiLo
{
   public static void main(String args[])
   {
      //create a HiLo object
      HiLo hiLo;
      
      int low = 200;
      int high = 500;
      
      hiLo = new HiLo(low, high);
      
      int status;
      int guess = (low + high) / 2;
      
      do {
         status = hiLo.nextGuess(guess);

         switch (status) {

         case HiLo.LO:
            System.out.println("status = LO");
            low = guess;
            break;

         case HiLo.HI:
            System.out.println("status = HI");
            high = guess;
            break;

         case HiLo.LOSS:
            System.out.println("status = LOSS");
            break;

         case HiLo.BINGO:
            System.out.println("status = BINGO");
            break;

         case HiLo.INVALID:
            System.out.println("status = INVALID");
            break;
         }
         
         guess = (low + high) / 2;
         
      } while (status != HiLo.LOSS && status != HiLo.BINGO);
   }
}   
