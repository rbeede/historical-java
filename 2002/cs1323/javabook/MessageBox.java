package javabook;

import java.awt.*;
import java.awt.event.*;

//JDK 1.1 MessageBox

public class MessageBox extends JavaBookDialog implements ActionListener, KeyListener
{

/***************************
    Data Members:
 ***************************/

    private Label  label;
    private Button okButton;
    private Font   font = new Font("Helvetica",Font.PLAIN, 12);

/***************************
    Constructors:
 ***************************/

    public MessageBox(Frame owner)
    {
        super(owner,true);
        initialize();
    }

    public MessageBox(Frame mainWindow, boolean modal)
    {
        super(mainWindow, modal);
        initialize();
    }


/***************************
    Public Methods:

            void actionPerformed( ActionEvent       ) Note: Do not call this method

            public abstract void keyTyped(KeyEvent e)
            public abstract void keyPressed(KeyEvent e)
            public abstract void keyReleased(KeyEvent e)

            void    show        ( long                      )
            void    show        ( double                    )
            void    show        ( String                    )
            void    show        ( StringBuffer              )

            void    show        ( long,   int, int          )
            void    show        ( double, int, int          )
            void    show        ( String, int, int          )
            void    show        ( StringBuffer, int, int    )

 ***************************/

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == okButton) {
            //close the dialog
            setVisible(false);
        }
    }

    public void keyTyped(KeyEvent e) {
    }
    
    public void keyPressed(KeyEvent e) {
    }
    
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == '\n') {
            setVisible(false);
        }
    }

    public void show (long number)
    {
        show(" " + number + " ");
    }

    public void show (double number)
    {
        show(" " + number + " ");
    }

    public void show (String text)
    {
        label.setText(text);
   
        adjustSize();
        moveToCenter();

        super.setVisible(true);

   }

    public void show (StringBuffer text)
    {
        show(text.toString());
    }

    public void show (long number, int x, int y)
    {
        show(" " + number + " ", x, y);
    }

    public void show (double number, int x, int y)
    {
        show(" " + number + " ",  x, y);
    }

    public void show (String text, int x, int y)
    {
        label.setText(text);
    
        adjustSize();
        setLocation(x,y);

		wasMoved = true;		
			
        super.setVisible(true);
    }

    public void show (StringBuffer text, int x, int y)
    {
        show(text.toString(), x, y);
    }


/***************************
    Protected Methods:

            void    adjustSize  (   )

 ***************************/

   //Place the label and button and adjust the dialog dimension
    protected void adjustSize()
    {
        //height will not vary
        int height = 130;
        int width  = 200; //this is a minimum

//        addNotify();
        Insets inset = getInsets();

        //get the label string length in pixels
        Toolkit     toolkit = Toolkit.getDefaultToolkit();
        FontMetrics fontMet = toolkit.getFontMetrics(font);

        int strWidth = fontMet.stringWidth(label.getText());
        int windWidth = strWidth + 60 + inset.left + inset.right;
        if (width < windWidth) 
            width = windWidth;
        setSize(width, height);

        //now place the control objects
        int midpoint = (width-inset.left-inset.right)/2;
        label.setBounds(midpoint-strWidth/2,height/3,strWidth,20);

        okButton.setBounds(midpoint-25,100, 50, 20);
    }

/***************************
    Private Methods:

            void    initialize  (   )

 ***************************/

    private void initialize()
    {
        setResizable(false);
        setForeground(Color.black);
        setBackground(Color.white);
        setLayout(null);

        label = new Label();
        label.setFont(font);
        add(label);

        okButton = new Button("OK");
        okButton.addActionListener(this);
        add(okButton);
 
        addWindowListener(this);
        okButton.addKeyListener(this);

    }

}