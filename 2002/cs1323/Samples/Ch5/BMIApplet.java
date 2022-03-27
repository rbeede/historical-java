/*
   Program BMIApplet

   An applet that accepts the user's height and weight
   and displays his/her body mass index (BMI) according to the formula
   
      BMI = weight / (height * height)

   Input:   height (in meters, e.g, 1.82)
            weight (in kilograms, e.g., 75.3)

   Output:   Message "Your BMI is <NN>" with <NN> replaced by the
             actual number
*/

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class BMIApplet extends Applet implements ActionListener
{

/****************
   Data Members
****************/

   private Label     heightLabel,    // label for heightInput
                     weightLabel,    // label for weightInput
                     BMILabel;       // label for displaying BMI

   private TextField heightInput,    // to accept the user's height
                     weightInput;    // to accept the user's weight

   private Button    computeButton;  // the user clicks this button


/****************
   Constructor
****************/

   public BMIApplet()
   {
      //create objects
      heightLabel    = new Label("Your height (in meters, e.g. 1.88):");
      heightInput    = new TextField(15);

      weightLabel    = new Label("Your weight (in kilograms, e.g. 180.5):");
      weightInput    = new TextField(15);

      computeButton = new Button("  Compute BMI  ");

      BMILabel       = new Label("This is your BMI Computer.");

      //add objects to the applet
      //the order of placement is significant
      add(heightLabel);
      add(heightInput);

      add(weightLabel);
      add(weightInput);

      add(computeButton);

      add(BMILabel);

      computeButton.addActionListener(this);
   }


/****************
   Public Methods
      void actionPerformed(ActionEvent)
****************/


   /* Method:     actionPerformed

      Purpose:    The method retrieves data from nameInput and heightInpput
                  and displays the BMI

      Parameters: None

      Returns:    None
   */

   public void actionPerformed(ActionEvent event)
   {
      String heightString, weightString, result;
      float  height, weight;
      int BMI;

      // get input values
      heightString = heightInput.getText();
      weightString = weightInput.getText();

      // convert input to numbers
      height = convertToFloat(heightString);
      weight = convertToFloat(weightString);

      // compute the BMI
      BMI = computeBMI(height, weight);

      // display the result
      result = "Your BMI is " + BMI;
      BMILabel.setText(result);

   }


   private float convertToFloat( String str )
   {
      Float floatObj = new Float(str);
      return floatObj.floatValue();      
   }

   private int computeBMI( float height, float weight)
   {
      int BMI;
      BMI = (int) Math.round(weight / (height * height));
      return BMI;
   }


}
