// Implementation of an iterator for elements of a singly linked list.
// (c) 1998 McGraw-Hill
package structure;
class SinglyLinkedListIterator implements Iterator
{
    protected SinglyLinkedListElement current;
    protected SinglyLinkedListElement head;

    public SinglyLinkedListIterator(SinglyLinkedListElement t)
    // post: returns an iterator that traverses a linked list.
    {
        head = t;
        reset();
    }
    
    public void reset()
    // post: resets the iterator to point to the head of the list
    {
        current = head;
    }

    public boolean hasMoreElements()
    // post: returns true iff there are unvisited elements
    {
        return current != null;
    }

    public Object nextElement()
    // pre: hasMoreElements()
    // post: returns value and advances iterator
    {
        Object temp = current.value();
        current = current.next();
        return temp;
    }

    public Object value()
    // pre: hasMoreElements()
    // post: returns the current element referenced by the iterator.
    {
        return current.value();
    }
}
