// A class for structuring associations that may be compared.
// (c) 1998 McGraw-Hill
package structure;

public class ComparableAssociation extends Association implements Comparable
{
    public ComparableAssociation(Comparable key)
    // pre: key is non-null
    // post: constructs comparable association with null value
    {
        this(key,null);
    }

    public ComparableAssociation(Comparable key, Object value)
    // pre: key is non-null
    // post: constructs ass'n between comparable key and a value
    {
        super(key,value);
    }

    public int compareTo(Object other)
    // pre: other is non-null ComparableAssociation
    // post: returns integer representing relation between values
    {
        Assert.pre(other instanceof ComparableAssociation,
                   "compareTo expects a ComparableAssociation");
        ComparableAssociation that = (ComparableAssociation)other;
        Comparable thisKey = (Comparable)this.key();
        Comparable thatKey = (Comparable)that.key();

        return thisKey.compareTo(thatKey);
    }

    public String toString()
    // post: returns string representation
    {
        StringBuffer s = new StringBuffer();
        s.append("<ComparableAssociation: "+key()+"="+value()+">");
        return s.toString();
    }
}
