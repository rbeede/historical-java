//+interface
public class BankAccount
{
    //-interface
    //+constructor
    protected String key;
    protected double value;
	
    //+interface
    public BankAccount(String account, double balance)
    // pre: account is a string identifying the bank account
    //      balance is the starting balance
    // post: constructs a bank account with desired balance
    //-interface
    {
	key = account;
	value = balance;
    }
    //-constructor
    //+interface

    //+equals
    public boolean equals(BankAccount other)
    // pre: other is a valid bank account
    // post: returns true if this bank account is the same as other
    //-interface
    {
	// two accounts are the same if their account numbers are same
	return this.key.equals(other.key);
    }
    //-equals
    //+interface
	
    //+stateMethods
    public String account()
    // post: returns the bank account number of this account
    //-interface
    {
	return key;
    }
    //+interface
	
    public double balance()
    // post: returns the balance of this bank account
    //-interface
    {
	return value;
    }
    //-stateMethods
    //+interface

    //+setBalance
    public void setBalance(double balance)
    // post: set the value of the bank account to balance.
    //-interface
    {
	value = balance;
    }
    //-setBalance
	
    //+example
    public static void main(String[] args)
    {
	// Question: is it better to invest $100 over 10 years at 5%
	//           or to invest $100 over 20 years at 2.5% interest?
	BankAccount jd = new BankAccount("Jain Dough",100.00);
	BankAccount js = new BankAccount("Jon Smythe",100.00);

	for (int years = 0; years < 10; years++)
	{
	    jd.setBalance(jd.balance() * 1.05);
	}
	for (int years = 0; years < 20; years++)
	{
	    js.setBalance(js.balance() * 1.025);
	}
	System.out.println("Jain invests $100 over 10 years at 5%.");
	System.out.println("After 10 years " + jd.account() +
			   " has $" + jd.balance());
	System.out.println("Jon invests $100 over 10 years at 2.5%.");
	System.out.println("After 20 years " + js.account() +
			   " has $" + js.balance());
    }
    //-example
    //+interface
}
//-interface
/*
//+output
Jain invests $100 over 10 years at 5%.
After 10 years Jain Dough has $162.8894626777442
Jon invests $100 over 10 years at 2.5%.
After 20 years Jon Smythe has $163.8616440290394
//-output
*/
