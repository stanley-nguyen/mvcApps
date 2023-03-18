package mvc;

import javax.swing.*;
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
    Stanley 3/17/23: updated file with AppFactory constructor and methods using factory
    Stanley 3/17/23: added display(), setModel() and getModel() from instructor
    Stanley 3/18/23: updated actionPerformed() commands
*/
public class AppPanel extends JPanel implements ActionListener, PropertyChangeListener {

        protected Model model;
        protected JPanel controlPanel;
        protected View view; //this is canvas view

//        private String fileName;
        private AppFactory af;
        private SafeFrame frame;
        public static int FRAME_WIDTH = 500;
        public static int FRAME_HEIGHT = 300;

        public AppPanel(AppFactory factory) {
            // create model, install controls & view
            af = factory;
            model = af.makeModel();
            view = af.makeView(model);
            controlPanel = new JPanel();
            addPropertyChangeListener(this);
            this.setLayout((new GridLayout(1, 2)));
            this.add(controlPanel);
            this.add(view);
            // create my frame with menus and display it
            frame = new SafeFrame();
            Container cp = frame.getContentPane();
            cp.add(this);
            frame.setJMenuBar(this.createMenuBar());
            frame.setTitle(af.getTitle());
            frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
//            frame.setVisible(true);
        }

        protected JMenuBar createMenuBar() {
            JMenuBar result = new JMenuBar();
            JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Save As", "Open", "Quit"}, this);
            result.add(fileMenu);
            JMenu editMenu = Utilities.makeMenu("Edit", af.getEditCommands(), this);
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
//                        if(fileName == null){
//                            fileName = Utilities.getFileName((String) null, false);
//                            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
//                            os.writeObject(this.model);
//                            os.close();
//                        }
                        Utilities.save(model, false);
                        break;
                    }
                    case "Save As": {
//                        String fName = Utilities.getFileName((String) null, false);
//                        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
//                        os.writeObject(this.model);
//                        os.close();
                        Utilities.save(model, true);
                        break;
                    }
                    case "Open": {
                        if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                            String fName = Utilities.getFileName((String) null, true);
                            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                            model = (Model) is.readObject();
                            setModel(model);
                            is.close();
                        }
                        break;
                    }
                    case "New": {
                        if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                            model = af.makeModel();
                            setModel(model); // set model
                        }
                        break;
                    }
                    case "Quit": {
                        if (Utilities.confirm("Are you sure? Unsaved changes will be lost!"))
                            System.exit(0);
                        break;
                    }
                    case "About": {
                        Utilities.inform(af.about());
                        break;
                    }
                    case "Help": {
                        String[] cmmds = af.getHelp();
                        Utilities.inform(cmmds);
                        break;
                    }
                    default: {
                        throw new Exception("Unrecognized command: " + cmmd);
                    }
                }
            } catch (Exception ex) {
                handleException(ex); // all error handling done here!
            }
        }

        public void display() { frame.setVisible(true); }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {

        }

        public Model getModel() { return model; }

        // called by file/open and file/new
        public void setModel(Model newModel) {
            this.model.removePropertyChangeListener(this);
            this.model = newModel;
            this.model.initSupport(); // defined in Bean
            this.model.addPropertyChangeListener(this);
            view.setModel(this.model);
            model.changed();
        }

        protected void handleException(Exception e)
        {
            Utilities.error(e);
        }

        // and away we go ...
//        public static void main(String[] args) {
//            AppPanel app = new AppPanel();
//        }

}
