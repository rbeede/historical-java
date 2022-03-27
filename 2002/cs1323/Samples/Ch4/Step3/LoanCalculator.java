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

/**********************
   Data Members
**********************/

   private final int MONTHS_IN_YEAR = 12;
      
   private MainWindow mainWindow;
   private InputBox  inputBox;
   private OutputBox outputBox;

   private float    loanAmount,
                    annualInterestRate,
                    monthlyInterestRate;

   private double  monthlyPayment,
                   totalPayment;

   private int   loanPeriod,              // loan period in years
                 numberOfPayments;

/**********************
   Constructors
**********************/

   public LoanCalculator()
   {
      mainWindow = new MainWindow("L O A N  C A L C U L A T O R");
      inputBox    = new InputBox(mainWindow);
      outputBox     = new OutputBox(mainWindow);
   }

/**********************
   Public Methods:
   
      void    start   (    )
      
**********************/

   /*  Method:       start
   
       Purpose:      Top level method that calls other provate methods
                     to compute the monthly and total loan payments

       Parameters:   None
                      
       Returns:      None
   */
   
   public void start()
   {
      mainWindow.show();
      outputBox.show();
      describeProgram();   //tell waht the program does
      getInput();          //get three input values
      computePayment();    //compute the monthly payment and total
      displayOutput();     //diaply the results
   }

/**********************
   Private Methods:
   
      void    computePayment    (    )
      void    describeProgram   (    )
      void    displayOutput     (    )
      void    getInputs         (    )
      
**********************/

   /*  Method:       computePayment
   
       Purpose:      Compute the monthly and total loan payments.

       Parameters:   None
                      
       Returns:      None
   */
   
   private void computePayment()
   {
      monthlyPayment = 132.15;   //TEMP
      totalPayment = 15858.10;   //TEMP
   }
   
   /*  Method:       describeProgram
   
       Purpose:      Provide a brief explaination of the program to the user.

       Parameters:   None
                      
       Returns:      None
   */
   
   private void describeProgram()
   {
      outputBox.printLine("inside describeProgram");   //TEMP
   }

   /*  Method:       displayOutput
   
       Purpose:      Display the input values and monthly and total payments

       Parameters:   None
                      
       Returns:      None
   */

   private void displayOutput() 
   {
      outputBox.printLine("For");
      outputBox.printLine("Loan Amount:          $" + loanAmount);
      outputBox.printLine("Annual Interest Rate:  " + annualInterestRate + "%");
      outputBox.printLine("Loan Period (years):   " + loanPeriod);
      outputBox.skipLine(1);

      outputBox.printLine("Monthly payment is    $ " + monthlyPayment);
      outputBox.printLine("  TOTAL payment is    $ " + totalPayment);
   }   

   /*  Method:       getInput
   
       Purpose:      Get three input values--loan amount, interest rate, and
                     loan period--using an InputBox object

       Parameters:   None
                      
       Returns:      None
   */

   private void getInput() 
   {
      loanAmount          = inputBox.getFloat("Loan Amount (Dollars&Cents):");
      annualInterestRate  = inputBox.getFloat("Annual Interest Rate (e.g. 9.5):");
      loanPeriod          = inputBox.getInteger("Loan Period - # of years:");
   }

}
