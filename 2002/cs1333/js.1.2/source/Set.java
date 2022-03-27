// A simple Set interface.
// (c) 1998 McGraw-Hill

package structure;

public interface Set extends Collection
{
    public boolean subset(Set other);
    // pre: other is non-null reference to set
    // post: returns true iff this set is subset of other

    public Object union(Set other);
    // pre: other is non-null reference to set
    // post: returns new set containing union of this and other

    public Object intersection(Set other);
    // pre: other is non-null reference to set
    // post: returns new set containing intersection of this and other

    public Object difference(Set other);
    // pre: other is non-null reference to set
    // post: returns new set containing difference of this and other
}
