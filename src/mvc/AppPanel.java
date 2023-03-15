package mvc;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
/**
 * This class establishes the App Panel, on which both the Control Panel and the Canvas can be seen.
 */
/*
Edits:
    Sanjana 3/13/23: created file and implemented basic methods and design of class

*/
public class AppPanel extends JPanel implements ActionListener, PropertyChangeListener {

        private Model m;
        private ControlPanel controls;
        private View view; //this is canvas view

        private String fileName;

        public AppPanel() {
            // create model, install controls & view
            m = new Model();
            view = new View(m);
            controls = new ControlPanel();
            this.setLayout((new GridLayout(World.APP_PANEL_ROWS, World.APP_PANEL_COLS)));
            this.add(controls);
            this.add(view);
            // create my frame with menus and display it
            SafeFrame frame = new SafeFrame();
            Container cp = frame.getContentPane();
            cp.add(this);
            frame.setJMenuBar(this.createMenuBar());
            frame.setTitle("Mine Field");
            frame.setSize(World.APP_PANEL_WIDTH, World.APP_PANEL_HEIGHT);
            frame.setVisible(true);
        }

        protected JMenuBar createMenuBar() {
            JMenuBar result = new JMenuBar();
            JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Save As", "Open", "Quit"}, this);
            result.add(fileMenu);
            JMenu editMenu = Utilities.makeMenu("Edit", new String[]{ "North", "East", "West", "South", "Clear",
                    "Pen", "Color", "# of Steps" }, this);
            result.add(editMenu);
            JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
            result.add(helpMenu);
            return result;
        }

        /**
         * This method processes events performed by the users when they interact with the control panel.
         * @param e the event to be processed
         */
        public void actionPerformed(ActionEvent e) {
            String cmmd = e.getActionCommand();
            //2.0: New, Save, SaveAs, Open, Quit, Help, and About.
            //Original: North, East, West, South, Clear, Pen, Color;
            try {
                switch (cmmd) {
                    case "Save": {
                        if(fileName == null){
                            fileName = Utilities.getFileName((String) null, false);
                            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
                            os.writeObject(this.m);
                            os.close();
                        }
                        break;
                    }
                    case "Save As": {
                        String fName = Utilities.getFileName((String) null, false);
                        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                        os.writeObject(this.m);
                        os.close();
                        break;
                    }
                    case "Open": {
                        if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                            String fName = Utilities.getFileName((String) null, true);
                            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                            m = (Model) is.readObject();
                            view.setModel(m);
                            is.close();
                        }
                        break;
                    }
                    case "New": {
                        if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                            m = new Model();
                            view.setModel(m); //set turtle
                        }
                        break;
                    }
                    case "Quit": {
                        if (Utilities.confirm("Are you sure? Unsaved changes will be lost!"))
                            System.exit(0);
                        break;
                    }
                    case "About": {
                        Utilities.inform("Cyberdellic Designs Turtle Graphics, 2021. All rights reserved.");
                        break;
                    }
                    case "Help": {
                        String[] cmmds = new String[]{
                                "North: Changes the Direction of the Turtle to North" + "\n" +
                                        "East: Changes the Direction of the Turtle to East " + "\n" +
                                        "West: Changes the Direction of the Turtle to West" + "\n" +
                                        "South: Changes the Direction of the Turtle to South" + "\n" +
                                        "Clear: Clears the current drawings" + "\n" +
                                        "Pen: Toggles the pen to be either up or down" + "\n" +
                                        "Color: Allows user to choose the color of the line to be drawn by the turtle" + " \n" +
                                        "# of Steps: Move the turtle the specified number of steps in the specified direction",
                        };
                        Utilities.inform(cmmds);
                        break;
                    }
                    default: {
                        throw new Exception("Unrecognized command: " + cmmd);
                    }
                }
            } catch (Exception ex) {
                Utilities.error(ex); // all error handling done here!
            }
        }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    /**
         * This is an Inner class that establishes the control panel on which many buttons
         * and selections are displayed for the user to manipulate appearance of the Canvas.
         */
        class ControlPanel extends JPanel {
            public ControlPanel() {



            }
        }
        // and away we go ...
        public static void main(String[] args) {
            AppPanel app = new AppPanel();
        }

}
