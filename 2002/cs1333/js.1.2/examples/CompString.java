// An extension of strings that is comparable.
// (c) 1998 McGraw-Hill
package structure;

//+interface
//+implementation
/**
 * A comparable wrapper class for strings.  This class holds a string
 * and implements a compareTo method.  This class is used to support
 * the insertion of strings into Ordered structures.
 * <p>
 * If Strings were not final in Java, it would be more reasonable to
 * make this class a direct extension of the String class, implementing
 * comparable.
 *
 * @version $Id: CompString.java,v 3.0 1998/01/26 19:18:11 bailey Exp $
 * @author duane a. bailey
 */
public class ComparableString implements Comparable
{
//-interface
//+private
    /**
     * The underlying String value.
     * Note that Strings support a compareTo operation which allows
     * them to be ordered.
     */
    protected String val;
//-private

//+interface
    /**
     * Constructs a comparable string from a String.
     * <p>
     * <dl>
     * <dt><b>Precondition:</b><dd> s is non-null
     * <dt><b>Postcondition:</b><dd> construct a ComparableString whose initial value is s.
     * </dl>
     * 
     * @param s The non-null string to be used as the value.
     */
    public ComparableString(String s)
    // pre: s is non-null
    // post: construct a ComparableString whose initial value is s.
//-interface
    {
	Assert.pre(s != null, "Initial string is non-null");
	val = s;
    }
//+interface

    /**
     * The compareTo method, required by the Comparable interface.
     * <p>
     * <dl>
     * <dt><b>Precondition:</b><dd> other is non-null
     * <dt><b>Postcondition:</b><dd> returns true if this string is less than the other
     *       string, lexically
     * </dl>
     * 
     * @param other The other comparable string.
     * @return True if this string is lexically less than the other.
     */
    public int compareTo(Object other)
    // pre: other is non-null
    // post: returns true if this string is less than the other
    //       string, lexically
//-interface
    {
	Assert.pre(other != null,"Comparison value is non-null.");
	Assert.pre(other instanceof ComparableString,
		   "compareTo expects a ComparableString");
	ComparableString otherString = (ComparableString)other;
	return val.compareTo(otherString.value());
    }
//+interface

    /**
     * Test for equality between this and other string.
     * Two comparable strings are "equals" if their component strings are.
     * <p>
     * <dl>
     * <dt><b>Precondition:</b><dd> other is non-null
     * <dt><b>Postcondition:</b><dd> returns true if this string is same as other
     *       string, lexically
     * </dl>
     * 
     * @param other The other comparable string.
     * @return True iff the underlying strings are equals.
     */
    public boolean equals(Object other)
    // pre: other is non-null
    // post: returns true if this string is same as other
    //       string, lexically
//-interface
    {
	Assert.pre(other != null,"Comparison value is non-null.");
	Assert.pre(other instanceof ComparableString,
		   "equals expects a ComparableString");
	ComparableString otherString = (ComparableString)other;
	return val.compareTo(otherString.value()) == 0;
    }
    //-implementation
    
    /**
     * Compute the hashcode for a comparable string.
     * <p>
     * <dl>
     * <dt><b>Precondition:</b><dd> returns hash code associated with string.
     * </dl>
     * 
     * @return An integer hashcode for underlying string.
     * @see structure.Hashtable
     */
    public int hashCode()
    // pre: returns hash code associated with string.
    {
	return val.hashCode();
    }
    //+implementation
//+interface

    /**
     * Fetch value of underlying string.
     * <p>
     * <dl>
     * <dt><b>Postcondition:</b><dd> returns the underlying value of the string.
     * </dl>
     * 
     * @return The string contained within the wrapper.
     */
    public String value()
    // post: returns the underlying value of the string.
//-interface
    {
        return val;
    }
    //-implementation

    /**
     * Return string representation of value (itself).
     * <p>
     * <dl>
     * <dt><b>Postcondition:</b><dd> returns string representation
     * </dl>
     * 
     * @return Underlying string.
     */
    public String toString()
    // post: returns string representation
    {
	return val;
    }
    //+implementation
//+interface
}
//-interface
//-implementation
//+logging
/*
 * $Log: CompString.java,v $
 * Revision 3.0  1998/01/26 19:18:11  bailey
 * Version compatible with JDK 1.2
 *
 * Revision 3.1  1998/01/12 16:31:15  bailey
 * Obsoleted version for JDK 1.2
 *
 * Revision 3.0  1998/01/12 16:03:23  bailey
 * Initial JDK 1.2 version.
 *
 * Revision 2.5  1998/01/12 15:47:04  bailey
 * Beta release.
 *
 * Revision 2.4  1998/01/06 17:55:15  bailey
 * Updated copyright for McGraw-Hill
 *
 * Revision 2.3  1997/08/08 12:44:47  bailey
 * Fix versioning problem.
 *
 * Revision 2.1  1997/08/07 21:11:35  bailey
 * Preprint release.
 *
 * Revision 1.13  1997/08/02 12:24:30  bailey
 * Weekly checkpoint
 *
 * Revision 1.12  1997/07/02 20:52:32  bailey
 * Updated javadoc comments.
 *
 * Revision 1.11  1997/06/27 01:33:46  bailey
 * (Re)added javadoc comments.
 *
 * Revision 1.10  1997/04/03 02:25:42  bailey
 * Removed javadoc comments.
 *
 * Revision 1.9  1997/01/09 16:40:06  bailey
 * *** empty log message ***
 *
 * Revision 1.8  1996/08/29 16:59:54  bailey
 * Moved from cs136 to structure.
 *
 * Revision 1.7  1996/08/23 02:18:32  bailey
 * Added automatically generated javadoc commenting.
 *
 * Revision 1.6  1996/08/23 01:54:08  bailey
 * Added toString & hashCode.
 *
 * Revision 1.5  1996/08/02 12:13:32  bailey
 * Added logging comments.
 *
 * Revision 1.4
 * 1996/07/25 13:18:45 bailey
 * Changed value to stringValue.
 * 
 * Revision 1.3
 * 1996/06/06 18:59:47 bailey
 * Changed greater than to less than.
 * 
 * Revision 1.2
 * 1996/06/06 14:39:50 bailey
 * Added versioning information.
 * 
 * Revision 1.1
 * 1996/06/05 23:47:25 bailey
 * Initial revision
 */
//-logging
