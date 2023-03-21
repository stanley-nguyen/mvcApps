package mvc;

/*
Edits:
    Stanley 3/12/23: created file
    Stanley 3/12/23: completed file
    Stanley 3/20/23: added initSupport to constructor
 */

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View extends JPanel implements PropertyChangeListener {
    protected Model model;

    public View(Model m)
    {
        model = m;
        model.addPropertyChangeListener(this);
        model.initSupport();
    }

    public void setModel(Model m)
    {
        model.removePropertyChangeListener(this);
        model = m;
        model.initSupport();
        model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}