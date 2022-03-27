// A set implemented using an ordered structure.
// (c) 1998 McGraw-Hill

package structure;

public class ComparableSet implements Set
{
    protected OrderedStructure t;

    public ComparableSet()
    // post: constructs a new, empty set
    {
        t = new SplayTree();
    }

    public void clear()
    // post: elements of set are removed
    {
        t = new SplayTree();
    }

    public boolean isEmpty()
    // post: returns true iff set is empty
    {
        return t.isEmpty();
    }

    public void add(Object e)
    // pre: e is non-null object
    // post: adds element e to set
    {
        t.add(e);
    }

    public Object remove(Object e)
    // pre: e is non-null object
    // post: e is removed from set, value returned
    {
        return t.remove(e);
    }

    public boolean contains(Object e)
    // pre: e is non-null
    // post: returns true iff e is in set
    {
        return t.contains(e);
    }

    public boolean subset(Set other)
    // pre: other is non-null reference to set
    // post: returns true iff this set is subset of other
    {
        Iterator myElements = t.elements();
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
        Set result = new ComparableSet();
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
        Set result = new ComparableSet();
        Iterator yourElements = other.elements();
        Iterator myElements = elements();
        Comparable myVal;
        Comparable yourVal;
        for (yourElements.reset(),myElements.reset();
             yourElements.hasMoreElements() && myElements.hasMoreElements();
             )
        {
            yourVal = (Comparable)yourElements.value();
            myVal = (Comparable)myElements.value();
            if (yourVal.compareTo(myVal) < 0)
            {
                result.add(yourVal);
                yourElements.nextElement();
            } else
            {
                result.add(myVal);
                if (myVal.equals(yourVal)) yourElements.nextElement();
                myElements.nextElement();
            }
        }
        while (yourElements.hasMoreElements())
        {
            result.add(yourElements.nextElement());
        }
        while (myElements.hasMoreElements())
        {
            result.add(myElements.nextElement());
        }
        return result;
    }

    public Object intersection(Set other)
    // pre: other is non-null reference to set
    // post: returns set intersection between this and other
    {
        Set result = new ComparableSet();
        Iterator yourElements = other.elements();
        Iterator myElements = elements();
        Comparable myVal;
        Comparable yourVal;
        for (yourElements.reset(),myElements.reset();
             yourElements.hasMoreElements() && myElements.hasMoreElements();
             )
        {
            yourVal = (Comparable)yourElements.value();
            myVal = (Comparable)myElements.value();
            if (yourVal.compareTo(myVal) < 0)
            {
                yourElements.nextElement();
            } else if (myVal.compareTo(yourVal) < 0)
            {
                myElements.nextElement();
            } else
            {
                result.add((Comparable)myVal);
                yourElements.nextElement();
                myElements.nextElement();
            }
        }
        return result;
    }

    public Object difference(Set other)
    // pre: other is non-null reference to set
    // post: returns the elements in this but not other.
    {
        Set result = new ComparableSet();
        Iterator yourElements = other.elements();
        Iterator myElements = elements();
        Comparable myVal;
        Comparable yourVal;
        for (yourElements.reset(),myElements.reset();
             yourElements.hasMoreElements() && myElements.hasMoreElements();
             )
        {
            yourVal = (Comparable)yourElements.value();
            myVal = (Comparable)myElements.value();
            if (myVal.compareTo(yourVal) < 0)
            {
                result.add(myVal);
                myElements.nextElement();
            } else if (yourVal.compareTo(myVal) < 0)
            {
                yourElements.nextElement();
            } else
            {
                yourElements.nextElement();
                myElements.nextElement();
            }
        }
        while (myElements.hasMoreElements())
        {
            result.add(myElements.nextElement());
        }
        return result;
    }

    public Iterator elements()
    // post: returns iterator to traverse the elements of set
    {
        return t.elements();
    }

    public int size()
    // post: returns number of elements in set
    {
        return t.size();
    }

    public String toString()
    // post: returns string representation of this set
    {
        StringBuffer s = new StringBuffer();
        s.append("<ComparableSet:");
        Iterator si = elements();
        while (si.hasMoreElements())
        {
            s.append(" "+si.value());
        }
        s.append(">");
        return s.toString();
    }

}
