// Implementation of value- iterators for driving Association iterators.
// (c) 1998  McGraw-Hill
package structure;

class ValueIterator implements Iterator
{
    protected Iterator slave;

    public ValueIterator(Iterator slave)
    // pre: slave is an iterator returning Association elements
    // post: creates a new iterator returning associated values
    {
        this.slave = slave;
    }

    public void reset()
    // post: resets iterator to point to first value
    {
        slave.reset();
    }

    public boolean hasMoreElements()
    // post: returns true if current value is valid
    {
        return slave.hasMoreElements();
    }

    public Object nextElement()
    // pre: hasMoreElements()
    // post: returns current value and increments iterator
    {
        Association pair = (Association)slave.nextElement();
        return pair.value();
    }

    public Object value()
    // pre: current value is valid
    // post: returns current value
    {
        Association pair = (Association)slave.value();
        return pair.value();
    }
}
