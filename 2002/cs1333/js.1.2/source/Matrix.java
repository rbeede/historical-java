// An implementation of a rectangular vector.
// (c) 1998 McGraw-Hill
package structure;

public class Matrix
{
    protected int height, width; // size of matrix
    protected Vector rows;       // vector of row vectors

    public Matrix()
    // post: constructs empty matrix
    {
        this(0,0);
    }

    public Matrix(int h, int w)
    // pre: h >= 0, w >= 0;
    // post: constructs an h row by w column matrix
    {
        height = h;  // initialize height and width
        width = w;
        // allocate a vector of rows
        rows = new Vector(height);
        for (int r = 0; r < height; r++)
        {   // each row is allocated and filled with nulls
            Vector theRow = new Vector(width);
            rows.addElement(theRow);
            for (int c = 0; c < width; c++)
            {
                theRow.addElement(null);
            }
        }
    }

    public Object elementAt(int row, int col)
    // pre: 0 <= row < height(), 0 <= col < width()
    // post: returns object at (row, col)
    {
        Assert.pre(0 <= row && row <= height, "Row in bounds.");
        Assert.pre(0 <= col && col <= width, "Col in bounds.");
        Vector theRow = (Vector)rows.elementAt(row);
        return theRow.elementAt(col);
    }

    public void setElementAt(Object value, int row, int col)
    // pre: 0 <= row < height(), 0 <= col < width()
    // post: changes location (row,col) to value
    {
        Assert.pre(0 <= row && row <= height, "Row in bounds.");
        Assert.pre(0 <= col && col <= width, "Col in bounds.");
        Vector theRow = (Vector)rows.elementAt(row);
        theRow.setElementAt(value,col);
    }

    public void insertRowAt(int r)
    // pre: 0 <= r <= height()
    // post: inserts row of null values to be row r
    {
        Assert.pre(0 <= r && r <= width, "Row in bounds.");
        height++;
        Vector theRow = new Vector(width);
        for (int c = 0; c < width; c++)
        {
            theRow.addElement(null);
        }
        rows.insertElementAt(theRow,r);
    }

    public void insertColAt(int c)
    // pre: 0 <= c <= width()
    // post: inserts column of null values to be column c
    {
        Assert.pre(0 <= c && c <= width, "Col in bounds.");
        width++;
        for (int r = 0; r < height; r++)
        {
            Vector theRow = (Vector)rows.elementAt(r);
            theRow.insertElementAt(null,c);
        }
    }

    public Vector removeRowAt(int r)
    // pre: 0 <= r < height()
    // post: removes row r and returns it as a Vector.
    {
        Assert.pre(0 <= r && r < height,"There is a row to be removed.");
        Vector result = (Vector)rows.elementAt(r);
        height--;
        rows.removeElementAt(r);
        return result;
    }

    public Vector removeColAt(int c)
    // pre: 0 <= c < width
    // post: removes column c and returns it as a vector
    {
        Assert.pre(0 <= c && c < width,"There is a column to be removed.");
        Vector result = new Vector(height);
        width--;
        for (int r = 0; r < height; r++)
        {
            Vector theRow = (Vector)rows.elementAt(r);
            result.addElement(theRow.elementAt(c));
            theRow.removeElementAt(c);
        }
        return result;
    }

    public int width()
    // post: returns number of columns in matrix
    {
        return width;
    }

    public int height()
    // post: returns number of rows in matrix
    {
        return height;
    }

    public String toString()
    // post: returns string description of matrix.
    {
        StringBuffer s = new StringBuffer();
        s.append("<Matrix:\n");
        for (int r = 0; r < height; r++)
        {
            for (int c = 0; c < width; c++)
            {
                s.append("  <Row "+r+", Col "+c+", value=");
                s.append(elementAt(r,c)+">");
            }
            s.append("\n");
        }
        s.append(">");
        return s.toString();
    }
}

