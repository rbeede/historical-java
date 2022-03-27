// Vertex base class, to be extended as needed.
// (c) 1998 McGraw-Hill

package structure;

class Vertex
{
    protected Object label;     // the user's label
    protected boolean visited;  // this vertex visited

    public Vertex(Object label)
    // post: constructs unvisited vertex with label
    {
        Assert.pre(label != null, "Vertex key is non-null");
        this.label = label;
        visited = false;
    }

    public Object label()
    // post: returns user label associated w/vertex
    {
        return label;
    }

    public boolean visit()
    // post: returns, then marks vertex as being visited.
    {
        boolean was = visited;
        visited = true;
        return was;
    }

    public boolean isVisited()
    // post: returns true iff vertex has been visited
    {
        return visited;
    }
 
    public void reset()
    // post: marks vertex unvisited
    {
        visited = false;
    }
    
    public boolean equals(Object o)
    // post: returns true iff vertex labels are equal
    {
        Vertex v = (Vertex)o;
        return label.equals(v.label());
    }

    public int hashCode()
    // post: returns hash code for vertex
    {
        return label.hashCode();
    }

    public String toString()
    // post: returns string representation of vertex.
    {
        return "<Vertex: "+label+">";
    }
}
  
