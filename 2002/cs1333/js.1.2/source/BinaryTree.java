// An interface for Binary Trees.
// (c) 1998 McGraw-Hill
package structure;

public class BinaryTree
{
    protected BinaryTreeNode root;   // the root of the binary tree
    protected BinaryTreeNode cursor; // pointer to the current node
    protected BinaryTreeNode prior;  // cursor's prior value
    protected boolean wentLeft;      // cursor result of moving left
    protected int size;              // the size of the tree

    public BinaryTree()
    // post: creates an empty binary tree
    {
        clear();
    }

    public void clear()
    // post: removes all nodes from tree
    {
        root = null;
        cursor = null;
        prior = null;
        size = 0;
        wentLeft = false;       // arbitrary
    }

    public void insert(Object value)
    // pre: cursor is null (invalid)
    // post: if tree is empty, value is inserted at root, otherwise
    //       value is inserted where cursor last moved off tree
    {
        Assert.pre(cursor == null,"Insertion does not overwrite value.");
        if (prior == null) {
            Assert.pre(root == null,
                       "Insertion at root only allowed in empty tree.");
            cursor = root = new BinaryTreeNode(value);
        } else {
            if (wentLeft) {
                prior.setLeft(cursor = new BinaryTreeNode(value));
            } else {
                prior.setRight(cursor = new BinaryTreeNode(value));
            }
        }
        size++;
    }

    public Object remove()
    // pre: cursor is valid and has no children
    // post: leaf is removed, cursor is moved to parent, if any
    {
        Assert.pre(cursor != null,"Node to be removed exists.");
        Assert.pre(!(hasLeft()||hasRight()),
                   "Node to be removed is leaf.");
        Object value = cursor.value();
        if (isLeftChild()) {
            moveUp();
            cursor.setLeft(null);
        } else if (isRightChild()) {
            moveUp();
            cursor.setRight(null);
        } else {
            root = cursor = prior = null;
        }
        size--;
        return value;
    }

    public Object value()
    // pre: cursor valid
    // post: returns value of object at cursor
    {
        return cursor.value();
    }

    public void setValue(Object value)
    // pre: cursor valid
    // post: sets value found at cursor
    {
        cursor.setValue(value);
    }

    public void reset()
    // post: moves the cursor to the root, if any
    {
        cursor = root;
        prior = null;
        wentLeft = false; // arbitrary
    }

    public boolean valid()
    // post: returns true if the cursor points to a valid node.
    {
        return cursor != null;
    }

    public void rotateLeft()
    // pre: cursor is valid, has right child
    // post: rotates the tree to left about cursor.
    //       cursor points to new root of subtree
    {
        Assert.pre(hasRight(),"Current node has right child.");
        cursor.rotateLeft();
        moveUp();
        if (root.parent() != null) root = root.parent();
    }

    public void rotateRight()
    // pre: cursor is valid, has left child
    // post: rotates the tree to left about cursor.
    //       cursor points to new root of subtree
    {
        Assert.pre(hasLeft(),"Current node has left child.");
        cursor.rotateRight();
        moveUp();
        if (root.parent() != null) root = root.parent();
    }

    public boolean hasLeft()
    // post: returns true iff cursor has left child
    {
        return (cursor != null) && (cursor.left() != null);
    }

    public boolean hasRight()
    // post: returns true iff cursor has right child 
    {
        return (cursor != null) && (cursor.right() != null);
    }

    public boolean hasParent()
    // pre: cursor is valid
    // post: returns true iff cursor has parent
    {
        return (cursor != null) && (cursor.parent() != null);
    }

    public boolean isLeftChild()
    // post: returns true if cursor has parent and is left child
    {
        return (cursor != null) && cursor.isLeftChild();
    }

    public boolean isRightChild()
    // post: returns true if cursor has parent and is right child
    {
        return (cursor != null) && cursor.isRightChild();
    }

    public void moveLeft()
    // pre: cursor is valid
    // post: cursor moves to left child of pre-cursor, or off tree
    {
        prior = cursor;
        wentLeft = true;
        cursor = cursor.left();
    }

    public void moveRight()
    // pre: cursor is valid
    // post: cursor moves to right child of pre-cursor, or off tree
    {
        prior = cursor;
        wentLeft = false;
        cursor = cursor.right();
    }

    public void moveUp()
    // pre: cursor is valid
    // post: cursor moves up to parent of pre-cursor
    {
        prior = null;
        cursor = cursor.parent();
    }

    public int height()
    // post: returns height of cursor in tree
    //       or -1 if tree is empty
    {
        return BinaryTreeNode.height(cursor);
    }

    public int depth()
    // post: returns depth of cursor in tree
    //       or -1 if tree is empty
    {
        return BinaryTreeNode.depth(cursor);
    }

    public boolean isFull()
    // post: returns true iff subtree rooted at cursor is full
    {
        return BinaryTreeNode.isFull(cursor);
    }

    public boolean isComplete()
    // post: returns true iff subtree rooted at cursor is complete
    {
        return BinaryTreeNode.isComplete(cursor);
    }

    public boolean isEmpty()
    // post: returns true iff tree is empty
    {
        return size == 0;
    }

    public int size()
    // post: returns number of nodes in tree
    {
        return size;
    }

    public Iterator elements()
    // post: returns inorder traversal of tree
    {
        return inorderElements();
    }

    public Iterator inorderElements()
    // post: returns inorder traversal of tree
    {
        return new BTInorderIterator(root);
    }

    public Iterator preorderElements()
    // post: returns preorder traversal of tree
    {
        return new BTPreorderIterator(root);
    }

    public Iterator postorderElements()
    // post: returns postorder traversal of tree
    {
        return new BTPostorderIterator(root);
    }

    private void format(BinaryTreeNode n,StringBuffer sb)
    // pre: sb non-null, n non-null
    // post: appends to sb a string representation of subtree rooted at n
    {
        if (n.left() == null && n.right() == null) {
            sb.append(" "+n.value());
        } else {
            sb.append(" ("+n.value());
            if (n.left() != null) format(n.left(),sb);
            else sb.append(" -");
            if (n.right() != null) format(n.right(),sb);
            sb.append(")");
        }
    }

    public String toString()
    // post: returns string representation of tree
    {
        StringBuffer sb = new StringBuffer();
        sb.append("<BinaryTree:");
        if (root != null) format(root,sb);
        sb.append(">");
        return sb.toString();
    }
}
