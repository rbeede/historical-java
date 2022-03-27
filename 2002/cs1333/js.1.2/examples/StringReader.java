import structure.*;

public class StringReader
{
    public static void main(String args[])
    {
	System.out.println("This code is not tested.");
    }
}

class StringReaderSilly
{
    //+sillyVersion
    public static void main(String args[])
    {
	// read in n = 4 strings
	ReadStream r = new ReadStream();
    	String v1, v2, v3, v4;
    	v1 = r.readString();  // read a space-delimited word
    	v2 = r.readString();
    	v3 = r.readString();
    	v4 = r.readString();
    }
    //-sillyVersion
}
class StringReaderA
{
    //+versionA	
    public static void main(String args[])
    {
    	// read in n = 4 strings
	ReadStream r = new ReadStream();
    	String data[];
    	int n = 4;
    	// allocate array of n String references:
  	data = new String[n];
    	for (int i = 0; i < n; i++)
    	{
    	    data[i] = r.readString();
	}
    }
    //-versionA
}
class StringReaderB
{
    //+versionB
    public static void main(String args[])
    {
    	// read in up to 1000000 Strings
	ReadStream r = new ReadStream();
    	String data[];
    	int n = 0;
    	data = new String[1000000];
    	// read in strings until we hit end of file
	for (r.skipWhite(); !r.eof(); r.skipWhite())
    	{
    	    data[n] = r.readString();
	    n++;
	}
    }
    //-versionB
}
class StringReaderC
{
    //+versionC
    public static void main(String args[])
    {
    	// read in as many String as demanded by input
	ReadStream r = new ReadStream();
    	String data[];
    	int n;
    	// read in the number of strings to be read
    	n = r.readInt();
    	// allocate references for n strings
    	data = new String[n];
    	// read in the n strings
	for (int i = 0; i < n; i++)
    	{
    	    data[i] = r.readString();
	}
    }
    //-versionC
}

class StringReaderVector
{
    //+vectorVersion
    public static void main(String args[])
    {
    	// read in an arbitrary number of strings
	ReadStream r = new ReadStream();
    	Vector data;
    	int n;
    	// allocate vector for storage
    	data = new Vector();
    	// read strings, adding them to end of vector, until eof
	for (r.skipWhite(); !r.eof(); r.skipWhite())
    	{
    	    String s = r.readString();
    	    data.addElement(s);
	}
    }
    //-vectorVersion
}

/*
//+output
This code is not tested.
//-output
*/
