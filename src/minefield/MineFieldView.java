package minefield;

/*
Edits:
    Stanley 3/16/23: created file
    Stanley 3/16/23: partially implemented mine grid and created test main() function
*/

import mvc.*;
import java.awt.*;
import javax.swing.*;

public class MineFieldView extends View {

    private Cell cells[][];
    private static int DIM = 20;

    public MineFieldView(Model m)
    {
        super(m);

        cells = new Cell[DIM][DIM];
        setLayout(new GridLayout(DIM, DIM));

        // row iterator
        for(int r = 0; r < DIM; r++)
        {
            // column iterator
            for(int c = 0; c < DIM; c++)
            {
                cells[r][c] = new Cell();
                cells[r][c].setText(" ? ");
                cells[r][c].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                // traveled cells tba, need MineField.java

                // if cell is goal (in bottom-right corner)
                if(r == DIM-1 & c == DIM-1)
                {
                    cells[r][c].setBorder(BorderFactory.createLineBorder(Color.GREEN));
                }

                this.add(cells[r][c]);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setTitle("MineField");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        MineFieldView view = new MineFieldView(new Model());
        panel.add(view);
        frame.add(panel);
        frame.setVisible(true);
    }
}