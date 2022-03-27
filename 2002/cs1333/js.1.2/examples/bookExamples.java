import java.lang.Math;
import structure.*;
/**
 * 
 * @version $Id: bookExamples.java,v 3.2 1998/02/24 17:13:38 bailey Exp $
 * @author duane a. bailey
 */
//+copyright
// Image compression barrel for downlink to robotic cow tipper.
// (c) 2001, 2002 duane r. bailey
//-copyright
public class bookExamples {
    public static void main(String[] args)
    {
	double x;
	for (x = 0.0; x <= 2000.0; x += 2.5) {
	    double y = sqrt(x);
	    Assert.condition(Math.abs(y*y-x)<.00000001,"sqrt function works.");
	    System.out.print("Square root of ");
	    System.out.print(x);
	    System.out.print(" is ");
	    System.out.println(sqrt(x));
	}
	sqrt(-1);
    }

// This example used in describe preconditions and postconditions.
//+assertion
    /**
     * @param x 
     * @return 
     */
    public static double sqrt(double x)
    //+sqrtPre
    // pre: x is non-negative
    //-sqrtPre
    // post: returns the square root of x
    {
	Assert.pre(x >= 0,"the value is non-negative.");
	double guess = 1.0;
	double guessSquared = guess * guess;

	while (Math.abs(x-guessSquared) >= 0.00000001) {
	    // guess is off a bit, adjust
	    guess += (x-guessSquared)/2.0/guess;
	    guessSquared = guess*guess;
	}
	return guess;
    }
//-assertion

/*
//+assertionFailure
structure.FailedPrecondition:
Assertion that failed: A precondition: the value is non-negative.
        at Assert.pre(Assert.java:17)
        at sqrt(examples.java:24)
	at main(examples.java:15)
//-assertionFailure
 */

//+reachableFrom
    static void reachableFrom(Graph g, Object vertexLabel)
    // pre: g is a non-null graph, vertexLabel labels a vertex of g
    // post: unvisited vertices reachable from vertex are visited
    {
	g.visit(vertexLabel);	// visit this vertex

	// recursively visit unvisited neighbor vertices
	Iterator ni = g.neighbors(vertexLabel);
	for (ni.reset(); ni.hasMoreElements(); ni.nextElement())
	{
	    Object neighbor = ni.value(); // adjacent node label
	    if (!g.isVisited(neighbor))
	    {
		reachableFrom(g,neighbor); // depth first search
	    }
	}
    }
//-reachableFrom
    static void ReachableFromTest()
    {
	Graph g = new GraphMatrixUndirected();
	String destinationLabel = "there";
	boolean canGetThere;
//+reachableFromEg
    g.reset();
    reachableFrom(g,sourceLabel);
    canGetThere = g.isVisited(destinationLabel));
//-reachableFromEg
    }
//+warshall
    static void warshall(Graph g)
    // pre: g is non-null
    // post: g contains edge (a,b) if there is a path from a to b.
    {
	Iterator uiter = g.elements();
	Iterator viter = g.elements();
	Iterator witer = g.elements();

	for (witer.reset();
	     witer.hasMoreElements();
	     witer.nextElement())
	{
	    Object w = witer.value();
	    for (uiter.reset();
	         uiter.hasMoreElements();
		 uiter.nextElement())
	    {
		Object u = uiter.value();
		for (viter.reset();
		     viter.hasMoreElements();
		     viter.nextElement())
		{
		    Object v = viter.value();
		    // check for edge from u to v via w
		    if (g.containsEdge(u, w) &&
			g.containsEdge(w, v))
		    {
			g.addEdge(u, v, null);
		    }	
		}
	    }
	}
    }
//-warshall
//+floyd
    static void floyd(Graph g)
    // post: g contains edge (a,b) if there is a path from a to b.
    {
	Iterator uiter = g.elements();
	Iterator viter = g.elements();
	Iterator witer = g.elements();

	for (witer.reset();
	     witer.hasMoreElements();
	     witer.nextElement())
	{
	    Object w = witer.value();
	    for (uiter.reset();
	         uiter.hasMoreElements();
		 uiter.nextElement())
	    {
		Object u = uiter.value();
		for (viter.reset();
		     viter.hasMoreElements();
		     viter.nextElement())
		{
		    Object v = viter.value();
		    if (g.containsEdge(u,w) && g.containsEdge(w,v))
		    {
			Edge leg1 = g.getEdge(u,w);
			Edge leg2 = g.getEdge(w,v);
			int leg1Dist = 
			     ((Integer)leg1.label()).intValue();
			int leg2Dist = 
			     ((Integer)leg2.label()).intValue();
			int newDist = leg1Dist+leg2Dist;

			if (g.containsEdge(u,v))
			{
			    Edge across = g.getEdge(u,v);
			    int acrossDist =
				((Integer)across.label()).intValue();
			    if (newDist < acrossDist)
				across.setLabel(new Integer(newDist));
			} else {
			    g.addEdge(u,v,new Integer(newDist));
			}
		    }	
		}
	    }
	}
    }
//-floyd
    public static void subtypingTest()
{
//+subtyping
    GraphMatrix g = new GraphMatrixDirected();

    g.add("Alice");
    g.add("Bob");
    g.addEdge("Alice","Bob","helps"); // "Alice: Help Bob!"
//-subtyping
}
//+topoSort
    /**
     * <dl>
     * <dt><b>Precondition:</b><dd> g is non-null
     * <dt><b>Postcondition:</b><dd> returns list of all vertices of g, topologically ordered
     * </dl>
     * 
     * @param g 
     * @return 
     */
    public static List topoSort(Graph g)
    // pre: g is non-null
    // post: returns list of all vertices of g, topologically ordered
    {
	// construct result list
	List l = new DoublyLinkedList();
	Iterator vi = g.elements();
	for (vi.reset(); vi.hasMoreElements(); vi.nextElement())
	{
	    // perform depth first search on unvisited vertices
	    if (!g.isVisited(vi.value()))
	    {
		DFS(g,vi.value(),l);
	    }
	}
	// result is queue of vertex labels
	return l;
    }

    static protected void DFS(Graph g, Object n, List l)
    // post: performs depth-first search enqueuing
    //       unvisited descendants of node n into l
    {
	g.visit(n); // mark node visited
	Iterator ei = g.neighbors(n); // get neighbors
	for (ei.reset(); ei.hasMoreElements(); ei.nextElement())
	{
	    // potentially deepen search if neighbor not visited
	    if (!g.isVisited(ei.value())) {
		DFS(g,ei.value(),l);
	    }
	}
	l.addToTail(n); // add this value once decendents added
    }
//-topoSort
//+atinlay
/**
 * 
 * @version $Id: bookExamples.java,v 3.2 1998/02/24 17:13:38 bailey Exp $
 * @author duane a. bailey
 */
public class atinlay {
    // a pig latin translator for 9 words
    /**
     * @param args 
     */
    public static void main(String args[])
    {
	// build and fill out an array of 9 translations
	Association dict[] = new Association[9];
	dict[0] = new Association("a","aay");
	dict[1] = new Association("bad","adbay");
	dict[2] = new Association("had","adhay");
	dict[3] = new Association("dad","adday");
	dict[4] = new Association("day","ayday");
	dict[5] = new Association("hop","ophay");
	dict[6] = new Association("on","onay");
	dict[7] = new Association("pop","oppay");
	dict[8] = new Association("sad","adsay");

	for (int argn = 0; argn < args.length; argn++)
	{   // for each argument
	    for (int dictn = 0; dictn < dict.length; dictn++)
	    {   // check each dictionary entry
		if (dict[dictn].key().equals(args[argn]))
		    System.out.println(dict[dictn].value());
	    }
	}
    }
}
//-atinlay
//+atinlayOutput
    ophay
    onay
    oppay
//-atinlayOutput
//+orderedNotVector
static void main(String args[])
{
    Vector v = new OrderedVector();

    v.addElement("Michael's Pizza");
    v.insertElementAt("Cozy Pizza",1);
    v.insertElementAt("Hot Tomatoes Pizza",0);
}
//-orderedNotVector
//+iteratorIdiom
    // an iterator to view values of structure s
    Iterator sIter;
    
    // construct iterator and print out values of s
    for (sIter = s.elements();
	 sIter.hasMoreElements();
	 sIter.nextElement())
    {           
        System.out.println(sIter.value());
    }
//-iteratorIdiom
//+vectorNonIterator
    Vector v = ...;
	...
    for (int i = 0; i < v.size(); i++) {
	System.out.println(v.elementAt(i));
    }
//-vectorNonIterator
//+isFull
    /**
     * <dl>
     * <dt><b>Postcondition:</b><dd> returns true iff the tree rooted at n is full.
     * </dl>
     * 
     * @param n 
     * @return 
     */
    public static boolean isFull(BinaryTreeNode n)
    // post: returns true iff the tree rooted at n is full.
    //-staticInterface
    {
        int h = height(n);
	int s = size(n);
	return s == (1<<(h+1))-1;
    }
//-isFull
}
