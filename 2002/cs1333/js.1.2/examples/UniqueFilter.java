import structure.*;

//+interface
public class UniqueFilter implements Iterator
{
    //-interface
    //+constructor
    protected Iterator base; // slave iterator
    protected List observed;  // list of previous values

    //+interface
    public UniqueFilter(Iterator baseIterator)
    // pre: baseIterator is a non-null iterator
    // post: constructs unique-value filter
    //       host iterator is reset
    //-interface
    {
	base = baseIterator;
	reset();
    }
    //+interface

    public void reset()
    // post: master and base iterators are reset
    //-interface
    {
	base.reset();
	observed = new SinglyLinkedList();
    }
    //-constructor
    //+interface

    //+values
    public boolean hasMoreElements()
    // post: returns true if there are more values available
    //       from base stream.
    //-interface
    {
	return base.hasMoreElements();
    }
    //-values
    //+interface

    //+cdrMethods
    public Object nextElement()
    // pre: traversal has more elements
    // post: returns current value and increments the iterator
    //-interface
    {
	Object current = base.nextElement();
	// record observation of current value
	observed.add(current);
	// now seek next new value
	while (base.hasMoreElements())
	{
	    Object possible = base.value();
	    if (!observed.contains(possible))
	    {	// new value found! leave
		break;
	    } else {
		// old value, continue
		base.nextElement();
	    }
	}
	return current;
    }
    //-cdrMethods
    //+interface
    //+values

    public Object value()
    // pre: traversal has more elements
    // post: returns the current value referenced by the iterator 
    //-interface
    {
	return base.value();
    }
    //-values
    
    public static void main(String args[])
    {
	ReadStream r = new ReadStream();
	//+main
	Vector data = new Vector(1000);
	//-main
	/*
	//+main
          ...
	//-main
	*/
	for (r.skipWhite(); !r.eof(); r.skipWhite())
	{
	    data.addElement(r.readString());
	}
	//+main
	Iterator dataIterator = data.elements();
	Iterator ui = new UniqueFilter(dataIterator);
	int count=0;

	for (ui.reset(); ui.hasMoreElements(); ui.nextElement())
	{
	    System.out.print(ui.value()+" ");
	    if (++count%8==0) System.out.println();	
	}
	System.out.println();
	//-main
    }
    //+interface
}
//-interface
/*
//+input
four score and seven years ago our fathers brought forth on this
continent a new nation conceived in liberty and dedicated to the
proposition that all men are created equal now we are engaged in a
great civil war testing whether that nation or any nation so conceived
and so dedicated can long endure we are met on a great battlefield of
that war we have come to dedicate a portion of that field as a final
resting place for those who here gave their lives that that nation
might live it is altogether fitting and proper that we should do this
but in a larger sense we cannot dedicate we cannot consecrate we
cannot hallow this ground the brave men living and dead who struggled
here have consecrated it far above our poor power to add or detract
the world will little note nor long remember what we say here but it
can never forget what they did here it is for us the living rather to
be dedicated here to the unfinished work which they who fought here
have thus far so nobly advanced it is rather for us to be here
dedicated to the great task remaining before us that from these
honored dead we take increased devotion to that cause for which they
gave the last full measure of devotion that we here highly resolve
that these dead shall not have died in vain that this nation under God
shall have a new birth of freedom and that government of the people by
the people for the people shall not perish from the earth
//-input
*/

/*
//+output
four score and seven years ago our fathers 
brought forth on this continent a new nation 
conceived in liberty dedicated to the proposition that 
all men are created equal now we engaged 
great civil war testing whether or any so 
can long endure met battlefield of have come 
dedicate portion field as final resting place for 
those who here gave their lives might live 
it is altogether fitting proper should do but 
larger sense cannot consecrate hallow ground brave living 
dead struggled consecrated far above poor power add 
detract world will little note nor remember what 
say never forget they did us rather be 
unfinished work which fought thus nobly advanced task 
remaining before from these honored take increased devotion 
cause last full measure highly resolve shall not 
died vain under God birth freedom government people 
by perish earth 
//-output
*/
