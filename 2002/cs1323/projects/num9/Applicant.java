//=========================================================================
//	Applicant.java
//=========================================================================
//	Programmer:		Rodney Beede
//	ID:			0000
//	Course:			CS1323
//	Lab Section:		222
//	Lab TA:			Lalitha
//
//	Project#:		9
//	Due Date:		4-28-2K
//
//	Description:		This is an abstract class, it gives the
//				general function for applicants to
//				insurance premium rates
//
//	Public Methods:		calculatePremium (ABSTRACT) -- It is up
//				to the sub-class to define this, it will
//				figure the user's premium and store it in
//				intPremium
//
//				getAge, getGender, getName -- Return the
//				respective value
//
//				setAge, setGender, setName -- Sets the
//				age, gender, or name of the applicant
//				It is preferred that this be done when the
//				object is created in it's subclass
//
//				getPremium -- Gives the value of intPremium
//
//=========================================================================


public abstract class Applicant
{

/*******************
   Data Members
*******************/   

   protected String  name;
   protected int     age;
   protected char    gender;
   protected int     intPremium;  //Holds the premium

/*******************
   Constructors
*******************/   
   public Applicant()
   {
      //Send some defaults for applicant data
      this("Not Given", 0, 'U');
   }
   
   public Applicant(String name, int age, char gender)
   {
      //Set the applicant's data
      this.age = age;
      this.name = name;
      this.gender = gender;

      //Give intPremium a clean value, that way if getPremium
      //is called before the premium is calculated, the program
      //won't error
      intPremium = 0;
   }
   

   /*
	Method:		calculatePremium

	Purpose:	Abstract method, filled in by sub-classes
			The sub-classes should simply set the
			intPremium variable

	Parameters:	None

	Returns:	None
   */
   public abstract void calculatePremium( );


   /*
	Methods:	getAge, getGender, getName

	Purpose:	These simply give access to the private
			data types age, gender, & name

	Parameters:	None

	Returns:	(int) getAge - Age of applicant
			(char) getGender - Gender of applicant
			(String) getName - Name of applicant
   */
   public int getAge()
   {
      return age;
   }
   
   public char getGender()
   {
      return gender;
   }
   
   public String getName()
   {
      return name;
   }
   

   /*
	Methods:	setAge, setGender, setName

	Purpose:	These simply give access to the private
			data types age, gender, & name so they
			can be changed

	Parameters:	(int) age, setAge -- Sets the age
			(char) gender, setGender -- Sets the gender
			(String) name, setName -- Sets the name

	Returns:	None
   */
   public void setAge(int age)
   {
      this.age = age;
   }
   
   public void setGender(char gender)
   {
      this.gender = gender;
   }
   
   public void setName(String name)
   {
      this.name = name;
   }


   /*
	Method:		getPremium

	Purpose:	Simply return the premium from intPremium

	Parameters:	None

	Returns:	(int) -- value in intPremium
   */
   public int getPremium( ) {
	return intPremium;
   }
}
