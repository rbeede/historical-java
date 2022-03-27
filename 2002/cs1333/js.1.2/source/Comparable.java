// An interface for ordering classes.
// (c) 1998 McGraw-Hill
package structure;

public interface Comparable
{
    public int compareTo(Object item);
    // pre: item is non-null
    // post: returns value < 0 if this<item; 0 if =; > 0 if this > item
}
