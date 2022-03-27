// A set implemented using lists.  Fairly slow, but works on non-Comparables.
// (c) 1998 McGraw-Hill

package structure;

public class SetList implements Set
{
    protected List l;

    public SetList()
    // post: constructs a new, empty set
    {
        l = new SinglyLinkedList();
    }

    public void clear()
    // post: elements of set are removed
    {
        l = new SinglyLinkedList();
    }

    public boolean isEmpty()
    // post: returns true iff set is empty
    {
        return l.isEmpty();
    }

    public void add(Object e)
    // pre: e is non-null object
    // post: adds element e to interface
    {
        if (!l.contains(e)) l.add(e);
    }

    public Object remove(Object e)
    // pre: e is non-null object
    // post: e is removed from set, value returned
    {
        return l.remove(e);
    }

    public boolean contains(Object e)
    // pre: e is non-null
    // post: returns true iff e is in set
    {
        return l.contains(e);
    }

    public boolean subset(Set other)
    // pre: other is non-null reference to set
    // post: returns true iff this set is subset of other
    {
        Iterator myElements = l.elements();
        for (myElements.reset();
             myElements.hasMoreElements();
             myElements.nextElement())
        {
            if (!other.contains(myElements.value())) return false;
        }
        return true;
    }
    
    public Object clone()
    // post: returns a copy of set
    {
        Set result = new SetList();
        Iterator myElements = elements();
        for (myElements.reset(); myElements.hasMoreElements(); myElements.nextElement()) {
            result.add(myElements.value());
        }
        return result;
    }

    public Object union(Set other)
    // pre: other is non-null reference to set
    // post: returns union of this set and other.
    {
        Set result = (Set)clone();
        Iterator yourElements = other.elements();
        for (yourElements.reset();
             yourElements.hasMoreElements();
             yourElements.nextElement())
        {
            Object v = yourElements.value();
            if (!result.contains(v))
            {
                result.add(v);
            }
        }
        return result;
    }

    public Object intersection(Set other)
    // pre: other is non-null reference to set
    // post: returns set intersection between this and other
    {
        Set result = new SetList();
        Iterator myElements = l.elements();
        for (myElements.reset();
             myElements.hasMoreElements();
             myElements.nextElement())
        {
            Object v = myElements.value();
            if (other.contains(v))
            {
                result.add(v);
            }
        }
        return result;
    }
    
    public Object difference(Set other)
    // pre: other is non-null reference to set
    // post: returns the elements in this but not other.
    {
        Set result = new SetList();
        Iterator myElements = l.elements();
        for (myElements.reset();
             myElements.hasMoreElements();
             myElements.nextElement())
        {
            Object v = myElements.value();
            if (!other.contains(v))
            {
                result.add(v);
            }
        }
        return result;
    }

    public Iterator elements()
    // post: returns iterator to traverse the elements of set
    {
        return l.elements();
    }

    public int size()
    // post: returns number of elements in set
    {
        return l.size();
    }

    public String toString()
    // post: returns a string representation of set
    {
        return "<SetList: "+l+">";
    }
    
}
