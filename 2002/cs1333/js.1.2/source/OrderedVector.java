// An implementation of and ordered structure, based on vectors.
// (c) 1998 McGraw-Hill

package structure;

public class OrderedVector implements OrderedStructure
{
    protected Vector data;
    public OrderedVector()
    // post: constructs an empty, ordered vector.
    {
        data = new Vector();
    }

    public void add(Object value)
    // pre: value is non-null
    // post: inserts value, leaves vector in order
    {
        int position = indexOf((Comparable)value);
        data.insertElementAt(value,position);
    }

    public boolean contains(Object value)
    // pre: value is non-null
    // post: returns true if the value is in the vector
    { 
        int position = indexOf((Comparable)value);
        return (position < size()) &&
               data.elementAt(position).equals(value);
    }

    public Object remove(Object value)
    // pre: value is non-null
    // post: removes one instance of value, if found in vector.
    {
        if (contains(value)) {
            // we know value is pointed to by indexOf
            int position = indexOf((Comparable)value);
            // since vector contains value, position < size()
            // keep track of the value for return
            Object target = data.elementAt(position);
            // remove the value from the underlying vector
            data.removeElementAt(position);
            return target;
        }
        return null;
    }

    public boolean isEmpty()
    // post: returns true if the OrderedVector is empty.
    {
        return data.size() == 0;
    }

    public void clear()
    // post: vector is emptied.
    {
        data.setSize(0);
    }

    public int size()
    // post: returns the number of elements in vector
    {
        return data.size();
    }

    public Iterator elements()
    // post: returns an iterator for traversing vector
    {
        return data.elements();
    }

    protected int indexOf(Comparable target)
    // pre: target is a non-null comparable object
    // post: returns ideal position of value in vector
    {
        Comparable midValue;
        int low = 0;  // lowest possible location
        int high = data.size(); // highest possible location
        int mid = (low + high)/2; // low <= mid <= high
        // mid == high iff low == high
        while (low < high) {
            // get median value
            midValue = (Comparable)data.elementAt(mid);
            // determine which side median resides on:
            if (midValue.compareTo(target) < 0) {
                low = mid+1;
            } else {
                high = mid;
            }
            // low <= high
            // recompute median index
            mid = (low+high)/2;
        }
        return low;
    }
    
    public String toString()
    // pre: returns string representation of ordered vector
    {
        return "<OrderedVector: "+data+">";
    }
}
