// An implementation of a priority queue in a vector.
// (c) 1998 McGraw-Hill
package structure;

public class VectorHeap implements PriorityQueue
{
    protected Vector data;

    public VectorHeap()
    // post: constructs a new priority queue.
    {
        data = new Vector();
    }

    public VectorHeap(Vector v)
    // post: constructs a new priority queue from an unordered vector.
    {
        int i;
        data = new Vector(v.size()); // we know ultimate size
        for (i = 0; i < v.size(); i++)
        {   // add elements to heap
            add((Comparable)v.elementAt(i));
        }
    }

    protected static int parentOf(int i)
    // post: returns index of parent of value at i
    {
        return (i-1)/2;
    }

    protected static int leftChildOf(int i)
    // post: returns index of left child of value at i
    {
        return 2*i+1;
    }

    protected static int rightChildOf(int i)
    // post: returns index of right child of value at i
    {
        return 2*(i+1);
    }

    public Comparable peek()
    // pre: !isEmpty()
    // post: returns minimum value in queue
    {
        return (Comparable)data.elementAt(0);
    }

    public Comparable remove()
    // pre: !isEmpty()
    // post: removes and returns minimum value in queue
    {
        Comparable minVal = peek();
        data.setElementAt(data.elementAt(data.size()-1),0);
        data.setSize(data.size()-1);
        if (data.size() > 1) pushDownRoot(0);
        return minVal;
    }

    public void add(Comparable value)
    // pre: value is non-null comparable object
    // post: adds value to priority queue
    {
        data.addElement(value);
        percolateUp(data.size()-1);
    }

    public boolean isEmpty()
    // post: returns true iff queue has no values
    {
        return data.size() == 0;
    }

    protected void percolateUp(int leaf)
    // pre: 0 <= leaf < size
    // post: takes value at leaf in near-heap,
    //       and pushes up to correct location
    {
        int parent = parentOf(leaf);
        Comparable value = (Comparable)(data.elementAt(leaf));
        while (leaf > 0 &&
          (value.compareTo((Comparable)(data.elementAt(parent))) < 0))
        {
            data.setElementAt(data.elementAt(parent),leaf);
            leaf = parent;
            parent = parentOf(leaf);
        }
        data.setElementAt(value,leaf);
    }

    protected void pushDownRoot(int root)
    // pre: 0 <= root < size
    // post: pushes root down into near-heap
    //       constructing heap
    {
        int heapSize = data.size();
        Comparable value = (Comparable)data.elementAt(root);
        while (root < heapSize) {
            int childpos = leftChildOf(root);
            if (childpos < heapSize)
            {
                if ((rightChildOf(root) < heapSize) &&
                  (((Comparable)(data.elementAt(childpos+1))).compareTo
                   ((Comparable)(data.elementAt(childpos))) < 0))
                {
                    childpos++;
                }
                // Assert: childpos indexes smaller of two children
                if (((Comparable)(data.elementAt(childpos))).compareTo
                    (value) < 0)
                {
                    data.setElementAt(data.elementAt(childpos),root);
                    root = childpos; // keep moving down
                } else { // found right location
                    data.setElementAt(value,root);
                    return;
                }
            } else { // at a leaf! insert and halt
                data.setElementAt(value,root);
                return;
            }       
        }
    }

    public int size()
    // post: returns number of values in queue
    {
        return data.size();
    }

    public void clear()
    // post: removes all values from queue
    {
        data.clear();
    } 
    
    public String toString()
    // post: returns string representation of heap
    {
        return "<VectorHeap: "+data+">";
    }
}
