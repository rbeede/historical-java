package javabook;

import java.awt.*;
import java.awt.event.*;

//JDK 1.1 MultiInputBox

public class MultiInputBox extends JavaBookDialog implements ActionListener, KeyListener
{

/***************************
    Data Members:
 ***************************/

    private final static int ACTION_PENDING = 0;
    private final static int ACTION_OK      = 1;
    private final static int ACTION_CANCEL  = 2;
    private int status = ACTION_PENDING;

    private TextField   inputLine[];
    private Label       prompt[];
    private Button      okButton, cancelButton;
    private Font        font = new Font("Helvetica",Font.PLAIN, 12);
    private int         numOfRows;
    private int         currentLine = 0;


/***************************
    Constructors:
 ***************************/

    public MultiInputBox(Frame owner, int size)
    {
        super(owner,true);
        numOfRows = size;
        initialize();
    }

    public MultiInputBox(Frame owner, String labels[])
    {
        this(owner, labels.length);
        setLabels(labels);
    }

/***************************
    Public Methods:

            void actionPerformed( ActionEvent       ) Note: Do not call this method

            public void windowClosing(WindowEvent e) Note: Do not call this method

            String[] getInputs   (              )
            boolean  isCanceled  (              )

            public void keyTyped(KeyEvent e) Note: Do not call this method
            public void keyPressed(KeyEvent e) Note: Do not call this method
            public void keyReleased(KeyEvent e) Note: Do not call this method

            void     setLabels   ( String[]     )
            void     setValue    ( int,String   )

 ***************************/

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == okButton) {
           //close the dialog
            setVisible(false);
            status = ACTION_OK;
        }
        else if (e.getSource() == cancelButton) {
            setVisible(false);
            status = ACTION_CANCEL;
            clearInputLines();
        }
    }

    public void windowOpened(WindowEvent e) 
    {
    }

    public void windowClosing(WindowEvent e)
    {
        dispose();
        status = ACTION_CANCEL;
    }

    public void windowClosed(WindowEvent e)
    {
    }

    public void windowIconified(WindowEvent e)
    {
    }

    public void windowDeiconified(WindowEvent e)
    {
    }

    public void windowActivated(WindowEvent e)
    {
    }

    public void windowDeactivated(WindowEvent e)
    {
    }

    public void keyTyped(KeyEvent e) {
     }
    
    public void keyPressed(KeyEvent e) {
    }
    
    public void keyReleased(KeyEvent e) {
//       if (e.getKeyChar() == '\t') {
//           currentLine = (currentLine+1) % numOfRows;
//           inputLine[currentLine].requestFocus();
//       }
       // disable the return key
//       else if (e.getKeyChar() == '\n') {
           //setVisible(false); //dispose();
           //status = ACTION_OK;
//       } 
    }

    public String[] getInputs()
    {
        showIt();
        boolean done = false;

        String[] answers = getInputLines();
        clearInputLines();

        return answers;
    }

    public boolean isCanceled()
    {
        if (status == ACTION_CANCEL) {
            return true;
        }
        else {
            return false;
        }
    }

    public void setLabels(String label[])
    {
        for (int i = 0; i < label.length; i++) {
            prompt[i].setText(label[i]);
        }
    }

    public void setValue(int index, String value)
    {
        inputLine[index].setText(value);
    }

/***************************
    Protected Methods:

            void     adjustSize     (   )
            
***************************/

   //Place the components
    protected void adjustSize()
    {

        //dialog dimension is fixed
        int windowHeight = 230, windowWidth = 300,
            buttonWidth  = 60, buttonHeight = 25,
            centerGap    = 10,

            leftMargin, rightMargin,
            topMargin, bottomMargin,

            labelWidth, strWidth,
            labelHeight,
            inputLineWidth,
            inputOffset;

//        addNotify();

        leftMargin   = 10;
        rightMargin  = 10;
        topMargin    = 40;
        bottomMargin = 20;

        //find the width of the prompt with largest number of characters
        Toolkit     toolkit = Toolkit.getDefaultToolkit();
        FontMetrics fontMet = toolkit.getFontMetrics(font);

        labelWidth = 0;
        for (int i = 0; i < numOfRows; i++) {
            strWidth = fontMet.stringWidth(prompt[i].getText());
            if (strWidth > labelWidth) {
                labelWidth = strWidth;
            }
        }

        labelHeight = 22;       //use a fixed value for height
        inputLineWidth = 100;   //use a fixed value input text field

        inputOffset = leftMargin + labelWidth+25 + centerGap; //add 25 to make it wider
        for (int i = 0; i < numOfRows; i++) {
            prompt[i].setBounds(leftMargin, topMargin+labelHeight*i,
                                labelWidth+25, labelHeight);

            inputLine[i].setBounds(inputOffset, topMargin+labelHeight*i,
                                   inputLineWidth, labelHeight);
        }

        //adjust the window size
        windowHeight = topMargin + bottomMargin + (numOfRows+1)*labelHeight
                        + buttonHeight+10;

        windowWidth = leftMargin + centerGap + rightMargin +
                        labelWidth+30 + inputLineWidth;

        setSize(windowWidth,windowHeight);

         //place buttons
        int midpoint = (leftMargin+rightMargin+centerGap+labelWidth+30+inputLineWidth)/2;

        okButton.setBounds(midpoint-(buttonWidth+10),topMargin+(numOfRows+1)*labelHeight,
                           buttonWidth,buttonHeight);
        cancelButton.setBounds(midpoint+10,topMargin+(numOfRows+1)*labelHeight,
                               buttonWidth,buttonHeight);

    }
    
/***************************
    Private Methods:

            void     clearInputLines(   )
            String[] getInputLines  (   )
            int      getStatus      (   )
            void     initialize     (   )
            void     showIt         (   )

 ***************************/

    private void initialize()
    {
        setResizable(false);
        setForeground(Color.black);
        setLayout(null);

        setTitle("MultiInputBox");
        prompt = new Label[numOfRows];
        inputLine = new TextField[numOfRows];

        for (int i = 0; i < numOfRows; i++) {
            prompt[i] = new Label();
            prompt[i].setFont(font);
            inputLine[i] = new TextField();
            inputLine[i].setFont(font);
            inputLine[i].addKeyListener(this);
            add(prompt[i]);
            add(inputLine[i]);
        }

        inputLine[0].requestFocus();

        okButton     = new Button("  OK  ");
        okButton.addActionListener(this);
        cancelButton = new Button("Cancel");
        cancelButton.addActionListener(this);
        add(okButton);
        add(cancelButton);
//        addNotify();

        addWindowListener(this);
        addKeyListener(this);
    }

    private void clearInputLines()
    {
        for (int i = 0; i < numOfRows; i++) {
            inputLine[i].setText("");
        }
        currentLine = 0;
    }

    private String[] getInputLines()
    {
         String[] answer = new String[inputLine.length];
         if (status == ACTION_CANCEL) {
             answer = null;
         }
         else {
             for (int i = 0; i < inputLine.length; i++) {
                 answer[i] = inputLine[i].getText();
             }
         }

         return answer;
    }

    private int getStatus()
    {
        return status;
    }

    private void showIt()
    {

        adjustSize();
        moveToCenter();

        super.setVisible(true);
        inputLine[currentLine].requestFocus();

    }

}
