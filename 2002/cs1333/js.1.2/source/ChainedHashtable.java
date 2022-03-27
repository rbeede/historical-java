// An implementation of hashtables, using external chaining
// Keys need not be comparable.
// (c) 1998 McGraw-Hill

package structure;
import java.lang.Math;
public class ChainedHashtable implements Dictionary
{
    protected List data[];
    protected int count;
    protected int capacity;

    public ChainedHashtable(int size)
    // pre: size > 0
    // post: constructs a new ChainedHashtable
    {
        data = new List[size];
        capacity = size;
        count = 0;
    }

    public ChainedHashtable()
    // post: constructs a new ChainedHashtable
    {
        this(997);
    }

    public void clear()
    //post: removes all elements from ChainedHashtable
    {
        int i;
        for (i = 0; i < capacity; i++) {
            data[i].clear();
        }
        count = 0;
    }

    public int size()
    // post: returns number of elements in hash table
    {
        return count;
    }

    public boolean isEmpty()
    // post: returns true iff hash table has 0 elements
    {
        return size() == 0;
    }

    protected List locate(Object key)
    // post: returns list potentially containing key, if in table
    {
        int hash = Math.abs(key.hashCode() % capacity);
        if (data[hash] == null) data[hash] = new SinglyLinkedList();
        return data[hash];
    }

    public boolean contains(Object value)
    // pre: value is non-null Object
    // post: returns true iff hash table contains value
    {
        Iterator elements = elements();

        while (elements.hasMoreElements())
        {
            if (value.equals(elements.nextElement())) return true;
        }
        return false;
    }

    public boolean containsKey(Object key)
    // pre: value is non-null key
    // post: returns true if key appears in hash table
    {
        List l = locate(key);
        return l.contains(new Association(key,null));
    }

    public Iterator elements()
    // post: returns iterator to traverse hash table
    {
        return new ValueIterator(new ChainedHashtableIterator(data));
    }

    public Object get(Object key)
    // pre: key is non-null Object
    // post: returns value associated with key, or null
    {
        List l = locate(key);
        Association a = (Association)l.remove(new Association(key,null));
        if (a == null) return null;
        l.addToHead(a);
        return a.value();
    }

    public Iterator keys()
    // post: returns iterator to traverse the keys of hash table.
    {
        return new KeyIterator(new ChainedHashtableIterator(data));
    }

    public Object put(Object key, Object value)
    // pre: key is non-null object
    // post: key-value pair is added to hash table
    {
        List l = locate(key);
        Association newa = new Association(key,value);
        Association olda = (Association)l.remove(newa);
        l.addToHead(newa);
        if (olda != null)
        {
            return olda.value();
        }
        else
        {
            count++;
            return null;
        }
    }

    public Object remove(Object key)
    // pre: key is non-null object
    // post: removes key-value pair associated with key
    {
        List l = locate(key);
        Association pair = (Association)l.remove(new Association(key,null));
        if (pair == null) return null;
        count--;
        return pair.value();
    }

    public String toString()
    // post: returns a string representation of hash table.
    {
        StringBuffer s = new StringBuffer();
        int i;

        s.append("<ChainedHashtable:");
        Iterator hi = new ChainedHashtableIterator(data);
        while (hi.hasMoreElements()) {
            Association a = (Association)hi.nextElement();
            s.append(" "+a.key()+"="+a.value());
        }
        s.append(">");
        return s.toString();
    }
}

class ChainedHashtableIterator implements Iterator
{
    protected List data;
    protected Iterator elements;

    public ChainedHashtableIterator(List[] table)
    // post: constructs a new hash table iterator
    {
        int i;
        int capacity = table.length;
        data = new SinglyLinkedList();
        for (i = 0; i < capacity; i++) {
            if (table[i] != null) {
                Iterator els = table[i].elements();
                while (els.hasMoreElements())
                {
                    data.addToHead(els.nextElement());
                }
            }
        }
        elements = data.elements();
    }

    public void reset()
    // post: resets iterator to beginning of hash table
    {
        elements.reset();
    }

    public boolean hasMoreElements()
    // post: returns true if there are unvisited elements
    {
        return elements.hasMoreElements();
    }

    public Object nextElement()
    // pre: hasMoreElements()
    // post: returns current element, increments iterator
    {
        return elements.nextElement();
    }

    public Object value()
    // post: returns current element
    {
        return elements.value();
    }
}
