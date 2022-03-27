/*
    Program Calculator

    This program provides a simple four function calculator with
    two input fields to enter the left and right operands of an
    arithmetic operation. The program uses the JDK 1.1 event model.

*/

import java.awt.*;
import java.awt.event.*;
import javabook.*;

class Calculator extends Frame implements ActionListener
{

/**************************
    Data Members
***************************/

   private TextField   leftOperand,
                       rightOperand;

   private Button      plusButton,
                       minusButton,
                       multiplyButton,
                       divideButton,
                       clearButton;
                       
   private MessageBox  errorMessageBox;

/*************************
    Constructor
**************************/

   public Calculator()
   {
      //set window properties
      super        ( "Calculator");

      setSize      ( 200, 150    );
      setResizable ( false       );
      setLayout    ( null        );
      setLocation  ( 300, 200    );

      //create and layout components
      initGUIComponents();

      addWindowListener( new ProgramTerminator() );

      errorMessageBox = new MessageBox(this);
    }


/*************************

    Public Methods

        void    actionPerformed ( ActionEvent   )

**************************/

   /*
      Method:       actionPerformed

      Purpose:      Called when one of the five buttons is clicked
                    Perform the specified calculation and display
                    the result.

      Parameters:   ActionEvent
      
      Returns:      None
   */

   public void actionPerformed (ActionEvent event)
   {
      Button clickedButton = (Button) event.getSource();

      if (clickedButton == clearButton) {
         clearEntries();
      }
      else {
         //an operator button is clicked, so compute
         compute( clickedButton );
      }
   }

/*************************

    Private Methods

        void        clearEntries        (           )
        void        compute             ( Button    )
        void        initGUIComponents   (           )

        void        displayResult       (           )

        boolean     isNumber            ( TextField )
        boolean     isOperandValid      ( TextField )
        
**************************/

   /*
      Method:       clearEntries

      Purpose:      Clear the left and right operand TextField objects

      Parameters:   None
      
      Returns:      None
   */

   private void clearEntries()
   {
      leftOperand.setText("");
      rightOperand.setText("");
   }

   /*
      Method:       compute

      Purpose:      Determine which operation is requested and perform
                    the appropriate computation.

      Parameters:   Button [operation]
      
      Returns:      None
   */

   private void compute (Button operatorButton)
   {
      float   leftOperandValue,
              rightOperandValue,
              result = 0f;

      //are values in text fields valid numbers?
      if ( isOperandValid(leftOperand) && isOperandValid(rightOperand) ) {

         //okay, so get the two operands
         leftOperandValue  = getNumberFrom(leftOperand);
    	   rightOperandValue = getNumberFrom(rightOperand);

         //compute the result
         if ( operatorButton == plusButton ) {
            result = leftOperandValue + rightOperandValue;
         }
         else if ( operatorButton == minusButton ) {
            result = leftOperandValue - rightOperandValue;
         }
         else if ( operatorButton == divideButton ) {
            result = leftOperandValue / rightOperandValue;
         }
         else if ( operatorButton == multiplyButton ) {
            result = leftOperandValue * rightOperandValue;
         }
         displayResult(result);
      }
      else  { //invalid operands
         errorMessageBox.show("Error: Invalid Data");
      }
   }

   /*
      Method:       initGUIComponents

      Purpose:      Create and initialize GUI components. Absolute positioning
                    is used.

      Parameters:   None
      
      Returns:      None
   */

   private void initGUIComponents( )
   {
      //create buttons
      leftOperand     = new TextField();
      rightOperand    = new TextField();

      plusButton      = new Button("+");
      minusButton     = new Button("-");
      multiplyButton  = new Button("X");
      divideButton    = new Button("/");
      clearButton     = new Button("CLEAR");


      //position buttons
      leftOperand.setBounds ( 20,  40, 75, 25);
      rightOperand.setBounds ( 20,  75, 75, 25);

      plusButton.setBounds (110,  40, 30, 30);
      minusButton.setBounds (150,  40, 30, 30);
      multiplyButton.setBounds (110,  75, 30, 30);
      divideButton.setBounds (150,  75, 30, 30);
      clearButton.setBounds (110, 110, 70, 30);

      //add this calculator as an action listener
      //to all five buttons
      plusButton.addActionListener( this );
      minusButton.addActionListener( this );
      multiplyButton.addActionListener( this );
      divideButton.addActionListener( this );
      clearButton.addActionListener( this );

      //add buttons to the calculator frame
      add(leftOperand);
      add(rightOperand);

      add(plusButton);
      add(minusButton);
      add(multiplyButton);
      add(divideButton);

      add(clearButton);
   }


   /*
      Method:       displayResult

      Purpose:      display the argument into the left operand 
                    text field.

      Parameters:   float [number to display]
      
      Returns:      None
   */

   private void displayResult(float number)
   {
      leftOperand.setText(Convert.toString(number));
      rightOperand.setText("");
   }

   /*
      Method:       getNumberFrom

      Purpose:      Called when one of the five buttons is clicked
                    Perform the specified calculation and display
                    the result.

      Parameters:   TextField
      
      Returns:      None
   */

   private float getNumberFrom(TextField operand)
   {
      return Convert.toFloat(operand.getText());
   }

   /*
      Method:       isOperandValid

      Purpose:      Determine whether the number in the specified
                    text is valid.

      Parameters:   TextField
      
      Returns:      None
   */

   private boolean isOperandValid(TextField operand)
   {
    	boolean status;

      try {
         float number = Convert.toFloat(operand.getText());
         status = true;
      }
      catch (NumberFormatException e) {
         status = false;
      }

    	return status;
   }

}
