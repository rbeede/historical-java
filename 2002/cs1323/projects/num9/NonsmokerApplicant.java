//=========================================================================
//	NonsmokerApplicant.java
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
//				fact that the applicant is a non-smoker
//
//	Public Methods:		calculatePremium -- Sets the value of
//				intPremium from the super class based on
//				the applicant's data
//
//=========================================================================

class NonsmokerApplicant extends Applicant {
	//intPremium is declared in the super class Applicant
   	//name, age, gender are also declared in the superclass


	//Class contructor
	public NonsmokerApplicant(String name, int age, char gender) {
		//Simply use the super class constructor, with these values
		super( name, age, gender );
	}

	public NonsmokerApplicant( ) {
		//No data given, pass along to super and it will use some
		//defaults
		super();
	}


	/*
	  Method:	calculatePremium

	  Purpose:	Calculates the insurance premium based on the age,
			gender for a non-smoker.
			It sets intPremium (from the super class) to the
			premium amount

	  Parameters:	None

	  Returns:	None
	*/
	public void calculatePremium( ) {
		//If a female, start at value of 120
		//If a female and over 30, add 20 to that to get 140
		//If a male, start at value of 130
		//If a male and over 30, add 20 to that to get 150
		if( gender == 'F' )
			intPremium = 120;
		else if( gender == 'M' )
			intPremium = 130;

		//If age is over 30 for either, just add 20
		if( age > 30 )
			intPremium += 20;
	}

}  //End of class NonsmokerApplicant
