class TestEggyPeggy
{
   public static void main(String args[])
   {
      EggyPeggy  eggyPeggy1, eggyPeggy2;
      String     str1, str2;

      eggyPeggy1 = new EggyPeggy();
      eggyPeggy2 = new EggyPeggy("bacon");
      
      str1 = eggyPeggy1.convert("testing one");
      System.out.println(str1);

      eggyPeggy1.setPrefixWord("avocado");
      str1 = eggyPeggy1.convert("testing one");
      System.out.println(str1);
      
      str2 = eggyPeggy2.convert("testing two");
      System.out.println(str2);
      
      eggyPeggy2.setPrefixWord("tomato");
      str2 = eggyPeggy2.convert("testing two");
      System.out.println(str2);
   }
}
