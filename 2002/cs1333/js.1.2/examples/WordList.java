import structure.*;
/*
  //+altDecl
  public class WordList implements Store
  //-altDecl
*/
//+interface
public class WordList
{
    public WordList(int size)
    // pre: size >= 0
    // post: construct a word list capable of holding "size" words
    //-interface
    {
    }
    //+interface
    
    public boolean isEmpty()
    // post: return true iff the word list contains no words
    //-interface
    {
	return true;
    }
    //+interface

    public void add(String s)
    // post: add a word to the word list, if it is not already there
    //-interface
    {
    }
    //+interface
    
    public String selectAny()
    // pre: the word list is not empty
    // post: return a random word from the list
    //-interface
    {
	return "";
    }
    //+interface
    
    public void remove(String word)
    // pre: word is not null
    // post: remove the word from the word list
    //-interface
    {
    }

    public void vecHangman()
    {
	//+vecHangman
	Vector list;
	String targetWord;
	java.util.Random generator = new java.util.Random();
	
	list = new Vector(10);
	list.addElement("Ephraim");
	list.addElement("Jeffrey");
	list.addElement("John");
	while (list.size() != 0)
	{
	    {   // select a word from the list
		int index = Math.abs(generator.nextInt())%list.size();
		targetWord = (String)list.elementAt(index);
	    }
	    // ... play the game using target word ...
	    list.removeElement(targetWord);
	}
	//-vecHangman
    }

    public static void hangman()
    {
	//+exampleDesign
	WordList list;				// declaration
	String targetWord;
		
	list = new WordList(10);		// construction
	list.add("Ephraim");			// building
	list.add("Jeffrey");
	list.add("John");
	while (!list.isEmpty())			// game loop
	{
	    targetWord = list.selectAny();	// selection
	    // ...play the game using target word...
	    list.remove(targetWord);		// update
	}
	//-exampleDesign
    }
	
    public static void main(String[] args)
    {
	//+constructorUse
	WordList list;
	list = new WordList(100);
	//-constructorUse
	//+addUse
	String s = "";
	list.add(s);
	//-addUse
    }
    //+interface
}
//-interface
class WordList2
{
    //+vecMethImpl
    protected Vector theList;
    protected java.util.Random generator;
    
    //-vecMethImpl
    /*
     //+vecMethImpl
    public WordList(int n)
     //-vecMethIMpl
     */
    public WordList2(int n)
    //+vecMethImpl
    {
	theList = new Vector(n);
	generator = new java.util.Random();
    }
    
    public String selectAny()
    {
	int i = Math.abs(generator.nextInt())%theList.size();
	return (String)theList.elementAt(i);
    }	
    //-vecMethImpl
}

