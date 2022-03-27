// Graph, implemented with an adjacency list
// (c) 1998 McGraw-Hill

package structure;

public class GraphListUndirected extends GraphList
{
    public GraphListUndirected()
    // post: constructs an undirected graph
    {
        super(false);
    }

    public void addEdge(Object vLabel1, Object vLabel2, Object label)
    // pre: vLabel1 and vLabel2 are labels of existing vertices, v1 & v2
    // post: an edge (undirected) is inserted between v1 and v2
    //       if edge is new, it is labeled with label (can be null)
    {
        GraphListVertex v1 = (GraphListVertex) dict.get(vLabel1);
        GraphListVertex v2 = (GraphListVertex) dict.get(vLabel2);
        Edge e = new Edge(v1.label(), v2.label(), label, false);
        v1.addEdge(e);
        v2.addEdge(e);
    }

    public Object remove(Object label)
    // pre: label is non-null vertex label
    // post: vertex with "equals" label is removed, if found
    {
        GraphListVertex v = (GraphListVertex)dict.get(label);

        // we need to remove each of the reverse edges:
        Iterator vi = neighbors(label);
        while (vi.hasMoreElements())
        {
            // list of adjacent labels
            Object v2 = vi.nextElement();
            // this will remove both edges:
            removeEdge(label,v2);
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
        Edge e = new Edge(v1.label(), v2.label(), null, false);
        v2.removeEdge(e);
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
        return count/2;
    }

    public String toString()
    // post: returns string representation of graph
    {

        return "<GraphListUndirected: "+dict.toString()+">";
    }
}
