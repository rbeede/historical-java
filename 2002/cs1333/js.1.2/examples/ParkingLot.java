//+implementation
import structure.*;
public class ParkingLot
{
//+lot
    public static void main(String[] args)
    {
	//+lotA
	List free = new SinglyLinkedList();  // available
	List rented = new SinglyLinkedList(); // rented spaces
	for (int number = 0; number < 10; number++) 
	{
	    if (number < 3) // 3 small spaces
		free.add(new Space(number,Space.COMPACT));
	    else if (number < 9) // 6 medium spaces
		free.add(new Space(number,Space.MINIVAN));
	    else // 1 large space
		free.add(new Space(number,Space.TRUCK));
	}
	//-lotA
	//+lotB
	ReadStream r = new ReadStream();
	for (r.skipWhite(); !r.eof(); r.skipWhite())
	{
	    String command = r.readString(); // rent/return
	    //-lotB
	    /*
	      //+lotB
	      ...
	      //-lotB
	    */
	    //+lotC
	    //+lotD
	    Space location;
	    //-lotD
	    if (command.equals("rent"))
	    {	// attempt to rent a parking space
		String size = r.readString();
		Space request;
		if (size.equals("small")) 
		    request = new Space(0,Space.COMPACT);
		else if (size.equals("medium")) 
		    request = new Space(0,Space.MINIVAN);
		else request = new Space(0,Space.TRUCK);
		// check free list for appropriate sized space
		if (free.contains(request)) 
		{   // a space is available
		    location = (Space)free.remove(request);
		    String renter = r.readString(); // to whom?
		    // link renter with space description
		    rented.add(new Association(renter,location));
		    System.out.println("Space "+location.number+" rented.");
		} else {
		    System.out.println("No space available. Sorry.");
		}
	    }
	    //-lotC
	    else
	    //+lotD
	    if (command.equals("return")){
		String renter = r.readString(); // from whom?
		// template for finding "rental contract"
		Association query = new Association(renter);
		if (rented.contains(query))
		{   // contract found
		    Association contract = (Association)rented.remove(query);
		    location = (Space)contract.value(); // where?
		    free.addToTail(location); // put in free list
		    System.out.println("Space "+location.number+" is now free.");
		} else {
		    System.out.println("No space rented to "+renter);
		}
	    }
	    //-lotD
	    else break;
	    //+lotB
	}
	System.out.println(free.size()+" slots remain available.");
	//-lotB
    }
//-lot
}

//+space
class Space
{   // structure describing parking space
    public final static int COMPACT = 0; // small space
    public final static int MINIVAN = 1; // medium space
    public final static int TRUCK = 2;   // large space
    protected int number;	// address in parking lot
    protected int size;		// size of space
    public Space(int n, int s)
    // post: construct parking space #n, size s
    {
	number = n;
	size = s;
    }
    public boolean equals(Object other)
    // pre: other is not null
    // post: true iff spaces are equivalent size
    {
	Space that = (Space)other;
	return this.size == that.size;
    }
}
//-space

//-implementation

/*
 Interaction
//+interaction
   rent small Alice
Space 2 rented.
   rent large Bob
Space 9 rented.
   rent small Carol
Space 1 rented.
   return Alice
Space 2 is now free.
   return David
No space rented to David
   rent small David
Space 0 rented.
   rent small Eva
Space 2 rented.
   quit
6 slots remain available.
//-interaction
*/

/*
 Input:
//+input
rent small John
rent large Anne
rent small Jim
return Jim
return George
rent small George
rent small Bob
quit
//-input
*/

/*
 Output:
//+output
Space 2 rented.
Space 9 rented.
Space 1 rented.
Space 1 is now free.
No space rented to George
Space 0 rented.
Space 1 rented.
6 slots remain available.
//-output
*/
