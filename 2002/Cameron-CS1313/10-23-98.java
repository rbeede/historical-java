//===================================================================
//  Interest.java -- 10/23/98 -- Rodney Beede
//===================================================================
//  Problem:  Compute the compound of interest on a investment
//  Input:  Rate of interest (in %, float); Amount of the investment
//          (float); Number of years for investment (int); Number of
//          times / year that compounding is done (int)
//  Output:  Future value of investment (float)
//  Method:  Formula to use is A = P ( 1 + r/n )^(n*t), A - future
//           amount, P - amount of investment, r - annual rate in %,
//           n - number of times compounded / year, t - num of years
//
//  Notes:  User shouldn't have to restart program to compute again
//          For a sentinel value to end the program the user can type
//          in 'quit';  To round the decimal two places use:
//          int = float * 100 + 0.5, then float = int / 100
//
//===================================================================

//Tell compilier to include the Java IO package of the Java API
import java.io.*;

class Interest {
     public static void main (String[] args) throws IOException {
          //Set up a text input stream from standard input
          BufferedReader stdin = new BufferedReader
             (new InputStreamReader(System.in) );

          Float FltAmountInvest;  //Amount of investment
          Float FltRate;  //Rate of interest

          float fltFutValue;  //Future value of investment

          int intNumYears;  //Number of years for investment
          int intNumCompound;  //Number of times compounded per year
          int intRoundFutValue;  //Used to round future value
          int intCount;  //Used as a counter to print out seperator

          String strKeyInput;  //Place for info read from keyboard

          //Use a infinite loop to keep program constantly running
          //The user makes the program quit by type in the word quit
          while( 1 == 1 ) {
               //Fill in default values for variables and clear out
               //any old data from last loop
               FltAmountInvest = new Float( -1.0 );
               FltRate = new Float( -1.0 );

               fltFutValue = (float) -1.0;

               intNumYears = -1;
               intNumCompound = -1;

               strKeyInput = "";

               //Print out who is responsible for this program
               System.out.println( "Rodney Beede -- Interest -- " 
                  + "CS1313 -- 10/23/98" );

               System.out.println( "" );  //Print a blank line

               //Tell the user what type of entry is valid
               System.out.println( "Instructions:  Enter the data "
                  + "you are prompted for." );
               System.out.println( "" );  //Blank Line
               System.out.println( "Valid values cannot contain any "
                  + "commas or dollar signs." );
               System.out.println( "Enter the rate of interest in "
                  + "percent (i.e. for 5.5% you would" );
               System.out.println( "enter 5.5).  You may use "
                  + "decimals inside the amount of the investment" );
               System.out.println( "and the rate of interest.  You "
                  + "may only use integers for the number" );
               System.out.println( "of years of the investment and "
                  + "the number of times compounded" );
               System.out.println( "per year." );
               System.out.println( "" );  //Blank Line
               System.out.println( "To terminate the program at any "
                  + "time type in QUIT." );

               //Loop until user gives valid data for
               //amount of investment
               while( FltAmountInvest.floatValue() < (float) 0.0 ) {
                    //Ask for the amount of the investment
                    System.out.print( "Enter the amount of the "
                       + "investment ==> " );

                    //Read user input from keyboard
                    strKeyInput = stdin.readLine();

                    //Check to see if the user wants to end
                    CheckQuit( strKeyInput );

                    //Convert user input into a Float object value
                    FltAmountInvest = Float.valueOf( strKeyInput );

                    //Check to see if user tried to enter a invalid
                    //value
                    if( FltAmountInvest.floatValue() < (float) 0.0 ) {
                         //User entered invalid value, tell them
                         System.out.println( "Invalid data entered. "
                            + " Please re-enter data." );
                    }
               }  //End of user input loop   

               //Loop until user gives valid data for
               //rate of interest
               while( FltRate.floatValue() < (float) 0.0 ) {
                    //Ask for the rate of interest
                    System.out.print( "Enter the rate of interest "
                       + "==> " );

                    //Read user input from keyboard
                    strKeyInput = stdin.readLine();

                    //Check to see if the user wants to end
                    CheckQuit( strKeyInput );

                    //Convert user input into a Float object value
                    FltRate = Float.valueOf( strKeyInput );

                    //Convert percentage into a decimal
                    FltRate = new Float( FltRate.floatValue() / (float) 100 );

                    //Check to see if user tried to enter a invalid
                    //value
                    if( FltRate.floatValue() < (float) 0.0 ) {
                         //User entered invalid value, tell them
                         System.out.println( "Invalid data entered. "
                            + " Please re-enter data." );
                    }
               }  //End of user input loop

               //Loop until user gives valid data for
               //the number of years for the investment
               while( intNumYears < 0 ) {
                    //Ask for the number of years
                    System.out.print( "Enter the number of years "
                       + "==> " );

                    //Read user input from keyboard
                    strKeyInput = stdin.readLine();

                    //Check to see if the user wants to end
                    CheckQuit( strKeyInput );

                    //Convert user input into a integer value
                    intNumYears = Integer.parseInt( strKeyInput ); 

                    //Check to see if user tried to enter a invalid
                    //value
                    if( intNumYears < 0 ) {
                         //User entered invalid value, tell them
                         System.out.println( "Invalid data entered. "
                            + " Please re-enter data." );
                    }
               }  //End of user input loop

               //Loop until user gives valid data for
               //number of times to compound yearly
               while( intNumCompound < 0 ) {
                    //Ask for the number of times to compound
                    System.out.print( "Enter the number of times "
                       + "compounded per year ==> " );

                    //Read user input from keyboard
                    strKeyInput = stdin.readLine();

                    //Check to see if the user wants to end
                    CheckQuit( strKeyInput );

                    //Convert user input into a integer value
                    intNumCompound = Integer.parseInt( strKeyInput ); 

                    //Check to see if user tried to enter a invalid
                    //value
                    if( intNumCompound < 0 ) {
                         //User entered invalid value, tell them
                         System.out.println( "Invalid data entered. "
                            + " Please re-enter data." );
                    }
               }  //End of user input loop

               //Calculate the future value
               fltFutValue = (float) (FltAmountInvest.floatValue() *
                  Math.pow( (double) ( 1 + FltRate.floatValue() /
                   (float) intNumCompound ), (double) (intNumCompound
                   * intNumYears ) ) );

               //Round the future value to two decimal places
               intRoundFutValue = (int) ((float) (fltFutValue *
                  100) + (float) 0.5);
               fltFutValue = (float) intRoundFutValue / (float) 100;

               //Format display and get ready to print result
               System.out.println( "\n\n" );  //3 blank spaces

               //Print out a seperator
               intCount = 0;
               while( intCount < 70 ) {
                    System.out.print( "=" );
                    intCount = intCount + 1;
               }
               System.out.flush();  //Flush out buffer

               //Print out the future value
               System.out.println( "\nThe future value of the "
                  + "investment is: $" + fltFutValue );

               //Print out another seperator
               intCount = 0;
               while( intCount < 70 ) {
                    System.out.print( "=" );
                    intCount = intCount + 1;
               }
               System.out.flush();  //Flush out buffer

               System.out.println( "\n\n" );  //Print out more blanks

          }  //End of infinite while loop
     }  //End of main method

     //Declare a method that will be called to check to see if user
     //has given the QUIT command
     public static void CheckQuit( String strUserInput ) {
          //Compare the user's input with the QUIT command string
          if( strUserInput.compareTo( "QUIT" ) == 0 ) {
               //Call exit command on program using a normal end
               System.exit( 0 );
          }
     }  //End of CheckQuit method
}  //End of class Interest