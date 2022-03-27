// A class that holds ints for comparison purposes.
// (c) 1998 McGraw-Hill
package structure;

//+interface
//+implementation
/**
 * A java.lang.Integer-like class that can be ordered.
 * 
 * @version $Id: CompInt.java,v 3.0 1998/01/26 19:17:36 bailey Exp $
 * @author duane a. bailey
 */
public class ComparableInt implements Comparable
{
//+easy
//-interface
//+private
    /**
     * The underlying data.
     */
    protected Integer data; // where the integer is stored.
//-private

//+interface
    /**
     * Construct a integer wrapper that can be compared.
     * <p>
     * <dl>
     * <dt><b>Postcondition:</b><dd> construct an integer object that may be compared.
     * </dl>
     * 
     * @param x The integer.
     */
    public ComparableInt(int x)
    // post: construct an integer object that may be compared.
//-interface
    {
	data = new Integer(x);
    }
//-easy
//+interface

//+compareTo
    /**
     * Determine the order of two ComparableInts.
     * <p>
     * <dl>
     * <dt><b>Precondition:</b><dd> other is a non-null ComparableInt
     * <dt><b>Postcondition:</b><dd> returns true iff the value of other is less than this
     * </dl>
     * 
     * @param other The other ComparableInt
     * @return True if this is less than the other ComparableInt.
     */
    public int compareTo(Object other)
    // pre: other is a non-null ComparableInt
    // post: returns true iff the value of other is less than this
//-interface
    {
	Assert.pre(other instanceof ComparableInt,
		   "compareTo expects a ComparableInt");
        ComparableInt that = (ComparableInt)other;
	return value() - that.value();
    }
//-compareTo
//+interface

//+equals
    /**
     * Determine if two ComparableInts are equal.
     * <p>
     * <dl>
     * <dt><b>Precondition:</b><dd> other is a ComparableInt
     * <dt><b>Postcondition:</b><dd> returns true iff other is logically equal
     * </dl>
     * 
     * @param other The other ComparableInt
     * @return True if both integers are equal.
     */
    public boolean equals(Object other)
    // pre: other is a ComparableInt
    // post: returns true iff other is logically equal
//-interface
    {
	Assert.pre(other instanceof ComparableInt,
		   "equals expects a ComparableInt");
        ComparableInt that = (ComparableInt)other;
        return value() == that.value();
        // alternatively:
	// return data.equals(otherInt.data);
    }
//-equals
    //-implementation

    /**
     * Generate hashcode associated with ComparableInt.
     * <p>
     * <dl>
     * <dt><b>Postcondition:</b><dd> return hashcode associated with underlying Integer.
     * </dl>
     * 
     * @return The hashcode associated with the contained integer.
     */
    public int hashCode()
    // post: return hashcode associated with underlying Integer.
    {
	return data.hashCode();
    }

    /**
     * Construct a string representation of the comparable integer.
     * <p>
     * <dl>
     * <dt><b>Postcondition:</b><dd> return string representation of the contained integer.
     * </dl>
     * 
     * @return The string representing the ComparableInt.
     */
    public String toString()
    // post: return string representation of the contained integer.
    {
	return data.toString();
    }
    //+implementation
//+interface

//+value
    /**
     * Fetch the value of the ComparableInt.
     * <p>
     * <dl>
     * <dt><b>Postcondition:</b><dd> returns the value associated with this object
     * </dl>
     * 
     * @return The integer contained within the ComparableInt
     */
    public int value()
    // post: returns the value associated with this object
//-interface
    {
	return data.intValue();
    }
//-value
//+interface
}
//-implementation
//-interface
//+logging
/*
 * $Log: CompInt.java,v $
 * Revision 3.0  1998/01/26 19:17:36  bailey
 * Version compatible with JDK 1.2
 *
 * Revision 3.1  1998/01/12 16:30:59  bailey
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
 * Revision 2.3  1997/08/08 12:44:46  bailey
 * Fix versioning problem.
 *
 * Revision 2.1  1997/08/07 21:11:35  bailey
 * Preprint release.
 *
 * Revision 1.19  1997/08/02 12:39:54  bailey
 * Formatting mods.
 *
 * Revision 1.18  1997/08/02 12:24:30  bailey
 * Weekly checkpoint
 *
 * Revision 1.17  1997/07/02 20:52:32  bailey
 * Updated javadoc comments.
 *
 * Revision 1.16  1997/06/27 01:33:46  bailey
 * (Re)added javadoc comments.
 *
 * Revision 1.15  1997/04/03 02:25:42  bailey
 * Removed javadoc comments.
 *
 * Revision 1.14  1997/04/03 02:15:36  bailey
 * *** empty log message ***
 *
 * Revision 1.13  1997/01/09 16:40:06  bailey
 * *** empty log message ***
 *
 * Revision 1.12  1996/08/29 16:59:54  bailey
 * Moved from cs136 to structure.
 *
 * Revision 1.11  1996/08/24 00:42:24  bailey
 * Balanced extract comments.
 *
 * Revision 1.10  1996/08/23 02:18:32  bailey
 * Added automatically generated javadoc commenting.
 *
 * Revision 1.9  1996/08/23 01:54:08  bailey
 * Added toString & hashCode.
 *
 * Revision 1.8  1996/08/20 03:02:32  bailey
 * Fixed spelling error in extract routine.
 *
 * Revision 1.7  1996/08/20 02:39:12  bailey
 * Revised pre & post conditions, extract comments, & casts.
 *
 * Revision 1.6  1996/08/02 12:13:18  bailey
 * Added logging comments.
 *
 * Revision 1.5
 * 1996/07/25 13:18:45 bailey
 * Moved base from int to Integer.
 * 
 * Revision 1.4
 * 1996/06/08 16:31:44 bailey
 * Changed equals to take Object
 * 
 * Revision 1.3
 * 1996/06/06 18:58:48 bailey
 * Changed greaterThan to lessThan.
 * 
 * Revision 1.2
 * 1996/06/06 14:36:31 bailey
 * Added versioning information.
 * 
 * Revision 1.1
 * 1996/06/05 13:28:17 bailey
 * Initial revision
 */
//-logging
