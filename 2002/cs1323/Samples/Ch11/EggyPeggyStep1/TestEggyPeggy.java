class TestEggyPeggy
{
   public static void main(String args[])
   {
      EggyPeggy  eggyPeggy1, eggyPeggy2;
      String     str1, str2;

      eggyPeggy1 = new EggyPeggy();
      eggyPeggy2 = new EggyPeggy("bacon");
      
      eggyPeggy1.setPrefixWord("avocado");
      
      str1 = eggyPeggy1.convert("testing one");
      str2 = eggyPeggy2.convert("testing two");
      
   }
}
