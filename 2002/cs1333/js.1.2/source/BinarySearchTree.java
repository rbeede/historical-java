// This is an implementation of binary search trees.
// (c) 1998 McGraw-Hill
package structure;

public class BinarySearchTree implements OrderedStructure
{
    protected BinaryTreeNode root;
    protected int count;

    public BinarySearchTree()
    // post: constructs an empty binary search tree.
    {
        root = null;
        count = 0;
    }

    public boolean isEmpty()
    // post: returns true iff the binary search tree is empty.
    {
        return root == null;
    }

    public void clear()
    // post: removes all elements from binary search tree
    {
        root = null;
        count = 0;
    }

    public int size()
    // post: returns the number of elements in binary search tree
    {
        return count;
    }
    
    protected BinaryTreeNode locate(BinaryTreeNode root,
                                    Comparable value)
    // pre: root and value are non-null
    // post: returned: 1 - existing tree node with the desired value, or
    //                 2 - the node to which value should be added.
    {
        Comparable rootValue = (Comparable)root.value();
        BinaryTreeNode child;

        // found at root: done
        if (rootValue.equals(value)) return root;
        // look left if less-than, right if greater-than
        if (rootValue.compareTo(value) < 0)
        {
            child = root.right();
        } else {
            child = root.left();
        }
        // no child there: not in tree, return this node,
        // else keep searching
        if (child == null) {
            return root;
        } else {
            return locate(child, value);
        }
    }

    protected BinaryTreeNode predecessor(BinaryTreeNode root)
    // pre: tree is not empty, root node has left child.
    // post: returns pointer to predecessor of root
    {
        Assert.pre(root != null, "No predecessor to middle value.");
        Assert.pre(root.left() != null, "Root has left child.");
        BinaryTreeNode result = root.left();
        while (result.right() != null) {
            result = result.right();
        }
        return result;
    }
    
    protected BinaryTreeNode successor(BinaryTreeNode root)
    // pre: tree is not empty, root node has right child.
    // post: returns pointer to successor of root
    {
        Assert.pre(root != null, "Tree is non-null.");
        Assert.pre(root.left() != null, "Root has right child.");
        BinaryTreeNode result = root.right();
        while (result.left() != null) {
            result = result.left();
        }
        return result;
    }

    public void add(Object val)
    // pre: val is non-null
    // post: adds a value to the binary search tree.
    {
        BinaryTreeNode newNode = new BinaryTreeNode(val);

        // add value to binary search tree 
        // if there's no root, create value at root.
        if (root == null)
        {
            root = newNode;
        } else {
            Comparable value = (Comparable)val;
            BinaryTreeNode insertLocation = locate(root,value);
            Comparable nodeValue = (Comparable)insertLocation.value();
            // The location returned is the successor or predecessor
            // of the to-be-inserted value.
            if (nodeValue.compareTo(value) < 0) {
                insertLocation.setRight(newNode);
            } else {
                if (insertLocation.left() != null) {
                    // if value is in tree we insert just before
                    predecessor(insertLocation).setRight(newNode);
                } else {
                    insertLocation.setLeft(newNode);
                }
            }
        }
        count++;
    }

    public boolean contains(Object val)
    // pre: val is non-null
    // post: returns true iff val is a value found within the tree
    {
        if (root == null) return false;

        BinaryTreeNode possibleLocation = locate(root,(Comparable)val);
        return val.equals(possibleLocation.value());
    }

    public Object get(Object val)
    // pre: val is non-null
    // post: returns object found in tree, or null
    {
        if (root == null) return null;

        BinaryTreeNode possibleLocation = locate(root,(Comparable)val);
        if (val.equals(possibleLocation.value()))
          return possibleLocation.value();
        else
          return null;
    }

    public Object remove(Object val) 
    // pre: val is non-null
    // post: removes one instance of val, if found
    {
        // remove value from a binary search tree
        // no root, just quit
        Comparable cval = (Comparable)val;

        if (isEmpty()) return null;
      
        if (val.equals(root.value())) // delete root value
        {
            BinaryTreeNode newroot = removeTop(root);
            count--;
            Object result = root.value();
            root = newroot;
            return result;
        }
        else
        {
            BinaryTreeNode location = locate(root,cval);

            if (cval.equals(location.value())) {
                count--;
                BinaryTreeNode parent = location.parent();
                if (parent.right() == location) {
                    parent.setRight(removeTop(location));
                } else {
                    parent.setLeft(removeTop(location));
                }
                return location.value();
            }
        }
        return null;
    }

    protected BinaryTreeNode removeTop(BinaryTreeNode topNode)
    // pre: tree is not empty.
    // post: root of tree (topNode) is disconnected from tree and
    //       a new root is returned, new root has no parent.

    {
        // remove topmost BinaryTreeNode from a binary search tree
        BinaryTreeNode left  = topNode.left();
        BinaryTreeNode right = topNode.right();
        // disconnect top node
        topNode.setLeft(null);
        topNode.setRight(null);
        // Case a, no left BinaryTreeNode
        //   easy: right subtree is new tree
        if (left == null) { return right; }
        // Case b, no right BinaryTreeNode
        //   easy: left subtree is new tree
        if (right == null) { return left; }
        // Case c, left node has no right subtree
        //   easy: make right subtree of left
        BinaryTreeNode predecessor = left.right();
        if (predecessor == null)
        {
            left.setRight(right);
            return left;
        }
        // General case, slide down left tree
        //   harder: successor of root becomes new root
        //           parent always points to parent of predecessor
        BinaryTreeNode parent = left;
        while (predecessor.right() != null)
        {
            parent = predecessor;
            predecessor = predecessor.right();
        }
        // Assert: predecessor is predecessor of root
        parent.setRight(predecessor.left());
        predecessor.setLeft(left);
        predecessor.setRight(right);
        return predecessor;
    }

    public Iterator elements()
    // post: returns iterator to traverse BST
    {
        return new BTInorderIterator(root); 
    }

    public String toString()
    // post: generates a string representation of the BST
    {
        StringBuffer s = new StringBuffer();
        s.append("<BinarySearchTree:");
        if (root != null) {
            s.append(root);
        }
        s.append(">");
        return s.toString();
    }
}
