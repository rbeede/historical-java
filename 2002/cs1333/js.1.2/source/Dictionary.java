// An interface for Dictionaries, much like java.util.Dictionary.
// (c) 1998 McGraw-Hill

package structure;

public interface Dictionary extends Store
{
    public Object put(Object key, Object value);
    // pre: key is non-null
    // post: puts key-value pair in Dictionary, returns old value

    public boolean contains(Object value);
    // pre: value is non-null
    // post: returns true iff the dictionary contains the value

    public boolean containsKey(Object key);
    // pre: key is non-null
    // post: returns true iff the dictionary contains the key

    public Object remove(Object key);
    // pre: value is non-null
    // post: removes an object "equal" to value within dictionary.
    
    public Object get(Object key);
    // pre: key is non-null
    // post: returns value associated with key, in dictionary

    public Iterator keys();
    // post: returns iterator for traversing keys in dictionary

    public Iterator elements();
    // post: returns iterator for traversing values in dictionary

    public int size();
    // post: returns number of elements in dictionary
}
