// Implementation of queues using doubly linked lists.
// (c) 1998 McGraw-Hill

package structure;

public class QueueList implements Queue
{
    protected List data;

    public QueueList()
    // post: constructs a new, empty queue
    {
        data = new DoublyLinkedList();
    }

    public void add(Object value)
    // post: the value is added to the tail of the structure
    {
        data.addToTail(value);
    }

    public void enqueue(Object value)
    // post: the value is added to the tail of the structure
    {
        add(value);
    }

    public Object remove()
    // pre: the queue is not empty
    // post: the element at the head of the queue is removed and returned
    {
        return data.removeFromHead();
    }

    public Object dequeue()
    // pre: the queue is not empty
    // post: the element at the head of the queue is removed and returned
    {
        return remove();
    }

    public Object peek()
    // pre: the queue is not empty
    // post: the element at the head of the queue is returned
    {
        return data.peek();
    }

    public int size()
    // post: returns the number of elements in the queue.
    {
        return data.size();
    }

    public void clear()
    // post: removes all elements from the queue.
    {
        data.clear();
    }

    public boolean isEmpty()
    // post: returns true iff the queue is empty
    {
        return data.isEmpty();
    }

    public String toString()
    // post: returns a string representation of the queue
    {
        StringBuffer s = new StringBuffer();
        s.append("<QueueList:");
        Iterator li = data.elements();
        while (li.hasMoreElements())
        {
            s.append(" "+li.value());
        }
        s.append(">");
        return s.toString();
    }
}
