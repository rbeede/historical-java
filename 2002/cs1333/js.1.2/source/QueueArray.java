// An implementation of queues, based on arrays.
// (c) 1998 McGraw-Hill

package structure;

public class QueueArray implements Queue
{
    protected Object data[]; // an array of the data
    protected int head; // next dequeue-able value
    protected int count; // current size of queue

    public QueueArray(int size)
    // post: create a queue capable of holding at most size values.
    {
        data = new Object[size];
        head = 0;
        count = 0;
    }

    public void add(Object value)
    // pre: queue is not full
    // post: the value is added to the tail of the structure
    {
        Assert.pre(!isFull(),"Queue is not full.");
        int tail = (head + count) % data.length;
        data[tail] = value;
        count++;
    }

    public void enqueue(Object value)
    // pre: queue is not full
    // post: the value is added to the tail of the structure
    {
        add(value);
    }

    public Object remove()
    // pre: the queue is not empty
    // post: the element at the head of the queue is removed and returned
    {
        Assert.pre(!isEmpty(),"The queue is not empty.");
        Object value = data[head];
        head = (head + 1) % data.length;
        count--;
        return value;
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
        Assert.pre(!isEmpty(),"The queue is not empty.");
        return data[head];
    }

    public int size()
    // post: returns the number of elements in the queue.
    {
        return count;
    }

    public void clear()
    // post: removes all elements from the queue.
    {
        // we could remove all the elements from the queue.
        count = 0;
        head = 0;
    }
    
    public boolean isFull()
    // post: returns true if the queue is at its capacity.
    {
        return count == data.length;
    }

    public boolean isEmpty()
    // post: returns true iff the queue is empty
    {
        return count == 0;
    }

    public String toString()
    // post: returns string representation of queue
    {
        StringBuffer s = new StringBuffer();
        int i,l;

        s.append("<QueueArray:");
        for (i = 0, l = head; i < count; i++, l = (l+1)%data.length)
        {
            s.append(" "+data[l]);
        }
        s.append(">");
        return s.toString();
    }
}
