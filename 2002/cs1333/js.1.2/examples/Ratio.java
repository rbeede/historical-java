/**
 * 
 * @version $Id: Ratio.java,v 3.0 1998/01/13 13:54:31 bailey Exp $
 * @author duane a. bailey
 */
//+implementation
public class Ratio
{ // a object for storing a fraction
    protected int numerator;   // numerator of ratio
    protected int denominator; // denominator of ratio

    /**
     * <dl>
     * <dt><b>Precondition:</b><dd> bottom != 0
     * <dt><b>Postcondition:</b><dd> constructs a ratio equivalent to top/bottom
     * </dl>
     * 
     * @param top 
     * @param bottom 
     */
    public Ratio(int top, int bottom)
    // pre: bottom != 0
    // post: constructs a ratio equivalent to top/bottom
    {
        numerator = top;
	denominator = bottom;
    }

    public int getNumerator()
    // post: return the numerator of the fraction
    {
	return numerator;
    }

    public int getDenominator()
    // post: return the denominator of the fration
    {
	return denominator;
    }

    /**
     * <dl>
     * <dt><b>Postcondition:</b><dd> returns the real value equivalent to ratio
     * </dl>
     * 
     * @return 
     */
    public double value()
    // post: returns the real value equivalent to ratio
    {
        return (double)numerator/(double)denominator;
    }

    public Ratio add(Ratio other)
    // pre: other is non-null
    // post: return new fraction --- the sum of this and other
    {
	return new Ratio(this.numerator*other.denominator+
			 this.denominator*other.numerator,
			 this.denominator*other.denominator);
    }
    //-implementation


    //+main
    public static void main(String[] args)
    {
	Ratio r = new Ratio(1,1);      // r == 1.0
	r = new Ratio(1,2);	       // r == 0.5
	r.add(new Ratio(1,3));	       // r still 0.5
	r = r.add(new Ratio(1,4));     // r == 0.75
	System.out.println(r.value()); // 0.75 printed
    }
    //-main
    //+implementation
}
//-implementation
/*
//+output
0.75
//-output
*/
