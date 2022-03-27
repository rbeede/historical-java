// Graph, implemented with an Adjacency List
// (c) 1998 McGraw-Hill

package structure;

abstract public class GraphList implements Graph
{
    protected Dictionary dict;  // label to vertex dictionary
    protected boolean directed; // is graph directed?

    protected GraphList(boolean dir)
    // post: constructs an empty graph.
    //       graph is directed iff dir is true.
    {
        dict = new Hashtable();
        directed = dir;
    }
    public void add(Object label)
    // pre: label is a non-null label for vertex
    // post: a vertex with label is added to graph.
    //       if vertex with label is already in graph, no action.
    {
        if (dict.containsKey(label)) return; // vertex exists
        GraphListVertex v = new GraphListVertex(label);
        dict.put(label,v);
    }

    abstract public void addEdge(Object v1, Object v2, Object label);
    // pre: v1 and v2 are labels of existing vertices
    // post: an edge (possibly directed) is inserted between v1 and v2
    //       if edge is new, it is labeled with label (can be null)

    abstract public Object remove(Object label);
    // pre: label is non-null vertex label
    // post: vertex with "equals" label is removed, if found

    abstract public Object removeEdge(Object vLabel1, Object vLabel2);
    // pre: vLabel1 and vLabel2 are labels of existing vertices
    // post: edge is removed, its label is returned

    public Object get(Object label)
    // pre: label labels a valid vertex
    // post: returns actual label of vertex with label "equals" 'label'
    {
        Assert.condition(dict.containsKey(label), "Vertex exists");
        return ((GraphListVertex)dict.get(label)).label();
    }

    public Edge getEdge(Object label1, Object label2)
    // post: returns actual edge between vertices.
    {    
        Assert.condition(dict.containsKey(label1), "Vertex exists");
        Edge e = new Edge(get(label1),get(label2), null, directed); 
        return ((GraphListVertex) dict.get(label1)).getEdge(e);
    }

    public boolean contains(Object label)
    // post: returns true iff vertex with "equals" label exits.
    {
        return dict.containsKey(label);   
    }

    public boolean containsEdge(Object vLabel1, Object vLabel2)
    // post: returns true iff edge with "equals" label exists
    {
        Assert.condition(dict.containsKey(vLabel1), "Vertex exists");
        Edge e = new Edge(vLabel1, vLabel2, null, directed); 
        return ((GraphListVertex) dict.get(vLabel1)).containsEdge(e);
    }

    public boolean visit(Object label)
    // post: sets visited flag on vertex, returns previous value
    {
        return ((GraphListVertex)dict.get(label)).visit();
    }
    
    public boolean visitEdge(Edge e)
    // pre: sets visited flag on edge; returns previous value
    {
        return e.visit();
    }

    public boolean isVisited(Object label)
    // post: sets visited flag on vertex, returns previous value
    {
        return ((GraphListVertex)dict.get(label)).isVisited();
    }

    public boolean isVisitedEdge(Edge e)
    // post: returns visited flag on edge between vertices
    {
        return e.isVisited();
    }

    public void reset()
    // post: resets visited flags
    {
        // reset the vertices
        Iterator vi = dict.elements();
        while (vi.hasMoreElements())
        {
            Vertex vtx = (Vertex)vi.nextElement();
            vtx.reset();
        }
        // reset the edges
        Iterator ei = edges();
        while (ei.hasMoreElements())
        {
            Edge e = (Edge)ei.nextElement();
            e.reset();
        }
    }

    public int size()
    // post: returns the number of vertices in graph
    { 
        return dict.size(); 
    }

    public int degree(Object label)
    // pre: label is a label of a vertex
    // post: returns the degree of vertex
    {
        Assert.condition(dict.containsKey(label), "Vertex exists.");
        return ((GraphListVertex) dict.get(label)).degree();
    }

    abstract public int edgeCount();
    // post: returns the number of edges in graph

    public Iterator elements()
    // post: returns iterator across all vertices of graph
    {
        return dict.keys();
    }

    public Iterator neighbors(Object label)
    // pre: label labels an existing vertex
    // post: returns an iterator traversing neighbor vertices
    {
        // return towns adjacent to vertex labeled label
        Assert.condition(dict.containsKey(label), "Vertex exists");
        return ((GraphListVertex) dict.get(label)).adjacentVertices();
    }

    public Iterator edges()
    // post: returns iterator over all edges
    {
        return new GraphListEIterator(dict);
    }

    public void clear()
    // post: removes all vertices from graph
    {
      dict.clear();
    }

    public boolean isEmpty()
    // post: returns true iff graph contains no vertices
    {
      return dict.isEmpty();
    }

    public boolean isDirected()
    // post: returns true iff graph is directed
    {
        return directed;
    }
}

class GraphListEIterator implements Iterator
{
    protected Iterator edges;

    public GraphListEIterator(Dictionary dict)
    // post: constructs a new iterator across edges of
    //       vertices within dictionary
    {
        List l = new DoublyLinkedList();
        Iterator dictIterator = dict.elements();
        while (dictIterator.hasMoreElements())
        {
            GraphListVertex vtx =
                (GraphListVertex)dictIterator.nextElement();
            Iterator vtxIterator = vtx.adjacentEdges();
            while (vtxIterator.hasMoreElements())
            {
                Edge e = (Edge)vtxIterator.nextElement();
                if (vtx.label().equals(e.here())) l.addToTail(e);
            }
        }
        edges = l.elements();
    }

    public void reset()
    // post: resets the iterator to first edge
    {
        edges.reset();
    }

    public boolean hasMoreElements()
    // post: returns true iff current element is valid
    {
        return edges.hasMoreElements();
    }

    public Object value()
    // pre: hasMoreElements()
    // post: returns the current element
    {
        return edges.value();
    }

    public Object nextElement()
    // pre: hasMoreElements()
    // post: returns current value and increments iterator
    {
        return edges.nextElement();
    }
}

class GraphListVertex extends Vertex
{
    protected Collection adjacencies; // adjacent edges

    public GraphListVertex(Object key)
    // post: constructs a new vertex, not incident to any edge
    {
        super(key); // init Vertex fields
        // new adjacency list
        adjacencies = new SinglyLinkedList();
    }

    public void addEdge(Edge e)
    // pre: e is an edge that mentions this vertex
    // post: adds edge to this vertex's adjacency list
    {
        if (!containsEdge(e)) adjacencies.add(e);
    }

    public boolean containsEdge(Edge e)
    // post: returns true if e appears on adjacency list
    {
        return adjacencies.contains(e);
    }

    public Edge removeEdge(Edge e)
    // post: removes and returns adjacent edge "equal" to e
    {
        return (Edge)adjacencies.remove(e);
    }

    public Edge getEdge(Edge e)
    // post: returns the edge that "equals" e, or null
    {
        Iterator edges = adjacencies.elements();
        while (edges.hasMoreElements())
        {
            Edge adjE = (Edge)edges.nextElement();
            if (e.equals(adjE)) return adjE;
        }
        return null;
    }

    public int degree()
    // post: returns the degree of this node
    { 
        return adjacencies.size(); 
    }

    public Iterator adjacentVertices()
    // post: returns iterator over adj. vertices
    {
        return new GraphListAIterator(adjacentEdges(), label());
    }

    public Iterator adjacentEdges()
    // post: returns iterator over adj. edges
    {
        return adjacencies.elements();
    }

    public String toString()
    // post: returns string representation of vertex
    {
        return "<GraphListVertex: "+label()+">";
    }
}

class GraphListAIterator implements Iterator
{
    protected Iterator edges;
    protected Object vertex;

    public GraphListAIterator(Iterator i, Object v)
    // pre: i is an edge iterator
    // post: returns iterator over vertices adj. to v.
    {
        edges = i;
        vertex = v;
    }

    public void reset()
    // post: resets iterator
    {
        edges.reset();
    }

    public boolean hasMoreElements()
    // post: returns true if more adj. vertices to traverse
    {
        return edges.hasMoreElements();
    }

    public Object nextElement()
    // pre: hasMoreElements
    // post: returns the next adjacent vertex
    {
        Edge e = (Edge)edges.nextElement();
        if (vertex.equals(e.here())) 
        {
            return e.there();
        } else { // N.B could be vertex if self-loop edge
            return e.here();
        }
    }

    public Object value()
    // pre: hasMoreElements
    // post: returns the current adj. vertex
    {
        Edge e = (Edge)edges.value();
        if (vertex.equals(e.here())) 
        {
            return e.there();
        } else { // NB. could be vertex if self-loop edge
            return e.here();
        }
    }
}
