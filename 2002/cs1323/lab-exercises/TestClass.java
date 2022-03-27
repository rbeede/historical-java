////////////////////////////////////////////////////////////////////////////////// 
//	class TestClass  
// 
//	A instantiable class to .... 
//		1.  Demonstrate pass-by-value. 
//		2.  Demonstrate the use of Modifiers 
// 
//////////////////////////////////////////////////////////////////////////////// 
class TestClass 
{ 
	//data member 
	private final double TAX_RATE = 0.075; 
		 
	//constructor 
	public TestClass() 
	{ 
		 
	}//end constructor 
	 
	//myMethod 
	public void myMethod(int number, double anotherNumber) 
	{ 
		number = 3; 
		anotherNumber = 4; 
	}//end myMethod method 
		 
	//figureTax 
	public double figureTax(double price) 
	{ 
		return price * TAX_RATE; 
	}//end figureTax method 
	 
}//end TestClass class
