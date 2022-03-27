




class ExamTwoA
{

	public static void main (String args[])
	{	
		//1.  Identify the valid if statement:

		//a.
		/*
		if  (a < b) then
		x = y;
			       else
				x = z;
		*/
		
		//b.ANSWER
		/*
		if  (a < b ) {
		x = y;  }  else
		x = z;
		*/
		
		//c.
		/*
		if  (a < b ) else x = y;
		*/
		
		//d.
		/*
		if  (a > b) 
		x = y;
			       else ( b > a )
				x = z;
		*/
		
		//e.
		/*
		if  ( a < b )
		x = y;
			       else {
				x = z;
			      };
		*/
		
		//2.  Which of the following boolean expressions evaluates to true?
		//a.
					/*
				   if (! ( 18    ==  ( 10 + 8 ) ))
						System.out.println("true");
				   else 
						System.out.println("false");
					*/	   
		//b.
				   /*
				   if((!( 3 < 5) )  ||  ( 21 < 18 )  &&  ( -81 < 0 ))
					    System.out.println("true");
				   else 
						System.out.println("false");
				   */
		//c.	
				   /*
				   if (2 < 4 && (false || 5 <= 4))
						System.out.println("true");
				   else 
						System.out.println("false");
					*/
				   
		//d. ANSWER
					/*
				   if (!  ( ( -5.0 == -6.2)  ||  ( ( 7 < 3 )  &&  ( 6 == ( 3 + 3 ) ) ) ) )
						System.out.println("true");
				   else 
						System.out.println("false");
					*/
		//e.	
					/*
				   if (( 4.2 >= 5.0 )  &&  (8   == ( 3 + 5 ) ))
						System.out.println("true");
				   else 
						System.out.println("false");
					*/
				   
		//3. What's wrong with the following switch statement?
		/*
					switch (x) {
						case 0:
						case 1:		y = 23;
									break;
						case 3:  	y = 40;
									break;
						case 2:		y = 56;
									default;
					}
		*/
		
					
		/*
		a. case 0:
		b. case 1:
		c. case 3:
		d. case 2:
		e. default - ANSWER
		*/
					
		// 4. Which case demonstrates correct syntax for a switch statement?
			/*
			switch (x) {
				case number > 10:	y = 4;
									break;
				case 4.5:			y = 23;
									break;
				case > 3:  			y = 40;
									break;
				case 5:				y = 56;
									break;
			}
			*/
			
		/*
		a. case number > 10:
		b. case 4. 5:
		c. case > 3:  
		d. case 5:ANSWER
		e. all are correct
		*/
				
		
		//5. Which of the two following if statements are equivalent?
		
		//Statement #1:	
		/*
			if (a == b )
					else b = 1;
			if (c == d ) a = 1; 
		*/	
		
		//Statement #2:	
		/*
			if ( a == b )
					if ( c == d) a = 1;
						else b = 1;
		*/	
		
		//Statement #3:	
		/*
				if (a == b ) {
					if (a == d ) a = 1; }
					else b = 1;
		*/	
		
		//Statement #4:
		/*
						if ( a == b ) {
					if ( c == b ) a = 1;
				else b = 1; }
		*/	
		
		//Statement #5:
		/*
					if ( a == b )
					if ( c == d ) a = 1;
				else b = 1;
		*/	
		
		/*
		a. Statement #1 and Statement #4
		b. Statement #1 and Statement #3
		c. Statement #2 and Statement #3
		d. Statement #2 and Statement #5 - ANSWER
		e. Statement #4 and Statement #5
		*/
		
		// 6. Identify the boolean expressions that evaluate to true. (assume x is 10, y is 20, and z is 30).
		//Expression #1:	
					/*	
					if( x < 10 || y > 10)
							    System.out.println("true");
					else 
						System.out.println("false");
				   */
		//Expression #2:
					/*
					if ( x > y && y > x)
						System.out.println("true");
				   else 
						System.out.println("false");
				   */
		//Expression #3:
					/*
						if ( ( x < y + z ) && ( x + 10 <= 20 ))
							    System.out.println("true");
				   else 
						System.out.println("false");
				   */
		//Expression #4:
					/*
					if(	 z - y == x && ( ( y - z )  ==  x ))
						System.out.println("true");
				   else 
						System.out.println("false");
				   */
		//Expression #5:	
					/*
					if ( x < 10 && x > 10)
						System.out.println("true");
				   else 
						System.out.println("false");
				   */
		//Expression #6:
					/*
					if (	 x > y || y > x)
						System.out.println("true");
				   else 
						System.out.println("false");
				   */
		//Expression #7:	
				/*
				if(	! ( x < y + z ) || ! ( x + 10 <= 20))
						System.out.println("true");
				   else 
						System.out.println("false");
				   */
		//Expression #8:
				/*
					if (	 ! (x == y) && ( x !=y) && ( x < y || y < x ))
						System.out.println("true");
				   else 
						System.out.println("false");
				   */

		/*
		a. Expressions #1, #3, #7, #8
		b. Expressions #1, #3, #6, #8 - ANSWER
		c. Expressions #1, #3, #4, #6
		d. Expressions #2, #4, #5, #7
		e. Expressions #2, #4, #5, #6
		*/

		//7. What is the value of minimum after this code segment executes?
		/*
		int num1 = 12;
		int num2 = 26;
		int num3 = 45;
		int minimum = 0;
		minimum = num1 = 50;
		if  (num2 > minimum) {
			minimum = num3; 
		}
		if  (num3 < minimum) {
			minimum = num2;
		}
		System.out.println(minimum);
		*/
		
		/*
		a. 0
		b. 12
		c. 26 - ANSWER
		d. 45
		e. 50
		*/
		
		//8. Which of the following is not an infinite loop?
		
		//Loop #1:	
		/*
		int j = 0;
		do  {
			j = j + 2; 
			System.out.println(j);
		} while ( j != 5);
		*/
		
		//Loop #2:
		/*
		int sum = 0, i = 0;
				while ( i >= 0 ) {
					sum += i;
					i ++;
				}
		*/
		
		//Loop #3:
		/*
		int number = 1;
		while (number > 0) {
			number = 5;
			System.out.println(number );
		}
		*/
		
		//Loop #4:	
		/*
		int power = 1;
				while ( power  != 100) {
			 System.out.println(power);
			power = power * 2;
		}
		*/
		
		//Loop #5:
		/*
				int sum =  0, i = 100;	
				while ( i ! = 0 ) {
					sum += i;
					i - -;
				}
		
		*/
		
		/*
		a. Loop #1
		b. Loop #2
		c. Loop #3
		d. Loop #4
		e. Loop #5 - ANSWER
		*/
				
		//9. Determine the value of sum after the loop is executed?
		/*
		int count = 0, sum = 0;
		while ( count < 20 ) {
		sum += 3 * count;
		count += 2;
		}
		
		System.out.println(sum);
		*/
		
		/*
		a. 18 - ANSWER
		b. 36
		c. 45
		d. 60
		e. 63
		*/
		
		//10. What is the value of count after this code segment is executed?
		/*
		int count = 0;
		do{
			count = count + 1;	
		} while ( count  < 6);
		
		System.out.println(count);
		*/
		
		/*		
		a. 0
		b. 1
		c. 4
		d. 5 - ANSWER
		e. 6
		*/
		
		/*
		11. How many distinct characters can you represent using 8 bits (this is also the number of symbols represented by ASCII code)?
		a. 7
		b. 8
		c. 128
		d. 256 - ANSWER
		e. none of the above
		*/

		/*
		12. Which of the following is a valid statement?
		a. Person [25] person;
		b. Person [25]  person = new Person[25];
		c. Person [ ]  person = new Person[25]; - ANSWER
		d. Person [ ] person = new Person;
		e. Person [ ] person = new Person{};
		*/
		
		//13. What is the value of power after this code segment is executed?
		/*
		int power = 1;
		int count = 1;
		while ( power  < 100){
			power = power * 2;
			count++;
		}	
		System.out.println(power);		
		*/
		/*
		a. 6
		b. 7
		c. 8
		d. 64
		e. 128 - ANSWER
		*/
		
		//14. What is the value of answer after this code segment is executed?
		/*
		int x=0, answer = 0;
			int j = 1  +  ++x;
			for (int i = j; i < 5; i  = i + 1){
				answer *= i;
			}
			
			System.out.println(answer);
		*/
		
		/*
		a. 0 - ANSWER
		b. 2
		c. 9
		d. 24
		e. 120
		*/
		
		/*
		15. Which standard coding scheme does Java use to represent characters?
		a. American Standard Code for Information Interchange
		b. The World Wide Web Character Set
		c. Japanese Standard Set
		d. American Sign Language Set
		e. The Unicode World Wide Character Set - ANSWER
		*/
		
		//16. What is the value of sum after this code segment is executed?
		
		/*
		int sum = 0;
			for ( int i = 0 ; i < 5 ; i++ ) {
				sum = sum + i ;
				for (int j = i ; j < 5 ; j++){
					sum = sum + j;	
				}
			}	
			System.out.println(sum);
		*/
		/*
		a. 0
		b. 10
		c. 50 - ANSWER
		d. 60
		e. 85
		*/
		
		//17. What is the value of sum after this code segment is executed?
		/*
		int sum = 0;
			for ( int i = 0 ; i <= 1 ; i++ ) {
				for (int j = 4; j > 2*i; j--){
					sum = sum + (j - i);
				}
			}		
		System.out.println(sum);
		*/
		
		/*
		a. 0
		b. 10
		c. 15 - ANSWER
		d. 16
		e. none of the above
		*/
		
		//18. Which code segment will output the following configuration?
		//*$
		//**$
		//***$
		//****$
		//*****$
		//
		//Segment #1:  
		/*
					  for (int line = 1; line <= 5; line++){
						for (int star = 1; star <= line; star++)  {
							System.out.print("*");
						System.out.println("$");
					}
				} 
		*/
		
		//Segment #2:  	
		/*
		for (int line = 1; line <= 5; line++){
						for (int star = 1; star <= line; star++) 
							System.out.print("*");
					System.out.println("$");
				} 
		*/
		
		//Segment #3:  	
		/*
		for (int line = 1; line <= 5; line++){
						for (int star = 1; star <= line; star++) 
							System.out.print("*");
				} 
				System.out.println("$");
		*/
		
		//Segment #4:  	
		/*
		for (int line = 1; line <= 5; line++){
						for (int star = 1; star <= line; star++) 
							System.out.print("*");
						if (star = = line)
					System.out.println("$");
				} 
		*/
		
		//Segment #5:  	
		/*
		for (int line = 1; line <= 5; line++){
				for (int star = 1; star <= line; star++) 
					if(line > star )
						System.out.print("*");
					System.out.println("$");
				}
		*/
		
		/*
		a. Segment #1
		b. Segment #2 - ANSWER
		c. Segment #3
		d. Segment #4
		e. Segment #5
		*/

		//19. Which of the following statements is valid?
		//a.
		  //float number [23];
		//b.
		  //float number = { 1.0f , 2.0f , 3.0f };
		//c.
		  //int number = new Array[23];
		//d.
		  //int [ ] number = [ 1 , 2 , 3 , 4 ];
		//e.
		 // float [ ] number; - ANSWER
			 
		//20. What is the output after the following code segment is executed?
		/*
		String str = "OHWhatADay!";
			for (int i = 0; i < 7; i+=2){
				System.out.println(str.charAt(i));	
			}		
		*/
		
		/*
			Output #1:	
			O
			W
			a
			A

			Output #2:	
			OWaA
					
			Output #3:	
			OHWhatA

			Output #4:	
			O
			H
			W
			h
			a
			t
			A

			Output #5:	
			O
			H
			W
			h
			a
			t
			A
			D
			a
			y
			!
				
		a. Output #1 - ANSWER
		b. Output #2
		c. Output #3
		d. Output #4
		e. Output #5
		*/
		
		
		//21. What is the output after the following code segment is executed?
		/*
			String str = "Caffeine";
			StringBuffer str1 = new StringBuffer(str.substring(1,3));
			str1.append('e');
			str = "De" + str1;
			System.out.println(str);
		*/
		
		/*
		Output #1:	Deaffe
		Output #2:	Deafe
		Output #3:	Decafe
		Output #4:	Decaffe
		Output #5:	Deeaf

		a. Output #1
		b. Output #2 - ANSWER
		c. Output #3
		d. Output #4
		e. Output #5
		*/
		
		
		//22. What is the output after the following code segment is executed?
		
		/*
		String str = "World Wide Web";
			for (int i = 0; i < 10; i++){
				if (str.charAt(i) == 'W') {
					System.out.println('M');
				}
				else {
					System.out.print(str.charAt(i));	
				}
			}
		*/			

		/*
		Output #1:	
		M
		orld M
		ide M
		eb

		Output #2:	
		Morld Mide
				
		Output #3:	
		M
		orld M
		ide

		Output #4:	
		M
		o
		r
		l
		d

		M
		i
		d
		e

		Output #5:	
		Morld Mide Meb

		a. Output #1
		b. Output #2
		c. Output #3 - ANSWER
		d. Output #4
		e. Output #5
		*/

		//23. What is the output after the following code segment is executed?
			/*
			String str = "Programming";
			int x = str.length() - 1;
			for (int i = x; i >= 5; i--){
				System.out.print(str.charAt(i));	
			}
			*/

		/*
		Output #1:	gnimmargorP
		Output #2:	Programming
		Output #3:	Progra
		Output #4:	gnimma 
		Output #5:	gnimm

		a. Output #1
		b. Output #2
		c. Output #3
		d. Output #4 - ANSWER
		e. Output #5
		*/
		
		//24. What is the output after the following code segment is executed?
		/*
			StringBuffer word1, word2;
			word1 = new StringBuffer("Mona");
			word2 = word1;
			word2.insert(0, "Lisa");
			System.out.println(word1);	
		*/
		
		/*
		Output #1:	Lisa

		Output #2:	Mona

		Output #3:	0Mona

		Output #4:	LisaMona

		Output #5:	MonaLisa

		a. Output #1
		b. Output #2
		c. Output #3
		d. Output #4 - ANSWER
		e. Output #5
		*/
		
		//25. What is the output after the following program is executed?
				/*
				String str1 = "Daisy", str2 = "Iris";
				TestExam test = new TestExam();
				test.exchange(str1, str2);	
				System.out.println(str2);
		
				//ALSO UNCOMMENT NUBMER 25 SEGMENT IN THE TestExam Class below
				*/

		/*	

		a. Daisy
		b. Iris - ANSWER
		c. Null
		d. DaisyIris
		e. IrisDaisy
		*/
		
		//26. What is the value of vowelCount after this code segment executes?
		
		/*
		String name = "Jeremiah";
		String nameUpper = name.toUpperCase();
		int numChars = name.length();
		int vowelCount = 0;
		char letter;
							
		for(int i = 0; i < numChars;i+=2){
			letter = nameUpper.charAt(i);
			if (	letter == 'A'  | |
				letter == 'E'  | |
				letter == 'I'   | |
				letter == 'O'  | |
				letter == 'U'  ){

				vowelCount++;
			}
		}
		*/
		/*
		a. 0
		b. 1 - ANSWER
		c. 2
		d. 3
		e. 4
		*/
		
		//27. What is output after the following code segment executes?
		/*
		String wordOne = new String("Java");
		String wordTwo = new String("Java");
				
		if (wordOne ==wordTwo){
			System.out.println("Must be True");	
		}
		else{
			System.out.println("Must be False");
		}
		*/
		/*
		a. Must be True
		b. Must be False - ANSWER
		c. none of the above
		d. Java
		e. Java Must be True
		*/

		//28. What is the output of the following code segment?
			/*
			String [ ]name = {"Sue", "Harry","Doris", "Jerry"};
			int deleteIndex = 0;
			int lastIndex = 3;
			name[deleteIndex] = null;
			deleteIndex = 3;
			name[lastIndex] = null;
			System.out.println(name[0] + " " + name[1] + " " + name[2] + " " + name[3]);
			*/
		
		/*
		a. null Harry Doris null - ANSWER
		b. Sue Harry Doris null
		c. Jerry Harry Doris null
		d. null Harry null Jerry
		e. Jerry Harry null null
		*/


		//29. What is the output from the following program?
		/*
			TestExam test = new TestExam();
			 int [ ] list = {10,20,30,40};
			 test.myMethod (list);
			 System.out.println(list[1]);
			 System.out.println(list[3]);
		*/
			//ALSO UNCOMMENT CODE SEGMENT IN TestExam ....see below)

		/*
		a.	0
			2

		b.	1
			3

		c.	10
			30

		d.	20 - ANSWER
			40

		e.	2
			6
		*/
		
		//30. Identify the problem in the following method.
		
			//For testing this question only
		/*
			TestExam testOnly = new TestExam();
			 int [ ] list = {10,20,30,40};
			 testOnly.searchAccount (list);
		*/	 
			//Uncomment code segment in TestExam class (see below)	 
		
		/*
		a. number cannot be declared with a size
		b. this method should not return anything
		c. the return statement is incorrect
		d. the square brackets are on the wrong side of the variable called number
		e. the for loop causes the array to be indexed out of bounds - ANSWER
		*/
	
	}//end main
}//end ExamTwoA class - version A


class TestExam
{

	//begin number 25
	/*
	public void exchange(String one, String two)
			{
					String temp;
					temp = one;
				one = two;
					two = temp;
			}
	*/
	//end number 25
	
	//Begin number 29	
	/*
	public void myMethod (int[] intArray)
	{
		 for (int i = 0; i<intArray.length;i+=2){
	   intArray[i] = i;
		 }
	}
	*/
	//end number 29	
	
	//Begin number 30
	/*
	public int[ ] searchAccount(int []number)
	{
		number = new int [50];
					
		for (int i = 0; i < number.length; i++){
			number[i] = number[i+1] + number[i-1];
		}
					
		return number;
	}
	*/
	//end number 30
		
}
