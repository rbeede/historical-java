/*
   Program Hi-Lo (Step 1: skeleton)

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
      outputBox.printLine("Inside generateSecretNumber"); //TEMP
   }

   /*
      Method:       playGame

      Purpose:      Play one Hi-Lo game

      Parameters:   None

      Returns:      None
   */

   private void playGame()
   {
      outputBox.printLine("Inside playGame"); //TEMP
   }
    
}
