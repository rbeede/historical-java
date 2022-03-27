// An implementation of stacks, using vectors.
// (c) 1998 McGraw-Hill

package structure;

public class StackVector implements Stack
{
    protected Vector data;

    public StackVector()
    // post: an empty stack is created
    {
        data = new Vector();
    }

    public StackVector(int size)
    // post: an empty stack with initial capacity of size is created
    {
        data = new Vector(size);
    }

    public void add(Object item)
    // post: item is added to stack
    //       will be popped next if no intervening add
    {
        data.addElement(item);
    }

    public void push(Object item)
    // post: item is added to stack
    //       will be popped next if no intervening push
    {
        add(item);
    }

    public Object remove()
    // pre: stack is not empty
    // post: most recently added item is removed and returned
    {
        Object result = data.elementAt(size()-1);
        data.removeElementAt(size()-1);
        return result;
    }

    public Object pop()
    // pre: stack is not empty
    // post: most recently pushed item is removed and returned
    {
        return remove();
    }

    public Object peek()
    // pre: stack is not empty
    // post: returns reference to most recent element in stack
    {
        // raise an exception if stack is already empty
        return data.elementAt(size()-1);
    }

    public boolean empty()
    // post: returns true iff the stack is empty
    {
        return size() == 0;
    }

    public int size()
    // post: returns the number of elements in stack
    {
        return data.size();
    }

    public void clear()
    // post: removes all elements from stack
    {
        data.clear();
    }

    public boolean isEmpty()
    // post: returns true iff the stack is empty
    {
        return size() == 0;
    }

    public String toString()
    // post: returns a string representation of stack
    {
        StringBuffer sb = new StringBuffer();
        int i;

        sb.append("<StackVector:");
        for (i = data.size()-1; i >= 0; i--)
        {
            sb.append(" "+i);
        }
        return sb.toString()+">";
    }
}
