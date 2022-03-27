// Implementation of key- iterators for driving Association iterators.
// (c) 1998 McGraw-Hill
package structure;

class KeyIterator implements Iterator
{
    protected Iterator slave;

    public KeyIterator(Iterator slave)
    // pre: slave is a fully reset iterator over Association elements
    // post: creates a new iterator that returns keys of slave iterator
    {
        this.slave = slave;
    }

    public void reset()
    // post: resets iterator to point to first key
    {
        slave.reset();
    }

    public boolean hasMoreElements()
    // post: returns true if current element is valid
    {
        return slave.hasMoreElements();
    }

    public Object nextElement()
    // pre: hasMoreElements()
    // post: returns current value and increments iterator
    {
        Association pair = (Association)slave.nextElement();
        return pair.key();
    }

    public Object value()
    // pre: current value is valid
    // post: returns current value
    {
        Association pair = (Association)slave.value();
        return pair.key();
    }
}
 
