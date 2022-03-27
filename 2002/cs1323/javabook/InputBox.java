package javabook;

import java.awt.*;
import java.awt.event.*;

public class InputBox extends JavaBookDialog implements ActionListener, KeyListener
{

 /***************************
    Data Members:
 ***************************/

    private TextField   inputLine;
    private Label       prompt, errorMessage;
    private Button      okButton;
    private Font        font = new Font("Helvetica",Font.PLAIN, 12);
    private String      errorMsgText = "Invalid entry. Try again...";
    private boolean     showErrorMsg;


/***************************
    Constructors:
 ***************************/

    public InputBox  (Frame owner)
    {
        super(owner,true);
        initialize();
    }

    public InputBox (Frame owner, String title)
    {
        super(owner,true);
        initialize();
        setTitle(title);
    }


/***************************
    Public Methods:

        void actionPerformed( ActionEvent       ) Note: Do not call this method

        int     getInteger  (                   )
        int     getInteger  ( String            )

        float   getFloat    (                   )
        float   getFloat    ( String            )

        String  getString   (                   )
        String  getString   ( String            )

        public abstract void keyTyped(KeyEvent e)
        public abstract void keyPressed(KeyEvent e)
        public abstract void keyReleased(KeyEvent e)

 ***************************/

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == okButton) {
            //close the dialog
            dispose();
        }
    }

    public int getInteger()
    {
        return getInteger("Enter an integer:");
    }

    public int getInteger(String text)
    {
        setPrompt(text);
        showIt(false);

        boolean done = false;
        int value = 0;

        do {
            try {
                value = Integer.parseInt(getInputLine());
                done = true;
            }

            catch (NumberFormatException e) {
                showIt(true);
            }
        } while (!done);

        return value;
    }

    public float getFloat()
    {
        return getFloat("Enter a float:");
    }

    public float getFloat(String text)
    {
        setPrompt(text);
        showIt(false);

        boolean done = false;
        float value = 0f;
        Float floatObject;

        do {
            try {
                floatObject = Float.valueOf(getInputLine());
                value = floatObject.floatValue();
                done = true;
            }

            catch (NumberFormatException e) {
               showIt(true);
            }
        } while (!done);

        return value;
    }

    public String getString()
    {
        return getString("Enter a string:");
    }

    public String getString(String text)
    {
        setPrompt(text);
        showIt(false);

        boolean done = false;

        return (getInputLine());
    }

    public void keyTyped(KeyEvent e) {
    }
    
    public void keyPressed(KeyEvent e) {
    }
    
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == '\n') {
           dispose();
        }
    }

/***************************
    Private Methods:

            void    adjustSize              (           )

 ***************************/

     //Place the components
    protected  void adjustSize()
    {
        //dialog dimension is fixed
        int height = 150;
        int width  = 200;

        addNotify();
        Insets inset = getInsets();
 
        //get the label string length in pixels
        Toolkit     toolkit = Toolkit.getDefaultToolkit();
        FontMetrics fontMet = toolkit.getFontMetrics(font);

        int strWidth = fontMet.stringWidth(prompt.getText());
        int lineWidth = inputLine.getPreferredSize().width;
        int errorMsgTextWidth = fontMet.stringWidth(errorMsgText);
        int maxStrLen = Math.max(strWidth, lineWidth);

        if (showErrorMsg) {
           maxStrLen = Math.max(errorMsgTextWidth, maxStrLen);
        }

        int windWidth = maxStrLen + 60 + inset.left + inset.right;

        if (width < windWidth) {
            width = windWidth;
        }
        setSize(width, height);

        //now place the component objects
        int midpoint = (width-inset.left-inset.right)/2;
        if (strWidth < lineWidth) {
            //align prompt with inputLine if its smaller than inputLine
            prompt.setBounds(midpoint-lineWidth/2,inset.top+35,strWidth,20);
        }
        else {
            prompt.setBounds(midpoint-strWidth/2,inset.top+35,strWidth,20);
        }

        inputLine.setBounds(midpoint-lineWidth/2,inset.top+60,lineWidth,20);
        okButton.setBounds(midpoint-25,height-inset.bottom-25, 50, 20);
        errorMessage.setBounds(midpoint-errorMsgTextWidth/2,inset.top+10,errorMsgTextWidth,20);

        pack(); 

    }

/***************************
    Private Methods:

            void    clearInputLine          (           )
            String  getInputLine            (           )
            void    initialize              (           )
            void    setPrompt               ( String    )
            void    showIt                  ( boolean   )

 ***************************/

    private void clearInputLine()
    {
        inputLine.setText("");
    }

    private String getInputLine()
    {
        return inputLine.getText();
    }

    private void initialize()
    {
        setResizable(false);
        setForeground(Color.black); //need this change the background to white
        setBackground(Color.white);
        setLayout(null);

        setTitle("InputBox");

        inputLine = new TextField(15); //make size user settable?
        inputLine.setFont(font);
        add(inputLine);

        prompt = new Label("Enter Data:");
        prompt.setFont(font);
        add(prompt);

        showErrorMsg = false;
        errorMessage = new Label(errorMsgText);
        errorMessage.setFont(font);
        add(errorMessage);

        okButton = new Button("OK");
        okButton.addActionListener(this);
        add(okButton);

        addWindowListener(this);
        inputLine.addKeyListener(this);
    }

    private void setPrompt(String text)
    {
        prompt.setText(text);
    }

    private void showIt(boolean showError)
    {
        /*
        Hiding components and showing them back
        has the effect of erasing previous content.
        This is necessary when the sizes of component
        change and get smaller than previous.
        Sequence of invalidate and validate did not work.
        */

        errorMessage.setVisible(false);
        inputLine.setVisible(false);
        prompt.setVisible(false);
        okButton.setVisible(false);
        
        showErrorMsg = showError;
        if (showErrorMsg) {
           errorMessage.setVisible(true);
        }
        else { //only clear the input line if no error
           clearInputLine();
        }
        
        inputLine.setVisible(true);
        prompt.setVisible(true);
        okButton.setVisible(true);

        inputLine.requestFocus();

        setVisible(true);

    }

}
