package minefield;
import mvc.*;
/**
 * Edits:
 * Sanjana 3/18/23: Created MineFieldPanel Class.
 * Sanjana 3/18/23: Wrote Constructor
 * Sanjana 3/18/23: wrote main method.
 * Bryant 3/21/23: Added GridLayout for the control panel buttons
 */
import javax.swing.*;
import java.awt.event.ActionEvent;

public class MineFieldPanel extends AppPanel {
    private JButton N; //change
    private JButton NE;
    private JButton E;
    private JButton SE;
    private JButton S;
    private JButton SW;
    private JButton W;
    private JButton NW;


    public MineFieldPanel(AppFactory factory) {
        super(factory);
        controlPanel.setLayout(new GridLayout(4,2);
        
        N = new JButton("N");
        N.addActionListener(this);
        controlPanel.add(N);

        NE = new JButton("NE");
        NE.addActionListener(this);
        controlPanel.add(NE);

        E = new JButton("E");
        E.addActionListener(this);
        controlPanel.add(E);

        SE = new JButton("SE");
        SE.addActionListener(this);
        controlPanel.add(SE);

        S = new JButton("S");
        S.addActionListener(this);
        controlPanel.add(S);

        SW = new JButton("SW");
        SW.addActionListener(this);
        controlPanel.add(SW);

        W = new JButton("W");
        W.addActionListener(this);
        controlPanel.add(W);

        NW = new JButton("NW");
        NW.addActionListener(this);
        controlPanel.add(NW);

    }




    public static void main(String[] args) {
        AppFactory factory = new MineFieldFactory();
        AppPanel panel = new MineFieldPanel(factory);
        panel.display();
    }

}
