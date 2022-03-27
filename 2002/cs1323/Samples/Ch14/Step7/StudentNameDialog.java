import java.awt.*;
import java.awt.event.*;
import javabook.*;

class StudentNameDialog extends JavaBookDialog implements ActionListener
{
   private String      currentName, newName;

   private TextField    currentNameTextField, newNameTextField;
   private Label       currentNameLabel, newNameLabel;

   private Button       changeButton, cancelButton;

   private boolean     canceled;

   private MessageBox  errorMessageBox;

   public StudentNameDialog(Frame owner)
   {
       //set the properties of the dialog
       super(owner);
       setTitle("Edit Student Name");
       setResizable(false);
       setLayout(null);

       errorMessageBox = new MessageBox(owner);
       canceled        = true; //in case close box is clicked

       //create GUI objects
       currentNameLabel = new Label("Current Name:");
       newNameLabel = new Label("New Name:");

       currentNameTextField = new TextField("");
       newNameTextField = new TextField("");

       changeButton = new Button("Change");
       cancelButton = new Button("Cancel");

       //add the created GUI objects to the dialog
       add(currentNameLabel);
       add(newNameLabel);
       add(currentNameTextField);
       add(newNameTextField);
       add(changeButton);
       add(cancelButton);

       cancelButton.addActionListener(this);
       changeButton.addActionListener(this);

   }


    public void actionPerformed (ActionEvent event)
    {
        Button buttonClicked = (Button) event.getSource();
        
        if (buttonClicked == cancelButton) {
            canceled = true;
        }
        else {
            setNames();
        }

        setVisible(false);
        clearEntries();
    }

    public boolean isCanceled()
    {
        return canceled;
    }

    public String getCurrentName()
    {
        return currentName;
    }

    public String getNewName()
    {
        return newName;
    }

    protected void adjustSize()
    {
       addNotify();
       Insets inset = getInsets();

       setSize(inset.left + inset.right + 250, inset.top + inset.bottom + 170);

       currentNameLabel.setBounds     (inset.left +  13, inset.top +  25,  81, 17);
       newNameLabel.setBounds         (inset.left +  13, inset.top +  75,  81, 17);

       currentNameTextField.setBounds (inset.left +  28, inset.top +  45, 192, 22);
       newNameTextField.setBounds     (inset.left +  28, inset.top + 100, 192, 22);

       changeButton.setBounds         (inset.left +  41, inset.top + 136,  67, 22);
       cancelButton.setBounds         (inset.left + 139, inset.top + 136,  67, 22);
    }

    private void clearEntries()
    {
        currentNameTextField.setText("");
        newNameTextField.setText("");
    }

    private void setNames()
    {
        //make sure both old and new names are given
        currentName = currentNameTextField.getText();
        newName     = newNameTextField.getText();

        if ( currentName.equals("") || newName.equals("") ) {
           canceled = true;
        }
        else {
           canceled = false;
        }
    }

}
