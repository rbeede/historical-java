// A program to determine the minimum number of coins needed to make change.
// (c) 1997 duane a. bailey
import structure.*;

public class FullPostage
{
    static int count1, count2;
    //+countImpl
    public static int stampCount(int amount)
    // pre: amount >= 0
    // post: return *number* of stamps needed to make change
    //       (only use 1 cent, 20 cent, and 32 cent stamps)
    {
	return stampCount(amount, new int[amount+1]);
    }

    protected static int stampCount(int amount, int answer[])
    // pre: amount >= 0; answer array has length >= amount
    // post: return *number* of stamps needed to make change
    //       (only use 1 cent, 20 cent, and 32 cent stamps)
    {
	int minStamps;
	Assert.pre(amount >= 0,"Reasonable amount of change.");
	if (amount == 0) return 0;
	if (answer[amount] != 0) return answer[amount];
	// consider use of a penny stamp
	minStamps = 1+stampCount(amount-1,answer);
	// consider use of a 20 cent stamp
	if (amount >= 20) {
	    int possible = 1+stampCount(amount-20,answer);
	    if (minStamps > possible) minStamps = possible;
	}
	// consider use of a 32 cent stamp
	if (amount >= 32) {
	    int possible = 1+stampCount(amount-32,answer);
	    if (minStamps > possible) minStamps = possible;
	}
	answer[amount] = minStamps;
	return minStamps;
    } 
    //-countImpl

    public static void main(String args[])
    {
	for (int amount = 1; amount < 100; amount++)
	{
	    System.out.println("Stamps returned to make "+amount+" cents: "+
			       stampCount(amount));
	}
    }

    static Vector amounts;
    public static coins change(int amount)
    {
	Assert.pre(amount >= 0, "Amount must be positive.");
	amounts.ensureCapacity(amount+1);
	if (amounts.elementAt(amount) == null)
	{	
	    coins best, possible;
	    if (amount == 0) best = new coins();
	    else {
		possible = change(amount-1).addPenny();
		best = possible;
		if (amount >= 5) {
		    possible = change(amount-5).addNickle();
		    if (best.total() > possible.total()) best = possible;
		}
		if (amount >= 10) {
		    possible = change(amount-10).addDime();
		    if (best.total() > possible.total()) best = possible;
		}
		if (amount >= 25) {
		    possible = change(amount-25).addQuarter();
		    if (best.total() > possible.total()) best = possible;
		}
	    }
	    amounts.setElementAt(best,amount);
	}
	return new coins((coins)amounts.elementAt(amount));
    } 

    /*    public static void main(String args[]) {
	amounts = new Vector();
	for (int i = 0; i < 100; i++)
	{
	    System.out.println("Change for "+i+" cents: "+change(i));
	}
    }*/

}

class coins
{
    int pennies, nickles, dimes, quarters;
    public coins()
    {
	pennies = nickles = dimes = quarters = 0;
    }
    public coins(coins that)
    {
	this.pennies = that.pennies;
	this.nickles = that.nickles;
	this.dimes = that.dimes;
	this.quarters = that.quarters;
    }
    public coins addPenny() { pennies++; return this;}
    public coins addNickle() { nickles++; return this;}
    public coins addDime() { dimes++; return this;}
    public coins addQuarter() { quarters++; return this;}
    public int total() { return pennies+nickles+dimes+quarters; }
    public String toString() {
	return "pennies: "+pennies+", nickles: "+nickles+
	       ", dimes: "+dimes+", quarters: "+quarters;
    }
}

/*
//+output
Stamps returned to make 1 cents: 1
Stamps returned to make 2 cents: 2
Stamps returned to make 3 cents: 3
Stamps returned to make 4 cents: 4
Stamps returned to make 5 cents: 5
Stamps returned to make 6 cents: 6
Stamps returned to make 7 cents: 7
Stamps returned to make 8 cents: 8
Stamps returned to make 9 cents: 9
Stamps returned to make 10 cents: 10
Stamps returned to make 11 cents: 11
Stamps returned to make 12 cents: 12
Stamps returned to make 13 cents: 13
Stamps returned to make 14 cents: 14
Stamps returned to make 15 cents: 15
Stamps returned to make 16 cents: 16
Stamps returned to make 17 cents: 17
Stamps returned to make 18 cents: 18
Stamps returned to make 19 cents: 19
Stamps returned to make 20 cents: 1
Stamps returned to make 21 cents: 2
Stamps returned to make 22 cents: 3
Stamps returned to make 23 cents: 4
Stamps returned to make 24 cents: 5
Stamps returned to make 25 cents: 6
Stamps returned to make 26 cents: 7
Stamps returned to make 27 cents: 8
Stamps returned to make 28 cents: 9
Stamps returned to make 29 cents: 10
Stamps returned to make 30 cents: 11
Stamps returned to make 31 cents: 12
Stamps returned to make 32 cents: 1
Stamps returned to make 33 cents: 2
Stamps returned to make 34 cents: 3
Stamps returned to make 35 cents: 4
Stamps returned to make 36 cents: 5
Stamps returned to make 37 cents: 6
Stamps returned to make 38 cents: 7
Stamps returned to make 39 cents: 8
Stamps returned to make 40 cents: 2
Stamps returned to make 41 cents: 3
Stamps returned to make 42 cents: 4
Stamps returned to make 43 cents: 5
Stamps returned to make 44 cents: 6
Stamps returned to make 45 cents: 7
Stamps returned to make 46 cents: 8
Stamps returned to make 47 cents: 9
Stamps returned to make 48 cents: 10
Stamps returned to make 49 cents: 11
Stamps returned to make 50 cents: 12
Stamps returned to make 51 cents: 13
Stamps returned to make 52 cents: 2
Stamps returned to make 53 cents: 3
Stamps returned to make 54 cents: 4
Stamps returned to make 55 cents: 5
Stamps returned to make 56 cents: 6
Stamps returned to make 57 cents: 7
Stamps returned to make 58 cents: 8
Stamps returned to make 59 cents: 9
Stamps returned to make 60 cents: 3
Stamps returned to make 61 cents: 4
Stamps returned to make 62 cents: 5
Stamps returned to make 63 cents: 6
Stamps returned to make 64 cents: 2
Stamps returned to make 65 cents: 3
Stamps returned to make 66 cents: 4
Stamps returned to make 67 cents: 5
Stamps returned to make 68 cents: 6
Stamps returned to make 69 cents: 7
Stamps returned to make 70 cents: 8
Stamps returned to make 71 cents: 9
Stamps returned to make 72 cents: 3
Stamps returned to make 73 cents: 4
Stamps returned to make 74 cents: 5
Stamps returned to make 75 cents: 6
Stamps returned to make 76 cents: 7
Stamps returned to make 77 cents: 8
Stamps returned to make 78 cents: 9
Stamps returned to make 79 cents: 10
Stamps returned to make 80 cents: 4
Stamps returned to make 81 cents: 5
Stamps returned to make 82 cents: 6
Stamps returned to make 83 cents: 7
Stamps returned to make 84 cents: 3
Stamps returned to make 85 cents: 4
Stamps returned to make 86 cents: 5
Stamps returned to make 87 cents: 6
Stamps returned to make 88 cents: 7
Stamps returned to make 89 cents: 8
Stamps returned to make 90 cents: 9
Stamps returned to make 91 cents: 10
Stamps returned to make 92 cents: 4
Stamps returned to make 93 cents: 5
Stamps returned to make 94 cents: 6
Stamps returned to make 95 cents: 7
Stamps returned to make 96 cents: 3
Stamps returned to make 97 cents: 4
Stamps returned to make 98 cents: 5
Stamps returned to make 99 cents: 6
//-output
*/
