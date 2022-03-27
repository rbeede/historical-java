// An implementation of Dictionaries, using hash tables. 
// Keys need not be comparable, but they must have hashcode methods.
// (c) 1998 McGraw-Hill

package structure;
import java.lang.Math;
public class Hashtable implements Dictionary
{
    protected static Association reserved =
                new Association("reserved",null);
    protected Association data[];
    protected int count;
    protected int capacity;
    protected final double loadFactor = 0.6;

    public Hashtable(int initialCapacity)
    // pre: initialCapacity > 0
    // post: constructs a new Hashtable
    //       holding initialCapacity elements
    {
        data = new Association[initialCapacity];
        capacity = initialCapacity;
        count = 0;
    }

    public Hashtable()
    // post: constructs a new Hashtable
    {
        this(997);
    }

    public void clear()
    //post: removes all elements from Hashtable
    {
        int i;
        for (i = 0; i < capacity; i++) {
            data[i] = null;
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

    public boolean contains(Object value)
    // pre: value is non-null Object
    // post: returns true iff hash table contains value
    {
        Iterator i = elements();
        for (;i.hasMoreElements();i.nextElement())
        {
            // the value we seek?
            if (i.value() != null &&
                i.value().equals(value)) return true; // yes!
        }
        // no value found
        return false;
    }

    public boolean containsKey(Object key)
    // pre: key is a non-null Object
    // post: returns true if key appears in hash table
    {
        int hash = locate(key);
        return data[hash] != null && data[hash] != reserved;
    }   

    public Iterator elements()
    // post: returns iterator to traverse hash table
    {
        return new ValueIterator(new HashtableIterator(data));
    }

    public Object get(Object key)
    // pre: key is non-null Object
    // post: returns value associated with key, or null
    {
        int hash = locate(key);
        Association a = data[hash];
        if (a == null || a == reserved) return null;
        return data[hash].value();
    }

    public Iterator keys()
    // post: returns iterator to traverse the keys of hash table.
    {
        return new KeyIterator(new HashtableIterator(data));
    }

    protected int locate(Object key)
    // pre: key is non-null
    // post: returns ideal index of key in table
    {
        // compute an initial hash code
        int hash = Math.abs(key.hashCode() % capacity);
        // keep track of first unused slot, in case we need it
        int firstReserved = -1;
        while (data[hash] != null)
        {
            if (data[hash] == reserved) {
                // remember reserved slot if we fail to locate value
                if (firstReserved == -1) firstReserved = hash;
            } else  {
                // value located? return the index in table
                if (key.equals(data[hash].key())) return hash;
            }
            // linear probing; other methods would change this line:
            hash = (1+hash)%capacity;
        }
        // return first empty slot we encountered
        if (firstReserved == -1) return hash;
        else return firstReserved;
    }

    public Object put(Object key, Object value)
    // pre: key is non-null object
    // post: key-value pair is added to hash table
    {
        if (loadFactor*capacity <= (1+count)) {
            rehash();
        }
        int hash = locate(key);
        Association a = data[hash];
        if (a == null || a == reserved)
        {   // logically empty slot; just add association
            data[hash] = new Association(key,value);
            count++;
            return null;
        } else {
            // full slot; add new and return old value.
            Object oldValue = a.value();
            a.setValue(value);
            return oldValue;
        }
    }

    public Object remove(Object key)
    // pre: key is non-null Object
    // post: removes key-value pair associated with key
    {
        int hash = locate(key);
        Association a = data[hash];
        if (a == null || a == reserved) {
            return null;
        }
        count--;
        Object oldValue = a.value();
        data[hash] = reserved; // in case anyone depends on us
        return oldValue;
    }

    protected void rehash()
    // post: re-hashes all values resized table
    {
        // extends the hashtable for larger capacity.
        int i;
        Iterator it = new HashtableIterator(data);
        // BE AWARE: at this point, we can change the hash table,
        // but changes to the hashtable iterator implementation might
        // be problematic.
        capacity = capacity*2+1;
        data = new Association[capacity];
        count = 0;
        for (; it.hasMoreElements(); it.nextElement())
        {
            Association a = (Association)it.value();
            put(a.key(),a.value());
        }
    }

    public String toString()
    // post: returns a string representation of hash table.
    {
        StringBuffer s = new StringBuffer();
        int i;

        s.append("<Hashtable: size="+size()+" capacity="+capacity);
        Iterator hi = new HashtableIterator(data);
        while (hi.hasMoreElements()) {
            Association a = (Association)hi.nextElement();
            s.append(" key="+a.key()+", value="+a.value());
        }
        s.append(">");
        return s.toString();
    }
}

class HashtableIterator implements Iterator
{
    protected int current;
    protected Association data[];

    public HashtableIterator(Association[] table)
    // post: constructs a new hash table iterator
    {
        data = table;
        reset();
    }

    public void reset()
    // post: resets iterator to beginning of hash table
    {
        for (current = 0; current < data.length; current++)
        {
            if (data[current] != null &&
                data[current] != Hashtable.reserved) break;
        }
        return;
    }

    public boolean hasMoreElements()
    // post: returns true if there are unvisited elements
    {
        return current < data.length;
    }

    public Object nextElement()
    // pre: hasMoreElements()
    // post: returns current element, increments iterator
    {
        Object result = data[current];
        for (current++; current < data.length; current++)
        {
            if (data[current] != null &&
                data[current] != Hashtable.reserved) break;
        }
        return result;
    }

    public Object value()
    // post: returns current element
    {
        return data[current];
    }
}
