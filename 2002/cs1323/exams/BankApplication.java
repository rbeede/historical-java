class BankApplication
	
	public static void main(String args[]) {

	EasyIn easyIn=new EasyIn();
	
	System.out.println("Enter Account number:");
	int account=easyIn.getInt();
	double balance=2000.0;

	Bank bank=new Bank(account, balance, easyIn);
	

	for(i=0,i<10,i++){
		
		System.out.println("Press 1 for withdrawal");
		System.out.println("Press 2 for deposit");
		int selection=easyIn.getInt();

		switch (selection) {
			
			case 1: System.out.println("Enter withdrawal amount:");
				double withdrawalAmt=easyIn.getDouble();
				bank.checkBalance(withdrawalAmt);
				bank.withdrawal(withdrawalAmt);
				break;
			
			case 2: System.out.println("Enter amount of deposit:");
				double deposit=easyIn.getDouble();
				bank.deposit(deposit);
				break;
		}
	
	}

	system.out.println("Account number "+account);
	system.out.println("Your total balance= "+bank.getBalance);
}