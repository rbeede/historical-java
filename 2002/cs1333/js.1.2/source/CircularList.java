// Implementation of circular lists, using singly linked elements.
// (c) 1998 McGraw-Hill

package structure;
public class CircularList implements List
{
    protected SinglyLinkedListElement tail;
    protected int count;

    public CircularList()
    // pre: constructs a new circular list
    {
        tail = null;
        count = 0;
    }

    public void add(Object value)
    // post: adds value to beginning of list.
    {
        addToHead(value);
    }

    public void addToHead(Object value)
    // pre: value non-null
    // post: adds element to head of list
    {
        SinglyLinkedListElement temp =
            new SinglyLinkedListElement(value);
        if (tail == null) { // first value added
            tail = temp;
            tail.setNext(tail);
        } else { // element exists in list
            temp.setNext(tail.next());
            tail.setNext(temp);
        }
        count++;
    }

    public void addToTail(Object value)
    // pre: value non-null
    // post: adds element to tail of list
    {
        // new entry:
        addToHead(value);
        tail = tail.next();
    }

    public Object peek()
    // pre: !isEmpty()
    // post: returns value at head of list
    {
        return tail.next().value();
    }

    public Object tailPeek()
    // pre: !isEmpty()
    // post: returns value at tail of list
    {
        return tail.value();
    }

    public Object removeFromHead()
    // pre: !isEmpty()
    // post: returns and removes value from head of list
    {
        SinglyLinkedListElement temp = tail.next(); // ie. the head of the list
        if (tail == tail.next()) {
            tail = null;
        } else {
            tail.setNext(temp.next());
            temp.setNext(null); // helps clean things up; temp is free
        }
        count--;
        return temp.value();
    }

    public Object removeFromTail()
    // pre: !isEmpty()
    // post: returns and removes value from tail of list
    {
        Assert.pre(!isEmpty(),"The list is not empty.");
        SinglyLinkedListElement finger = tail;
        while (finger.next() != tail) {
            finger = finger.next();
        }
        // finger now points to second-to-last value
        SinglyLinkedListElement temp = tail;
        if (finger == tail)
        {
            tail = null;
        } else {
            finger.setNext(tail.next());
            tail = finger;
        }
        count--;
        return temp.value();
    }

    public boolean contains(Object value)
    // pre: value != null
    // post: returns true if list contains value, else false
    {
        if (tail == null) return false;

        SinglyLinkedListElement finger;
        finger = tail.next();
        while ((finger != tail) && (!finger.value().equals(value)))
        {
            finger = finger.next();
        }
        return finger.value().equals(value);
    }

    public Object remove(Object value)
    // pre: value != null
    // post: removes and returns element equal to value, or null
    {
        if (tail == null) return null;
        SinglyLinkedListElement finger = tail.next();
        SinglyLinkedListElement previous = tail;
        int compares;
        for (compares = 0;
             (compares < count) && (!finger.value().equals(value));
             compares++) 
        {
            previous = finger;
            finger = finger.next();
        }
        if (finger.value().equals(value)) {
            // an example of the pigeon-hole principle
            if (tail == tail.next()) { tail = null; }
            else {
                if (finger == tail) tail = previous;
                previous.setNext(previous.next().next());
            }
            // finger value free
            finger.setNext(null); // to keep things disconnected
            count--;            // fewer elements
            return finger.value();
        } else return null;
    }

    public int size()
    // post: returns number of elements in list
    {
        return count;
    }

    public Iterator elements()
    // post: returns iterator to traverse list elements.
    {
        return new CircularListIterator(tail);
    }

    public boolean isEmpty()
    // post: returns true if no elements in list
    {
        return tail == null;
    }

    public void clear()
    // post: removes all elements from list.
    {
        count = 0;
        tail = null;
    }

    public String toString()
    // post: returns a string representing list
    {
        StringBuffer s = new StringBuffer();
        s.append("<CircularList:");
        Iterator li = elements();
        while (li.hasMoreElements())
        {
            s.append(" "+li.nextElement());
        }
        s.append(">");
        return s.toString();
    }
}

class CircularListIterator implements Iterator
{
    protected SinglyLinkedListElement tail;
    protected SinglyLinkedListElement current;

    public CircularListIterator(SinglyLinkedListElement t)
    // pre: t is a reference to a circular list element
    // post: constructs an iterator for traversing circular list
    {
        tail = t;
        reset();
    }

    public void reset()
    // post: rests iterator to point to head of list
    {
        if (tail == null) current = null;
        else current = tail.next();
    }

    public boolean hasMoreElements()
    // post: returns true if some elements not visited
    {
        return current != null;
    }

    public Object nextElement()
    // pre: hasMoreElements()
    // post: returns current element, increments iterator
    {
        Object result = current.value();
        if (current == tail) current = null;
        else current = current.next();
        return result;
    }

    public Object value()
    // pre: hasMoreElements()
    // post: returns current value
    {
        return current.value();
    }
}
