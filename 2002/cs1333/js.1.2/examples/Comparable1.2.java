// An interface for ordering classes.
// (c) 1998 McGraw-Hill
package structure;

//+interface
/**
 * An interface that describes objects that can be compared.
 * Structures that can be compared are provided a compareTo method.
 * This method returns
 * <ul>
 *   <li> a negative number if this object is less than the other,
 *   <li> zero if objects are equal,
 *   <li> a positive number if this object is greater than the other.
 * </ul>
 * <p>
 * @version $Id: Comparable1.2.java,v 1.1 1998/02/02 19:32:31 bailey Exp $
 * @author duane a. bailey
 */
public interface Comparable
{
    /**
     * A routine to determine one value is less than another.
     * <p>
     * <dl>
     * <dt><b>Precondition:</b><dd> item is non-null
     * <dt><b>Postcondition:</b><dd> returns integer indicating relation between objects.
     * </dl>
     * 
     * @param item The value to be compared to this.
     */
    public int compareTo(Object item);
    // pre: item is non-null
    // post: returns value < 0 if this<item; 0 if =; > 0 if this > item
}
//-interface
//+logging
/*
 * $Log: Comparable1.2.java,v $
 * Revision 1.1  1998/02/02 19:32:31  bailey
 * Initial revision
 *
 * Revision 3.0  1998/01/26 19:12:32  bailey
 * JDK version 1.2 compatable Comparable object.
 *
 * Revision 1.1  1998/01/26 19:08:28  bailey
 * Initial revision
 *
 * Revision 3.0  1998/01/12 16:03:23  bailey
 * Initial JDK 1.2 version.
 *
 * Revision 2.4  1998/01/12 15:47:04  bailey
 * Beta release.
 *
 * Revision 2.3  1998/01/06 17:55:15  bailey
 * Updated copyright for McGraw-Hill
 *
 * Revision 2.2  1997/08/08 12:44:44  bailey
 * Fix versioning problem.
 *
 * Revision 1.11  1997/04/03 02:24:33  bailey
 * Removed javadoc comments.
 *
 * Revision 1.10  1996/08/29 16:59:54  bailey
 * Moved from cs136 to structure.
 *
 * Revision 1.9  1996/08/23 02:18:32  bailey
 * Added automatically generated javadoc commenting.
 *
 * Revision 1.8  1996/08/02 12:13:05  bailey
 * Added logging comments.
 *
 * Revision 1.7
 * 1996/08/02 11:38:56 bailey
 * Minor mods?
 * 
 * Revision 1.6
 * 1996/07/22 11:29:08 bailey
 * Moved interface comments outside interface boundary.
 * 
 * Revision 1.5
 * 1996/06/25 19:14:51 bailey
 * Removed logging comment.
 * 
 * Revision 1.4
 * 1996/06/25 19:13:51 bailey
 * Added pre and post conditions, comments.
 * 
 * Revision 1.3
 * 1996/06/06 18:56:47 bailey
 * Changed greaterThan to lessThan.
 * 
 * Revision 1.2
 * 1996/06/06 14:36:58 bailey
 * Added versioning information.
 * 
 * Revision 1.1
 * 1996/06/05 13:28:17 bailey
 * Initial revision
 */
//-logging
