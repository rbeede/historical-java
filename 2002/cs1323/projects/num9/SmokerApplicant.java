//=========================================================================
//	SmokerApplicant.java
//=========================================================================
//	Programmer:		Rodney Beede
//	ID:			0000
//	Course:			CS1323
//	Lab Section:		222
//	Lab TA:			Lalitha
//
//	Project#:               9
//	Due Date:		4-28-2K
//
//	Description:		This is a sub-class for Applicant
//				It calculates the premium based on the
//				fact that the applicant is a smoker
//
//	Public Methods:		calculatePremium -- Sets the value of
//				intPremium from the super class based on
//				the applicant's data
//
//=========================================================================

class SmokerApplicant extends Applicant {
	//intPremium is declared in the super class Applicant
   	//name, age, gender are also declared in the superclass


	//Class contructor
	public SmokerApplicant(String name, int age, char gender) {
		//Simply use the super class constructor, with these values
		super( name, age, gender );
	}

	public SmokerApplicant( ) {
		//No data given, pass along to super and it will use some
		//defaults
		super();
	}


	/*
	  Method:	calculatePremium

	  Purpose:	Calculates the insurance premium based on the age,
			gender for a smoker.
			It sets intPremium (from the super class) to the
			premium amount

	  Parameters:	None

	  Returns:	None
	*/
	public void calculatePremium( ) {
		//If a female, start at value of 165
		//If a female and over 30, add 25 to that to get 190
		//If a male, start at value of 175
		//If a male and over 30, add 25 to that to get 200
		if( gender == 'F' )
			intPremium = 165;
		else if( gender == 'M' )
			intPremium = 175;

		//If age is over 30 for either, just add 25
		if( age > 30 )
			intPremium += 25;
	}

}  //End of class SmokerApplicant
