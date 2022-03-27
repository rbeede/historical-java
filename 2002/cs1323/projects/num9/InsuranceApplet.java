//=========================================================================
//	InsuranceApplet.java
//=========================================================================
//	Programmer:		Rodney Beede
//	ID:			0000
//	Course:			CS1323
//	Lab Section:		222
//	Lab TA:			Lalitha
//
//	Project#:		9
//	Due Date:		4-28-2K
//
//	Description:		This is a modification of BMIApplet that
//				will accept input from the user about their
//				user's name, age, gender, and smoking
//				preference and displays their insurance
//				premium based on their age and smoking
//				preference
//
//	Public Methods:         actionPerformed -- Grabs the button click
//				event and calculates the premium
//
//=========================================================================


import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import Applicant;

public class InsuranceApplet extends Applet implements ActionListener
{

/****************
   Data Members
****************/

   private Label     nameLabel,		// label for nameInput
                     ageLabel,		// label for ageInput
		     genderLabel,	// label for genderInput
		     smokerLabel,	// label for smokerInput
                     premiumLabel;	// label for displaying insurance
					// premium

   private TextField nameInput,		// to accept the user's name
                     ageInput,		// to accept the user's age
		     genderInput,	// to accept the user's gender
		     smokerInput;	// to accept the user's smoking
					// prefernce

   private Button    computeButton;	// the user clicks this button to
					// compute the insurance premimum


/****************
   Constructor
****************/

   public InsuranceApplet()
   {
      //Create needed objects for grabbing input
      nameLabel		= new Label("Your Name:");
      nameInput		= new TextField(15);

      ageLabel		= new Label("Your Age:");
      ageInput		= new TextField(15);

      genderLabel	= new Label("Your Gender:");
      genderInput	= new TextField(15);

      smokerLabel	= new Label("Do you smoke? (Y/N):");
      smokerInput	= new TextField(15);

      computeButton = new Button("Calculate Insurance Premium");

      //This label simply gives instructions for now
      premiumLabel	= new Label("Fill in the above info and click Calculate.");

      //Add objects to the applet
      //The order of placement is determined by what we add first, the layout
      //is done automatically for us);

      add(nameLabel);
      add(nameInput);

      add(ageLabel);
      add(ageInput);

      add(genderLabel);
      add(genderInput);

      add(smokerLabel);
      add(smokerInput);

      add(computeButton);

      add(premiumLabel);

      //Allow applet to respond to the button being clicked
      computeButton.addActionListener(this);
   }


   /* Method:     actionPerformed

      Purpose:    The method retrieves data from all of the TextField
		  objects and displays the insurance premium

      Parameters: None

      Returns:    None
   */

   public void actionPerformed(ActionEvent event)
   {
      Applicant objApplicant;  //Declare an Applicant object
	
      String strName;
      char chrGender, chrSmoker;
      int intAge;

      // Get inputed values and convert them correctly if needed
      strName = nameInput.getText();
      intAge = Integer.parseInt(ageInput.getText());
      chrGender = genderInput.getText().charAt(0);
      chrSmoker = smokerInput.getText().charAt(0);

      //Create the correct Applicant sub-type, smoker or non-smoker
      //based on the given input
      switch( chrSmoker ) {
	case 'Y':	//Smoker
		objApplicant = new SmokerApplicant( strName, intAge,
		   chrGender );
		break;
	case 'N':	//Non-smoker
		objApplicant = new NonsmokerApplicant( strName, intAge,
		   chrGender );
		break;
	default:	//Error, tell user
		premiumLabel.setText( "You didn't enter in Y or N for your " +
		   "smoking preference, so I can't calculate." );
		return;  //Leave this method
      }

      // Calculate the premium
      objApplicant.calculatePremium();

      // Display the premium
      premiumLabel.setText( strName + "'s estimated monthly premium is $" + objApplicant.getPremium());
   }
}
