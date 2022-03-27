/*
   Program EggyPeggy (Step 4 : finalize)

   A program to play a word game Eggy-Peggy. The program reads in a string
   from the user and generates a new converted string which contains the
   word "egg" in front of all vowels in the input string

   Input:   A string from the user

   Output:  Display the input string and the eggy-peggy string generated
            from the input string

*/

import javabook.*;

class EggyPeggy
{
   /***********************
      Data Members
   ***********************/

   private MainWindow  mainWindow;
   private InputBox    inputBox;
   private OutputBox   outputBox;
   private ResponseBox responseBox;

   private String inputString,
                  eggyPeggyString;

   private final String EGG = "egg";

   /***********************
      Constructor
   ***********************/

   public EggyPeggy()
   {
      mainWindow   = new MainWindow("Let's Play Eggy-Peggy");
      outputBox    = new OutputBox(mainWindow);
      responseBox  = new ResponseBox(mainWindow);
      inputBox     = new InputBox(mainWindow);

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

      describeGame();

      answer = responseBox.prompt("Do you want to play Eggy-Peggy?");

      while (answer == ResponseBox.YES) {

         getInput();
         generateEggyPeggy();
         displayResult();

         answer = responseBox.prompt("Do you want to play another Eggy-Peggy?");
      }

   }

   /***********************
      Private Methods
 
         void describeGame         (      )
         void displayResult        (      )
         void generateEggyPeggy    (      )
         void getInput             (      )
         boolean  isVowel          ( char )

   ***********************/

   /*
      Method:       describeGame

      Purpose:      Describe how the eggy-peggy strings are generated.
                    Provide description only if theuser wants it.

      Parameters:   None

      Returns:      None
   */

   private void describeGame() 
   {
//      outputBox.printLine("Inside describeGame"); //TEMP
   }

   /*
      Method:       displayResult

      Purpose:      Show the input string and the eggy-peggy string generated
                    from the input string
   
      Parameters:   None

      Returns:      None
   */

   private void displayResult() 
   {
      outputBox.skipLine(3);
      outputBox.printLine("Input String:");
      outputBox.printLine("               " + inputString);

      outputBox.skipLine(1);
      outputBox.printLine("Eggy Peggy:");
      outputBox.printLine("               " + eggyPeggyString);
   }

   /*
      Method:       generateEggyPeggy

      Purpose:      Generate a new string from the input string by putting the
                    word "egg" in front of all vowels in the input string.

      Parameters:   None

      Returns:      None
   */

   private void generateEggyPeggy() 
   {
      StringBuffer outputStrBuf = new StringBuffer();
      int length = inputString.length();
      char ch;

      for (int i = 0; i < length; i++) {
         ch = inputString.charAt(i);

         if ( isVowel(ch) ) {
            outputStrBuf.append(EGG);  //if vowel, append
                                       //"egg" first
         }

         outputStrBuf.append(ch);
      }
      
      eggyPeggyString = outputStrBuf.toString();
   }

   /*
      Method:       getInput

      Purpose:      Read the input string using InputBox.

      Parameters:   None

      Returns:      None
   */

   private void getInput() 
   {
      inputString = inputBox.getString("Enter the original sentance:");
   }

   /*
      Method:       isVowel

      Purpose:      Determine if the argument character is a vowel
      
      Parameters:   a character

      Returns:      true if the argument is a vowel, false otherwise
   */

   private boolean isVowel(char letter)
   {
      boolean result = false;

      if ( letter == 'A' || letter == 'a' ||
           letter == 'E' || letter == 'e' ||
           letter == 'I' || letter == 'i' ||
           letter == 'O' || letter == 'o' ||
           letter == 'U' || letter == 'u'    ) {
      
         result = true;
      }
      
      return result;
   }
}
