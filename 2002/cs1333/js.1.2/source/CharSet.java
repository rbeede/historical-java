// A simple CharSet class.
// (c) 1998 McGraw-Hill

package structure;

public class CharSet
{
    protected BitSet s; // underlying set of bits 0..255

    public CharSet()
    // post: constructs an empty set of characters
    {   
        s = new BitSet();
    }
    
    public void add(char c)
    // post: adds c to set
    {
        s.add((int)c);
    }

    public void remove(char c)
    // post: removes c from set, if present
    {
        s.remove((int)c);
    }

    public boolean contains(char c)
    // post: returns true iff c in set
    {
        return s.contains((int)c);
    }

    public void clear()
    // post: removes all characters from set
    {
        s.clear();
    }

    public Object clone()
    // post: constructs a copy of this set
    {
        CharSet duplicate = new CharSet();
        duplicate.s = (BitSet)s.clone();
        return duplicate;
    }

    public Object union(CharSet other)
    // pre: other is not null
    // post: returns new set with characters from this or other
    {
        return s.union(other.s);
    }

    public Object intersection(CharSet other)
    // pre: other is not null
    // post: returns new set with characters from this and other
    {
        return s.intersection(other.s);
    }

    public Object difference(CharSet other)
    // pre: other is not null
    // post: returns new set with characters from this but not other
    {
        return s.difference(other.s);
    }

    public boolean subset(CharSet other)
    // pre: other is not null
    // post: returns true if this is a subset of other
    {
        return s.subset(other.s);
    }

    public boolean isEmpty()
    // post: returns true iff set is empty
    {
        return s.isEmpty();
    }

    public boolean equals(Object other)
    // pre: other is not null
    // post: returns true if this contains same values as other
    {
        return s.equals(((CharSet)other).s);
    }

    public String toString()
    // post: returns string representation of set
    {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        sb.append("<CharSet:");
        for (i = 0; s.probe(i); i++) {
            if (s.contains(i)) {
                if (i < ' ' || i > '~') {
                    sb.append(" "+(char)i);
                } else {
                    sb.append(" (char)"+i);
                }
            }
        }
        sb.append(">");
        return s.toString();
    }
}
