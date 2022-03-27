// The interface for stacks.
// (c) 1998 McGraw-Hill

package structure;

public interface Stack extends Linear
{
    public void add(Object item);
    // post: item is added to stack
    //       will be popped next if no intervening add

    public void push(Object item);
    // post: item is added to stack
    //       will be popped next if no intervening push

    public Object remove();
    // pre: stack is not empty
    // post: most recently added item is removed and returned

    public Object pop();
    // pre: stack is not empty
    // post: most recently pushed item is removed and returned

    public Object peek();
    // pre: stack is not empty
    // post: top value (next to be popped) is returned

    public boolean empty();
    // post: returns true if and only if the stack is empty
}
