// An interface for LIFO/FIFO structures.
// (c) 1998 McGraw-Hill

package structure;

public interface Linear extends Store
{
    public void add(Object value);
    // pre: value is non-null
    // post: the value is added to the collection,
    //       the consistent replacement policy not specified.

    public Object peek();
    // pre: structure is not empty
    // post: returns reference to next object to be removed.

    public Object remove();
    // pre: structure is not empty.
    // post: removes an object from store
}
