// List unique lines from input.
// (c) 1996 duane a. bailey

import structure.*;
import java.io.*;

/**
 * 
 * @version $Id: Unique.java,v 3.0 1998/01/13 13:54:31 bailey Exp $
 * @author duane a. bailey
 */
public class Unique {
//+code
    /**
     * @param args 
     */
    public static void main(String[] args)
    {
	// input is read from System.in
	ReadStream s = new ReadStream(System.in);
	String current;	   	         // current line
	List lines = new SinglyLinkedList(); // list of unique lines

	// read a list of possibly duplicated lines
	while (!s.eof()) {
	    current = s.readLine();
	    // check to see if we need to add it
	    if (!lines.contains(current)) {
		System.out.println(current);
		lines.add(current);
	    }
	}
    }
//-code
}

/*
//+input
madam
I'm
Adam!
...
Adam!
I'm
Ada!
...
mad
am I...
madam
//-input
//+output
madam
I'm
Adam!
...
Ada!
mad
am I...
//-output
*/
