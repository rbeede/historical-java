// Level-order iterator for binary trees.
// (c) 1998 McGraw-Hill
package structure;

class BTLevelorderIterator implements Iterator
{
    protected BinaryTreeNode root; // root of traversed subtree
    protected Queue todo;  // queue of unvisited relatives

    public BTLevelorderIterator(BinaryTreeNode root)
    // post: constructs an iterator to traverse in levelorder
    {
        todo = new QueueList();
        this.root = root;
        reset();
    }   

    public void reset()
    // post: resets the iterator to root node
    {
        todo.clear();
        // stack is empty; push on nodes from root down to the
        // leftmost descendant
        if (root != null) todo.enqueue(root);
    }

    public boolean hasMoreElements()
    // post: returns true iff iterator is not finished
    {
        return !todo.isEmpty();
    }

    public Object value()
    // pre: hasMoreElements()
    // post: returns reference to current value
    {   
        return ((BinaryTreeNode)todo.peek()).value();
    }

    public Object nextElement()
    // pre: hasMoreElements();
    // post: returns current value, increments iterator
    {
        BinaryTreeNode current = (BinaryTreeNode)todo.dequeue();
        Object result = current.value();
        if (current.left() != null) 
            todo.enqueue(current.left());
        if (current.right() != null)
            todo.enqueue(current.right());
        return result;
    }
}

