/*
   Program LoanCalculator (Step 3)

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

      float    loanAmount,
               annualInterestRate;

      int      loanPeriod;

      double   monthlyPayment,
               totalPayment;

      //describe the program
      outputBox.printLine("This program computes the monthly and total");
      outputBox.printLine("payments for a given loan amount, annual ");
      outputBox.printLine("interest rate, and loan period.");
      outputBox.printLine("Loan amount in dollars and cents, e.g. 12345.50");
      outputBox.printLine("Annual interest rate in percentage, e.g. 12.75");
      outputBox.printLine("Loan period in number of years, e.g. 15");
      outputBox.skipLine(2);

      //get input         
      loanAmount          = inputBox.getFloat("Loan Amount (Dollars+Cents):");
      annualInterestRate  = inputBox.getFloat("Annual Interest Rate (e.g. 9.5):");
      loanPeriod          = inputBox.getInteger("Loan Period - # of years:");

      //compute the monthly payment
      monthlyPayment = 132.15;
      totalPayment  =  15858.10;

      //display the result
      outputBox.printLine("For");
      outputBox.printLine("Loan Amount:          $" + loanAmount);
      outputBox.printLine("Annual Interest Rate:  " + annualInterestRate + "%");
      outputBox.printLine("Loan Period (years):   " + loanPeriod);
      outputBox.skipLine(1);

      outputBox.printLine("Monthly payment is    $ " + monthlyPayment);
      outputBox.printLine("  TOTAL payment is    $ " + totalPayment);

   }

}
