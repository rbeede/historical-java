package javabook;

import java.awt.*;
import java.awt.event.*;

//JDK 1.1 ListBox

public class ListBox extends JavaBookDialog implements ActionListener
{

/***************************
    Data Members:
 ***************************/

    public static final int     NO_SELECTION = -1;
    public static final int     CANCEL = -2;
    public static final String  NO_ITEM      = null;

    private List    list;
    private Button  okButton, cancelButton;
    private Font    font = new Font("Helvetica",Font.PLAIN, 12);

    private final static int ACTION_OK      =  0;
    private final static int ACTION_CANCEL  = -1;
    private int status;

 /***************************
    Constructors:
 ***************************/

    public ListBox(Frame owner)
    {
        this(owner,"Select One:",true);
    }

    public ListBox(Frame owner, String text)
    {
        this(owner,text,true);
    }

    public ListBox(Frame owner, boolean modal)
    {
        this(owner,"Select One:",modal);
    }

    public ListBox(Frame owner, String text, boolean modal)
    {
        super(owner,modal);
        setTitle(text);
        initialize();
    }


/***************************
    Public Methods:

            public void windowClosing(WindowEvent e) Note: Do not call this method

            void    addItem             ( String            )
            void    deleteItem          ( int               )
            void    deleteItem          ( String            )

            int     getSelectedIndex    (                   )
            String  getItemFromIndex    ( int               )

            boolean handleEvent         ( Event             ) Note: Do not call this method

            boolean isCanceled          (                   )

            void    show                (                   ) Note: Do not call this method

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
        }
    }

    public void addItem(String item)
    {
        list.add(item);
    }

    public void deleteItem(int index)
    {
        list.remove(index);
    }

    public void deleteItem(String item)
    {
        //search for an item in the list
        int i       = 0;
        int length  = list.getItemCount();

        while (i < length && list.getItem(i) != item) {
            i++;
        }

        if (i < length) {
            //found, so delete it
            list.remove(i);
        }

        //if not found, do nothing
    }

    public int getSelectedIndex()
    {
        showIt();
        if (status == ACTION_OK)  {
            return list.getSelectedIndex();
            //returns NO_SELECTION if nothing is selected
        }
        else if (status == ACTION_CANCEL) {
            return CANCEL; // user closed with Cancel button
            //NOTE: returns CANCEL if canceled
        }
        else {
            return NO_SELECTION;
            //NOTE: returns NO_SELECTION if closed without selecting an item.
        }
    }

    public String getItemFromIndex(int index) 
    {
       return list.getItem(index);
    }

    public String getSelectedItem()
    {
        showIt();
        if (status == ACTION_OK) {
            return list.getSelectedItem();
            //returns NO_ITEM if nothing is selected
        }
        else {
            return NO_ITEM;
            //NOTE: returns NO_ITEM if canceled, closed,
            //      or okayed without selecting an item.
        }
    }

    public void windowClosing(WindowEvent e)
    {
        dispose();
        status = ACTION_CANCEL;
    }

    public boolean isCanceled( )
    {
        if (status == ACTION_CANCEL) {
            return true;
        }
        else {
            return false;
        }
    }

/***************************
    Protected Methods:

            void    adjustSize      (           )

 ***************************/
 
    protected void adjustSize()
    {
    }


/***************************
    Private Methods:

            void    initialize      (           )
            void    showIt          (           )

 ***************************/

   private void initialize()
   {
       setResizable(false);
       setSize(170,200);

       list = new List();
       list.setFont(font);
       add("Center",list);

       Panel p = new Panel();

       okButton     = new Button("  OK  ");
       okButton.addActionListener(this);
       cancelButton = new Button("Cancel");
       cancelButton.addActionListener(this);
       p.add(okButton);
       p.add(cancelButton);
       add("South",p);

       addWindowListener(this);
       moveToCenter();
   }

   private void showIt()
   {
       //deselect any previously selected item
       int index = list.getSelectedIndex();
       if (index >=0) {
           list.deselect(index);
       }

       super.setVisible(true);
   }

}
