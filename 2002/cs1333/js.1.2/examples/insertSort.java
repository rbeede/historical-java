// A simplistic bubble sort program.
// (c) 1996 duane a. bailey
import structure.*;
import java.io.*;

/**
 * 
 * @version $Id: insertSort.java,v 3.0 1998/01/13 13:54:31 bailey Exp $
 * @author duane a. bailey
 */
public class insertSort {
    //+versionA
    /**
     * @param arguments 
     */
    public static void main(String arguments[])
    {
	// read from System.in
	ReadStream r = new ReadStream(System.in);
	// allocate an array of n string references
	int n = 10;
	String data[] = new String[n];
	String temp;     // a temporary string
	int location;    // ideal location for insert
	int i;
		
	System.out.println("Enter "+n+" words:");
	for (i = 0; i < n; i++) {
	    // read in string
	    temp = r.readString();
	    // identify ideal location for insertion
	    location = i;
	    while (location > 0) {
		// if (temp >= data[location-1]) insert at loc.
		if (temp.compareTo(data[location-1]) >= 0) break;
		// push data to right to make room for insert
		data[location] = data[location-1];
		location--;
	    }
	    data[location] = temp;	    // insert
	}
	    
	// print results
	for (i = 0; i < n; i++) {
	    System.out.println(data[i]);
	}
    }
    //-versionA
    //+versionB
    /**
     * @param arguments 
     */
    public static void main(String arguments[])
    {
	// read from System.in
	ReadStream r = new ReadStream(System.in);
	// allocate an array of up to n string refs
	int n = 0;
	int maxN = 1000000; // maximum size of input
	String data[] = new String[maxN];
	String temp;     // a temporary string
	int location;    // ideal location for insert
	int i;
		
	while (!r.eof())
	{
	    Assert.condition(n < maxN,"sufficient space");
	    // read in string
	    temp = r.readString(); r.skipWhite();
	    // identify ideal location for insertion
	    location = n;
	    while (location > 0) {
		// if (temp >= data[location-1]) insert at loc.
		if (temp.compareTo(data[location-1]) >= 0) break;
		// push data to right to make room for insert
		data[location] = data[location-1];
		location--;
	    }
	    data[location] = temp;          // insert
	    n++;
	}
	// print results
	for (i = 0; i < n; i++) {
	    System.out.println(data[i]);
	}
    }
    //-versionB
    //+versionC
    /**
     * @param arguments 
     */
    public static void main(String arguments[])
    {
	// read from System.in
	ReadStream r = new ReadStream(System.in);
	// allocate variable length vector of objects
	Vector data = new Vector(0);
	String temp;  // temporary string
	int location; // ideal location for insert
	int i;

	while (!r.eof())
	{
	    // read in a string
	    temp = r.readString(); r.skipWhite();
	    // extend vector by adding to end
	    data.addElement(temp);
	    /// identify ideal location for insertion
	    location = data.size()-1;
	    while (location > 0) {
	      // if (temp >= data[location-1]) insert at loc.
	      if (temp.compareTo(
                  (String)data.elementAt(location-1)) >= 0) break;
	      // push data to right to make room for insert
	      data.setElementAt(data.elementAt(location-1),location);
	      location--;
	    }
	    data.setElementAt(temp,location); // insert
	}
	// print results
	for (i = 0; i < size; i++) {
	    System.out.println(data.elementAt(i));
	}
    }
    //-versionC
}
