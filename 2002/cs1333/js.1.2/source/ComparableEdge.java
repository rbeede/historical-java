// Generic base class for describing edges in graphs.
// (c) 1998 McGraw-Hill

package structure;

public class ComparableEdge extends Edge implements Comparable
{
    public ComparableEdge(Object vtx1, Object vtx2, Object label,
                boolean directed)
    // post: edge associates vtx1 and vtx2. labeled with label.
    //       directed if "directed" set true
    {
        super(vtx1,vtx2,label,directed);
    }

    public ComparableEdge(Edge e)
    // post: edge associates vtx1 and vtx2. labeled with label.
    //       directed if "directed" set true
    {
        this(e.here(),e.there(),e.label(),e.isDirected());
    }

    public int compareTo(Object other)
    // pre: labels are Comparable
    // post: returns integer representing relation between labels on edges.
    {
        Comparable thisLabel = (Comparable)label();
        Comparable thatLabel = (Comparable)((Edge)other).label();
        return thisLabel.compareTo(thatLabel);
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
