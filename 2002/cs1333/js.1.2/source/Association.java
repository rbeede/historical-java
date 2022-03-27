// A class for binding key/value pairs.
// (c) 1998 McGraw-Hill
package structure;

public class Association
{
    protected Object theKey; // the key of the key-value pair
    protected Object theValue; // the value of the key-value pair

    public Association(Object key, Object value)
    // pre: key is non-null.
    // post: constructs a key-value pair.
    {
        Assert.pre(key != null, "Key must not be null.");
        theKey = key;
        theValue = value;
    }

    public Association(Object key)
    // pre: key is non-null
    // post: constructs a key-value pair; value is null.
    {
        this(key,null);
    }

    public boolean equals(Object other)
    // pre: other is non-null Association
    // post: returns true iff the keys are equal
    {
        Association otherAssoc = (Association)other;
        return key().equals(otherAssoc.key());
    }
    
    public int hashCode()
    // post: return hash code associated with this association
    {
        return key().hashCode();
    }
    
    public Object value()
    // post: returns value from association
    {
        return theValue;
    }

    public Object key()
    // post: returns key from association
    {
        return theKey;
    }

    public void setValue(Object value)
    // post: sets association's value to value.
    {
        theValue = value;
    }

    public String toString()
    // post: returns string representation
    {
        StringBuffer s = new StringBuffer();
        s.append("<Association: "+key()+"="+value()+">");
        return s.toString();
    }
}
