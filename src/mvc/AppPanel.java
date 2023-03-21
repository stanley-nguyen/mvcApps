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
    Stanley 3/20/23: added edit commands to actionPerformed
    Stanley 3/20/23: added property change to New and Open, fixed default switch in actionPerformed, added try catch block in setModel
*/
public class AppPanel extends JPanel implements ActionListener, PropertyChangeListener {

        protected Model model;
        protected JPanel controlPanel;
        protected View view; //this is canvas view

//        private String fileName;
        private AppFactory af;
        private SafeFrame frame;
        public static int FRAME_WIDTH = 700;
        public static int FRAME_HEIGHT = 500;

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
            try {
                switch (cmmd) {
                    case "Save": {
                        Utilities.save(model, false);
                        break;
                    }
                    case "Save As": {
                        Utilities.save(model, true);
                        break;
                    }
                    case "Open": {
                        if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                            String fName = Utilities.getFileName((String) null, true);
                            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                            Model oldModel = model;
                            model = (Model) is.readObject();
                            setModel(model);
                            model.firePropertyChange("Open", oldModel, model);
                            is.close();
                        }
                        break;
                    }
                    case "New": {
                        if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                            Model oldModel = model;
                            model = af.makeModel();
                            setModel(model); // set model
                            model.firePropertyChange("New", oldModel, model);
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
                        boolean cmmdExists = false;
                        for(String s : af.getEditCommands())
                        {
                            if(cmmd.equals(s))
                            {
                                af.makeEditCommand(model, s, null).execute();
                                cmmdExists = true;
                                break;
                            }
                        }
                        if(cmmdExists) break;
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
            repaint();
        }

        public Model getModel() { return model; }

        // called by file/open and file/new
        public void setModel(Model newModel) {
            try { this.model.removePropertyChangeListener(this); }
            catch(NullPointerException e){}

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
