// An implementation of an ordered structure, using lists.
// (c) 1998 McGraw-Hill

package structure;
public class OrderedList implements OrderedStructure
{
    protected SinglyLinkedListElement data; // smallest value
    protected int count;        // number of values in list

    public OrderedList()
    // post: constructs an empty ordered list.
    {
        clear();
    }

    public void clear()
    // post: the ordered list is empty
    {
        data = null;
        count = 0;
    }
    
    public void add(Object value)
    // pre: value is non-null
    // post: value is added to the list, leaving it in order
    {
        SinglyLinkedListElement previous = null; // element to adjust
        SinglyLinkedListElement finger = data;   // target element
        Comparable cValue = (Comparable)value;   // the inserted value
        // search for the correct location
        while ((finger != null) &&
               (((Comparable)finger.value()).compareTo(cValue) < 0))
        {
            previous = finger;
            finger = finger.next();
        }
        // spot is found, insert
        if (previous == null) // check for insert at top
        {      
            data = new SinglyLinkedListElement(cValue,data);
        } else {
            previous.setNext(
               new SinglyLinkedListElement(cValue,previous.next()));
        }
        count++;
    }

    public boolean contains(Object value)
    // pre: value is non-null comparable object
    // post: returns true iff contains value
    {
        SinglyLinkedListElement finger = data; // target
        Comparable cValue = (Comparable)value; // value sought
        // search down list until we fall off or find bigger value
        while ((finger != null) &&
               (((Comparable)finger.value()).compareTo(cValue) < 0))
        {
            finger = finger.next();
        }
        return finger != null && cValue.equals(finger.value());
    }

    public Object remove(Object value)
    // pre: value is non-null
    // post: an instance of value is removed, if in list.
    {
        SinglyLinkedListElement previous = null; // element to adjust
        SinglyLinkedListElement finger = data;   // target element
        Comparable cValue = (Comparable)value;   // value to remove
        // search for value or fall off list
        while ((finger != null) &&
               (((Comparable)finger.value()).compareTo(cValue) < 0))
        {
            previous = finger;
            finger = finger.next();
        }
        // did we find it?
        if ((finger != null) && cValue.equals(finger.value())) {
            // yes, remove it
            if (previous == null)  // at top? 
            {
                data = finger.next();
            } else {
                previous.setNext(finger.next());
            }
            count--;
            // return value
            return finger.value();
        }
        // return non-value
        return null;
    }

    public int size()
    // pre: returns the number of elements in the ordered list
    {
        return count;
    }

    public boolean isEmpty()
    // post: returns true iff the size is non-zero
    {
        return size() == 0;
    }
    public Iterator elements()
    // post: returns an iterator over ordered list
    {
        return new SinglyLinkedListIterator(data);
    }

    public String toString()
    // post: returns string representation of list
    {
        StringBuffer s = new StringBuffer();
        s.append("<OrderedList:");
        Iterator si = elements();
        while (si.hasMoreElements())
        {
            s.append(" "+si.value());
        }
        s.append(">");
        return s.toString();
    }
}
