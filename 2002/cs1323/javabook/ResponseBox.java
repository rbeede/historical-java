package javabook;

import java.awt.*;
import java.awt.event.*;

//JDK 1.1 ResponseBox

public class ResponseBox extends JavaBookDialog implements ActionListener
{

 /***************************
    Data Members:
 ***************************/

    static public final int BUTTON1 =  1;
    static public final int BUTTON2 =  2;
    static public final int BUTTON3 =  3;
    static public final int YES = BUTTON1;
    static public final int NO  = BUTTON2;
    static public final int CANCEL = -1;

    private final static int ACTION_PENDING = 0;
    private int status = ACTION_PENDING;

    private int     numberOfButtons;
    private Label   prompt;
    private Button  button[] = new Button[3];
    private Font    font = new Font("Helvetica",Font.PLAIN, 12);


/***************************
    Constructors:
 ***************************/

    public ResponseBox(Frame owner)
    {
        this(owner, 2);
    }

    public ResponseBox(Frame owner, int buttonCount)
    {
        super(owner, true);

        if (buttonCount < 1 || buttonCount > 3) {
            numberOfButtons = 1;
        }
        else {
            numberOfButtons = buttonCount;
        }

        initialize();
    }


/***************************
    Public Methods:

            void actionPerformed( ActionEvent       ) Note: Do not call this method

            public void windowClosing(WindowEvent e) Note: Do not call this method

            int     prompt      ( String            )
            void    setLabel    ( int,   String     )

 ***************************/

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == button[0]) {
            //close the dialog
            setVisible(false);
            status = BUTTON1;
        }
        else if (e.getSource() == button[1]) {
            //close the dialog
            setVisible(false);
            status = BUTTON2;
        }
        else if (e.getSource() == button[2]) {
            //close the dialog
            setVisible(false);
            status = BUTTON3;
        }
    }

    public void windowClosing(WindowEvent e)
    {
        setVisible(false);
        status = CANCEL;
    }

    public int prompt(String text)
    {
        setPrompt(text);
        showIt();

        return getStatus();
    }

    public void setLabel(int id, String text)
    {
        if (id <= numberOfButtons && id > 0) {
            button[id-1].setLabel(text);
        }

        //do nothing for invalid button#
    }

/***************************
    Protected Methods:

            void    adjustSize      (           )

 ***************************/

    protected void adjustSize()
    {
        //height will not vary
        int height = 110;
        int width  = 200; //this is a minimum

//         addNotify();
        Insets inset = getInsets();

        //get the label string length in pixels
        Toolkit     toolkit = Toolkit.getDefaultToolkit();
        FontMetrics fontMet = toolkit.getFontMetrics(font);

        int strWidth = fontMet.stringWidth(prompt.getText());

        int buttonsWidth = button[0].getPreferredSize().width + 5 + //hgap
                           button[1].getPreferredSize().width + 5 +
                           button[2].getPreferredSize().width;
        //worked with "preferredSize", but didn't with "bounds"

         int windWidth = Math.max(strWidth,buttonsWidth)
                         + 60 + inset.left + inset.right;

        if (width < windWidth)  {
            width = windWidth;
        }
        setSize(width, height);
    }

/***************************
    Private Methods:

            int     getStatus       (           )
            void    initialize      (           )
            void    setPrompt       ( String    )
            void    showIt          (           )

 ***************************/

    private int getStatus()
    {
        return status;
    }

    private void initialize()
    {
        //setResizable(false);
        setForeground(Color.black);

        Panel emptyPanel = new Panel();
        add("North",emptyPanel); //this will position the prompt
                                 //at the middle of dialog

        Panel promptPanel = new Panel();

        prompt = new Label();
        prompt.setFont(font);
        prompt.setText("   ");

        promptPanel.add(prompt);
        add("Center",promptPanel);

        Panel buttonPanel = new Panel();

        button[0]   = new Button(" Yes  ");
        button[0].addActionListener(this);
        button[1]   = new Button("  No  ");
        button[1].addActionListener(this);
        button[2]   = new Button("Cancel");
        button[2].addActionListener(this);

        switch (numberOfButtons) {
            case 1:
                button[0].setLabel("  OK  ");
                button[1].setVisible(false);
            case 2:
                button[2].setVisible(false);
        }

        buttonPanel.add(button[0]);
        buttonPanel.add(button[1]);
        buttonPanel.add(button[2]);
        add("South",buttonPanel);

        addWindowListener(this);
    }
  
    private void setPrompt(String text)
    {
        prompt.setText(text);
    }

    private void showIt()
    {
        //status = ACTION_PENDING;

        adjustSize();
        moveToCenter();
        super.setVisible(true);
    }

}
