/*
    class MenuFrame

    This frame includes one MenuBar, two Menu objects File and Edit,
    and eight MenuItem objects. When a menu item is selected, a string
    showing which menu choice is selected will appear on the frame.

*/

import java.awt.*;
import java.awt.event.*;

class MenuFrame extends Frame implements ActionListener
{

/******************
   Data Members
******************/

   private static final int FRAME_WIDTH    = 450;
   private static final int FRAME_HEIGHT   = 300;
   private static final int FRAME_X_ORIGIN = 150;
   private static final int FRAME_Y_ORIGIN = 250;

   private Label   response;
   private Menu    fileMenu;
   private Menu    editMenu;

/******************
   Constructor
******************/

   public MenuFrame()
   {
      //set the frame properties
      setTitle     ("Testing Menu");
      setSize      (FRAME_WIDTH, FRAME_HEIGHT);
      setResizable (false);
      setLocation  (FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
      setLayout    (null);

      //create two menus and their menu items
      createFileMenu();
      createEditMenu();

      //and add them to the menubar
      MenuBar menuBar = new MenuBar();
      setMenuBar(menuBar);
      menuBar.add(fileMenu);
      menuBar.add(editMenu);

      //create and position reponse label
      response = new Label("Hello, this is your menu tester." );
      response.setBounds(100, 100, 250, 50);
      add(response);

      addWindowListener( new ProgramTerminator() );
   }

/*****************************
   Menu and MenuItem Creation
*****************************/

   private void createFileMenu( )
   {
      MenuItem    item;
        
      fileMenu = new Menu("File");

      item = new MenuItem("New");        //New
      item.addActionListener( this );
      fileMenu.add( item );

      item = new MenuItem("Open...");    //Open...
      item.addActionListener( this );
      fileMenu.add( item );

      item = new MenuItem("Save");       //Save
      item.addActionListener( this );
      fileMenu.add( item );

      item = new MenuItem("Save As..."); //Save As...
      item.addActionListener( this );
      fileMenu.add( item );

      fileMenu.addSeparator();           //add a horizontal separator line

      item = new MenuItem("Quit");       //Quit
      item.addActionListener( this );
      fileMenu.add( item );
   }

   private void createEditMenu( )
   {
      MenuItem    item;

      editMenu = new Menu("Edit");

      item = new MenuItem("Cut");      //Cut
      item.addActionListener( this );
      editMenu.add( item );

      item = new MenuItem("Copy");    //Copy
      item.addActionListener( this );
      editMenu.add( item );

      item = new MenuItem("Paste");    //Paste
      item.addActionListener( this );
      editMenu.add( item );
   }

/****************************
    Action Event Handling
****************************/

   public void actionPerformed(ActionEvent event)
   {
      String  menuName;

      menuName = event.getActionCommand();

      if (menuName.equals("Quit")) {
         System.exit(0);
      }
      else {
         response.setText(menuName + " is selected.");
      }
   }

}
