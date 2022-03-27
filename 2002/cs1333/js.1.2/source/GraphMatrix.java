// Graph, implemented with an adjacency matrix
// (c) 1998 McGraw-Hill

package structure;

abstract public class GraphMatrix implements Graph
{
    protected int size;          // allocation size for graph
    protected Edge data[][];     // matrix - array of arrays
    protected Dictionary dict;   // translates labels->vertices
    protected List freeList;    // available indices in matrix
    protected boolean directed;  // graph is directed

    protected GraphMatrix(int size, boolean dir)
    // pre: size > 0
    // post: constructs an empty graph that may be expanded to
    //       at most size vertices.  Graph is directed if dir true
    //       and undirected otherwise
    {
        this.size = size; // set maximum size
        directed = dir;   // fix direction of edges
        // the following constructs a size x size matrix
        data = new Edge[size][size];
        // label to index translation table
        dict = new Hashtable(size);
        // put all indices in the free list
        freeList = new SinglyLinkedList();
        for (int row = size-1; row >= 0; row--)
            freeList.add(new Integer(row));
    }

    public void add(Object label)
    // pre: label is a non-null label for vertex
    // post: a vertex with label is added to graph.
    //       if vertex with label is already in graph, no action.
    {
        // if there already, do nothing.
        if (dict.containsKey(label)) return;

        Assert.pre(!freeList.isEmpty(), "Matrix not full");
        // allocate a free row and column
        int row = ((Integer) freeList.removeFromHead()).intValue();
        // add vertex to dictionary
        dict.put(label, new GraphMatrixVertex(label, row));
    }

    abstract public void addEdge(Object v1, Object v2, Object label);
    // pre: v1 and v2 are labels of existing vertices, v1 & v2
    // post: an edge (possibly directed) is inserted between v1 and v2
    //       if edge is new, it is labeled with label (can be null)

    public Object remove(Object label)
    // pre: label is non-null vertex label
    // post: vertex with "equals" label is removed, if found
    {      
        // find and extract vertex
        GraphMatrixVertex vert;
        vert = (GraphMatrixVertex)dict.remove(label);
        if (vert == null) return null;
        // remove vertex from matrix
        int index = vert.index();
        // clear row and column entries
        for (int row=0; row<size; row++) {
            data[row][index] = null;
            data[index][row] = null;
        }
        // add node index to free list
        freeList.add(new Integer(index));
        return vert.label();
    }

    abstract public Object removeEdge(Object vLabel1, Object vLabel2);
    // pre: vLabel1 and vLabel2 are labels of existing vertices
    // post: edge is removed, its label is returned

    public Object get(Object label)
    // post: returns actual label of vertex with label "equals" 'label'
    {
        GraphMatrixVertex vert;
        vert = (GraphMatrixVertex) dict.get(label);
        return vert.label();
    }

    public Edge getEdge(Object label1, Object label2)
    // post: returns actual edge between vertices.
    {
        int row,col;
        row = ((GraphMatrixVertex) dict.get(label1)).index();
        col = ((GraphMatrixVertex) dict.get(label2)).index();
        return data[row][col];
    }

    public boolean contains(Object label)
    // post: returns true iff vertex with "equals" label exits.
    {
        return dict.containsKey(label);
    }

    public boolean containsEdge(Object vLabel1, Object vLabel2)
    // post: returns true iff edge with "equals" label exists
    {
        GraphMatrixVertex vtx1, vtx2;
        vtx1 = (GraphMatrixVertex) dict.get(vLabel1);
        vtx2 = (GraphMatrixVertex) dict.get(vLabel2);
        Assert.condition(vtx1 != null, "Vertex exists");
        Assert.condition(vtx2 != null, "Vertex exists");
        return data[vtx1.index()][vtx2.index()] != null;
    }

    public boolean visit(Object label)
    // post: sets visited flag on vertex, returns previous value
    { 
        Vertex vert = (Vertex) dict.get(label);
        return vert.visit();
    }

    public boolean visitEdge(Edge e)
    // pre: sets visited flag on edge; returns previous value
    {
        return e.visit();
    }

    public boolean isVisited(Object label)
    // post: returns visited flag on labeled vertex
    {
        GraphMatrixVertex vert;
        vert = (GraphMatrixVertex) dict.get(label);
        return vert.isVisited();
    }

    public boolean isVisitedEdge(Edge e)
    // post: returns visited flag on edge
    {
        return e.isVisited();
    }

    public void reset()
    // post: resets visited flags to false
    {
        Iterator it = dict.elements();
        for (it.reset(); it.hasMoreElements(); it.nextElement()) 
        {
            ((GraphMatrixVertex)it.value()).reset();
        }
        for (int row=0; row<size; row++)
            for (int col=0; col<size; col++) {
                Edge e = data[row][col];
                if (e != null) e.reset();
            }
    }

    public int size()
    // post: returns the actual number of vertices in graph
    {
        return dict.size();
    }

    public int degree(Object label)
    // pre: label labels an existing vertex
    // post: returns number of vertices adjacent to label
    {
        // get index
        int row = ((GraphMatrixVertex)dict.get(label)).index();
        int col;
        int result = 0;
        // count non-null columns in row
        for (col = 0; col < size; col++)
        {
            if (data[row][col] != null) result++;
        }
        return result;
    }

    abstract public int edgeCount();
    // post: returns the number of edges in graph

    public Iterator elements()
    // post: returns iterator across all vertices of graph
    {
        return dict.keys();
    }

    public Iterator neighbors(Object label)
    // pre: label is label of vertex in graph
    // post: returns iterator vertices adj. to labeled vertex
    {
        GraphMatrixVertex vert;
        vert = (GraphMatrixVertex) dict.get(label);
        List list = new SinglyLinkedList();
        for (int row=size-1; row>=0; row--)
        {
            Edge e = data[vert.index()][row];
            if (e != null) {
                if (e.here().equals(vert.label()))
                     list.add(e.there());
                else list.add(e.here());
            }
        }
        return list.elements();
    }
          
    abstract public Iterator edges();
    // post: returns iterator across all edges of graph (returns Edges)

    public void clear()
    // post: removes vertices and edges from graph
    {
        dict.clear();
        for (int row=0; row<size; row++)
            for (int col=0; col<size; col++)
                data[row][col] = null;
        freeList = new SinglyLinkedList();
        for (int row=size-1; row>=0; row--)
            freeList.add(new Integer(row));
    }

    public boolean isEmpty()
    // post: returns true iff graph is empty
    {
      return dict.isEmpty();
    }

    public boolean isDirected()
    // post: returns true iff graph is directed
    {
        return directed;
    }
}

class GraphMatrixVertex extends Vertex 
{
    protected int index;

    public GraphMatrixVertex(Object label, int idx)
    // post: constructs a new augmented vertex
    {
        super(label);
        index = idx;
    }

    public int index()
    // post: returns index associated with vertex
    {
        return index;
    }

    public String toString()
    // post: returns string representation of vertex
    {
        return "<GraphMatrixVertex: "+label()+">";
    }
}
