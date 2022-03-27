// Graph, implemented with an adjacency matrix
// (c) 1998 McGraw-Hill

package structure;

public class GraphMatrixUndirected extends GraphMatrix
{
    public GraphMatrixUndirected(int size)
    // pre: size > 0
    // post: constructs an empty graph that may be expanded to
    //       at most size vertices.  Graph is undirected.
    {
        super(size,false);
    }
    public void addEdge(Object vLabel1, Object vLabel2, Object label)
    // pre: vLabel1 and vLabel2 are labels of existing vertices, v1 & v2
    // post: an edge (possibly directed) is inserted between v1 and v2
    //       if edge is new, it is labeled with label (can be null)
    {
        GraphMatrixVertex vtx1,vtx2;
        // get vertices
        vtx1 = (GraphMatrixVertex) dict.get(vLabel1);
        vtx2 = (GraphMatrixVertex) dict.get(vLabel2);
        // update matrix with new edge
        Edge e = new Edge(vtx1.label(), vtx2.label(), label, false);
        data[vtx1.index()][vtx2.index()] = e;
        data[vtx2.index()][vtx1.index()] = e;
    }

    public Object removeEdge(Object vLabel1, Object vLabel2)
    // pre: vLabel1 and vLabel2 are labels of existing vertices
    // post: edge is removed, its label is returned
    {
        // get indices
        int row = ((GraphMatrixVertex)dict.get(vLabel1)).index();
        int col = ((GraphMatrixVertex)dict.get(vLabel2)).index();
        // cache old value
        Edge e = data[row][col];
        // update matrix
        data[row][col] = null;
        data[col][row] = null;
        if (e == null) return null;
        else return e.label();
    }

    public int edgeCount()
    // post: returns the number of edges in graph
    {
        // count non-null entries in table
        int sum = 0;                
        for (int row=0; row<size; row++) 
            for (int col=row; col<size; col++)
                if (data[row][col] != null) sum++;
        return sum;
    }
          
    public Iterator edges()
    // post: returns iterator across all edges of graph (returns Edges)
    {
        List list = new SinglyLinkedList();
        for (int row=size-1; row>=0; row--) 
            for (int col=size-1; col >= row; col--) {
                Edge e = data[row][col];
                if (e != null) list.add(e);
            }
        return list.elements();
    }

    public String toString()
    // post: returns string representation of graph
    {
        StringBuffer s = new StringBuffer();
        Iterator source = elements();
        Iterator dest;

        s.append("<GraphMatrixUndirected:");
        for (source.reset(); source.hasMoreElements(); source.nextElement()) {
            s.append(" ("+source.value()+"->");
            dest = neighbors(source.value());
            for (dest.reset(); dest.hasMoreElements(); dest.nextElement()) {
                s.append(source.value()+"->"+dest.value());
            }
            s.append(")");
        }
        s.append(">");
        return s.toString();
    }
}
