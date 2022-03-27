// The interface for Graphs.
// (c) 1998 McGraw-Hill

package structure;

public interface Graph extends Collection
{
    public void add(Object label);
    // pre: label is a non-null label for vertex
    // post: a vertex with label is added to graph.
    //       if vertex with label is already in graph, no action.

    public void addEdge(Object vtx1, Object vtx2, Object label);
    // pre: vtx1 and vtx2 are labels of existing vertices
    // post: an edge (possibly directed) is inserted between
    //       vtx1 and vtx2.

    public Object remove(Object label);
    // pre: label is non-null vertex label
    // post: vertex with "equals" label is removed, if found

    public Object removeEdge(Object vLabel1, Object vLabel2);  
    // pre: vLabel1 and vLabel2 are labels of existing vertices
    // post: edge is removed, its label is returned

    public Object get(Object label);
    // post: returns actual label of indicated vertex

    public Edge getEdge(Object label1, Object label2);
    // post: returns edge between vertices.

    public boolean contains(Object label);
    // post: returns true iff vertex with "equals" label exists.

    public boolean containsEdge(Object vLabel1, Object vLabel2);
    // post: returns true iff edge with "equals" label exists

    public boolean visit(Object label);
    // post: sets visited flag on vertex, returns previous value

    public boolean visitEdge(Edge e);
    // pre: sets visited flag on edge; returns previous value

    public boolean isVisited(Object label);
    // post: returns visited flag on labeled vertex

    public boolean isVisitedEdge(Edge e);
    // post: returns visited flag on edge between vertices

    public void reset();
    // post: resets visited flags to false

    public int size();
    // post: returns the number of vertices in graph

    public int degree(Object label);
    // pre: label labels an existing vertex
    // post: returns the number of vertices adjacent to vertex

    public int edgeCount();
    // post: returns the number of edges in graph

    public Iterator elements();
    // post: returns iterator across all vertices of graph

    public Iterator neighbors(Object label);
    // pre: label is label of vertex in graph
    // post: returns iterator over vertices adj. to vertex
    //       each edge beginning at label visited exactly once

    public Iterator edges();
    // post: returns iterator across edges of graph
    //       iterator returns edges; each edge visited once

    public void clear();
    // post: removes all vertices from graph

    public boolean isEmpty();
    // post: returns true if graph contains no vertices

    public boolean isDirected();
    // post: returns true if edges of graph are directed
}

