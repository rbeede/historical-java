// Import Enumerations and Vectors from java.utils.
import java.util.Enumeration;
import structure.*;
public class HelloWorld
{
    public static void main2(String args[])
    /*
    //+main
    public static void main(String args[])
    //-main
    */
    //+main
    {
	// construct a vector containing two strings:
	Vector v = new Vector();
	v.addElement("Hello");
	v.addElement("world!");
	
	// construct an enumeration to view values of v
	Enumeration e = v.elements();
	while (e.hasMoreElements())
	{
	    // SILLY: v.insertElementAt("silly",1);
	    System.out.print(e.nextElement()+" ");
	}
	System.out.println();
    }
    //-main
    //+mainIterator
    public static void main(String args[])
    {
	// construct a vector containing two strings:
	Vector v = new Vector();
	Iterator i;
	v.addElement("Hello");
	v.addElement("world!");
	
	// construct an iterator to view values of v
	for (i = v.elements(); i.hasMoreElements(); i.nextElement())
	{
	    System.out.print(i.value()+" ");
	}
	System.out.println();
    }
    //-mainIterator
}

/*
//+output
Hello world! 
//-output
//+sillyOutput
Hello silly silly silly silly silly silly [most output deleted]
//-sillyOutput
*/
