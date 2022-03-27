/* 
   Class	Lab 
 
   Purpose:	To compute a discount from a retail price. 
 
   Input:  	retail price(float) 
           	discount (float) 
            
   Compute: 	sale price 
*/ 
 
import javabook.*; 
 
class Lab 
{ 
 
/********************** 
   Data Members 
**********************/ 
       
	//declare data members private, 3 objects and 3 variables 
	 
	private MainWindow mainWindow; 
	private InputBox  inputBox; 
	private OutputBox outputBox; 
 
	private float    retail, discount, salePrice; 
    
/********************** 
   Constructors 
**********************/ 
 
   public Lab(String title) 
   { 
		//instantiate three objects 
		mainWindow = new MainWindow(title + " MainWindow object"); 
		inputBox    = new InputBox(mainWindow); 
		outputBox     = new OutputBox(mainWindow); 
		outputBox.setTitle(title + " OutputBox object"); 
   } 
 
 
  
 
/********************** 
   Public Methods: 
    
		void    computeSalePrice  (    ) 
		void    describeProgram   (    ) 
		void    displayOutput     (    ) 
		void    getInput          (    ) 
		void    showObjects       (    ) 
       
**********************/ 
 
   /*  Method:       computeSalePrice 
    
       Purpose:      Compute the sale Price from the given retail and discount 
 
       Parameters:   None 
                       
       Returns:      None 
   */ 
    
   public void computeSalePrice() 
   { 
	//write an arithmetic expression to calculate the private data member, salePrice 
	salePrice = retail - (retail * (discount / 100)); 
   } 
    
   /*  Method:       describeProgram 
    
       Purpose:      Provide a brief explaination of the program to the user. 
 
       Parameters:   None 
                       
       Returns:      None 
   */ 
    
   	public void describeProgram() 
   	{ 
      		//Print the purpose of the program using the OutputBox object and skip two 		//lines 
		outputBox.printLine("This program calculates the sale price for a" 
				+ " given retail and discount"); 
      		outputBox.skipLine(2); 
   } 
 
   /*  Method:       displayOutput 
    
       Purpose:      Display the input values and sale price 
 
       Parameters:   None 
                       
       Returns:      None 
   */ 
 
	public void displayOutput()  
	{ 
		//Using the OutputBox object, echo the user input, skip a line, and print 		//the calculated sale price 
		outputBox.printLine("Retail Price:          $" + retail); 
		outputBox.printLine("Discounted Amount:      " + discount + "%"); 
		outputBox.skipLine(1); 
		outputBox.printLine("Sale Price:            $" + salePrice); 
	}    
 
	/*  Method:       getInput 
	  
	     Purpose:      Get two input values--retail and discount amount--using an 				InputBox object 
 
	     Parameters:   None 
	                     
	     Returns:      None 
	*/ 
 
	public void getInput()  
	{ 
		//Using the InputBox object to get user input for the retail price  
		//	and the discounted amount 
		retail	= inputBox.getFloat("Enter Retail Amount   (Dollars&Cents):"); 
		discount	= inputBox.getFloat("Enter Discount Amount (e.g. 20):"); 
	} 
 
	public void showObjects() 
	{ 
		//call the show()method for the applicable objects 
		mainWindow.show(); 
		outputBox.show(); 
	} 
}//end class Lab
