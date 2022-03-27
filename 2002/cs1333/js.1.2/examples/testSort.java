import structure.*;
/**
 * 
 * @version $Id: testSort.java,v 3.0 1998/01/13 13:54:31 bailey Exp $
 * @author duane a. bailey
 */
public class testSort {
    /**
     * @param args 
     */
    public static void main(String args[])
    {
	Vector v = new Vector();
	int i;
	for (i = 0; i < args.length; i++) {
	    v.addElement(args[i]);
	}
	Sort.insertion(v);
	for (i = 0; i < v.size(); i++)
	{
	    System.out.println(v.elementAt(i));
	}
    }
}
