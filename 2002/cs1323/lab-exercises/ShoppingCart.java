/*
	class ShoppingCart

	The top level object for managing all other objects in the program	

*/

import javabook.*;

class ShoppingCart
{

/**********************
   Data Members
**********************/
      
   private MainWindow mainWindow;
   private InputBox  inputBox;
   private OutputBox outputBox;
   private MessageBox messageBox;
   private ListBox itemListBox, paymentListBox;

   private final float CANDLE_COST = (float)19.99;
   private final float TOOL_KIT_COST = (float)39.99;
   private final float PUZZLE_COST = (float)15.99;

   private final int CANDLE = 0;
   private final int TOOL_KIT = 1;
   private final int PUZZLE = 2;
   
   private final int VISA = 0;
   private final int MASTER_CARD = 1;
   private final int DISCOVER = 2;
   
   private int selectedItem, selectedPayment;
   
   private int quantity;
   
   
/**********************
   Constructors
**********************/

   public ShoppingCart()
   {
      mainWindow		= new MainWindow("Shopping Cart");
      inputBox			= new InputBox(mainWindow);
      outputBox			= new OutputBox(mainWindow);
	  messageBox		= new MessageBox(mainWindow);
	 
	  itemListBox		= new ListBox(mainWindow, "Select an Item");
	  itemListBox.addItem("Candle    $19.99");
	  itemListBox.addItem("Tool Kit   $39.99");
	  itemListBox.addItem("Puzzle     $15.99");
	  
	  paymentListBox	= new ListBox(mainWindow, "Select Payment Method");
	  paymentListBox.addItem("Visa");
	  paymentListBox.addItem("Master Card");
	  paymentListBox.addItem("Discover");
	  
	  mainWindow.show();
	  outputBox.show();
   }

/**********************
   Public Methods:
   
      void    start   (    )
      
**********************/

   /*  Method:       start
   
       Purpose:      Top level method that calls other private methods
                     to compute the cost of N number of items and display
					 that cost with the choice of payment method

       Parameters:   None
                      
       Returns:      None
   */
   
   public void start()
   {
      describeProgram();	//tell what the program does
      getItem();			//get item choice
	  getQuantity();		//get quantity
	  getPaymentMethod();	//get payment method
      displayInvoice();     //display the invoice with total cost and payment method
   }

/**********************
   Private Methods:
   
    float		computeTotalCost	(   )
    void		describeProgram		(   )
    void		displayInvoice		(   )
    void		getItem				(	)
	void		getPaymentMethod	(	)	  
	void		getQuantity();		(	)
	  
**********************/

    /*  Method:       computeTotalCost
   
       Purpose:      Compute the total cost from the quantity and price

       Parameters:   an integer (the quantity) and a float (the price)
                      
       Returns:      a float (the total Cost)
   */
   
   
    private float computeTotalCost(int quan, float price)
   {
		return quan * price;
   }
   
   /*  Method:       describeProgram
   
       Purpose:      Provide a brief explaination of the program to the user.

       Parameters:   None
                      
       Returns:      None
   */
   
   private void describeProgram()
   {
      outputBox.printLine("This program displays the invoice for a chosen item.");
      outputBox.skipLine(2);
   }

   /*  Method:       displayOutput
   
       Purpose:      Display the input values, total cost, and payment method

       Parameters:   None
                      
       Returns:      None
   */

   private void displayInvoice() 
   {
		outputBox.printLine("INVOICE");
		outputBox.printLine("Quantity    Item Ordered   Price Each    Total Cost");
		outputBox.print(quantity + "           ");
	  
		switch(selectedItem)
		{
			case CANDLE:	outputBox.print("Candle         ");
							outputBox.print("$" + CANDLE_COST);
							outputBox.printLine("        $" + 
								((float)((int)(computeTotalCost(quantity,CANDLE_COST)*100))/100));
							break;
							
			case TOOL_KIT:	outputBox.print("Tool Kit       ");
							outputBox.print("$" + TOOL_KIT_COST);
							outputBox.printLine("        $" + 
								((float)((int)(computeTotalCost(quantity,TOOL_KIT_COST)*100))/100));
							break;
							
			case PUZZLE:	outputBox.print("Puzzle         ");
							outputBox.print("$" + PUZZLE_COST);
							outputBox.printLine("        $" + 
								((float)((int)(computeTotalCost(quantity,PUZZLE_COST)*100))/100));
							break;
							
			default:		outputBox.print("Error: No Item Selected");
							break;  
		}
     
		
		outputBox.skipLine(1);
		outputBox.print("Method of Payment:  ");
		
		switch(selectedPayment-1+1)
		{
			case VISA:			outputBox.print("VISA");
								break;
							
			case MASTER_CARD:	outputBox.print("Master Card");
								break;
							
			case PUZZLE:		outputBox.print("Discover");
								break;
							
			default:			outputBox.print("Error: No Payment Method Selected");
								break;  
		}
							
  }   

   /*  Method:      getItem
   
       Purpose:     Get input value for Item
					Let the user select an item from the ListBox

       Parameters:  None
                      
       Returns:     None
   */

    private void getItem()
	{
		selectedItem = itemListBox.getSelectedIndex();
	}
	
	/*  Method:     getPaymentMethod
   
       Purpose:		Get input value for payment method.
					Let the user select a payment method from the ListBox

       Parameters:  None
                      
       Returns:     None
   */

    private void getPaymentMethod()
	{
		selectedPayment = paymentListBox.getSelectedIndex();
	}
	
	/*  Method:     getQuantity
   
       Purpose:		Get input value for quantity

       Parameters:  None
                      
       Returns:     None
   */

    private void getQuantity()
	{
		quantity = inputBox.getInteger("Enter quantity");
	}
	
}
