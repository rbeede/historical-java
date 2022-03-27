// Implementation of priority queues/heaps using binary trees.
// (c) 1998 McGraw-Hill
package structure;

public class SkewHeap implements PriorityQueue
{
    protected BinaryTreeNode root;
    protected int count;

    public SkewHeap()
    // post: creates an empty priority queue
    {
        root = null;    
        count = 0;
    }

    public Comparable peek()
    // pre: !isEmpty()
    // post: returns the minimum object in queue
    {
        return (Comparable)(root.value());
    }

    public Comparable remove()
    // pre: !isEmpty()
    // post: returns and removes the minimum object in queue
    {
        Comparable result = (Comparable)(root.value());
        root = merge(root.left(),root.right());
        count--;
        return result;  
    }

    public void add(Comparable value)
    // pre: value is non-null object
    // post: adds value to priority queue
    {
        BinaryTreeNode smallTree = new BinaryTreeNode(value);
        root = merge(smallTree,root);
        count++;
    }

    public int size()
    // post: returns the number of elements in queue.
    {
        return count;
    }

    public void clear()
    // post: removes all elements from the queue
    {
        root = null;
    }

    public boolean isEmpty()
    // post: returns true iff queue has no elements
    {
        return size() == 0;
    }

    protected static BinaryTreeNode merge(BinaryTreeNode left,
                                          BinaryTreeNode right)
    // post: merges two skew heaps into one.
    {
        if (left == null) return right;
        if (right == null) return left;
        Comparable leftVal = (Comparable)(left.value());
        Comparable rightVal = (Comparable)(right.value());
        BinaryTreeNode result;
        if (rightVal.compareTo(leftVal) < 0)
        {
            result = merge(right,left);
        } else {
            result = left;
            // assertion left side is smaller than right
            // left is new root
            if (result.left() == null)
            {
                result.setLeft(right);
            } else {
                BinaryTreeNode temp = result.right();
                result.setRight(result.left());
                result.setLeft(merge(temp,right));
            }
        }
        return result;
    }

    public String toString()
    // post: returns string representation of heap
    {
        if (root == null) return "<SkewHeap: >";
        StringBuffer sb = new StringBuffer();
        sb.append("<SkewHeap:");
        if (root != null) {
            Iterator i = root.elements();
            while (i.hasMoreElements())
            {
                sb.append(" "+i.value());
            }
        }
        return sb+">";
    }

}
