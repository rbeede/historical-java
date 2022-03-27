import structure.*;

public class Huffman
{
    //+main
    public static void main(String args[])
    {
        ReadStream r = new ReadStream();
        List freq = new SinglyLinkedList();
    
        // read data from input
        while (!r.eof())
        {
            char c = r.readChar();
	    //-main
	    if (c == '\n') continue;
	    //+main
            // look up character in frequency list
            leaf query = new leaf(c);
            leaf item = (leaf)freq.remove(query);
            if (item == null)
            {   // not found, add new leaf
                freq.add(query);
            } else { // found, increment leaf
                item.frequency++;
                freq.add(item);
            }
        }
             
        // insert each character into a huffman tree
        Iterator li = freq.elements();
	//+mergingPhase
        OrderedList trees = new OrderedList();
	//-mergingPhase
        for (li.reset(); li.hasMoreElements(); li.nextElement())
        {
            trees.add(new huffmanTree((leaf)li.value()));
        }
    
	//+mergingPhase
        // merge trees in pairs until one remains
        Iterator ti = trees.elements();
        while (trees.size() > 1)
        {
            // construct a new iterator
            ti = trees.elements();
            // grab two smallest values
            huffmanTree smallest = (huffmanTree)ti.nextElement();
            huffmanTree small = (huffmanTree)ti.nextElement();
            // remove them
            trees.remove(smallest);
            trees.remove(small);
            // add bigger tree containing both
            trees.add(new huffmanTree(smallest,small));
        }
        // print only tree in list
        ti  = trees.elements();
        huffmanTree encoding = (huffmanTree)ti.value();
	//-mergingPhase
        encoding.print();
    }
    //-main
}

//+leaf
class leaf
{
    int frequency; // frequency of char
    char ch;	// the character

    public leaf(char c)
    // post: construct character entry with frequency 1
    //-leaf
    {
	ch = c;
	frequency = 1;
    }
    //+leaf

    public boolean equals(Object other)
    // post: return true if leaves represent same character
    //-leaf
    {
	leaf that = (leaf)other;
	return this.ch == that.ch;
    }
    //+leaf
}
//-leaf

//+huffmanTree
class huffmanTree implements Comparable
{
    BinaryTreeNode root; // root of tree
    int totalWeight;     // weight of tree

    public huffmanTree(leaf e)
    // post: construct a leaf with associated character
    //-huffmanTree
    {
	root = new BinaryTreeNode(e);
	totalWeight = e.frequency;
    }
    //+huffmanTree

    public huffmanTree(huffmanTree left, huffmanTree right)
    // pre: left and right non-null
    // post: merge two trees together and merge their weights
    //-huffmanTree
    {
	this.totalWeight = left.totalWeight + right.totalWeight;
	root = new BinaryTreeNode(null,left.root,right.root);
    }
    //+huffmanTree

    public int compareTo(Object other)
    // pre: other is not null
    // post: return integer reflecting relation between values
    //-huffmanTree
    {
	huffmanTree that = (huffmanTree)other;
	return this.totalWeight - that.totalWeight;
    }
    //+huffmanTree

    public boolean equals(Object that)
    // post: return true if this and that are same tree instance
    //-huffmanTree
    {
	return this == that;
    }
    //+huffmanTree
    
    public void print()
    // post: print out strings associated with characters in tree
    //-huffmanTree
    {
	print(this.root,"");
    }
    //+huffmanTree

    protected void print(BinaryTreeNode r, String representation)
    // post: print out strings associated with chars in tree r,
    //       prefixed by representation
    //-huffmanTree
    {
	if (r.left() != null)
	{   // interior node
	    print(r.left(),representation+"0"); // append a 0
	    print(r.right(),representation+"1"); // append a 1
	} else { // leaf; print encoding
	    leaf e = (leaf)r.value();
	    System.out.println("Encoding of "+e.ch+" is "+
	       representation+" (frequency was "+e.frequency+")");
	}
    }
    //+huffmanTree
}
//-huffmanTree

/*
//+input
If a woodchuck could chuck wood!
//-input
*/
/*
//+output
    Encoding of ! is 0000 (frequency was 1)
    Encoding of a is 00010 (frequency was 1)
    Encoding of l is 00011 (frequency was 1)
    Encoding of u is 001 (frequency was 3)
    Encoding of d is 010 (frequency was 3)
    Encoding of k is 0110 (frequency was 2)
    Encoding of w is 0111 (frequency was 2)
    Encoding of I is 10000 (frequency was 1)
    Encoding of f is 10001 (frequency was 1)
    Encoding of h is 1001 (frequency was 2)
    Encoding of c is 101 (frequency was 5)
    Encoding of   is 110 (frequency was 5)
    Encoding of o is 111 (frequency was 5)
//-output
    Old length=256 new length=111 57% compression.
*/
