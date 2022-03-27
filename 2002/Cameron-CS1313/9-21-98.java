//===================================================================
//  Quadratic.java -- 9/21/98 -- Rodney Beede
//===================================================================
//  Problem:  Solve A * x² + B * x + C = 0
//  Input:  Three integers, A, B, & C; the coefficents
//  Output:  Value of x to screen; x is the solution to a quadratic
//           equation
//  Method:  Use quadratic forumula to solve for x
//           x = ( -b ± "SQUARE ROOT( b² - 4 * a * c )" ) ÷ 2 * a
//  Notes:  If the discriminant ( b² - 4 * a * c ) is equal to a
//          negative number have program output "COMPLEX SOLUTION"
//          If the discriminant = 0 then only show the one repeated
//          real solution
//===================================================================

//Load Input/Output java package to allow input from the keyboard
import java.io.*;

//The square root function is automatically loaded
//from the java.lang package in the .Math class

class Quadratic {

      public static void main (String[] args) throws IOException {

             //Create a object to help with I/O activities
             BufferedReader stdin = new BufferedReader
                    (new InputStreamReader(System.in));

             int intCoeffA;  //Coefficent A
             int intCoeffB;  //Coefficent B
             int intCoeffC;  //Coefficent C

             double dblDiscriminant;  //Discriminant (b² - 4 * a * c)
             double dblSolutionX1;  //First solution
             double dblSolutionX2;  //Second possible solution
             
             //Print out my name to make this easier to grade
             System.out.println ( "Rodney Beede" );

             //Show the user the standard form of a
             //quadratic equation
             System.out.print ( "Standard form of a quadratic"
                    + " equation is:\n"
                    + " A * x² + B * x + C = 0\n"
                    + " A cannot be equal to zero\n\n" );

                    System.out.println ( "Let A, B, & C represent"
                           + " the coefficents.\n" );

             //Prompt the user to enter the first coefficent
             System.out.print ( "Enter the coefficent for A:  " );

             //Force any data in buffer to go to screen
             System.out.flush ();

             //Read in a string and convert it to a number
             intCoeffA = Integer.parseInt ( stdin.readLine() );

             //Check to see if use entered zero in for coefficent A
             if( intCoeffA == 0 ) {
                    //User did, tell user why program cannot go on
                    System.out.println ( "You entered the number 0"
                           + " for coefficent A.\nThat does not make"
                           + " a quadratic equation." );

                    //Terminate the program now
                    return;
             }

             //Prompt the user to enter the second coefficent
             System.out.print ( "Enter the coefficent for B:  " );

             //Force any data in buffer to go to screen
             System.out.flush ();

             //Read in a string and convert it to a number
             intCoeffB = Integer.parseInt ( stdin.readLine() );

             //Prompt the user to enter the third coefficent
             System.out.print ( "Enter the coefficent for C:  " );

             //Force any data in buffer to go to screen
             System.out.flush ();

             //Read in a string and convert it to a number
             intCoeffC = Integer.parseInt ( stdin.readLine() );

             //Calculate the discriminate so it can be checked
             dblDiscriminant = Math.pow ( (double) intCoeffB,
                    (double) 2 ) - (4 * intCoeffA * intCoeffC );

		//Check the discriminate to see if it is less than zero
             if ( dblDiscriminant < 0 ) {
                    //It is, tell user it is a complex solution
                    System.out.println ( "COMPLEX SOLUTION" );
             }
             else if ( dblDiscriminant == 0 ) {
                    //Their can be only one repeated real solution

                    //Solve the equation for the one value, force a
                    //double type conversion so answer isn't rounded
                    dblSolutionX1 = -(intCoeffB) / (double)
                           (2 * intCoeffA);

                    //Print out the result
                    System.out.println ( "The solution is x = "
                           + dblSolutionX1 );
             }
		else if ( dblDiscriminant > 0 ) {
                    //Their are two real solutions

                    //Solve the equation for the first one, force a
                    //double type conversion so answer isn't rounded
                    dblSolutionX1 = ( -(intCoeffB)
                           + Math.sqrt(dblDiscriminant) )
                           / (double) (2 * intCoeffA);

                    //Solve the equation for the second one, force a
                    //double type conversion so answer isn't rounded
                    dblSolutionX2 = ( -(intCoeffB)
                           - Math.sqrt(dblDiscriminant) )
                           / (double) (2 * intCoeffA);

                    //Print out the results
                    System.out.println ( "The solutions are x = "
                           + dblSolutionX1 );
                    System.out.println ( "or x = " + dblSolutionX2 );
             }  //Else-if statement close

      }  //Method main

}  //Class Quadratic
