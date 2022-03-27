// An implementation of an OrderedDictionary.
// (c) 1998 McGraw-Hill

package structure;

public class Table implements OrderedDictionary
{
    protected OrderedStructure data;

    public Table()
    // post: constructs a new table
    {
        data = new SplayTree();
    }

    public Object get(Object key)
    // pre: key is a non-null object
    // post: returns value associated with key, or null
    {
        ComparableAssociation ca =
            new ComparableAssociation((Comparable)key,null);
        ComparableAssociation result =
            ((ComparableAssociation)data.remove(ca));
        if (result == null) return null;
        data.add(result);
        return result.value();
    }

    public Object put(Object key, Object value)
    // pre: key is non-null object
    // post: key-value pair is added to table
    {
        ComparableAssociation ca = 
            new ComparableAssociation((Comparable)key,value);
        // fetch old key-value pair
        ComparableAssociation old =
            (ComparableAssociation)data.remove(ca);
        // insert new key-value pair
        data.add(ca);
        // return old value
        if (old == null) return null;
        else return old.value();
    }
    
    public boolean isEmpty()
    // post: returns true iff table is empty
    {
        return data.isEmpty();
    }

    public void clear()
    // post: removes all elements from the table
    {
        data.clear();
    }

    public Iterator keys()
    // post: returns an iterator for traversing keys of table
    {
        return new KeyIterator(data.elements());
    }

    public Iterator elements()
    // post: returns an iterator for traversing values in table
    {
        return new ValueIterator(data.elements());
    }

    public boolean containsKey(Object key)
    // pre: key is non-null object
    // post: returns true iff key indexes a value in table
    {
        ComparableAssociation a =
            new ComparableAssociation((Comparable)key,null);
        return data.contains(a);
    }

    public boolean contains(Object value)
    // pre: value is non-null object
    // post: returns true iff value in table
    {
        Iterator i = elements();
        for (;i.hasMoreElements();i.nextElement())
        {
            if (i.value() != null &&
                i.value().equals(value)) return true;
        }
        return false;
    }

    
    public Object remove(Object key)
    // pre: key is non-null object
    // post: removes value indexed in table
    {
        ComparableAssociation target = 
            new ComparableAssociation((Comparable)key,null);
        target = (ComparableAssociation)data.remove(target);
        if (target == null) return null;
        else return target.value();
    }

    public int size()
    // post: returns number of key-value pairs in table
    {
        return data.size();
    }

    public String toString()
    // post: returns string representation
    {
        StringBuffer s = new StringBuffer();
        s.append("<Table: size="+size());
        Iterator ti = data.elements();
        while (ti.hasMoreElements()) {
            ComparableAssociation ca = (ComparableAssociation)ti.nextElement();
            s.append(" key="+ca.key()+", value="+ca.value());
        }
        s.append(">");
        return s.toString();
    }
}
