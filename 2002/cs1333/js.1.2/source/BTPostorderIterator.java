// Post-order iterator for binary trees.
// (c) 1998 McGraw-Hill
package structure;

class BTPostorderIterator implements Iterator
{
    protected BinaryTreeNode root; // root of traversed subtree
    protected Stack todo;  // stack of nodes whose descendants
                           // are currently being visited

    public BTPostorderIterator(BinaryTreeNode root)
    // post: constructs an iterator to traverse in postorder
    {
        todo = new StackList();
        this.root = root;
        reset();
    }   

    public void reset()
    // post: resets the iterator to retraverse
    {
        todo.clear();
        // stack is empty.  push on nodes from root to
        // leftmost descendant
        BinaryTreeNode current = root;
        while (current != null) {
            todo.push(current);
            if (current.left()!=null)
                current = current.left();
            else
                current = current.right();
        }
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
        BinaryTreeNode current = (BinaryTreeNode)todo.pop();
        Object result = current.value();
        if (!todo.isEmpty())
        {
            BinaryTreeNode parent = (BinaryTreeNode)todo.peek();
            if (current == parent.left()) {
                current = parent.right();
                while (current != null)
                {
                    todo.push(current);
                    if (current.left() != null)
                         current = current.left();
                    else current = current.right();
                }
            }
        }
        return result;
    }
}
