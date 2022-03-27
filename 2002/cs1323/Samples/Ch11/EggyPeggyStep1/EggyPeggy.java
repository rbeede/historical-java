/*
   EggyPeggy Class (Step 1 : skeleton)

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
      String newString;
      
      System.out.println("Inside convert; prefix is " + prefix); //TEMP

      newString = "temp new string";
      return newString;
   }

   /*
      Method:       setPrefixWord

      Purpose:      Sets the prefix word to the passed argument.

      Parameters:   String [new prefix word]

      Returns:      None
   */

   public void setPrefixWord(String prefix)
   {
      System.out.println("Inside setPrefixWord"); //TEMP
   }

}
