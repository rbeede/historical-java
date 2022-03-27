//=========================================================================
//	CalculateGrade.java
//=========================================================================
//	Programmer:		Rodney Beede
//	ID:			0000
//	Course:			CS1323
//	Lab Section:		222
//	Lab TA:			Lalitha
//
//	Project#:		5
//	Due Date:		3-10-2K	
//
//	Description:		This is the main class used to get a CS1323
//				student's grades and give them their class
//				average
//
//	Inputs:			Program asks user for number of grades in
//				each category and uses the CS1323Grades
//				class to get the grades and compute them
//
//	Outputs:                Displays the grades for each subcategory
//				(Projects, Quizzes, Lab Attendance,
//				Lab Final, Exam I & II, & Final Exam)
//
//				Course Average and Course Letter Grade
//
//	Public Methods:		main -- Startups the program
//
//	Private Methods:	describeProgram -- Describes the program
//				
//=========================================================================

import EasyIn;  //Load this class for getting input
import CS1323Grades;  //Load the class for CS1323 calculating grades

class CalculateGrade {
	public static void main( String[] args ) {
		//Declare  a CS1323Grades object for use in
		//getting and calculating all the grades
		CS1323Grades objGrade;

		/*
		  Create the object and use these percents for determining
		  the overall course average:

			Projects -- 20%

			Quizzes -- 10%

			Lab Attendance -- 10%

			Lab Final -- 10%

			Exam I & II -- 30%

			Final Exam -- 20%

			Total Possible for all above -- 100%
		*/
		objGrade = new CS1323Grades( (20f / 100), (10f / 100),
					     (10f / 100), (10f / 100),
					     (30f / 100), (20f / 100) );

		//Declare a EasyIn object for getting input
		EasyIn objGetData = new EasyIn( );

		//Use this variable to store the final course grade
		float fltCourseAverage = 0.0f;


		describeProgram();  //Describe this program to the user

		//Prompt the user for the number of projects they have done
		//This number should be the number of grades they have for
		//those projects
		System.out.print( "Enter the number of project grades " +
		   "you wish to enter in:  " );

		//Call objGrade.calculateProjectAverage to get the grades
		//and calculate the average
		//Before the method is called, we get the number of grades
		//to input from the user and pass that along to the method
		objGrade.calculateProjectAverage( objGetData.readInt() );

                //Prompt the user for the number of quiz grades they wish
		//to enter
		System.out.print( "Enter the number of quiz grades you " +
		   "wish to enter in:  " );

		//Call objGrade.calculateQuizAverage to get and find the
		//average for the quiz grades
		//Before this method is called, we get the number of grades
		//to enter from the user
		objGrade.calculateQuizAverage( objGetData.readInt() );

                //Prompt the user for the number of labs they have missed
		System.out.print( "Enter the number of labs you have " +
		   "missed:  " );

		//Call objGrade.calculateAttendanceGrade to figure out the
		//student's lab attendance grade
		//Before this method is called, we get the number of labs
		//missed from the user
		objGrade.calculateAttendanceGrade( objGetData.readInt() );

                //Ask the user if they have taking the Lab Final yet
		System.out.print( "Have you taking your lab final yet? " +
		   "(y/n):  " );

		//See if the user has taking their lab final yet
		if( objGetData.readChar() == 'y' ) {
			//User has taking lab final, call
			//objGrade.calculateLabFinalGrade to get the grade
			objGrade.calculateLabFinalGrade();
		} else {
			//User hasn't yet, tell them it won't be figured in
			System.out.println( "You said your lab final " +
			   "hasn't been taking yet." );
			System.out.println( "It won't be figured in." );
			System.out.println( "" );  //Blank line
		}

		//Prompt the user for the number of exams they have done
		//This number should only be 1 or 2 since it doesn't
		//include the final exam
		System.out.print( "Enter the number of exams you have " +
		   "taking so far (either 1 or 2, don't include the " +
		   "final):  " );

		//Call objGrade.calculateExamAverage to get the grades
		//and calculate the average
		//Before the method is called, we get the number of exams
		//to input from the user and pass that along to the method
		objGrade.calculateExamAverage( objGetData.readInt() );

		//Ask the user if they have taking the final exam yet
		System.out.print( "Have you taking the final exam " +
		   "yet? (y/n):  " );

		//Get the users input and figure out how they responded
		if( objGetData.readChar() == 'y' ) {
			//User has taking final exam (poor guy/gal)
			//Call objGrade.calculateFinalExamGrade to get the
			//exam grade
			objGrade.calculateFinalExamGrade();
		} else {
			//User hasn't taking final exam, it won't figure in
			System.out.println( "You said you didn't take " +
			   "the final exam." );
			System.out.println( "It won't be figured in." );
			System.out.println( "" );  //Blank line
		}

		/*  Done getting input from user */


		//Calculate up the final Course Average & Course Letter
		//Grade and give the user all the results
		fltCourseAverage = objGrade.calculateCourseGrade();

		System.out.println( "" );  //Blank line
		System.out.println( "=========================" );
		System.out.println( "Your Course Average is " +
		   fltCourseAverage );
		System.out.println( "" );
		System.out.print( "Your Course Letter Grade is  " );

		//Figure out the Course grade and print it
		if( fltCourseAverage >= 90.0 )
			System.out.println( "A" );  //Got an A
		else if( fltCourseAverage >= 80.0 )
			System.out.println( "B" );  //Got a B
		else if( fltCourseAverage >= 70.0 )
			System.out.println( "C" );  //Got a C
		else if( fltCourseAverage >= 60.0 )
			System.out.println( "D" );  //Got a D
		else
			System.out.println( "F" );  //Got an F

	}  //End of main method


	/*
	  Method:	describeProgram

	  Purpose:	Tell the user what this program is and how they
			use it

	  Parameters:	None

	  Returns:	None
	*/
	private static void describeProgram( ) {
		//Describe this program via output to the consol
		System.out.println( "" );  //Blank line
		System.out.println( "CS1323 Grade Calculator" );
		System.out.println( "=======================" );
		System.out.println( "" );
		System.out.println( "This program inputs the grades for " +
		   "your CS1323 projects, your quizzes," );
		System.out.println( "your lab attendance, your lab " +
		   "final, your 1st & 2nd exams, and your final exam." );
		System.out.println( "It then calculates the average for " +
		   "each one and also gives you" );
		System.out.println( "your course average and letter " +
		   "grade." );
		System.out.println( "" );
		System.out.println( "When prompted enter the number of " +
		   "grades you have for each of the above." );
		System.out.println( "For each grade you may enter " +
		   "decimals, and they will be rounded to two decimals " +
		   "places." );
		System.out.println( "" );
		System.out.println( "" );
	}
}  //End of class CalculateGrade


