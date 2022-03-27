import structure.*;

public class PhoneBook
{
    
    public static void main(String args[])
    {
	PhoneEntry data[] = new PhoneEntry[30];
	ReadStream r = new ReadStream();
	int count = 0;
	while (!r.eof())
	{
	    String name, title, building;
	    int room, extension;
	    name = r.readString()+" "+r.readString();
	    title = r.readString();
	    extension = r.readInt();
	    room = r.readInt();
	    building = r.readString();
	    r.readLine();
	    data[count++] = new PhoneEntry(name,title,extension,building,room);
	}
	insertionSort(data,count);
	for (int i = 0; i < count; i++)
	{
	    System.out.println(data[i]);
	}
    }

    //+swap
    protected static void swap(PhoneEntry data[], int i, int j)
    // pre: 0 <= i,j < data.length
    // post: data[i] and data[j] are exchanged
    {
	PhoneEntry temp;
	temp = data[i];
	data[i] = data[j];
	data[j] = temp;
    }
    //-swap

    //+insertionSort
    public static void insertionSort(PhoneEntry data[], int n)
    // pre: n <= data.length
    // post: values in data[0..n-1] are in ascending order
    {
	int numSorted = 1;	// number of values in place
	int index;		// general index
	while (numSorted < n)
	{
	    // take the first unsorted value
	    PhoneEntry temp = data[numSorted];
	    // ...and insert it among the sorted:
	    for (index = numSorted; index > 0; index--)
	    {
		if (temp.compareTo(data[index-1]) < 0)
		{
		    data[index] = data[index-1];
		} else {
		    break;
		}
	    }
	    // re-insert value
	    data[index] = temp;
	    numSorted++;
	}
    }
    //-insertionSort
}

//+record
class PhoneEntry
{
    String name;	// person's name
    String title;	// person's title
    int extension;	// telephone number
    int room;		// number of room
    String building;	// office building
    
    public PhoneEntry(String n, String t, int e,
		      String b, int r)
    // post: construct a new phone entry
    {
    //-record
    /*
    //+record
      ...
    //-record
     */
	name = n;
	title = t;
	building = b;
	room = r;
	extension = e;
    //+record
    }

    public int compareTo(PhoneEntry other)
    // pre: other is non-null
    // post: returns integer representing relation between values
    {
	return this.extension - other.extension;
    }
    //-record

    /*
    //+compareTo2
    public int compareTo(PhoneEntry other)
    // pre: other is non-null
    // post: returns integer representing relation between values
    {
	if (this.extension != other.extension)
	    return this.extension - other.extension;
	else return this.name.compareTo(other.name);
    }
    //-compareTo2
    */

    public String toString()
    {
	return name+" "+title+" "+extension+" "+room+" "+building;
    }
    //+record
}
//-record

class PhoneVector extends Vector
{
    //+vectorSort
    protected void swap(int i, int j)
    // pre: 0 <= i,j < this.size
    // post: elements i and j are exchanged within the vector
    {
	Object temp;
	temp = elementAt(i);
	setElementAt(elementAt(j),i);
	setElementAt(temp,j);
    }

    public void insertionSort()
    // post: values of vector are in ascending order
    {
	int numSorted = 0;	// number of values in place
	int index;		// general index
	while (numSorted < size())
	{
	    // take the first unsorted value
	    PhoneEntry temp = (PhoneEntry)elementAt(numSorted);
	    // ...and insert it among the sorted:
	    for (index = numSorted; index > 0; index--)
	    {
		if (temp.compareTo((PhoneEntry)elementAt(index-1)) < 0)
		{
		    setElementAt(elementAt(index-1),index);
		} else {
		    break;
		}
	    }
	    // re-insert value
	    setElementAt(temp,index);
	    numSorted++;
	}
    }
    //-vectorSort
}

/*
//+input
Dicks Norman      Representative 55916 2467 Rayburn
Dunn Jennifer     Representative 57761  432 Cannon
Gorton Slade      Senator        43441  730 Hart
Hastings Doc      Representative 55816 1323 Longworth
McDermott Jim     Representative 53106 2349 Rayburn
Metcalf Jack      Representative 52605 1510 Longworth
Murray Patty      Senator        42621  111 Russell
Nethercutt George Representative 52006 1527 Longworth
Smith Adam        Representative 58901 1505 Longworth
Smith Linda       Representative 53526 1317 Longworth
White Rick        Representative 56311  116 Cannon
//-input
//+output
Murray Patty Senator 42621 111 Russell
Gorton Slade Senator 43441 730 Hart
Nethercutt George Representative 52006 1527 Longworth
Metcalf Jack Representative 52605 1510 Longworth
McDermott Jim Representative 53106 2349 Rayburn
Smith Linda Representative 53526 1317 Longworth
Hastings Doc Representative 55816 1323 Longworth
Dicks Norman Representative 55916 2467 Rayburn
White Rick Representative 56311 116 Cannon
Dunn Jennifer Representative 57761 432 Cannon
Smith Adam Representative 58901 1505 Longworth
//-output
 */
