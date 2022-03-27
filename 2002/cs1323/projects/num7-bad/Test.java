import Check;

class Test {
	public static void main( String args[] ) {
		Check testCheck;

		testCheck = new Check( 45, "04-12-2K", "Rodney", "One Million Dollars", 1000000.00f, "He's getting loaded now" );

		System.out.println( testCheck.gimmeCheckNum() );
		System.out.println( testCheck.gimmeDate() );
		System.out.println( testCheck.gimmeMemo() );
		System.out.println( testCheck.gimmeNumAmount() );
		System.out.println( testCheck.gimmePayTo() );
		System.out.println( testCheck.gimmeWritAmount() );
		

	}
}
