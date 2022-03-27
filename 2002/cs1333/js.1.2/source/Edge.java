// Generic base class for describing edges in graphs.
// (c) 1998 McGraw-Hill

package structure;

public class Edge
{
    protected Object[] vLabel;  // labels of adjacent vertices
    protected Object label;     // edge label
    protected boolean visited;  // this edge visited
    protected boolean directed; // this edge directed

    public Edge(Object vtx1, Object vtx2, Object label,
                boolean directed)
    // post: edge associates vtx1 and vtx2. labeled with label.
    //       directed if "directed" set true
    {
        vLabel = new Object[2];
        vLabel[0] = vtx1;
        vLabel[1] = vtx2;
        this.label = label;
        visited = false;
        this.directed = directed;
    }

    public Object here()
    // post: returns first node in edge
    {
        return vLabel[0];
    }

    public Object there()
    // post: returns second node in edge
    {
        return vLabel[1];
    }

    public void setLabel(Object label)
    // post: sets label of this edge to label 
    {
        this.label = label;
    }

    public Object label()
    // post: returns label associated with this edge
    {
        return label;
    }

    public boolean visit()
    // post: visits edge, returns whether previously visited
    {
        boolean was = visited;
        visited = true;
        return was;
    }

    public boolean isVisited()
    // post: returns true iff edge is visited
    {
        return visited;
    }

    public boolean isDirected()
    // post: returns true iff edge is directed.
    {
        return directed;
    }

    public void reset()
    // post: resets edge's visited flag to initial state
    {
        visited = false;
    }

    public int hashCode()
    // post: returns suitable hashcode.
    {
        if (directed) return here().hashCode()-there().hashCode();
        else          return here().hashCode()^there().hashCode();
    }

    public boolean equals(Object o)
    // post: returns true iff edges connect same vertices
    {   
        Edge e = (Edge)o;
        return ((here().equals(e.here()) && 
                 there().equals(e.there())) ||
                (!directed &&
                 (here().equals(e.there()) && 
                  there().equals(e.here()))));
    }
    
    public String toString()
    // post: returns string representation of edge
    {
        StringBuffer s = new StringBuffer();
        s.append("<Edge:");
        if (visited) s.append(" visited");
        s.append(" "+here());
        if (directed) s.append(" <->");
        else s.append("->");
        s.append(" "+there()+">");
        return s.toString();
    }
}
