/*
   Program Hi-Lo (Step 3: generate random numbers)

   A program to play Hi-Lo games. The objective of the game is to guess
   the secret number (any integer between 1 and 100) with the least number 
   of tries.  The maximum number of tries allowed is six.

   Input:    User guess

   Output:   Display the hint HI if the guess is higher than the secret
             number and LO if the guess s lower than the secret number.
             Display the message "You guessed it in <N> tries" or "You
             lost. Secret no. was <X>" at the end of the game.
*/

import javabook.*;

class HiLo
{

/***********************
   Data Members
***********************/

   private MainWindow   mainWindow;
   private MessageBox   messageBox;
   private OutputBox    outputBox;
   private ResponseBox  responseBox;
   private InputBox     inputBox;

   private final int MAX_GUESS_ALLOWED = 6;

   private int secretNumber;

/***********************
   Constructor
***********************/

   public HiLo()
   {
       mainWindow  = new MainWindow("Let's play HiLo");
       outputBox   = new OutputBox(mainWindow);
       responseBox = new ResponseBox(mainWindow);
       messageBox  = new MessageBox(mainWindow);
       inputBox    = new InputBox(mainWindow);

       mainWindow.show();
       outputBox.show();
   }

/***********************
   Public Methods
 
      void start (    )

***********************/

   public void start ()
   {
      int answer;

      describeRules();

      answer = responseBox.prompt("Do you want to play a Hi-Lo game?");

      while (answer == ResponseBox.YES) {

         generateSecretNumber();
         playGame();

         answer = responseBox.prompt("Do you want to play another Hi-Lo game?");
      }
   }

/***********************
   Private Methods
 
      void describeRules        (    )
      void generateSecretNumber (    )
      void playGame             (    )
      void getNextGuess         (    )

***********************/

   /*
      Method:       describeRules

      Purpose:      Describe what the program does

      Parameters:   None

      Returns:      None
   */

   private void describeRules()
   {
      outputBox.printLine("Inside describeRules"); //TEMP
   }

   /*
      Method:       generateSecretNumber

      Purpose:      Generate a random number between 1 and 100

      Parameters:   None

      Returns:      None
   */

   private void generateSecretNumber()
   {
      double X = Math.random();

      secretNumber = (int) Math.floor( X * 100 ) + 1;

      System.out.println("Secret Number: " + secretNumber); //TEMP
   }

   /*
      Method:       playGame

      Purpose:      Play one Hi-Lo game

      Parameters:   None

      Returns:      None
   */

   private void playGame()
   {
      int guessCount = 0;
      int guess;

      do {
         //get the next guess
         guess = getNextGuess();
   
         guessCount++;
   
         //check the guess
         if (guess < secretNumber) {
            messageBox.show("Your guess is LO");
         }
         else if (guess > secretNumber) {
            messageBox.show("Your guess is HI");
         }
            
      } while (guessCount < MAX_GUESS_ALLOWED && 
               guess != secretNumber);
      if (guess == secretNumber) {
         messageBox.show("You guessed it in " + 
                         guessCount + " tries.");
      }
      else {
         messageBox.show("You lost. Secret no. was " + 
                         secretNumber);
      }
   }
    
   /*
      Method:       getNextGuess

      Purpose:      Get the players next guess

      Parameters:   None

      Returns:      The players next guess between 1 and 100
   */

   private int getNextGuess()
   {
      int nextGuess;

      nextGuess = inputBox.getInteger("Enter guess between 1 an 100");

      while (nextGuess < 1 || nextGuess > 100) {
         messageBox.show("Guess must be between 1 and 100");
         nextGuess = inputBox.getInteger("Your guess:");
      }

      System.out.println("Guess: " + nextGuess); //TEMP

      return nextGuess;
   }
}
