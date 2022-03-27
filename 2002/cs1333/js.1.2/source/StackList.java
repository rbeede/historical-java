// An implementation of stacks using lists.
// (c) 1998 McGraw-Hill

package structure;

public class StackList implements Stack
{
    protected List data;

    public StackList()
    // post: constructs a new stack, based on lists
    {
        // Think about why we use singly linked lists here:
        // They're simple, and take less space.
        data = new SinglyLinkedList();
    }
    
    public void clear()
    // post: removes all elements from stack
    {
        data.clear();
    }

    public boolean empty()
    // post: returns true iff stack is empty
    {
        return data.isEmpty();
    }

    public boolean isEmpty()
    // post: returns true iff stack is empty
    {
        return data.isEmpty();
    }

    public Object peek()
    // pre: !isEmpty()
    // post: returns first element of stack
    {
        return data.peek();
    }

    public void add(Object value)
    // post: pushes value onto stack.
    //       will be first value popped off.
    {
        data.addToHead(value);
    }

    public void push(Object value)
    // post: pushes value onto stack.
    //       will be first value popped off.
    {
        add(value);
    }

    public Object remove()
    // pre: !isEmpty()
    // post: returns and removes top element of stack
    {
        return data.removeFromHead();
    }

    public Object pop()
    // pre: !isEmpty()
    // post: returns and removes top element of stack
    {
        return remove();
    }

    public int size()
    // post: returns the number of elements in the stack
    {
        return data.size();
    }

    public String toString()
    // post: returns a string representation of stack
    {
        return "<StackList: "+data+">";
    }
}
