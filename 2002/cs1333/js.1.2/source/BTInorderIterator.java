// In-order iterator for binary trees.
// (c) 1998 McGraw-Hill
package structure;

class BTInorderIterator implements Iterator
{
    protected BinaryTreeNode root; // root of subtree to be traversed
    protected Stack todo; // stack of unvisited ancestors of current

    public BTInorderIterator(BinaryTreeNode root)
    // post: constructs an iterator to traverse inorder
    {
        todo = new StackList();
        this.root = root;
        reset();
    }   

    public void reset()
    // post: resets the iterator to retraverse
    {
        todo.clear();
        // stack is empty.  Push on nodes from root to
        // leftmost descendant
        BinaryTreeNode current = root;
        while (current != null) {
            todo.push(current);
            current = current.left();
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
        BinaryTreeNode old = (BinaryTreeNode)todo.pop();
        Object result = old.value();
        // we know this node has no unconsidered left children.
        // if this node has a right child, 
        //  we push the right child and its leftmost descendants:
        // else top element of stack is next node to be visited.
        if (old.right() != null) {
            BinaryTreeNode current = old.right();
            do {
                todo.push(current);
                current = current.left();
            } while (current != null);
        }
        return result;
    }
}
