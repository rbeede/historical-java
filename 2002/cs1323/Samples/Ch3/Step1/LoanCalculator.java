/*
   Program LoanCalculator (Step 1)

   A program to compute the monthly payment for a given loan
   amount, annual interest rate, and number of years.

   Input:  loan amount (float)
           annual interest rate (float)
           loan period: number of years (int)

   Compute: monthly payment (float)
            total payment (float)
*/

import javabook.*;

class LoanCalculator
{

   public static void main (String args[])
   {
      
      MainWindow mainWindow = new MainWindow("Program LoanCalculator");
      InputBox  inputBox    = new InputBox(mainWindow);
      OutputBox outputBox     = new OutputBox(mainWindow);
      mainWindow.show();
      outputBox.show();

   }

}
