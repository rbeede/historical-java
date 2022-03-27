//===================================================================
//  Loan.java -- 11/9/98 -- Rodney Beede
//===================================================================
//  Problem:  Write program to print a loan ammorization table
//  Input:  Amount of loan (float), yearly rate of interest (float),
//          Number of years of loan (int)
//  Output:  Table with these headers - Payment # / Interest payment 
//           / Accumulated interest / Principle payment / 
//           Accumulated principal / Balance due; Monthly payment
//  Method:  Formula is M = ( A * r ) / ( 1 - ( 1 + r )^(-t) ), M =
//           monthly payment, A = amount of loan, r = monthly rate of
//           interest, t = time in months
//
//  Notes:  User shouldn't have to restart program to compute again
//          For a sentinel value to end the program the user can type
//          in 'quit'; There is a rounding function used for decimals
//          Last value may need to be added into next to last value
//          The table should format like this:
/*
                       Loan Ammorization Table                      
=====================================================================
 Payment | Interest |  Accum.  | Principal |  Accum.   | Balance Due
         | Payment  | Interest |  Payment  | Principal |
---------------------------------------------------------------------
 # ????? | $????.?? | $????.?? | $?????.?? | $?????.?? | $???????.??

*/

//Tell compilier to include the Java IO package of the Java API
import java.io.*;

class Loan {
     public static void main (String[] args) throws IOException {
          //Set up a input stream from standard input
          BufferedReader stdin = new BufferedReader
             (new InputStreamReader(System.in) );

          //Declare variables used for table
          int intPaymentNum;  //Payment number
          double dblInterestPay;  //Interest payment
          double dblAccumInterest;  //Amount of accumulated interest
          double dblPrincipalPay;  //Principal payment
          double dblAccumPrincipal;  //Amount accumulated principal
          double dblBalanceDue;  //Balance due on loan

          //Declare variables used to store user input
          double dblAmountLoan;  //Amount of loan
          double dblYearRateInterest;  //Yearly rate of interest
          int intYearsLoanLength;  //Number of years of loan
          String strKeyInput;  //Holds input from keyboard

          //Declare other variables
          double dblMonthPay;  //Monthly payment

          //Loop program forever so user won't have to restart, user
          //will have to give the "QUIT" command to end program
          while ( 1 == 1 ) {
               //Fill in values that indicate nothing has been
               //entered in yet for variables that hold user input
               dblAmountLoan = -1.0;
               dblYearRateInterest = -1.0;
               intYearsLoanLength = -1;
               strKeyInput = "";

               //Clear out variables that store calculations
               intPaymentNum = 0;  //Payment number
               dblInterestPay = 0.0;  //Interest payment
               dblAccumInterest = 0.0;  //Accumlated interest
               dblPrincipalPay = 0.0;  //Principal payment
               dblAccumPrincipal = 0.0;  //Accumulated principal
               dblBalanceDue = 0.0;  //Balance due
               dblMonthPay = 0.0;  //Monthly payment

               //Print out who is responsible for this program
               System.out.println( "\nRodney Beede -- Loan -- " 
                  + "CS1313 -- 11/9/98" );
               System.out.println( "" );  //Print a blank line

               //Print out the directions for the program
               System.out.println( "Instructions:  Enter the data"
                  + " you are prompted for." );
               System.out.println( "" );  //Blank line
               System.out.println( "Valid data cannot contain any"
                  + " commas or dollar signs." );
               System.out.println( "You may enter decimals for the"
                  + " amount of the loan and for the yearly" );
               System.out.println( "rate of interest.  You may only"
                  + " enter a integer value for the number" );
               System.out.println( "of years of the loan.  When"
                  + " entering a percent do not use a percent" );
               System.out.println( "(%) sign.  Example:  For 5.5%"
                  + " you would type in 5.5 and press enter." );
               System.out.println( "" );  //Blank line
               System.out.println( "To terminate the program type in"
                  + " the word QUIT at any prompt." );
               System.out.println( "" );  //Blank line

               //Loop until the user enters valid data or quits               
               while ( dblAmountLoan < 0.0 ) {
                    //Ask for the amount of the loan
                    System.out.print( "Enter the amount of the"
                       + " loan ==> " );

                    //Flush buffer so message shows
                    System.out.flush();

                    //Read in the amount of the loan
                    strKeyInput = stdin.readLine();

                    //Check to see if the user wants to quit
                    CheckQuit( strKeyInput );

                    //Convert input into a double
                    dblAmountLoan = new
                       Float( strKeyInput ).doubleValue();

                    //Check to see if user entered invalid data so
                    //program can tell them they did if they did
                    if( dblAmountLoan < 0.0 ) {  //Invalid data
                         //Tell user their error
                         System.out.println( "Invalid data entered."
                            + "  Please re-enter data." );
                    }
               }  //End of user input loop

               //Loop until the user enters valid data or quits               
               while ( dblYearRateInterest < 0.0 ) {
                    //Ask for the amount of interest per year
                    System.out.print( "Enter the amount of interest"
                       + " per year ==> " );

                    //Flush buffer so message shows
                    System.out.flush();

                    //Read in the amount of interest per year
                    strKeyInput = stdin.readLine();

                    //Check to see if the user wants to quit
                    CheckQuit( strKeyInput );

                    //Convert input into a double 
                    dblYearRateInterest = new
                       Float( strKeyInput ).doubleValue();

                    //Convert from a fraction to a decimal value and
                    //use round function to trim off extra data that
                    //seems to be added by using the doubleValue
                    //call and making input different
                    dblYearRateInterest = Round( (double)
                       (dblYearRateInterest / 100), 5 );

                    //Check to see if user entered invalid data so
                    //program can tell them they did if they did
                    if( dblYearRateInterest < 0.0 ) {  //Invalid data
                         //Tell user their error
                         System.out.println( "Invalid data entered."
                            + "  Please re-enter data." );
                    }
               }  //End of user input loop

               //Loop until the user enters valid data or quits               
               while ( intYearsLoanLength < 0 ) {
                    //Ask for the length (in years) of loan
                    System.out.print( "Enter the number of years for"
                       + " the loan ==> " );

                    //Flush buffer so message shows
                    System.out.flush();

                    //Read in the number of years
                    strKeyInput = stdin.readLine();

                    //Check to see if the user wants to quit
                    CheckQuit( strKeyInput );

                    //Convert input into a integer
                    intYearsLoanLength = new
                       Integer( strKeyInput ).intValue();

                    //Check to see if user entered invalid data so
                    //program can tell them they did if they did
                    if( intYearsLoanLength < 0 ) {  //Invalid data
                         //Tell user their error
                         System.out.println( "Invalid data entered."
                            + "  Please re-enter data." );
                    }
               }  //End of user input loop

               //Calculate the monthly payment(s)
               dblMonthPay = (dblAmountLoan * (dblYearRateInterest
                  / 12) )
                  /  ( 1 - Math.pow( (1 + (dblYearRateInterest
                   / 12)), (double) (intYearsLoanLength * -12) ) );

               //Print out the monthly payment
               System.out.println( "\nYour monthly payment is $"
                  + Round( dblMonthPay, 2 ) );
               System.out.println( "" );  //Blank line

               //Print out the table title
               System.out.println( "                       Loan Ammo"
                  + "rization Table" );

               //Use a loop to print up the divider
               for( int i = 1; i <= 70; i++ )
                    System.out.print( "=" );

               System.out.print( "\n" );  //Go to the next line

               //Print up the table column headers
               System.out.println( " Payment | Interest |  Accum.  |"
                  + " Principal |  Accum.   | Balance Due" );
               System.out.println( "         | Payment  | Interest |"
                  + "  Payment  | Principal |" );
 
               //Use a loop to print up the small divider
               for( int i = 1; i <= 70; i++ )
                    System.out.print( "-" );

               System.out.print( "\n" );  //Go to next line

               //Set the initial balance due
               dblBalanceDue = dblAmountLoan;

               //Loop through and print up payments until balance is
               //completely paid off
               for( intPaymentNum = 1; intPaymentNum <=
                (intYearsLoanLength * 12); intPaymentNum++ ) {
                    //Do the calculations
                    //Interest payment = balance due * monthly rate
                    //of interest
                    dblInterestPay = dblBalanceDue
                       * (dblYearRateInterest / 12);
 
                    //Accumulated interest
                    dblAccumInterest = dblAccumInterest
                       + dblInterestPay;

                    //Principal payment = monthly payment
                    //- interest payemnt
                    dblPrincipalPay = dblMonthPay - dblInterestPay;

                    //Accumulated principal
                    dblAccumPrincipal = dblAccumPrincipal
                       + dblPrincipalPay;

                    //Balance due = balance due - principal payment
                    dblBalanceDue = dblBalanceDue - dblPrincipalPay;

                    //Give the good news of this payment
                    System.out.println( " # " + FormatNumber(
                     intPaymentNum, 5 ) + " | $" +  FixDecimal(
                      dblInterestPay, FormatNumber( Round(
                       dblInterestPay, 2 ), 4 ) ) + " | $"
                        + FixDecimal( dblAccumInterest, FormatNumber(
                         Round( dblAccumInterest, 2 ), 4 ) ) + " | $"
                          + FixDecimal( dblPrincipalPay,
                           FormatNumber( Round( dblPrincipalPay, 2 ),
                            5 ) ) + " | $" + FixDecimal(
                             dblAccumPrincipal, FormatNumber( Round(
                              dblAccumPrincipal, 2 ), 5 ) ) + " | $"
                               + FixDecimal( dblBalanceDue,
                                FormatNumber( Round( dblBalanceDue,
                                 2 ), 7 ) )  );  //End of table line
              }
          }  //End of infinite 'while' loop
     }  //End of method main

     //This method rounds a number to the specified number of
     //places in the decimal or in the whole number area
     //To set the place to round to a user enters what power of ten
     //to multiple the number by

     //Example:  If dblNumber = 5.53 and someone wanted to round it
     //to the 1st decimal number they would pass the value 1 for
     //intPlace (10^1 = 10, 10 * 5.53 = 55.3, 55.3 + 0.5 = 55.8, (int) 55.8 / 10^1 = 5.5)
     //If dblNumber = 2654 and they wanted to round it to the
     //hundreds place the would pass the value -2 for intPlace
     //(10^-2 = .01, .01 * 2654 = 26.54, 26.54 + 0.5 = 27.04, (int) 27.04 / 10^-2 = 2700)
     //A quick chart for this would be:
     //   Any number, the place to round to decides the power of ten
     //                          #########.##########
     //   Power of ten is --     876543210,12345678910
     //Where places on the left side of the decimal are a negative
     //value for the power and places on the right are postive values

     public static double Round( double dblNumber, int intPlace ) {
          int intCutOffNum = 0;  //Used to cutoff extra decimals

          //Align the placement where the rounding should occur
          dblNumber = dblNumber
             * Math.pow( (double) 10, (double) intPlace );

          //Determine if number is positive or negative
          if( dblNumber >= 0 ) {  //Number is postive
             //Round off decimal
             intCutOffNum = (int) (dblNumber + 0.5);
          }
          else {  //Number is negative
             //Round off decimal
             intCutOffNum = (int) (dblNumber - 0.5);
          }

          //Put decimal back into the proper place for the number
          dblNumber = (double) (intCutOffNum / Math.pow( (double) 10,
             (double) intPlace ));

          return dblNumber;  //Return the value
     }  //End of method Round

     //Declare a method that will be called to check to see if user
     //has given the QUIT command
     public static void CheckQuit( String strUserInput ) {
          //Compare the user's input with the QUIT command string
          if( strUserInput.compareTo( "QUIT" ) == 0 ) {
               //Call exit command on program using a normal end
               System.exit( 0 );
          }
     }  //End of CheckQuit method

     //This method takes a number and formats it so it fits in a
     //table cell correctly, the intDigits paramter tells how big a
     //number can be when it can be fitted into a cell, a 5-digit
     //number would have the value 5 passed to intDigits because
     //10^5 = 100000, and that value is used to determine the number
     //to start at and go down through for the number of spaces need
     //(i.e. since intDigits=5 the number 345 would have 2 spaces
     //put in front of it
     public static String FormatNumber( double dblNumber,
      int intDigits ) {
          int intCurDigit = 0;  //Current digit place being checked
          int intNumSpaces = 0;  //For the number of spaces to use

          String strNumber = "";  //For holding formatted number

          //Loop until the correct space is found
          do {
               intCurDigit++;

               if( dblNumber < (Math.pow( (double) 10,
                (double) intCurDigit )) )  //Found how many digits
                    break;  //Bust out of the loop
          } while( intCurDigit <= intDigits );

          //Store the number of spaces needed
          intNumSpaces = intDigits - intCurDigit;        

          //Format the string spaces
          for( int i = 0; i <= (intNumSpaces - 1); i++ )
               strNumber = strNumber + " ";

          //Add the number to the string
          strNumber = strNumber + dblNumber;

          return strNumber;  //Return the formatted string
     }  //End of FormatNumber method

     //This is the same method as above, but (int overloaded)
     public static String FormatNumber( int intNumber,
      int intDigits ) {
          int intCurDigit = 0;  //Current digit place being checked
          int intNumSpaces = 0;  //For the number of spaces to use

          String strNumber = "";  //For holding formatted number

          //Loop until the correct space is found
          do {
               intCurDigit++;

               if( intNumber < (Math.pow( (double) 10,
                (double) intCurDigit )) )  //Found how many digits
                    break;  //Bust out of the loop
          } while( intCurDigit <= intDigits );

          //Store the number of spaces needed
          intNumSpaces = intDigits - intCurDigit;        

          //Format the string spaces
          for( int i = 0; i <= (intNumSpaces - 1); i++ )
               strNumber = strNumber + " ";

          //Add the number to the string
          strNumber = strNumber + intNumber;

          return strNumber;  //Return the formatted string
     }  //End of FormatNumber (int overloaded) method

     //This method will add a '0' to the end of the string that
     //contains the formatted number incase it needs a extra one
     //(their should be two decimal places after this is done)
     public static String FixDecimal( double dblNumber,
      String strNumber ) {
          double dblConvertNum = 0.0;  //Used to convert decimal
          String strFormattedNum = "";  //For the formatted number

          //Get just the decimal part
          dblConvertNum = (double) (dblNumber - (int) dblNumber);

          //Use the Round method to clean up the end of the decimal
          dblConvertNum = Round( dblConvertNum, 2 );

          //Multiple by 100 so we can work with a integer
          dblConvertNum = dblConvertNum * 100;

          if( dblConvertNum == 0.0 )
               //No decimal digits at all, just add one to make two
               //from the extra '0' already their
               strFormattedNum = strNumber + "0";
          else if( dblConvertNum % 10 == 0 )
               //One decimal (non-zero) at end, add another
               strFormattedNum = strNumber + "0";
          else
               //Already two (non-zero) at end, nothing needed
               strFormattedNum = strNumber;

          return strFormattedNum;  //Return the formatted number
     }  //End of FixDecimal method

}  //End of Loan class