// An implementation of stacks, using vectors.
// (c) 1998 McGraw-Hill

package structure;

public class StackArray implements Stack
{
    protected int top;
    protected Object data[];
    public StackArray(int size)
    // post: an empty stack with initial capacity of size is created
    {
        data = new Object[size];
        clear();
    }

    public void clear()
    // post: removes all elements from stack
    {
        top = -1;
    }

    public void add(Object item)
    // post: adds an element to stack.
    //       Will be next element popped if no intervening push
    {
        Assert.pre(!isFull(),"Stack is not full.");
        top++;
        data[top] = item;
    }    

    public void push(Object item)
    // post: adds an element to stack.
    //       will be next element popped if no intervening push
    {
        add(item);
    }

    public Object remove()
    // pre: stack is not empty
    // post: removes and returns the top element from stack.
    {
        Assert.pre(!isEmpty(),"Stack is not empty.");
        Object result = data[top];
        data[top] = null;
        top--;
        return result;
    }

    public Object pop()
    // pre: stack is not empty
    // post: removes and returns the top element from stack.
    {
        return remove();
    }

    public Object peek()
    // pre: stack is not empty
    // post: returns the top element (most recently pushed) from stack
    {
        // raise an exception if stack is already empty
        Assert.pre(!isEmpty(),"Stack is not empty.");
        return data[top];
    }

    public boolean empty()
    // post: returns true iff the stack is empty
    {
        return isEmpty();
    }

    public int size()
    // post: returns the size of the stack
    {
        return top+1;
    }

    public boolean isEmpty()
    // post: returns true iff the stack is empty
    {
        return size() == 0;
    }

    public boolean isFull()
    // post: returns true iff the stack is empty
    {
        return top == (data.length-1);
    }

    public String toString()
    // post: returns a string representation of stack
    {
        StringBuffer sb = new StringBuffer();
        int i;
        sb.append("<StackArray: ");
        for (i = top; i >= 0; i--)
        {
            sb.append(" "+data[i]);
        }
        sb.append(">");
        return sb.toString();
    }
}
