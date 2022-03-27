package javabook;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

//JDK 1.1 OutputBox

public class OutputBox extends JavaBookDialog
{

/***************************
    Data Members:
 ***************************/

    private TextArea    outputArea;
    private Font        font = new Font("Courier",Font.PLAIN, 12);


/***************************
    Constructors:
 ***************************/

    public OutputBox(Frame owner, int width, int height, String title)
    {
        super(owner, false);
        setTitle(title);
        initialize(width, height);
    }

    public OutputBox(Frame owner, int width, int height)
    {
       this(owner, width, height, "OutputBox");
    }

    public OutputBox(Frame owner, String title)
    {
       this(owner, 550, 380, title);
    }

    public OutputBox(Frame owner)
    {
       this(owner, 550, 380, "OutputBox");
    }


/***************************
    Public Methods:

            void    clear           (         )

            public void windowClosing(WindowEvent e) Note: Do not call this method

            void    print           ( boolean )
            void    print           ( char    )
            void    print           ( double  )
            void    print           ( long    )
            void    print           ( String  )
            void    print           ( StringBuffer  )

            void    printLine       ( boolean )
            void    printLine       ( char    )
            void    printLine       ( double  )
            void    printLine       ( long    )
            void    printLine       ( String  )
            void    printLine       ( StringBuffer  )

            void    setFont         ( Font    )
            void    skipLine        ( int     )
            void    waitUntilClose  (         )

            void    saveToFile      ( String  )
            void    appendToFile    ( String  )
            
 ***************************/

    public void clear()
    {
        //clears the whole content
        outputArea.setText("");
    }

    public void windowClosing(WindowEvent e)
    {
       dispose();
       setModal(false);
    }

    public void print(boolean b)
    {
        print("" + b);
    }

    public void print(char ch)
    {
        print("" + ch);
    }

    public void print(double number)
    {
        print("" + number);
    }

    public void print(long number)
    {
        print("" + number);
    }

    public void print(String text)
    {
        outputArea.append(text);
    }

    public void print(StringBuffer strBuf)
    {
        print(strBuf.toString());
    }

    public void printLine(boolean b)
    {
        printLine("" + b);
    }

    public void printLine(char ch)
    {
        printLine("" + ch);
    }

    public void printLine(double number)
    {
        printLine("" + number);
    }

    public void printLine(long number)
    {
        printLine("" + number);
    }

    public void printLine(String text)
    {
        print(text);
        newLine();
    }

    public void printLine(StringBuffer strBuf)
    {
        printLine(strBuf.toString());
    }

    public void setFont(Font font)
    {
        outputArea.setFont(font);
        this.font = font;
    }

    public void skipLine(int numberOfLines)
    {
        if (numberOfLines > 0 || numberOfLines < 21) {
            for (int i = 0; i < numberOfLines; i++) {
                newLine();
            }
        }
    }

    public void waitUntilClose( )
    {
      setVisible(false);
      setModal(true);
      setVisible(true);
    }


    public void saveToFile( String fileName ) 
    {
        writeToFile(fileName, false);
    }

    public void appendToFile( String fileName )
    {
        writeToFile(fileName, true);
    }
    

 /***************************
    Protected Methods:

            void    adjustSize  (   )

 ***************************/

   //Place the components
    protected void adjustSize(int width, int height)
    {
        setSize(width,height); //(**need to check for size too big or small**)
    }

    protected void adjustSize()
    {
    }

 /***************************
    Private Methods:

            void    initialize  (   )
            void    newLine     (   )
            void    writeToFile( String, boolean );

 ***************************/

    private void initialize(int width, int height)
    {
        pack();
        setResizable(true);

        outputArea = new TextArea();
        setFont(font);
        add(outputArea, BorderLayout.CENTER); 
        adjustSize(width, height);
        moveToCenter();
        addWindowListener(this);
    }

    private void newLine()
    {
        outputArea.append("\r\n");
    }

    private void writeToFile( String fileName, boolean append)
    {
        try 
        {
            FileWriter outputFile = new FileWriter(fileName, append);
            outputFile.write(outputArea.getText());
            outputFile.close();
        }
        catch (IOException e)
        {
        }
    }


}
