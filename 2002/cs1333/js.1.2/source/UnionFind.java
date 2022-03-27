// A forest implementation of the Union-Find structure.
// (c) 1998 McGraw-Hill

package structure;

public class UnionFind
{
    protected UnionFindElement element[];
    public UnionFind(int elementCount)
    // pre: elementCount >= 0
    // post: constructs elementCount disjoint sets labeled 0..elementCount-1
    {
        int i;

        element = new UnionFindElement[elementCount];
        for (i = 0; i < elementCount; i++)
        {
            element[i] = new UnionFindElement(i);
        }
    }

    public int find(int i)
    // pre: 0 <= i < elementCount
    // post: returns the index or name of the set containing i
    {
        int name = i;
        if (element[i].setName != i) {
            name = find(element[i].setName);
            element[i].setName = name;
        }
        return name;
    }

    public int union(int i,int j)
    // pre: 0 <= i,j < elementCount
    // post: merges sets containing i and j, and returns new set name.
    {
        i = find(i);
        j = find(j);
        if (i == j) return i;
        if (element[i].size < element[j].size) 
        { int temp = i; i = j; j = temp; }
        // set i is as large as j
        element[j].setName = i;
        element[i].size += element[j].size;
        return i;
    }

    public String toString()
    // post: returns string representation of sets
    {
        int i,j,first;
        int size = element.length;
        StringBuffer sb = new StringBuffer();

        sb.append("{UnionFind:");
        for (i = 0; i < size; i++)
        {
            if (element[i].setName == i) {
                sb.append(" {");
                for (j = 0, first = 1; j < size; j++)
                {
                    if (element[j].setName == i)
                    {
                        if (first == 1) sb.append(j);
                        else sb.append(" "+j);
                        first = 0;
                    }
                }
                sb.append("}");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}

class UnionFindElement
{
    protected int size;
    protected int name;
    /*
     * The name of the set containing this element
     */
    protected int setName;

    public UnionFindElement(int i)
    // post: constructs a new set, with label i
    {
        size = 1;
        name = setName = i;
    }

    public String toString()
    // post: returns string representation of set
    {
        return "<UnionFindElement: "+name+" in set "+setName+">";
    }
}
