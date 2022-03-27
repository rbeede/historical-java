//=========================================================================
//	Inventory.java 
//=========================================================================
//	Programmer:  		Rodney Beede 
//	ID:			0000 
//	Course:      		CS1323 
//	Lab Section:		222 
//	Lab TA:			Lalitha
//
//	Project#:		4
//	Due Date:		3-03-2000	 
//
//	Description: 		This is a instantiable class that is used
//				to gather data about a vechile and will
//				return the vehical series
//
//	Public Methods:		Inventory -- Just the constructor
//
//	Private Methods:	findSeries() -- Determines the Series
//
//				findTrimLevel() -- Determines the Trim,
//				only called if a G series
//
//				getHorsePower() -- Gets the Horsepower
//				data, only called if a G series
//
//				getSeriesInput() -- Gets the Price, Doors,
//				and Transmission data
//				
//=========================================================================

import javabook.*;  //Load the javabook package

class Inventory {
	//Declare a InputBox object for reading in various data, it will be
	//created when this class is instantiated because we then have the
	//reference to the MainWindow object
	private InputBox objInData;

	//Declare some global variables for input
	public short shrtPrice;  //Price
	public byte bytDoors;  //Number of doors
	public char chrTransmission;  //Transmission type
	public short shrtHorsePower;  //Horse power

	//Declare some global variables for output
	public char chrSeries;  //Vechile series
	public byte bytTrimLvl;  //Trim level

	
	/*
	  Method:	Inventory class constructor

	  Purpose:	Kick off program on getting inputs, finding the
			series, and getting the Horse Power and Trim Level
			(if appliable)

	  Parameters:	MainWindow object to bind to InputBox

	  Returns:	N/A
	*/
	public Inventory( MainWindow objMainWin ) {
		//Create the InputBox object, objInData
		objInData = new InputBox( objMainWin, "Input Required" );

		//This will ask for the Price, Num Doors, & Transmission
		getSeriesInput();

		//Next find the series
		findSeries();

		//Check to see if the Series is 'G' and if so ask for the
		//amount of horsepower and figure out the trim level
		if( chrSeries == 'G' ) {
			getHorsePower();
			findTrimLevel();
		}
	}  //End of Inventory constructor


	/*
	  Method:	findSeries

	  Purpose:	Figure out which series a vechile belongs to based
			on the data given about the Price, Num of Doors,
			and Transmission type

	  Parameters:	None

	  Returns:	None

	  Notes:	First compare the price to narrow down the possible
			series, then use the number of doors and/or
			transmission type to figure out the series
			For the G series, we don't worry about figuring
			the trim yet, we just know that it is the G series

			Here is a table indicating how we narrow things up:
			===================================================
			Possible Series:	A, E
			Price Range:		11,250-14,500
			Num of Doors:		4 doors on both
			Decider is
			Transmission:		Series A = manual, E = auto
			---------------------------------------------------
			Possible Series:	B, D, F
			Price Range:		16,000-19,250
			Decider is
			Num of Doors and
			Transmission Type:	Series B = 4 Doors, Auto
						Series D = 2 Doors, Manual
						Series F = 4 Doors, Manual
			--------------------------------------------------
			Possible Series:	C, G1, G2
			Price Range:		21,500-27,100
						To have G, must have min
						of 22150
			Decider is
			Price &			(See above about price)
			Num of Doors:		Series C = 4 Doors
						Series G = 2 Doors
			Transmission Type:	Series G must have Auto
						Series C must have manual
			--------------------------------------------------
			Possible Series:	D, B, F
			This is redundant and the same as B, D, F
			--------------------------------------------------
			Possible Series:	E, A
			This is redundant and the same as A, E
			--------------------------------------------------
			Possible Series		F, B, D
			This is redundant and the same as B, D, F
			--------------------------------------------------
			Possible Series		G1, C, G2, G3, G4
			This is redundant to C, G1, G2 even though it also
			includes G3 & G4.  We don't worry about trim now.
			--------------------------------------------------
			Possible Series		G2, C, G1, G3, G4
			This is redundant and same as G1, C, G2, G3, G4
			--------------------------------------------------
			Possible Series		G3, G4
			When the price is equal to or above 24500 the
			series can only be G.  The trim is figured out
			later on.
			==================================================
			
	  End of Notes
	*/
	private void findSeries( ) {
		//Narrow down the series possiblities by looking at the
		//prices first
		if( (shrtPrice >= 11250) && (shrtPrice <= 14500) ) {
			//Possibly A or E
			//The transmission type determines which one

			//Check to make sure we have 4 doors like we should
			if( bytDoors != 4 ) {
				//User entered bad data, no series possible
				chrSeries = '?';
				return;  //Leave this method
			}

			//Figure out the transmission type
			if( chrTransmission == 'A' )
				chrSeries = 'E';
			else if( chrTransmission == 'M' )
				chrSeries = 'A';
		} else if( (shrtPrice >= 16000) && (shrtPrice <= 19250) ) {
			//Possibly B, D, or F
			//The number of doors and transmission type says so

			//First figure out how many doors, 2 doors is D,
			//and 4 doors could be B or F
			if( bytDoors == 2 ) {
				//Series should be D, make sure we have a
				//manual transmission

				if( chrTransmission != 'M' ) {
					//Bad data
					chrSeries = '?';
					return;
				}

				chrSeries = 'D';
			} else if( bytDoors == 4 ) {
				//Transmission type determines series
				//between B or F
				if( chrTransmission == 'A' )  //Series B
					chrSeries = 'B';
				else if( chrTransmission == 'M' )
					chrSeries = 'F';  //Series F
			} else {
				//Number of doors isn't correct, no series
				chrSeries = '?';
				return;  //Leave method right away
			}  //End of bytDoors if block
		} else if( (shrtPrice >= 21500) && (shrtPrice <= 27100) ) {
			//Possibly C, G1, or G2

			//Number of doors or transmission can decide if
			//it is C or G, we don't worry about trim yet
			//If it is G, price should not go below 22150 or we
			//actually have no matching series
			if( bytDoors == 4 ) {
				//Series should just be C, make sure user
				//entered in manual for transmission

				if( chrTransmission != 'M' ) {
					//Bad data
					chrSeries = '?';
					return;  //Leave method
				}

				chrSeries = 'C';  //C Series
			} else if( (bytDoors == 2) && (shrtPrice >= 22150))
			{
				//Series should just be G, make sure user
				//entered in automatic transmission
				if( chrTransmission != 'A' ) {
					//Bad data
					chrSeries = '?';
					return;  //Leave method
				}

				chrSeries = 'G';  //G Series
			} else {
				//User entered bad data
				chrSeries = '?';
				return;
			}
		} else if( (shrtPrice >= 24500) && (shrtPrice <= 29200) ) {
			//Possibly G3 or G4

			//Just double check number of doors and trans
			if( bytDoors != 2 || chrTransmission != 'A') {
				//User entered bad data
				chrSeries = '?';
				return;  //Leave right away
			}

			//Doors & Trans are ok, trim is figured out later
			chrSeries = 'G';
		} else {
			//User entered a unknown price
			chrSeries = '?';
		}  //End of Price check
	}  //End of findSeries method


	/*
	  Method:	findTrimLevel

	  Purpose:	Figure out the Trim Level of a vechile based on the
			data given about the Horse Power
			This method only executes for the 'G' series

	  Parameters:	None

	  Returns:	None
	*/
	private void findTrimLevel( ) {
		//Figure out the trim level based on HP
		switch( shrtHorsePower ) {
			case 200:	bytTrimLvl = 1;
					break;

			case 215:	bytTrimLvl = 2;
					break;

			case 240:	bytTrimLvl = 3;
					break;

			case 310:	bytTrimLvl = 4;
					break;

			default:	//User entered bad data
				chrSeries = '?';
				return;  //Leave method
		}  //End of switch
	}  //End of findTrimLevel method


	/*
	  Method:	getHorsePower

	  Purpose:	When the 'G' series is involved, this method will
			ask the user for the horse power of the vehicle so
			the trim level can be determined

	  Parameters:	None

	  Returns:	None
	*/
	private void getHorsePower( ) {
		//Ask the user for the horsepower
		shrtHorsePower = (short) objInData.getInteger( "Enter " +
		   "in the horse power:" );
	}


	/*
	  Method:	getSeriesInput

	  Purpose:	Ask the user for the Price, Number of Doors, and
			Transmission type of the vechile in question
			With this data, the series can be determined

	  Parameters:	None

	  Returns:	None
	*/
	private void getSeriesInput( ) {
		//Ask the user for the Price of the vehicle
		shrtPrice = (short) objInData.getInteger( "Enter in the " +
		   "price of the vechile:" );

		//Ask the user for the number of doors	
		bytDoors = (byte) objInData.getInteger( "Enter in the " +
		   "number of doors:" );

		//Ask the user for the transmission type
		chrTransmission = objInData.getString( "Enter in the " +
		   "transmission type (A for automatic, M for manual):"
		   ).charAt(0);

		//Make sure the input is in upper-case and not lower-case
		chrTransmission = Character.toUpperCase( chrTransmission );
	}
}  //End of class Inventory


