// Graph, implemented with an adjacency list
// (c) 1998 McGraw-Hill

package structure;

public class GraphListDirected extends GraphList
{

    public GraphListDirected()
    // post: constructs an directed graph
    {
        super(true);
    }

    public void addEdge(Object vLabel1, Object vLabel2, Object label)
    // pre: vLabel1 and vLabel2 are labels of existing vertices, v1 & v2
    // post: an edge is inserted between vLabel1 and vLabel2.
    //       if edge is new, it is labeled with label (can be null)
    {
        GraphListVertex v1 = (GraphListVertex) dict.get(vLabel1);
        GraphListVertex v2 = (GraphListVertex) dict.get(vLabel2);
        Edge e = new Edge(v1.label(), v2.label(), label, true);
        v1.addEdge(e);
    }

    public Object remove(Object label)
    // pre: label is non-null vertex label
    // post: vertex with "equals" label is removed, if found
    {
        GraphListVertex v = (GraphListVertex)dict.get(label);

        Iterator vi = elements();
        while (vi.hasMoreElements())
        {
            Object v2 = vi.nextElement();
            if (!label.equals(v2)) removeEdge(v2,label);
        }
        dict.remove(label);
        return v.label();
    }

    public Object removeEdge(Object vLabel1, Object vLabel2)  
    // pre: vLabel1 and vLabel2 are labels of existing vertices
    // post: edge is removed, its label is returned
    {
        GraphListVertex v1 = (GraphListVertex) dict.get(vLabel1);
        GraphListVertex v2 = (GraphListVertex) dict.get(vLabel2);
        Edge e = new Edge(v1.label(), v2.label(), null, true);
        e = v1.removeEdge(e);
        if (e == null) return null;
        else return e.label();
    }

    public int edgeCount()
    // post: returns the number of edges in graph
    {
        int count = 0;
        Iterator i = dict.elements();
        for (i.reset(); i.hasMoreElements(); i.nextElement()) 
            count += ((GraphListVertex) i.value()).degree();
        return count;
    }

    public String toString()
    // post: returns string representation of graph
    {

        return "<GraphListDirected: "+dict.toString()+">";
    }
}
