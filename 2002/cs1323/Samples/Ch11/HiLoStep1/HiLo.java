/*
   Hi-Lo Class (Step 1)

   The classHi-Lo games. The objective of the game is for the user to
   guess the computer generated secret number in the least number of 
   tries. The low and high bounds of a secret number and the number of
   guesses can be set by the programmer. If the number of guesses allowed 
   is not set by the programmer, then the default value is determined
   based on the low and high bounds. The default low and high bounds, 
   if not set by the programmer are 1 and 100. The default number of guesses 
   for the default low and high bounds is 7.
*/

class HiLo
{

/***********************
   Data Members
***********************/

   public static final int HI       = 1;
   public static final int LO       = 2;
   public static final int BINGO    = 3;
   public static final int GAMEOVER = 4;

   private static final int DEFAULT_MAX_GUESSES = 7;
   private static final int DEFAULT_LOW         = 1;
   private static final int DEFAULT_HIGH        = 100;

   private int   numberOfGuessesAllowed;
   private int   guessLowBound;
   private int   guessHighBound;
   
   private int   guessCount;
   private int   secretNumber;

/***********************
   Constructor
***********************/

   public HiLo()
   {
      this(DEFAULT_LOW, DEFAULT_HIGH, DEFAULT_MAX_GUESSES);
   }
   
   public HiLo(int low, int high) 
   {
      this(low, high, maxGuess(low, high));
   }

   public HiLo(int low, int high, int numGuess) 
   {
      if (low > high) {
         int temp = low;
         low = high;
         high = temp;
      }
      
      if (numGuess < 0) {
         numGuess = maxGuess(low, high);
      }
      
      guessLowBound  = low;
      guessHighBound = high;
      numberOfGuessesAllowed = numGuess;
      
      newGame();
      
      System.out.println("low bound:     " + guessLowBound); //TEMP
      System.out.println("high bound:    " + guessHighBound); //TEMP
      System.out.println("max guesses:   " + numberOfGuessesAllowed); //TEMP
      System.out.println("secretNumber:  " + secretNumber); //TEMP
      System.out.println("    "); //TEMP
   }

/***********************
   Public Methods
 
      boolean  isGameOver (     )
      void     newGame    (     )
      int      nextGuess  ( int )

***********************/

   /*
      Method:       isGameOver

      Purpose:      Determine if the number of guesses allowed is used up

      Parameters:   None

      Returns:      boolean [true if game is over]
   */
   
   public boolean isGameOver()
   {
      boolean status = false;
      
      System.out.println("Inside isGameOver");
      
      return status;
   }

   /*
      Method:       newGame

      Purpose:      Sets the object for a new game

      Parameters:   None

      Returns:      None
   */

   public void newGame ()
   {
      System.out.println("Inside newGame");
   }

   /*
      Method:       nextGuess

      Purpose:      Determines whether the guess is the secret number
                    or not. Returns the stauts of the guess: HI, LO, etc.

      Parameters:   int [next guess]

      Returns:      int [status of game]
   */

   public int nextGuess (int guess)
   {
      int status = LO;
      
      System.out.println("Inside nextGuess"); // TEMP
      
      return status;
   }

/***********************
   Private Methods
 
      int maxGuess         ( int, int )

***********************/

   /*
      Method:       maxGuess

      Purpose:      Computes the number of allowed guesses.

      Parameters:   int [low bound of the secret number]
                    int [high bound of the secret number]

      Returns:      int [maximum number of guesses allowed]
   */

   private static int maxGuess(int low, int high)
   {
      return (int) Math.ceil(Math.log(high - low + 1) / Math.log(2));
   }

}
