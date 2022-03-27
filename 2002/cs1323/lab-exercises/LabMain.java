/*		 
 
	Class:			LabMain class 
    
	Purpose:		To create a Lab object in order to 
				to compute a sale price 
 
	Parameters:		None 
			                
	Returns:		None 
*/ 
	    
class LabMain 
{ 
 
	public static void main (String args[] ) 
	{ 
		Lab lab = new Lab("Lab Exercises"); 
		 
		lab.showObjects(); 
		lab.describeProgram();   //tell what the program does 
		lab.getInput();          //get two input values 
		lab.computeSalePrice();  //compute the discounted price 
		lab.displayOutput();     //display the results 
      
	}//end main 
 
}//end class LabMain
