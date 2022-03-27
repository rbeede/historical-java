//=========================================================================
//	CS1323Grades.java
//=========================================================================
//	Programmer:		Rodney Beede
//	ID:			0000
//	Course:			CS1323
//	Lab Section:		222
//	Lab TA:			Lalitha
//
//	Project#:		5
//	Due Date:		3/10/2K	 
//
//	Description:		This class is used to make a object that
//				will get the grades of a student and then
//				calculate the grade for that student
//                                          
//	Inputs:			fltProjectPercentage -- % of the total
//				grade from the student's project grades
//
//				fltQuizPercentage -- % of the total grade
//				from the student's quiz grades
//
//				fltAttendancePercentage -- % of the total
//				grade from the student's attendance grades
//
//				fltLabFinalPercentage -- % of the total
//				grade from the student's lab final grade
//
//				fltTotalExamPercentage -- % of the total
//				grade from the student's 1st & 2nd exam
//				grades
//
//				fltFinalExamPercentage -- % of the total
//				grade from the student's final exam grade
//
//				This class will also prompt the user for
//				each of the grades in each subcategory
//				(Projects, Quizzes, Lab Attendance,
//				Lab Final, Exam I & II, Final Exam)
//
//	Outputs:		This class can return back (via methods)
//				grades for each subcategory, the Course
//				Average, and the Course Letter Grade)
//
//	Public Methods:		CS1323Grades -- Class constructor
//
//	Private Methods:	round -- Rounds a float to the specified
//				number of decimal places
//				
//=========================================================================

import EasyIn;  //Load the class for getting inputs

class CS1323Grades {
	//Declare and create a EasyIn object for getting input
	private EasyIn objGetData = new EasyIn( );

	//Declare some private variables for holding the percentages
	private float	fltProjectPercentage,	  //% of project grades
			fltQuizPercentage,	  //% of quiz grades
			fltAttendancePercentage,  //% of attendance grades
			fltLabFinalPercentage,	  //% of final lab grade
			fltTotalExamPercentage,	  //% of exam 1 & 2 grades
			fltFinalExamPercentage;	  //% of final exam grade

	//Declare some arrays for holding the grades of everything
	private float[]	fltProjectGrades,	//Project Grades
			fltQuizGrades,		//Quiz Grades
			fltExamGrades;		//Exams I & II grades

	//Since the Lab Attendance, the Lab Final, & the Final Exam only
	//have one possible value we don't use an array
	private float	fltLabAttendGrade,	//Lab Attendance Grade
			fltLabFinalGrade,	//Lab Final Grade
			fltFinalExamGrade;	//Final Exam Grade

	//This variable keeps a count of the total percent of all the
	//grades; that way if the user hasn't taking an exam yet, we don't
	//end up figuring it in
	private float	fltTotalPercentage;

	//Declare some variables for holding the averages of each category
	//Some will simply be that one grade
	private float	fltProjectAverage,	//Project's Average
			fltQuizAverage,		//Quizzes Average
			fltAttendanceAverage,	//Lab Attendance Average
			fltLabFinalAverage,	//Lab Final Average
			fltTotalExamAverage,	//Exams I & II Average
			fltFinalExamAverage;	//Final Exam Average


	/*
	  Method:	CS1323Grades class constructor

	  Purpose:	Setups the grade percentages when the object is
			created

	  Parameters:	fltPP -- Project Percentage
			fltQP -- Quiz Percentage
			fltAP -- Attendance Percentage
			fltLFP -- Lab Final Percentage
			fltTEP -- Total Exam Percentage
			flt FEP -- Final Exam Percentage

	  Returns:	Not Appliable
	*/
	public CS1323Grades( float fltPP, float fltQP, float fltAP,
			     float fltLFP, float fltTEP, float fltFEP ) {
		//Setup the private values from what the user passed
		fltProjectPercentage = fltPP;
		fltQuizPercentage = fltQP;
		fltAttendancePercentage = fltAP;
		fltLabFinalPercentage = fltLFP;
		fltTotalExamPercentage = fltTEP;
		fltFinalExamPercentage = fltFEP;
	}


	/*
	  Method:	calculateAttendanceGrade

	  Purpose:	Calculates the user's attendance grade for lab

	  Parameters:	numberAbsences -- Number of labs missed

	  Returns:	None
	*/
	public void calculateAttendanceGrade(int numberAbsences) {
		//Add to the total percent for all the grades
		fltTotalPercentage = fltTotalPercentage
		   + fltAttendancePercentage;

		//If the user has missed more then 1 day, they get a zero,
		//otherwise they get a hundred
		if( numberAbsences > 1 )
			fltAttendanceAverage = 0.0f;
		else
			fltAttendanceAverage = 100.0f;

		//Show the user their attendance grade
		System.out.println( "" );  //Blank line
		System.out.println( "Your lab attendance grade is "
		   + fltAttendanceAverage );
		System.out.println( "" );  //Blank line
		System.out.println( "" );  //Blank line
	}


	/*
	  Method:	calculateCourseGrade

	  Purpose:	Figures out and returns the Course Grade

	  Parameters:	None

	  Returns:	Float value of the Course Grade
	*/
	public float calculateCourseGrade( ) {
		float fltAverage = 0.0f;

		//Add all the grades together and multiple them by their
		//percentages that they count for
		fltAverage = fltProjectAverage * fltProjectPercentage;

		fltAverage = fltAverage + fltQuizAverage *
		   fltQuizPercentage;

		fltAverage = fltAverage + fltAttendanceAverage *
		   fltAttendancePercentage;

		fltAverage = fltAverage + fltLabFinalAverage *
		   fltLabFinalPercentage;

		fltAverage = fltAverage + fltTotalExamAverage *
		   fltTotalExamPercentage;

		fltAverage = fltAverage + fltFinalExamAverage *
		   fltFinalExamPercentage;

		//Since it is possible that the Lab Final & Final Exam
		//haven't been taking, we take the total percentage that
		//this average represents (from fltTotalPercentage) and
		//divide that into this average to get the average for all
		//the exams taking so far
		fltAverage = fltAverage / fltTotalPercentage;

		//Round the average to two decimals
		fltAverage = round( fltAverage, 2 );

		//Return the final Course Average
		return fltAverage;
	}


	/*
	  Method:	calculateExamAverage

	  Purpose:	Prompt user to input Exam I & II grades, calculate
			those grades, and then outputs the result

	  Parameters:	numberExams -- Number of exams taking
				       (the final exam isn't included)

	  Returns:	None
	*/
	public void calculateExamAverage(int numberExams) {
		//Create the fltExamGrades array with a size equal to the
		//number of grades to be entered
		fltExamGrades = new float[(numberExams)];

		//Add to the total percent for all the grades
		fltTotalPercentage = fltTotalPercentage
		   + fltTotalExamPercentage;

		float fltAverage = 0.0f;

		//Give a blank line before getting input
		System.out.println( "" );

		//Loop until all the grades have been read from the user
		for( int iCount = 0; iCount < numberExams; iCount++ ) {
			//Prompt the user for a grade
			System.out.print( "Enter in exam #" +
			   (iCount + 1) + "'s grade:  " );

			//Read in the grade and store it in the array
			fltExamGrades[iCount] = objGetData.readFloat();
		}

		//Calculate the average by adding up all the grades,
		//dividing by the number of grades, and then round it
		for( int iCount = 0; iCount < numberExams; iCount++ )
			//Add it all up
			fltAverage = fltAverage + fltExamGrades[iCount];

		fltAverage = fltAverage / numberExams;  //Divide it up

		//Round it to 2 decimals
		fltAverage = round( fltAverage, 2 );

		//Save the final average
		fltTotalExamAverage = fltAverage;

		//Show the user the results
		System.out.println( "" );  //Blank line
		System.out.println( "Your Exam I & II's Average is "
		   + fltTotalExamAverage );
		System.out.println( "" );  //Blank line
		System.out.println( "" );  //Blank line
	}


	/*
	  Method:	calculateFinalExamGrade

	  Purpose:	Prompt user for thier Final Exam grade

	  Parameters:	None

	  Returns:	None
	*/
        public void calculateFinalExamGrade( ) {
		//Add to the total percent for all the grades
		fltTotalPercentage = fltTotalPercentage
		   + fltFinalExamPercentage;

		//Prompt the user
		System.out.print( "Enter in your Final Exam grade:  " );

		//Get the users input
		fltFinalExamAverage = objGetData.readFloat();

		//Give a extra blank line for the next output
		System.out.println( "" );
	}


	/*
	  Method:	calculateLabFinalGrade

	  Purpose:	Ask the user for their Lab Final grade

	  Parameters:	None

	  Returns:	None
	*/
	public void calculateLabFinalGrade( ) {
		//Add to the total percent for all the grades
		fltTotalPercentage = fltTotalPercentage
		   + fltLabFinalPercentage;

		//Prompt the user
		System.out.print( "Enter in your Lab Final grade:  " );

		//Get the users input
		fltLabFinalAverage = objGetData.readFloat();

		//Give a extra blank line for the next output
		System.out.println( "" );
	}


	/*
	  Method:	calculateProjectAverage

	  Purpose:	Gets the student's project grades, computes the
			average of those grades, and outputs the result

	  Parameters:	numberProjects -- Number of project grades

	  Returns:	None
	*/
	public void calculateProjectAverage(int numberProjects) {
		float fltAverage = 0.0f;  //For holding the average grade

		//Create the fltProjectGrades array with a size equal to
		//the number of grades to be entered
		fltProjectGrades = new float[(numberProjects)];

		//Add to the total percent for all the grades
		fltTotalPercentage = fltTotalPercentage
		   + fltProjectPercentage;

		//Give a blank line before getting input
		System.out.println( "" );

		//Loop until all the grades have been read from the user
		for( int iCount = 0; iCount < numberProjects; iCount++ ) {
			//Prompt the user for a grade
			System.out.print( "Enter in project #" +
			   (iCount + 1) + "'s grade:  " );

			//Read in the grade and store it in the array
			fltProjectGrades[iCount] = objGetData.readFloat();
		}

		//Calculate the average by adding up all the grades,
		//dividing by the number of grades, and then round it
		for( int iCount = 0; iCount < numberProjects; iCount++ )
			//Add it all up
			fltAverage = fltAverage + fltProjectGrades[iCount];

		fltAverage = fltAverage / numberProjects;  //Divide it up

		//Round it to 2 decimals
		fltAverage = round( fltAverage, 2 );

		//Save the final average
		fltProjectAverage = fltAverage;

		//Show the user the results
		System.out.println( "" );  //Blank line
		System.out.println( "Your Project's Average is "
		   + fltProjectAverage );
		System.out.println( "" );  //Blank line
		System.out.println( "" );  //Blank line
	}


	/*
	  Method:	calculateQuizAverage

	  Purpose:	Gets the user's quiz grades, compute their average,
			and then tell the user their quiz average

	  Parameters:	numberQuizzes -- Number of quizzes to ask for

	  Returns:	None
	*/
	public void calculateQuizAverage(int numberQuizzes) {
		//Create the fltQuizGrades array with a size equal to the
		//number of grades to be entered
		fltQuizGrades = new float[(numberQuizzes)];

		//Add to the total percent for all the grades
		fltTotalPercentage = fltTotalPercentage
		   + fltQuizPercentage;

		float fltAverage = 0.0f;  //For holding the average grade

		//Give a blank line before getting input
		System.out.println( "" );

		//Loop until all the grades have been read from the user
		for( int iCount = 0; iCount < numberQuizzes; iCount++ ) {
			//Prompt the user for a grade
			System.out.print( "Enter in quiz #" +
			   (iCount + 1) + "'s grade:  " );

			//Read in the grade and store it in the array
			fltQuizGrades[iCount] = objGetData.readFloat();
		}

		//Calculate the average by adding up all the grades,
		//dividing by the number of grades, and then round it
		for( int iCount = 0; iCount < numberQuizzes; iCount++ )
			//Add it all up
			fltAverage = fltAverage + fltQuizGrades[iCount];

		fltAverage = fltAverage / numberQuizzes;  //Divide it up

		//Round it to 2 decimals
		fltAverage = round( fltAverage, 2 );

		//Save the final average
		fltQuizAverage = fltAverage;

		//Show the user the results
		System.out.println( "" );  //Blank line
		System.out.println( "Your Quizzes' Average is "
		   + fltQuizAverage );
		System.out.println( "" );  //Blank line
		System.out.println( "" );  //Blank Line
	}


	/*
	  Method:	round

	  Purpose:	Rounds a decimal number of the specified number of
			decimal places

	  Parameters:	fltNum -- Number to round

			intDecPlaces -- Number of decimals places to
			round to

	  Returns:	(float) value of the rounded number
	*/
	private float round( float fltNum, int intDecPlaces ) {
		float fltCalcNum;	//Something for holding calcs

		/*
			This is how this all works:

			Take some number and multiply it by a power of ten,
			where the power is the number of decimals places

			This will move the number that may be rounded up
			right in front of the decimal
			(i.e.  25.56 when rounding to 1 decimal place gives
			us 255.6)

			Next we add 0.5 to the number and this rounds up
			number is it is suppose to be (i.e. 25.56 rounded
			to 1 decimal gives 255.6, add 0.5 to get 256.1)

			Now truncate the leftover decimals and then move
			the decimal back into place (i.e. 256.1 goes to
			256, and then back to the rounded number 25.6)
		*/

		fltCalcNum = (fltNum * (float) Math.pow(10.0,
		   (double) intDecPlaces)) + 0.5f;
		fltCalcNum = (int) fltCalcNum;
		fltCalcNum = fltCalcNum / (float) Math.pow(10,
		   intDecPlaces);

		return fltCalcNum;
	}
}  //End of class CS1323Grades

