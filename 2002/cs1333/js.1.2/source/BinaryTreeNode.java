// An implementation of nodes for use in binary trees.
// (c) 1998 McGraw-Hill
package structure;
import java.lang.Math;

public class BinaryTreeNode
{
    protected Object val; // value associated with node
    protected BinaryTreeNode parent; // parent of node
    protected BinaryTreeNode left; // left child of node
    protected BinaryTreeNode right; // right child of node

    public BinaryTreeNode(Object value)
    // post: returns a tree referencing value with two null subtrees
    {
        val = value;
        parent = left = right = null;
    }

    public BinaryTreeNode(Object value,
                          BinaryTreeNode left,
                          BinaryTreeNode right) 
    // post: returns a node referencing value & subtrees
    {
        this(value);
        setLeft(left);
        setRight(right);
    }

    public BinaryTreeNode left()
    // post: returns reference to left subtree, or null
    {
        return left;
    }

    public BinaryTreeNode right()
    // post: returns reference to right subtree, or null
    {
        return right;
    }

    public BinaryTreeNode parent()
    // post: returns reference to parent node, or null
    {
        return parent;
    }
    
    public void setLeft(BinaryTreeNode newLeft)
    // post: sets left subtree to newLeft
    //       re-parents newLeft if not null
    {
        if (left != null &&
            (left.parent() == this)) left.setParent(null);
        left = newLeft;
        if (left != null) left.setParent(this);
    }

    public void setRight(BinaryTreeNode newRight)
    // post: sets left subtree to newRight
    //       re-parents newRight if not null
    {
        if (right != null &&
            (right.parent() == this)) right.setParent(null);
        right = newRight;
        if (right != null) right.setParent(this);
    }

    protected void setParent(BinaryTreeNode newParent)
    // post: re-parents this node to parent reference, or null
    {
        parent = newParent;
    }

    public static int size(BinaryTreeNode n)
    // post: returns the size of the subtree rooted at n
    {
        if (n == null) return 0;
        return size(n.left()) + size(n.right()) + 1;
    }

    public static BinaryTreeNode root(BinaryTreeNode n)
    // post: returns the root of the tree node n
    {
        if ((n == null) || (n.parent() == null)) return n;
        else return root(n.parent());
    }

    public static int height(BinaryTreeNode n)
    // post: returns the height of a node n in its tree
    {
        if (n == null) return -1;
        return 1 + Math.max(height(n.left()),height(n.right()));
    }

    public static int depth(BinaryTreeNode n)
    // post: returns the depth of a node in the tree
    {
        if (n == null) return -1;
        return 1 + depth(n.parent());
    }

    public static boolean isFull(BinaryTreeNode n)
    // post: returns true iff the tree rooted at n is full.
    {
        if (n == null) return true;
        if (height(n.left()) != height(n.right())) return false;
        return isFull(n.left()) && isFull(n.right());
    }
    
    public static boolean isComplete(BinaryTreeNode n)
    // post: returns true iff the tree rooted at n is complete
    {
        int leftHeight, rightHeight;
        boolean leftIsFull, rightIsFull;
        boolean leftIsComplete, rightIsComplete;
        if (n == null) return true;
        leftHeight = height(n.left());
        rightHeight = height(n.right());
        leftIsFull = isFull(n.left());
        rightIsFull = isFull(n.right());
        leftIsComplete = isComplete(n.left());
        rightIsComplete = isComplete(n.right());

        // case 1: left is full, right is complete, heights same
        if (leftIsFull && rightIsComplete &&
            (leftHeight == rightHeight)) return true;
        // case 2: left is complete, right is full, heights differ
        if (leftIsComplete && rightIsFull &&
            (leftHeight == (rightHeight + 1))) return true;
        return false;
    }

    public static boolean isBalanced(BinaryTreeNode n)
    // post: returns true iff the tree rooted at n is balanced
    {
        if (n == null) return true;
        return (Math.abs(height(n.left())-height(n.right())) <= 1) &&
               isBalanced(n.left()) && isBalanced(n.right());
    }

    public Iterator elements()
    // post: returns an inorder traversal of the elements.
    {
        return inorderElements();
    }

    public Iterator preorderElements()
    // post: The elements of the binary tree rooted at node n are
    //       traversed in preorder
    {
        return new BTPreorderIterator(this);
    }

    public Iterator inorderElements()
    // post: The elements of the binary tree rooted at node n are
    //       traversed in inorder
    {
        return new BTInorderIterator(this);
    }

    public Iterator postorderElements()
    // pre: none
    // post: The elements of the binary tree rooted at node n are
    //       traversed in postorder
    {
        return new BTPostorderIterator(this);
    }

    public Iterator levelorderElements()
    // pre: none
    // post: The elements of the binary tree rooted at node n are
    //       traversed in levelorder
    {
        return new BTLevelorderIterator(this);
    }

    protected void rotateRight()
    // pre: this node has a left subtree
    // post: rotates local portion of tree so left child is root
    {
        BinaryTreeNode parent = parent();
        BinaryTreeNode newRoot = left();
        boolean wasChild = parent != null;
        boolean wasLeftChild = isLeftChild();

        // hook in new root (sets newRoot's parent, as well)
        setLeft(newRoot.right());

        // put pivot below it (sets this's parent, as well)
        newRoot.setRight(this);

        if (wasChild) {
            if (wasLeftChild) parent.setLeft(newRoot);
            else              parent.setRight(newRoot);
        }
    }

    protected void rotateLeft()
    // pre: this node has a right subtree
    // post: rotates local portion of tree so right child is root
    {
        // all of this information must be grabbed before
        // any of the references are set.  Draw a diagram for help.
        BinaryTreeNode parent = parent();
        BinaryTreeNode newRoot = right();
        // is the this node a child; if so, a left child?
        boolean wasChild = parent != null;
        boolean wasRightChild = isRightChild();

        // hook in new root (sets newRoot's parent, as well)
        setRight(newRoot.left());

        // put pivot below it (sets this's parent, as well)
        newRoot.setLeft(this);

        if (wasChild) {
            if (wasRightChild) parent.setRight(newRoot);
            else               parent.setLeft(newRoot);
        }
    }

    public boolean isLeftChild()
    // post: returns true if this is a left child of parent.
    {
        if (parent() == null) return false;
        return this == parent().left();
    }

    public boolean isRightChild()
    // post: returns true if this is a right child of parent.
    {
        if (parent() == null) return false;
        return this == parent().right();
    }

    public Object value()
    // post: returns value associated with this node.
    {
        return val;
    }

    public void setValue(Object value)
    // post: sets the value associated with this node
    {
        val = value;
    }

    public String toString()
    // post: returns string representation
    {
        StringBuffer s = new StringBuffer();
        s.append("<BinaryTreeNode "+value());
        if (left != null) s.append(" "+left());
        else s.append(" -");
        if (right != null) s.append(" "+right());
        else s.append(" -");
        s.append('>');
        return s.toString();
    }
}

