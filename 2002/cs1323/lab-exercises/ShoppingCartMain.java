/*
	Program:	ShoppingCart
	
	Class:		ShoppingCartMain

	A program to get an item, quantity, and payment method from the user
	and display the total cost and payment method in an invoice,
	using a ShoppingCart object.

	Input:	item (let the user select an item from a ListBox)
			quantity (an integer)
			payment method (let the user select the payment method from a ListBox) 
           
	Compute: total cost
*/

class ShoppingCartMain
{
	public static void main (String arg[] )
	{
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.start();
	}
}