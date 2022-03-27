/////////////////////////////////////////////////////////////////////////////////////////// 
// 
//	Program TestTypes 
// 
//	A program to .... 
//		1.  Test Implicit and Explicit Type Coercion to show the difference 
//				between integer and real number division. 
//		2.  Demonstrate the use of System.out 
// 
/////////////////////////////////////////////////////////////////////////////////////////// 
 
 
class TestTypes 
{ 
 
	public static void main (String args[]) 
   	{ 
		///////////////////////////////////////// 
		//	Declare and initialize variables 
		///////////////////////////////////////// 
		int	intNumFirst	= 6,  
			intNumSecond	= 3,  
			intNumThird	= 4,  
			intNumFourth	= 2, 
			intAnswer	= 0; 
		 
		double	doubleNumFirst	= 6.0,  
			doubleNumSecond	= 3.0,  
			doubleNumThird	= 4.0,  
			doubleNumFourth	= 2.0, 
			doubleAnswer	= 0.0; 
		 
		//////////////////////////////////////////////////////// 
		//	Calculate answers for... 
		//		Integer Division:	6   * 3   / 4   * 2  	 
		//		realNumber Division:	6.0 * 3.0 / 4.0 * 2.0	 
		//////////////////////////////////////////////////////// 
		intAnswer	= intNumFirst * intNumSecond / intNumThird * intNumFourth; 
		doubleAnswer	= doubleNumFirst * doubleNumSecond / doubleNumThird * doubleNumFourth; 
					 
		////////////////////// 
		//	Output Heading 
		////////////////////// 
		System.out.println(); 
		System.out.print("**********************"); 
		System.out.print(" Application:  TestTypes "); 
		System.out.print("**********************"); 
		System.out.println(); 
		System.out.println(); 
		 
		///////////////////////////////////////////////// 
		//	Output arithmetic equations and results 
		///////////////////////////////////////////////// 
		System.out.println("Comparison of Integer and Real Number Division"); 
		System.out.println("Result of Integer Division:      "  
					   + intNumFirst	+ "   * "  
					   + intNumSecond	+ "   /  " 
					   + intNumThird	+ "   * "  
					   + intNumFourth	+ "    is : "  
					   + intAnswer); 
												  
		System.out.println("Result of Real Number Division:  "  
					   + doubleNumFirst	+ " * "  
					   + doubleNumSecond	+ " /  " 
					   + doubleNumThird	+ " * "  
					   + doubleNumFourth	+ "  is : "  
					   + doubleAnswer); 
		 
		////////////////////////// 
		//	Skip two lines 
		////////////////////////// 
		System.out.println(); 
		System.out.println(); 
		 
		////////////////////////////////////////////////////////////////////// 
		//	Re-calculate answers with added parentheses... 
		//		Integer Division:	6   * 3   / (4   * 2  )	 
		//		realNumber Division:	6.0 * 3.0 / (4.0 * 2.0)	 
		////////////////////////////////////////////////////////////////////// 
		intAnswer	= intNumFirst * intNumSecond / (intNumThird * intNumFourth); 
		doubleAnswer 	= doubleNumFirst * doubleNumSecond / (doubleNumThird * doubleNumFourth); 
		 
		///////////////////////////////////////////////////////////////////////////// 
		//	Output second set of arithmetic equations and results (with parentheses) 
		///////////////////////////////////////////////////////////////////////////// 
		System.out.println("Problem changed by addition of parentheses"); 
		System.out.println("Result of Integer Division:      "  
					   + intNumFirst	+ "   * "  
					   + intNumSecond	+ "   / (" 
					   + intNumThird	+ "   * "  
					   + intNumFourth	+ ")   is : "  
					   + intAnswer); 
												  
		System.out.println("Result of Real Number Division:  "  
					   + doubleNumFirst	+ " * "  
					   + doubleNumSecond	+ " / (" 
					   + doubleNumThird	+ " * "  
					   + doubleNumFourth	+ ") is : "  
					   + doubleAnswer); 
			 
		//////////////////////////// 
		//	@Begin Exercises 
		//////////////////////////// 
		 
		System.out.println(); 
		System.out.print("Answer to exercises:  "); 
System.out.println("(double)((5 + 2) / 3) = " + (double)((5 + 2) / 3));

   		//	@End Exercises 
		 
		 
		//////////////////////////// 
		//	Output Ending  
		//////////////////////////// 
		System.out.println(); 
		System.out.println("****************************** The End *******************************"); 
		 
   }//end main 
 
}//end class TestTypes 
