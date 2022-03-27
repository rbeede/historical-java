/*
   EggyPeggy Class (Step 2)

   An EggyPeggy object plays the word game Eggy-Peggy. Pass the original 
   string to the convert method, and it will return an Eggy-Peggy string,
   a string where all the vowels are preceded by the word 'egg'. You can
   change the default prefix word 'egg' to any word you like by calling
   the SetPrefixWord method.
*/

class EggyPeggy
{
   /***********************
      Data Members
   ***********************/

   private static final String DEFAULT_PREFIX = "egg";
   
   private String prefix;

   /***********************
      Constructors
   ***********************/

   public EggyPeggy()
   {
      this(DEFAULT_PREFIX);
   }
   
   public EggyPeggy(String prefix)
   {
      this.prefix = prefix;
   }

   /***********************
      Public Methods
 
         String convert        ( String )
         void   setPrefixWord  ( String )

   ***********************/

   /*
      Method:       convert

      Purpose:      Converts an argument string to an Eggy-Peggy string.
                    Data member prefix is the word placed in front of
                    all vowels in the original string.

      Parameters:   String [original string]

      Returns:      String [Eggy-Peggy string]
   */

   public String convert(String originalSentence)
   {
      int           numberOfCharacters;
      char          letter;
      StringBuffer  encryptedSentence;
      
      encryptedSentence = new StringBuffer("");
      numberOfCharacters = originalSentence.length();
      
      for (int index = 0; index < numberOfCharacters; index++) {
         //get the next letter
         letter = originalSentence.charAt(index);

         if ( isVowel(letter) ) {
            //add the prefix string and then the vowel
            //to encryptedSentence
            encryptedSentence.append(prefix + letter);  
         }
         else {
            //add the letter to encryptedSentence
            encryptedSentence.append(letter);
         }
      }
      
      return encryptedSentence.toString();
   }

   /*
      Method:       setPrefixWord

      Purpose:      Sets the prefix word to the passed argument.

      Parameters:   String [new prefix word]

      Returns:      None
   */

   public void setPrefixWord(String prefix)
   {
      this.prefix = prefix;
   }

   /***********************
      Private Methods
 
         boolean isVowel ( char )

   ***********************/

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
