/*
    Program Calculator

    This program provides a simple four function calculator with
    two input fields to enter the left and right operands of an
    arithmetic operation. The program uses the JDK 1.1 event model.

*/

import java.awt.*;
import java.awt.event.*;

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
      System.out.println("Inside actionPerformed");  //TEMP
   }

/*************************

    Private Methods

        void        initGUIComponents   (           )
        
**************************/

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

}
