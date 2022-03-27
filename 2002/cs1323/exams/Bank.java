class Bank
{
	private double balance;
	private int accountNumber;

//Constructor
public Bank(int account, double balance)
{
accountNumber=account;
this.balance=balance;
}

public void deposit(double deposit)
{
balance=balance+deposit;
}

public void withdrawal(double withdrawalAmt)
{
balance=balance-withdrawalAmt;
}

public void checkBalance(double withdrawalAmt)
{
if(withdrawalAmt>balance) {
	system.out.println("Transaction not possible");
}
	
}

public double getBalance()
{
return balance;
}