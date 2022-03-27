//===================================================================
//  WindChill.java -- 10/7/98 -- Rodney Beede
//===================================================================
//  Problem:  Use Wind Chill formula to display table of wind chills
//  Input:  No user input, Predefined Wind Speeds (integer type) and
//	      Temperatures (integer type, °F) are used
//  Output:  Wind Chills (integer type) in a table
//  Method:  Wind Chill Formula is: .0817 * (3.71 * SQRT(Wind Speed)
//		+ 5.81 - 0.25 * (Wind Speed)) * (Temperature - 91.4) +
//		91.4
//
//  Notes:  Round the output by using integer = float + 0.5, if the
//	      output is negative then round using int = float - 0.5
//	      The desired look for the table is:
//    Wind Speed
//   ======================================================
// T |          5    10    15    20    25    30    35    40
// E |     ------------------------------------------------
// M | -30 | ####  ####  ####  ####  ####  ####  ####  ####
// P | -20 | ####  ####  ####  ####  ####  ####  ####  ####
// E | -10 | ####  ####  ####  ####  ####  ####  ####  ####
// R |   0 | ####  ####  ####  ####  ####  ####  ####  ####
// A |  10 | ####  ####  ####  ####  ####  ####  ####  ####
// T |  20 | ####  ####  ####  ####  ####  ####  ####  ####
// U |  30 | ####  ####  ####  ####  ####  ####  ####  ####
// R |  40 | ####  ####  ####  ####  ####  ####  ####  ####
// E |  50 | ####  ####  ####  ####  ####  ####  ####  ####
//   |  60 | ####  ####  ####  ####  ####  ####  ####  ####
//
//       Four digit places are needed since the fatest value seems
//       to be -100, if statements will determine how to align spaces
//       depending on the size of the number, hince -100 needs no
//       spaces in the digit hold area to align it to the far right
//       while the number 0 would need three spaces.  Each column
//       will be seperated by two spaces.  A negative value counts in
//       the alignment value for a digit place holder (#).
//===================================================================

class WindChill {
	public static void main (String[] args) {
		int intCountTemp;  //Counter variable for temperature
		int intCountWind;  //Counter variable for wind speed

		int intRoundCal;  //Rounded calculation
		
		double dblTempCal;  //Temporary Calculation storage area

		//Print out who wrote this program and what it is
		System.out.println( "Rodney Beede -- Wind Chill -- CS1313 -- 10/7/98" );
		System.out.println( "" );  //Put in a extra blank line

		//Print the header section of the table
		System.out.println( "   Wind Speed" );
		System.out.println( "  ======================================================" );
		System.out.println( "T |          5    10    15    20    25    30    35    40" );
		System.out.println( "E |     ------------------------------------------------" );

		intCountTemp = -30;  //Set the initial value for the first Temperature

		while( intCountTemp <= 60 ) {  //Loop until counter is above maximum value for Temperature
			//Determine what part of the side caption is being printed and print the correct section
			//A different one will be used each time the loop repeats
			if( intCountTemp == -30 )
				System.out.print( "M | -30 | " );
			else if( intCountTemp == -20 )
				System.out.print( "P | -20 | " );
			else if( intCountTemp == -10 )
				System.out.print( "E | -10 | " );
			else if( intCountTemp == 0 )
				System.out.print( "R |   0 | " );
			else if( intCountTemp == 10 )
				System.out.print( "A |  10 | " );
			else if( intCountTemp == 20 )
				System.out.print( "T |  20 | " );
			else if( intCountTemp == 30 )
				System.out.print( "U |  30 | " );
			else if( intCountTemp == 40 )
				System.out.print( "R |  40 | " );
			else if( intCountTemp == 50 )
				System.out.print( "E |  50 | " );
			else if( intCountTemp == 60 )
				System.out.print( "  |  60 | " );
			//End of side caption formatting

			intCountWind = 5;  //Set the initial value for the first Wind Speed

			while( intCountWind <= 40 ) {  //Loop until counter is above maxiumu value for Wind Speed
				//Calculate the wind chill
				dblTempCal = (0.0817 * (3.71 * Math.sqrt(intCountWind) + 5.81 - 0.25 * intCountWind) * (intCountTemp - 91.4 ) + 91.4);

				//Round off calculation for wind chill
				if( dblTempCal < 0 )  //To round off a negative number program needs to subtract 0.5
					intRoundCal = (int) (dblTempCal - 0.5);
				else  //To round off a positive number program needs to add 0.5
					intRoundCal = (int) (dblTempCal + 0.5);

				//Check the spacing of the final number and space it in the table
				if( intRoundCal < -99 )  //Number has three digits and is negative, no extra spaces needed
					System.out.print( intRoundCal + "  " );
				else if( intRoundCal < -9 )  //Number has two digits and is negative, one extra space needed (e.g. ' -10')
					System.out.print( " " + intRoundCal + "  " );
				else if( intRoundCal < 0 )  //Number has one digit and is negative, two extra spaces needed (e.g. '  -1')
					System.out.print( "  " + intRoundCal + "  " );
				else if( intRoundCal < 10 )  //Number has one digit and is not negative, three spaces needed (e.g. '   0')
					System.out.print( "   " + intRoundCal + "  " );
				else if( intRoundCal < 100 )  //Number has two digits and is not negative, two spaces needed (e.g. '  11')
					System.out.print( "  " + intRoundCal + "  " );
				else if( intRoundCal < 1000 )  //Number has three digits and is not negative, one space needed (e.g. ' 999')
					System.out.print( " " + intRoundCal + "  " );
				else if( intRoundCal < 10000 )  //Number has four digits (unlikely with current values) and is not negative, no spaces needed (e.g. '9999')
					System.out.print( intRoundCal + "  " );
				//End of number spacing alignment and printing
			
				//Increment wind speed count by 5 mph
				intCountWind = intCountWind + 5;
			}  //Close of Wind Speed loop

			System.out.println( "" );  //Go down to next line
					
			intCountTemp = intCountTemp + 10;  //Increment temperature count by 10 °F
		}  //Close of Temperature loop
	}  //Close of main method
}  //Close of WindChill class