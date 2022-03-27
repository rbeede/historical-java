// Interface for queues.
// (c) 1998 McGraw-Hill

package structure;

public interface Queue extends Linear
{
    public void add(Object value);
    // post: the value is added to the tail of the structure

    public void enqueue(Object value);
    // post: the value is added to the tail of the structure

    public Object remove();
    // pre: the queue is not empty
    // post: the head of the queue is removed and returned

    public Object dequeue();
    // pre: the queue is not empty
    // post: the head of the queue is removed and returned

    public Object peek();
    // pre: the queue is not empty
    // post: the element at the head of the queue is returned
}
