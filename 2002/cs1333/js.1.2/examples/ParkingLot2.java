//+implementation
import structure.*;
public class ParkingLot2
{
//+lot
    public static void main(String[] args)
    {
	//+lotA
	List free = new SinglyLinkedList();  // available
	//+decl
	OrderedStructure rented = new OrderedList(); // rented spaces
	//-decl
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
		if (size.equals("small")) request = new Space(0,Space.COMPACT);
		else if (size.equals("medium")) request = new Space(0,Space.MINIVAN);
		else request = new Space(0,Space.TRUCK);
		// check free list for appropriate sized space
		if (free.contains(request)) 
		{   // a space is available
		    location = (Space)free.remove(request);
		    //+contract
		    String renter = r.readString();
		    // link renter with space description
		    rented.add(new ComparableAssociation(renter,location));
		    System.out.println("Space "+location.number+" rented.");
		    //-contract
		} else {
		    System.out.println("No space available. Sorry.");
		}
	    }
	    //-lotC
	    else
	    //+contractsCmd
	    if (command.equals("contracts"))
	    {	// print out contracts in alphabetical order
		Iterator ci = rented.elements();
		for (ci.reset(); ci.hasMoreElements(); ci.nextElement())
		{   // extract contract from iterator
		    ComparableAssociation contract =
			(ComparableAssociation)ci.value();
		    // extract person from contract
		    String person = (String)contract.key();
		    // extract parking slot description from contract
		    Space slot = (Space)contract.value();
		    // print it out
		    System.out.println(person+" is renting "+slot.number);

		}
	    }
	    //-contractsCmd
	    else
	    //+lotD
	    if (command.equals("return")){
		String renter = r.readString(); // from whom?
		// template for finding "rental contract"
		ComparableAssociation query = 
		    new ComparableAssociation(renter);
		if (rented.contains(query))
		{   // contract found
		    ComparableAssociation contract = 
			(ComparableAssociation)rented.remove(query);
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
    rent small Carol
Space 2 rented.
    rent small Alice
Space 1 rented.
    rent large David
Space 9 rented.
    contracts
Alice is renting 1
Carol is renting 2
David is renting 9
    return Alice
Space 1 is now free.
    rent medium Eva
Space 8 rented.
    rent small Bob
Space 0 rented.
    contracts
Bob is renting 0
Carol is renting 2
David is renting 9
Eva is renting 8
    quit   
6 slots remain available.
//-interaction
*/

/*
 Input:
//+input
   rent small Carol
   rent small Alice
   rent large David
   contracts
   return Alice
   rent medium Eva
   rent small Bob
   contracts
   quit   
//-input
*/

/*
 Output:
//+output
Space 2 rented.
Space 1 rented.
Space 9 rented.
Alice is renting 1
Carol is renting 2
David is renting 9
Space 1 is now free.
Space 8 rented.
Space 0 rented.
Bob is renting 0
Carol is renting 2
David is renting 9
Eva is renting 8
6 slots remain available.
//-output
*/
