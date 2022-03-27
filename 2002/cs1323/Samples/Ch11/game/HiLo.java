/*
   Hi-Lo Class

   The classHi-Lo games. The objective of the game is for the user to
   guess the computer generated secret number in the least number of 
   tries. The low and high bounds of a secret number and the number of
   guesses can be set by the programmer. If the number of guesses allowed 
   is not set by the programmer, then the default value is determined
   based on the low and high bounds. The default low and high bounds, 
   if not set by the programmer are 1 and 100. The default number of guesses 
   for the default low and high bounds is 7.
*/
package game;

public class HiLo
{

/***********************
   Data Members
***********************/

   public static final int INVALID = -1;
   public static final int LOSS    = 0;
   public static final int HI      = 1;
   public static final int LO      = 2;
   public static final int BINGO   = 3;

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
      this(low, high, -1);
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
      
//      System.out.println("low bound:     " + guessLowBound); //TEMP
//      System.out.println("high bound:    " + guessHighBound); //TEMP
//      System.out.println("max guesses:   " + numberOfGuessesAllowed); //TEMP
//      System.out.println("secretNumber:  " + secretNumber); //TEMP
//      System.out.println("    "); //TEMP
   }

/***********************
   Public Methods
 
      boolean  isGameOver (     )
      void     newGame    (     )
      int      nextGuess  ( int )
      
      int getSecretNumber ( )
      int getLowBound     ( )
      int getHighBound    ( )
      int getMaxGuess     ( )

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
      
      status = guessCount > numberOfGuessesAllowed;
      
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
      guessCount = 0;
      secretNumber = getRandomNumber(guessLowBound, guessHighBound);
   }

   /*
      Method:       nextGuess

      Purpose:      Determines whether the guess is the secret number
                    or not. Returns the stauts of the guess: HI, LO, etc.

      Parameters:   int [next guess]

      Returns:      int [status of game]
   */

   public int nextGuess (int userGuess)
   {
      int status;
      
      guessCount++;
      
      if (guessCount > numberOfGuessesAllowed) {
         status = LOSS;
      }
      else if (!isValid(userGuess)) {
         status = INVALID;
      }
      else if (userGuess < secretNumber) {
         status = LO;
      }
      else if (userGuess > secretNumber) {
         status = HI;
      } 
      else {
         status = BINGO;
      }
         
      return status;
   }

   /*
      Method:       getSecretNumber

      Purpose:      Access function for secretNumber variable

      Parameters:   None

      Returns:      int [the secret number]
   */
   
   public int getSecretNumber( )
   {
      return secretNumber;
   }
   
   /*
      Method:       getLowBound

      Purpose:      Access function for guessLowBound variable

      Parameters:   None

      Returns:      int [the low bound of the range]
   */
   
   public int getLowBound( )
   {
      return guessLowBound;
   }
   
   /*
      Method:       getHighBound

      Purpose:      Access function for guessHighBound variable

      Parameters:   None

      Returns:      int [the high bound of the range]
   */
   
   public int getHighBound( )
   {
      return guessHighBound;
   }
   
   /*
      Method:       getMaxGuess

      Purpose:      Access function for numberOfGuessesAllowed variable

      Parameters:   None

      Returns:      int [the maximum number of guesses allowed]
   */
   
   public int getMaxGuess( )
   {
      return numberOfGuessesAllowed;
   }

/***********************
   Private Methods
 
      int maxGuess         ( int, int )
      int getRandomNumber  ( int, int )
      boolean isValid      ( int )

***********************/

   /*
      Method:       maxGuess

      Purpose:      Computes the number of allowed guesses.

      Parameters:   int [low bound of the secret number]
                    int [high bound of the secret number]

      Returns:      int [maximum number of guesses allowed]
   */

   private int maxGuess(int low, int high)
   {
      return (int) Math.ceil(Math.log(high - low + 1) / Math.log(2));
   }
   
   /*
      Method:       getRandomNumber

      Purpose:      computes a new secret number between low and high

      Parameters:   int [low bound]
                    int [high bound]

      Returns:      int [random number in the range low..high (inclusive)]
   */
   
   private int getRandomNumber(int low, int high)
   {
      double x;
      int number;
      
      x = Math.random();
      number = (int) Math.floor(x * (high - low + 1)) + low;
      
//      System.out.println("New secret number: " + number); //TEMP
      
      return number;
   }
   
   /*
      Method:       isValid

      Purpose:      Determine if the argument is in the valid range

      Parameters:   int [guess]

      Returns:      boolean [true if the argument is between 
                             guessLowBound and guessHighBound]
   */
   
   private boolean isValid(int guess)
   {
      boolean status;
      
      if (guess < guessLowBound || guess > guessHighBound) {
         status = false;
      }
      else {
         status = true;
      }
      
      return status;
   }      

}
