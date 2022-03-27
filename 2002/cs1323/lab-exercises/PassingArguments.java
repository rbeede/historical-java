////////////////////////////////////////////////////////////////////////////////// 
//	class PassingArguments  
// 
//	A program to .... 
//		1.  Call a method to demonstrate pass-by-value. 
//		2.  Demonstrate the use of Modifiers 
// 
//////////////////////////////////////////////////////////////////////////////// 
 
class PassingArguments 
{ 
        public static void main (String args[]) 
        { 
			TestClass testClass = new TestClass(); 
						 
			int x = 1; 
			double y = 2; 
					 
			testClass.myMethod(x,y); 
						 
			System.out.println("The Value of x is:  "  + x); 
			System.out.println("The Value of y is:  "  + y); 
			 
			//////////////////////////// 
			//	@Begin Exercises 
			//////////////////////////// 
			 
			System.out.println(); 
System.out.print("Tax on" + x + " is:  " + testClass.figureTax(x));		
	
			System.out.println(); 
			 
			//	@End Exercises 
			 
		 
				 
		}//end main 
 
}//end PassingArguments class 
 

