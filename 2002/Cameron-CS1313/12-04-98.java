//=========================================================================
//  MoneyManager.java -- 12/04/98 -- Rodney Beede
//=========================================================================
//  Problem:  Write a program that uses a object which will allow the user
//            to:  1.  Compute the future value of an investment
//                 2.  Compute the monthly payment for a loan
//                 3.  Compute a loan amorization table
//  Input:  Present user with a menu to select the finicial action that
//          they want to do;  For the future value of an investment the
//          inputs are:  Rate of interest (in %, float), Amount of the
//          investment (float), Number of years for investment (int),
//          Number of times / year that compounding is done (int);
//          For the monthly payment for a loan the inputs are:  Amount of
//          loan (float), yearly rate of interest (float), Number of years
//          of loan (int);
//          For the loan amorization table the inputs are:  Amount of loan
//          (float), yearly rate of interest (float), Number of years of
//          loan (int);
//  Output:  Output data based on what user wanted to do (i.e. the future
//           value of the investment, the monthly payment for a loan, and a
//           loan amorization table
//  Method:  The future value of a investment formula to use is A = P
//           * ( 1 + r/n )^(n*t), A - future amount, P - amount of
//           investment, r - annual rate in %, n - number of times
//           compounded / year, t - num of years;  The monthly payment for
//           a loan formula is M = ( A * r ) / ( 1 - ( 1 + r )^(-t) ),
//           M = monthly payment, A = amount of loan, r = monthly rate of
//           interest, t = time in months
//  Notes:  User shouldn't have to restart program to compute again;  If
//          the user decides to stop doing what they are in the program
//          (they want to stop a computation) they can type in "QUIT" to
//          get out of it and back to the main menu;  The table for the
//          loan ammorization should format like this:
//
/*
                       Loan Amortization Table                      
=====================================================================
 Payment | Interest |  Accum.  | Principal |  Accum.   | Balance Due
         | Payment  | Interest |  Payment  | Principal |
---------------------------------------------------------------------
 # ????? | $????.?? | $????.?? | $?????.?? | $?????.?? | $???????.??

*/
//=========================================================================

//Tell compilier to include the Java IO package of the Java API
import java.io.*;

//This is the main class of the program
class MoneyManager {
     public static void main (String[] args) throws IOException {
          //Set up a input stream from standard input
          BufferedReader stdin = new BufferedReader
             (new InputStreamReader(System.in) );

          String strKeyInput;  //Keyboard input

          Finicial Money = new Finicial();  //Finicial object

          //Use a infinite loop to keep the program running forever unless
          //the user gives the exit command
          while( 1 == 1 ) {
               //Print out who is responsible for this program
               System.out.println( "\nRodney Beede -- MoneyManager --"
                  + "CS1313 -- 12/04/98" );

               System.out.println( "" );  //Print a blank line

               //Present the user with a menu of choices
               System.out.println( "Command Menu" );
               System.out.println( "1.  Compute and print the future value"
                  + " of an investment." );
               System.out.println( "2.  Compute and print the monthly"
                  + " payment for a loan." );
               System.out.println( "3.  Compute and print a loan"
                  + " amortization table." );
               System.out.println( "E.  Exit this program." );
               System.out.println( "" );  //Blank line
               System.out.print( "Type in the number or letter of the"
                  + " command you wish to issue: " );
               System.out.flush();  //Flush buffer
               strKeyInput = stdin.readLine();  //Read in user input

               //Check to see if user didn't enter anything
               if( strKeyInput.compareTo("") == 0 )
                    //Nothing entered, set a invalid value
                    strKeyInput = "BAD VALUE";

               //Figure out which command was issued
               switch ( strKeyInput.charAt(0) ) {
                    case '1':  //Future value of an investment
                         Money.Investment();  //Call investment method
                         break;
                    case '2':  //Monthly payment for a loan
                         Money.MonthlyPayment();  //Call MonthlyPay method
                         break;
                    case '3':  //Loan amortization table
                         Money.Loan();  //Call Loan method
                         break;
                    case 'E':  //Exit program
                         //Call exit command on program using a normal end
                         System.exit( 0 );
                         break;
                    default:  //Invalid command
                         //Tell user invalid command, menu will be printed
                         //over again
                         System.out.println( "" );  //Blank line

                         for( int i = 0; i < 70; i++ )  //Print a sep bar
                              System.out.print( "=" );

                         System.out.println( "\nInvalid command.  Try"
                            + " again." );

                         for( int i = 0; i < 70; i++ )  //Print a sep bar
                              System.out.print( "=" );

                         System.out.println( "" );  //Blank line
               }  //End of switch statement

          }  //End of infinite while loop

     }  //End of main method
}  //End of MoneyManager class


//This class is used to define a object that has the methods to do fincial
//things like calculate and print the future value of a investment,
//calculate and print a monthly payment on a loan, and calculate and print
//a loan amortization table
class Finicial {
     //Method for future value of a investment
     public void Investment() throws IOException {
          //Set up a input stream from standard input
          BufferedReader stdin = new BufferedReader
             (new InputStreamReader(System.in) );

          String strKeyInput;  //Keyboard input

          Float FltAmountInvest;  //Amount of investment
          Float FltRate;  //Rate of interest

          float fltFutValue;  //Future value of investment

          int intNumYears;  //Number of years for investment
          int intNumCompound;  //Number of times compounded per year
          int intRoundFutValue;  //Used to round future value

          //Initialize variables
          strKeyInput = "";

          FltAmountInvest = new Float( -1.0 );
          FltRate = new Float( -1.0 );

          fltFutValue = (float) -1.0;

          intNumYears = -1;
          intNumCompound = -1;
          intRoundFutValue = -1;

          //Give the user some instructions
          System.out.println( "\nFuture Value of a Investment" );
          System.out.println( "" );  //Blank line
          System.out.println( "Instructions:  Enter the data you are"
             + " prompted for." );
          System.out.println( "" );  //Blank Line
          System.out.println( "Valid values cannot contain any commas or"
             + " dollar signs." );
          System.out.println( "Enter the rate of interest in percent (i.e."
             + " for 5.5% you would" );
          System.out.println( "enter 5.5).  You may use decimals inside"
             + " the amount of the investment" );
          System.out.println( "and the rate of interest.  You may only use"
             + " integers for the number" );
          System.out.println( "of years of the investment and the number"
             + " of times compounded" );
          System.out.println( "per year." );
          System.out.println( "" );  //Blank Line
          System.out.println( "To escape back to the main menu type in"
             + " QUIT at any prompt." );
          System.out.println( "" );  //Blank line

          //Loop until user gives valid data for amount of investment
          while( FltAmountInvest.floatValue() < (float) 0.0 ) {
               //Ask for the amount of the investment
               System.out.print( "Enter the amount of the investment"
                  + " ==> " );

               System.out.flush();  //Flush buffer

               //Read user input from keyboard
               strKeyInput = stdin.readLine();

               //Check to see if the user wants to escape out
               if( strKeyInput.compareTo( "QUIT" ) == 0 )
                    return;  //Leave method

               //Convert user input into a Float object value
               FltAmountInvest = Float.valueOf( strKeyInput );

               //Check to see if user tried to enter a invalid value
               if( FltAmountInvest.floatValue() < (float) 0.0 )
                    //User entered invalid value, tell them
                    System.out.println( "Invalid data entered.  Please"
                       + " re-enter data." );
          }  //End of user input loop   

          //Loop until user gives valid data for rate of interest
          while( FltRate.floatValue() < (float) 0.0 ) {
               //Ask for the rate of interest
               System.out.print( "Enter the rate of interest"
                  + " ==> " );

               System.out.flush();  //Flush Buffer

               //Read user input from keyboard
               strKeyInput = stdin.readLine();

               //Check to see if the user wants to escape out
               if( strKeyInput.compareTo( "QUIT" ) == 0 )
                    return;  //Leave method

               //Convert user input into a Float object value
               FltRate = Float.valueOf( strKeyInput );

               //Convert percentage into a decimal
               FltRate = new Float( FltRate.floatValue() / (float) 100 );

               //Check to see if user tried to enter a invalid value
               if( FltRate.floatValue() < (float) 0.0 )
                    //User entered invalid value, tell them
                    System.out.println( "Invalid data entered.  Please"
                       + " re-enter data." );
          }  //End of user input loop

          //Loop until user gives valid data for the number of years for
          //the investment
          while( intNumYears < 0 ) {
               //Ask for the number of years
               System.out.print( "Enter the number of years"
                  + " ==> " );

               System.out.flush();  //Flush Buffer

               //Read user input from keyboard
               strKeyInput = stdin.readLine();

               //Check to see if the user wants to escape out
               if( strKeyInput.compareTo( "QUIT" ) == 0 )
                    return;  //Leave method

               //Convert user input into a integer value
               intNumYears = Integer.parseInt( strKeyInput ); 

               //Check to see if user tried to enter a invalid value
               if( intNumYears < 0 )
                    //User entered invalid value, tell them
                    System.out.println( "Invalid data entered.  Please"
                       + " re-enter data." );
          }  //End of user input loop

          //Loop until user gives valid data for number of times to
          //compound yearly
          while( intNumCompound < 0 ) {
               //Ask for the number of times to compound
               System.out.print( "Enter the number of times compounded per"
                  + " year ==> " );

               System.out.flush();  //Flush Buffer

               //Read user input from keyboard
               strKeyInput = stdin.readLine();

               //Check to see if the user wants to escape out
               if( strKeyInput.compareTo( "QUIT" ) == 0 )
                    return;  //Leave method

               //Convert user input into a integer value
               intNumCompound = Integer.parseInt( strKeyInput ); 

               //Check to see if user tried to enter a invalid value
               if( intNumCompound < 0 )
                    //User entered invalid value, tell them
                    System.out.println( "Invalid data entered.  Please"
                       + " re-enter data." );
          }  //End of user input loop

          //Calculate the future value
          fltFutValue = (float) (FltAmountInvest.floatValue()
             * Math.pow( (double) ( 1 + FltRate.floatValue()
             / (float) intNumCompound ), (double) (intNumCompound
             * intNumYears ) ) );

          //Round the future value to two decimal places
          fltFutValue = (float) Round( (double) fltFutValue, 2 );

          //Format display and get ready to print result
          System.out.println( "\n\n" );  //3 blank spaces

          //Print out a seperator
          for( int i = 0; i < 70; i++ )
               System.out.print( "=" );

          System.out.flush();  //Flush out buffer

          //Print out the future value
          System.out.println( "\nThe future value of the investment is: $"
               + FixDecimal( (double) fltFutValue, "" + Round(
               (double) fltFutValue, 2 ) ) );

          //Print out a seperator
          for( int i = 0; i < 70; i++ )
               System.out.print( "=" );

          System.out.flush();  //Flush out buffer

          System.out.println( "\n" );  //Print out more blank lines
     }  //End of Investment method


     //This method calculates a montly payment on a loan and will ask the
     //user for the input
     public void MonthlyPayment() throws IOException {
          //Set up a input stream from standard input
          BufferedReader stdin = new BufferedReader
             (new InputStreamReader(System.in) );

          String strKeyInput = "";  //Holds input from keyboard
          String strFormatPay = "";  //Formatted output for monthly payment

          double dblAmountLoan = -1.0;  //Amount of loan
          double dblYearRateInterest = -1.0;  //Yearly rate of interest
          double dblMonthPay = -1.0;  //Monthly payment

          int intYearsLoanLength = -1;  //Number of years of loan

          //Print out the directions for the program
          System.out.println( "\nCompute and Print the Monthly Payment for"
             + " a Loan" );
          System.out.println( "" );  //Blank line
          System.out.println( "Instructions:  Enter the data you are"
             + " prompted for." );
          System.out.println( "" );  //Blank line
          System.out.println( "Valid data cannot contain any commas or"
             + " dollar signs." );
          System.out.println( "You may enter decimals for the amount of"
             + " the loan and for the yearly" );
          System.out.println( "rate of interest.  You may only enter a"
             + " integer value for the number" );
          System.out.println( "of years of the loan.  When entering a"
             + " percent do not use a percent" );
          System.out.println( "(%) sign.  Example:  For 5.5% you would"
             + " type in 5.5 and press enter." );
          System.out.println( "" );  //Blank line
          System.out.println( "To escape back to the main menu type in"
             + " QUIT at any prompt." );
          System.out.println( "" );  //Blank line

          //Loop until the user enters valid data or quits               
          while ( dblAmountLoan < 0.0 ) {
               //Ask for the amount of the loan
               System.out.print( "Enter the amount of the loan ==> " );

               //Flush buffer so message shows
               System.out.flush();

               //Read in the amount of the loan
               strKeyInput = stdin.readLine();

               //Check to see if the user wants to escape out
               if( strKeyInput.compareTo( "QUIT" ) == 0 )
                    return;  //Leave method

               //Convert input into a double
               dblAmountLoan = new Float( strKeyInput ).doubleValue();

               //Check to see if user entered invalid data so program can
               //tell them they did if they did
               if( dblAmountLoan < 0.0 )  //Invalid data
                    //Tell user their error
                    System.out.println( "Invalid data entered.  Please"
                       + " re-enter data." );
          }  //End of user input loop

          //Loop until the user enters valid data or quits               
          while ( dblYearRateInterest < 0.0 ) {
               //Ask for the amount of interest per year
               System.out.print( "Enter the amount of interest per year"
                  + " ==> " );

               //Flush buffer so message shows
               System.out.flush();

               //Read in the amount of interest per year
               strKeyInput = stdin.readLine();

               //Check to see if the user wants to escape out
               if( strKeyInput.compareTo( "QUIT" ) == 0 )
                    return;  //Leave method

               //Convert input into a double 
               dblYearRateInterest =
                    new Float( strKeyInput ).doubleValue();

               //Convert from a fraction to a decimal value and use round
               //function to trim off extra data that seems to be added by
               //using the doubleValue call and making input different
               dblYearRateInterest = Round( (double) (dblYearRateInterest
                  / 100), 5 );

               //Check to see if user entered invalid data so program can
               //tell them they did if they did
               if( dblYearRateInterest < 0.0 )  //Invalid data
                    //Tell user their error
                    System.out.println( "Invalid data entered.  Please"
                       + " re-enter data." );
          }  //End of user input loop

          //Loop until the user enters valid data or quits               
          while ( intYearsLoanLength < 0 ) {
               //Ask for the length (in years) of loan
               System.out.print( "Enter the number of years for the loan"
                  + " ==> " );

               //Flush buffer so message shows
               System.out.flush();

               //Read in the number of years
               strKeyInput = stdin.readLine();

               //Check to see if the user wants to escape out
               if( strKeyInput.compareTo( "QUIT" ) == 0 )
                    return;  //Leave method

               //Convert input into a integer
               intYearsLoanLength = new Integer( strKeyInput ).intValue();

               //Check to see if user entered invalid data so program can
               //tell them they did if they did
               if( intYearsLoanLength < 0 )  //Invalid data
                    //Tell user their error
                    System.out.println( "Invalid data entered.  Please"
                       + " re-enter data." );
          }  //End of user input loop

          //Call method to calculate monthly payment
          dblMonthPay = MonthlyPayment( dblAmountLoan, dblYearRateInterest,
             intYearsLoanLength );

          //Print out the monthly payment
          System.out.println( "\nYour monthly payment is $"
             + FixDecimal( dblMonthPay, "" + Round( dblMonthPay, 2 ) ) );

          System.out.println( "" );  //Blank line
     }  //End of MonthlyPayment method


     //This method calculates and prints a loan amortization table
     public void Loan() throws IOException {
          //Set up a input stream from standard input
          BufferedReader stdin = new BufferedReader
             (new InputStreamReader(System.in) );

          String strKeyInput;  //Keyboard input

          //Declare variables used for table
          int intPaymentNum;  //Payment number
          double dblInterestPay;  //Interest payment
          double dblAccumInterest;  //Amount of accumulated interest
          double dblPrincipalPay;  //Principal payment
          double dblAccumPrincipal;  //Amount of accumulated principal
          double dblBalanceDue;  //Balance due on loan

          //Declare variables used to store user input
          double dblAmountLoan;  //Amount of loan
          double dblYearRateInterest;  //Yearly rate of interest
          int intYearsLoanLength;  //Number of years of loan
 
          //Declare other variables
          double dblMonthPay;  //Monthly payment

          //Fill in values that indicate nothing has been entered in yet
          //for variables that hold user input
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

          //Print out the directions for the program
          System.out.println( "\nCompute a Loan Amortization Table" );
          System.out.println( "" );  //Blank line
          System.out.println( "Instructions:  Enter the data you are"
             + " prompted for." );
          System.out.println( "" );  //Blank line
          System.out.println( "Valid data cannot contain any commas"
             + " or dollar signs." );
          System.out.println( "You may enter decimals for the amount"
             + " of the loan and for the yearly" );
          System.out.println( "rate of interest.  You may only enter a"
             + " integer value for the number" );
          System.out.println( "of years of the loan.  When entering a"
             + " percent do not use a percent" );
          System.out.println( "(%) sign.  Example:  For 5.5% you would"
             + " type in 5.5 and press enter." );
          System.out.println( "" );  //Blank line
          System.out.println( "To escape back to the main menu type in"
             + " QUIT at any prompt." );
          System.out.println( "" );  //Blank line

          //Loop until the user enters valid data or quits               
          while ( dblAmountLoan < 0.0 ) {
               //Ask for the amount of the loan
               System.out.print( "Enter the amount of the loan ==> " );

               //Flush buffer so message shows
               System.out.flush();

               //Read in the amount of the loan
               strKeyInput = stdin.readLine();

               //Check to see if the user wants to escape out
               if( strKeyInput.compareTo( "QUIT" ) == 0 )
                    return;  //Leave method

               //Convert input into a double
               dblAmountLoan = new Float( strKeyInput ).doubleValue();

               //Check to see if user entered invalid data so program can
               //tell them they did if they did
               if( dblAmountLoan < 0.0 )  //Invalid data
                    //Tell user their error
                    System.out.println( "Invalid data entered.  Please"
                       + " re-enter data." );
          }  //End of user input loop

          //Loop until the user enters valid data or quits               
          while ( dblYearRateInterest < 0.0 ) {
               //Ask for the amount of interest per year
               System.out.print( "Enter the amount of interest per year"
                  + " ==> " );

               //Flush buffer so message shows
               System.out.flush();

               //Read in the amount of interest per year
               strKeyInput = stdin.readLine();

               //Check to see if the user wants to escape out
               if( strKeyInput.compareTo( "QUIT" ) == 0 )
                    return;  //Leave method

               //Convert input into a double 
               dblYearRateInterest =
                  new Float( strKeyInput ).doubleValue();

               //Convert from a fraction to a decimal value and use round
               //function to trim off extra data that seems to be added by
               //using the doubleValue call and making input different
               dblYearRateInterest = Round( (double) (dblYearRateInterest
                  / 100), 5 );

               //Check to see if user entered invalid data so program can
               //tell them they did if they did
               if( dblYearRateInterest < 0.0 )  //Invalid data
                    //Tell user their error
                    System.out.println( "Invalid data entered.  Please"
                       + " re-enter data." );
          }  //End of user input loop

          //Loop until the user enters valid data or quits               
          while ( intYearsLoanLength < 0 ) {
               //Ask for the length (in years) of loan
               System.out.print( "Enter the number of years for the loan"
                  + " ==> " );

               //Flush buffer so message shows
               System.out.flush();

               //Read in the number of years
               strKeyInput = stdin.readLine();

               //Check to see if the user wants to escape out
               if( strKeyInput.compareTo( "QUIT" ) == 0 )
                    return;  //Leave method

               //Convert input into a integer
               intYearsLoanLength = new Integer( strKeyInput ).intValue();

               //Check to see if user entered invalid data so program can
               //tell them they did if they did
               if( intYearsLoanLength < 0 )  //Invalid data
                    //Tell user their error
                    System.out.println( "Invalid data entered.  Please"
                       + " re-enter data." );
          }  //End of user input loop

          //Call method to calculate monthly payment
          dblMonthPay = MonthlyPayment( dblAmountLoan, dblYearRateInterest,
             intYearsLoanLength );

          //Print out the monthly payment
          System.out.println( "\nYour monthly payment is $"
             + Round( dblMonthPay, 2 ) );
          System.out.println( "" );  //Blank line

          //Print out the table title
          for( int i = 0; i < 24; i++ )  //Print spaces to center title
               System.out.print( " " );
          System.out.println( "Loan Amortization Table" );

          //Use a loop to print up the divider
          for( int i = 1; i <= 70; i++ )
               System.out.print( "=" );

          System.out.print( "\n" );  //Go to the next line

          //Print up the table column headers
          System.out.println( " Payment | Interest |  Accum.  | Principal"
             + " |  Accum.   | Balance Due" );
          System.out.println( "         | Payment  | Interest |  Payment"
             + "  | Principal |" );
 
          //Use a loop to print up the small divider
          for( int i = 1; i <= 70; i++ )
               System.out.print( "-" );

          System.out.print( "\n" );  //Go to next line

          dblBalanceDue = dblAmountLoan;  //Set the initial balance due

          //Loop through and print up payments until balance is completely
          //paid off
          for( intPaymentNum = 1; intPaymentNum
             <= (intYearsLoanLength * 12); intPaymentNum++ ) {
               //Do the calculations
               //Interest payment = balance due * monthly rate of interest
               dblInterestPay = dblBalanceDue * (dblYearRateInterest / 12);
 
               //Accumulated interest
               dblAccumInterest = dblAccumInterest + dblInterestPay;

               //Principal payment = monthly payment - interest payemnt
               dblPrincipalPay = dblMonthPay - dblInterestPay;

               //Accumulated principal
               dblAccumPrincipal = dblAccumPrincipal + dblPrincipalPay;

               //Balance due = balance due - principal payment
               dblBalanceDue = dblBalanceDue - dblPrincipalPay;

               //Give the good news of this payment
               System.out.println( " # " + FormatNumber( intPaymentNum, 5 )
                  + " | $" +  FixDecimal( dblInterestPay, FormatNumber(
                  Round( dblInterestPay, 2 ), 4 ) ) + " | $" + FixDecimal(
                  dblAccumInterest, FormatNumber( Round( dblAccumInterest,
                  2 ), 4 ) ) + " | $" + FixDecimal( dblPrincipalPay,
                  FormatNumber( Round( dblPrincipalPay, 2 ), 5 ) ) + " | $"
                  + FixDecimal( dblAccumPrincipal, FormatNumber( Round(
                  dblAccumPrincipal, 2 ), 5 ) ) + " | $" + FixDecimal(
                  dblBalanceDue, FormatNumber( Round( dblBalanceDue, 2 ),
                  7 ) )  );
          }  //End of for loop

          //Print some extra lines
          System.out.println( "\n\n" );

     }  //End of Loan method


     //This is a overloaded method that actually calculates and just
     //returns a monthly payment on a loan
     private static double MonthlyPayment( double dblAmountLoan,
        double dblYearRateInterest, int intYearsLoanLength ) {

          //Calculate and return the monthly payment
          return (dblAmountLoan * (dblYearRateInterest / 12) )
             /  ( 1 - Math.pow( (1 + (dblYearRateInterest / 12)),
             (double) (intYearsLoanLength * -12) ) );
     }  //End of MonthlyPayment (overloaded) method


     //This method rounds a number to the specified number of places in the
     //decimal or in the whole number area
     //To set the digit place to round to the method user a power of ten to
     //multiple the number by

     //Example:  If dblNumber = 5.53 and someone wanted to round it to the
     //1st decimal number they would pass the value 1 for intPlace
     //(10^1 = 10, 10 * 5.53 = 55.3, 55.3 + 0.5 = 55.8, (int) 55.8 / 10^1
     //= 5.5)
     //If dblNumber = 2654 and they wanted to round it to the hundreds
     //place the would pass the value -2 for intPlace (10^-2 = .01, .01
     //* 2654 = 26.54, 26.54 + 0.5 = 27.04, (int) 27.04 / 10^-2 = 2700)

     //A quick chart for this would be:
     //   Any number, the place to round to decides the power of ten
     //                          ##########.#########
     //   Power of ten is --     9876543210,123456789
     //Where places on the left side of the decimal are a negative value
     //for the power and places on the right are postive values

     public static double Round( double dblNumber, int intPlace ) {
          int intCutOffNum = 0;  //Used to cutoff extra decimals

          //Align the placement where the rounding should occur
          dblNumber = dblNumber * Math.pow( (double) 10,
             (double) intPlace );

          //Determine if number is positive or negative
          if( dblNumber >= 0 )  //Number is postive
             intCutOffNum = (int) (dblNumber + 0.5);  //Round off decimal
          else  //Number is negative
             intCutOffNum = (int) (dblNumber - 0.5);  //Round off decimal

          //Put decimal back into the proper place for the number
          dblNumber = (double) (intCutOffNum / Math.pow( (double) 10,
             (double) intPlace ));

          return dblNumber;  //Return the value
     }  //End of method Round


     //This method will add a '0' to the end of the string that
     //contains the formatted number incase it needs a extra one
     //(their should be two decimal places after this is done)
     public static String FixDecimal( double dblNumber,
        String strNumber ) {
          double dblConvertNum = 0.0;  //Used to convert decimal number
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


     //This method takes a number and formats it so it fits in a table cell
     //correctly, the intDigits paramter tells how big a number can be when
     //it can be fitted into a cell, a 5-digit number would have the value
     //5 passed to intDigits because 10^5 = 100000, and that value is used
     //to determine the number to start at and go down through for the
     //number of spaces need (i.e. since intDigits=5 the number 345 would
     //have 2 spaces put in front of it
     public static String FormatNumber( double dblNumber, int intDigits ) {
          int intCurDigit = 0;  //For current digit place being checked
          int intNumSpaces = 0;  //For the number of spaces to use

          String strNumber = "";  //For holding formatted number

          //Loop until the correct space is found
          do {
               intCurDigit++;

               if( dblNumber < (Math.pow( (double) 10,
                  (double) intCurDigit )) )
                    //Found how many digits
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
     public static String FormatNumber( int intNumber, int intDigits ) {
          int intCurDigit = 0;  //For current digit place being checked
          int intNumSpaces = 0;  //For the number of spaces to use

          String strNumber = "";  //For holding formatted number

          //Loop until the correct space is found
          do {
               intCurDigit++;

               if( intNumber < (Math.pow( (double) 10,
                  (double) intCurDigit )) )
                    //Found how many digits
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
}  //End of Finicial class