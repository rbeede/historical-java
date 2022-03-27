// Pre-order iterator for binary trees.
// (c) 1998 McGraw-Hill
package structure;


class BTPreorderIterator implements Iterator
{
    protected BinaryTreeNode root; // root of tree to be traversed
    protected Stack todo; // stack of unvisited nodes whose
                         // nontrivial ancestors have been visited

    public BTPreorderIterator(BinaryTreeNode root)
    // post: constructs an iterator to traverse in preorder
    {
        todo = new StackList();
        this.root = root;
        reset();
    }   

    public void reset()
    // post: resets the iterator to retraverse
    {
        todo.clear();
        // stack is empty.  Push on the current node.
        if (root != null) todo.push(root);
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
        BinaryTreeNode old = (BinaryTreeNode)todo.pop();
        Object result = old.value();
        
        if (old.right() != null) todo.push(old.right());
        if (old.left() != null) todo.push(old.left());
        return result;
    }
}
