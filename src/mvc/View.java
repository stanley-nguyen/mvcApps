package mvc;

/*
Edits:
    Stanley 3/12/23: created file
    Stanley 3/12/23: completed file
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
    }

    public void setModel(Model m)
    {
        model.removePropertyChangeListener(this);
        model = m;
        model.addPropertyChangeListener(this);
        model.initSupport();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}