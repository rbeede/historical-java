// A simple bitset class.
// (c) 1998 McGraw-Hill

package structure;

public class BitSet
{
    protected final int bitsPerInt = 32;
    protected final int initialCapacity = 256;
    protected int data[];
    protected int allocated;

    public BitSet()
    // post: constructs an empty set of small integers
    {   
        clear(initialCapacity);
    }

    public BitSet(int count)
    // post: constructs an empty set with count potential elements
    {   
        clear(count);
    }

    public void add(int i)
    // pre: i >= 0
    // post: i is added to the set
    {
        extend(i);
        int index = indexOf(i);
        int offset = offsetOf(i);
        data[index] |= 1<<offset;
    }

    public void remove(int i)
    // pre: i >= 0
    // post: removes i from set if present
    {
        if (probe(i)) {
            int index = indexOf(i);
            int offset = offsetOf(i);
            data[index] &= ~(1<<offset);
        }
    }
    public boolean contains(int i)
    // pre: i >= 0
    // post: returns true iff i in set
    {
        return probe(i) && (0 != (data[indexOf(i)] & (1<<offsetOf(i))));
    }

    public void clear()
    // post: removes all values from set
    {
        clear(initialCapacity);
    }

    public void clear(int count)
    // post: removes all values from set, sets set size to count
    {
        int i;
        allocated = (count+bitsPerInt-1)/bitsPerInt;
        data = new int[allocated];
        for (i = 0; i < allocated; i++)
        {
            data[i] = 0;
        }
    }

    public Object clone()
    // post: constructs a copy of the set
    {
        BitSet duplicate = new BitSet(allocated*bitsPerInt);
        int i;
        for (i = 0; i < allocated; i++)
        {
            duplicate.data[i] = data[i];
        }
        return duplicate;
    }

    public Object union(BitSet other)
    // pre: other is non-null
    // post: constructs set w/elements from this and other
    {
        int leftSize = allocated;
        int rightSize = other.allocated;
        if (leftSize < rightSize) return other.union(this);
        BitSet result = new BitSet(leftSize*bitsPerInt);
        int i;
        for (i = 0; i < rightSize; i++)
        {
            result.data[i] = data[i] | other.data[i];
        }
        for (;i < leftSize; i++)
        {
            result.data[i] = data[i];
        }
        return result;
    }

    public Object intersection(BitSet other)
    // pre: other is not null
    // post: constructs set w/elements in this and other
    {
        int leftSize = allocated;
        int rightSize = other.allocated;
        if (leftSize < rightSize) return other.intersection(this);
        BitSet result = new BitSet(rightSize*bitsPerInt);
        int i;
        for (i = 0; i < rightSize; i++)
        {
            result.data[i] = data[i] & other.data[i];
        }
        return result;
    }

    public Object difference(BitSet other)
    // pre: other is not null
    // post: constructs set w/elements from this but not other
    {
        int leftSize = allocated;
        int rightSize = other.allocated;
        BitSet result = new BitSet(leftSize*bitsPerInt);
        int i;
        if (leftSize <= rightSize) {
            for (i = 0; i < leftSize; i++)
            {
                result.data[i] = data[i] & ~other.data[i];
            }
        } else {
            for (i = 0; i < rightSize; i++)
            {
                result.data[i] = data[i] & ~other.data[i];
            }
            for (i = rightSize; i < leftSize; i++)
            {
                result.data[i] = data[i];
            }
        }
        return result;
    }

    public boolean subset(BitSet other)
    // pre: other is not null
    // post: returns true iff elements of this are all in other
    {
        int leftSize = allocated;
        int rightSize = other.allocated;
        int i;
        if (leftSize <= rightSize) {
            for (i = 0; i < leftSize; i++)
            {
                if (0 != (data[i] & ~other.data[i])) return false;
            }
        } else {
            for (i = 0; i < rightSize; i++)
            {
                if (0 != (data[i] & ~other.data[i])) return false;
            }
            for (i = rightSize; i < leftSize; i++)
            {
                if (0 != data[i]) return false;
            }
        }
        return true;
    }

    public boolean isEmpty()
    // post: returns true iff this set is empty
    {
        int i;
        for (i = 0; i < allocated; i++) {
            if (data[i] != 0) return false;
        }
        return true;
    }

    public boolean equals(Object o)
    // pre: o is not null
    // post: returns true iff this and o have same elements
    {
        BitSet other = (BitSet)o;
        int leftSize = allocated;
        int rightSize = other.allocated;
        if (leftSize < rightSize) return other.equals(this);
        int i;
        for (i = 0; i < rightSize; i++) {
            if (data[i] != other.data[i]) return false;
        }
        for (i = rightSize; i < leftSize; i++) {
            if (data[i] != 0) return false;
        }
        return true;
    }

    protected int indexOf(int b)
    // pre: bit >= 0
    // post: returns index of integer containing bit b
    {
        return b/bitsPerInt;
    }   

    protected int offsetOf(int bit)
    // pre: bit >= 0
    // post: returns bit position of bit in word
    {
        return bit%bitsPerInt;
    }

    protected void extend(int bit)
    // pre: bit >= 0
    // post: ensures set is large enough to contain bit
    {
        if (!probe(bit)) {
            int index = indexOf(bit);
            int newData[];
            int newAllocated = allocated;
            int i;
            while (newAllocated <= index) newAllocated *= 2;
            newData = new int[newAllocated];
            for (i = 0; i < allocated; i++) {
                newData[i] = data[i];
            }
            for (i = allocated; i < newAllocated; i++) {
                newData[i] = 0;
            }
            data = newData;
            allocated = newAllocated;
        }
    }


    protected boolean probe(int bit)
    // pre: bit >= 0
    // post: returns true if set is large enough to contain bit
    {
        int index = indexOf(bit);
        return data.length > index;
    }

    public String toString()
    // post: returns string representation of set
    {
        StringBuffer s = new StringBuffer();
        int i;
        s.append("<BitSet:");
        for (i = 0; probe(i); i++) {
            if (contains(i)) s.append(" "+Integer.toString(i));
        }
        s.append(">");
        return s.toString();
    }
}
