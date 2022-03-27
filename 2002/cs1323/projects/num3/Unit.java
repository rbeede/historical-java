//=========================================================================
//	Unit.java 
//=========================================================================
//	Programmer:  		Rodney Beede 
//	ID:			0000 
//	Course:      		CS1323 
//	Lab Section:		222 
//	Lab TA:			Lalitha
//
//	Project#:    		#3
//	Due Date:		February 25th, 2000	 
//
//	Description: 		This is a instantiable class that can
//				convert from metric measurements to U.S.
//				units.  Centimeters are used as the
//				input to convert from.
//				
//				Units:
//				  1 inch = 2.54 centimeters
//				  12 inches = 1 foot
//				  3 feet = 1 yard
//				  5280 feet = 1 mile
//
//				Conversion Methods Available:
//				  toFeet -- Converts centimeters to feet
//				  toInches -- Converts centimeters to
//				              inches
//				  toYards -- Converts centimeters to yards
//				  toMiles -- Converts centimeters to miles
//
//=========================================================================

class Unit {
	//Declare some constants for inches/centimeter, feet/yard, etc...
	//Will allow others to see the values of the constants
	public final float CENTIMETERS_PER_INCH = 2.54f;
	public final float INCHES_PER_FOOT = 12.0f;
	public final float FEET_PER_YARD = 3.0f;
	public final float FEET_PER_MILE = 5280.0f;

	//This variable will hold the number of centimeters to convert from
	public float fltCentMtr;

	/*
	  Method:	Class Constructor

	  Purpose:	Used to initilize object variables

	  Paramaters:	float ftlCentMtr -- number of centimeters to
			to convert from

	  Returns:	Doesn't Apply
	*/
	public Unit( float fltNumCentMtr ) {
		fltCentMtr = fltNumCentMtr;
	}


	/*
	  Method:	toInches

	  Purpose:	Converts a float value, centimeters, to another
			float value, inches

	  Parameters:	None

	  Returns:	float value of number of inches in fltCentMtr
			centimeters
	*/
	public float toInches( ) {
		//Convert the passed value and return it
		return ( fltCentMtr / CENTIMETERS_PER_INCH );
	}


	/*
	  Method:	toFeet

	  Purpose:	Converts a float value, centimeters, to another
			float value, feet

	  Parameters:	None

	  Returns:	float value of number of feet in fltCentMtr
			centimeters
	*/
	public float toFeet( ) {
		//Convert centimeters to inches, then from inches to feet
		//and return the value
 		return ( toInches() / INCHES_PER_FOOT );
	}


	/*
	  Method:	toYards

	  Purpose:	Converts a float value, centimeters, to another
			float value, yards

	  Parameters:	None

	  Returns:	float value of number of yards in fltCentMtr
			centimeters
	*/
	public float toYards( ) {
		//Convert centimeters to feet, then from feet to yards
		//and return the value
		return ( toFeet() / FEET_PER_YARD );
	}


	/*
	  Method:	toMiles

	  Purpose:	Converts a float value, centimeters, to another
			float value, miles

	  Parameters:	None

	  Returns:	float value of number of miles in fltCentMtr
			centimeters
	*/
	public float toMiles( ) {
		//Convert centimeters to feet, then from feet to miles
		//and return the value
		return ( toFeet() / FEET_PER_MILE );
	}
}  //End of class Unit



