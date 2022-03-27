// This is a implementation of splay trees, in Java.
// (c) 1996, 1997, 1998 duane a. bailey
// (c) 1998 McGraw-Hill
package structure;

public class SplayTree extends BinarySearchTree
{
    public SplayTree()
    // post: construct a new splay tree
    {
        root = null;
        count = 0;
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
        }
        else
        {
            Comparable value = (Comparable)val;
            BinaryTreeNode insertLocation = locate(root,value);
            Comparable nodeValue = (Comparable)insertLocation.value();

            // The location returned is the successor or predecessor
            // of the to-be-inserted value.

            if (nodeValue.compareTo(value) < 0) {
                insertLocation.setRight(newNode);
            } else {
                if (insertLocation.left() != null) {
                    // if value is in array, we insert just before
                    predecessor(insertLocation).setRight(newNode);
                } else {
                    insertLocation.setLeft(newNode);
                }
            }
            splay(root = newNode);
        }
        count++;
    }

    public boolean contains(Object val)
    // pre: val is non-null
    // post: returns true iff val is a value found within the tree
    {
        if (root == null) return false;

        BinaryTreeNode possibleLocation = locate(root,(Comparable)val);
        if (val.equals(possibleLocation.value())) {
            splay(root = possibleLocation);
            return true;
        } else {
            return false;
        }
    }

    public Object get(Object val)
    // pre: val is non-null
    // post: returns object found in tree, or null
    {
        if (root == null) return null;

        BinaryTreeNode possibleLocation = locate(root,(Comparable)val);
        splay(root = possibleLocation);
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
                splay(root = parent);
                return location.value();
            }
        }
        return null;
    }

    protected void splay(BinaryTreeNode splayedNode)
    // pre: splayedNode is non-null node within tree
    // post: splayed note becomes root
    {
        BinaryTreeNode parent,grandParent;

        while ((parent = splayedNode.parent()) != null)
        {
            if ((grandParent = parent.parent()) == null)
            {
                if (splayedNode.isLeftChild()) parent.rotateRight();
                else parent.rotateLeft();
            }
            else
            {
                if (parent.isLeftChild())
                {
                    if (splayedNode.isLeftChild())
                    {
                        // notice the order of this rotation.
                        // not doing this in order works, but not
                        // efficiently.
                        grandParent.rotateRight();
                        parent.rotateRight();
                    }
                    else
                    {
                        parent.rotateLeft();
                        grandParent.rotateRight();
                    }
                }
                else
                {
                    if (splayedNode.isRightChild()) {
                        grandParent.rotateLeft();
                        parent.rotateLeft();
                    }
                    else
                    {
                        parent.rotateRight();
                        grandParent.rotateLeft();
                    }
                }
            }
        }
    }

    public Iterator elements()
    // post: returns iterator that traverses tree nodes in order
    {
        return new SplayTreeIterator(root);
    }

    public String toString()
    // post: returns string representation
    {
        StringBuffer s = new StringBuffer();
        s.append("<SplayTree: size="+count+" root="+root+">");
        return s.toString();
    }
}

class SplayTreeIterator implements Iterator
{
    protected BinaryTreeNode tree; // node of splay tree, root computed
    protected BinaryTreeNode current; // current node.
    // In this iterator, the "stack" normally used is implied by 
    // looking back up the path from the current node.  Those nodes
    // for which the path goes left are on the stack.

    public SplayTreeIterator(BinaryTreeNode root)
    // pre: root is the root of the tree to be traversed
    // post: constructs a new iterator to traverse splay tree
    {
        this.tree = root;
        reset();
    }

    public void reset()
    // post: resets iterator to smallest node in tree
    {
        current = tree;
        if (current != null) {
            while (current.parent() != null) current = current.parent();
            while (current.left() != null) current = current.left();
        }
    }

    public boolean hasMoreElements()
    // post: returns true if there are unvisited nodes
    {
        return current != null;
    }

    public Object nextElement()
    // pre: hasMoreElements()
    // post: returns current element and increments iterator
    {
        Object result = current.value();
        if (current.right() != null) {
            current = current.right();
            while (current.left() != null)
            {
                current = current.left();
            }
        } else {
            // we're finished with current's subtree.  We now "pop" off
            // nodes until we come to the parent of a leftchild ancestor
            // of current:
            boolean lefty;
            do
            {
                lefty = current.isLeftChild();
                current = current.parent();
            } while (current != null && !lefty);
        }
        return result;
    }
    
    public Object value()
    // pre: hasMoreElements()
    // post: returns current value.
    {
        return current.value();
    }
}
