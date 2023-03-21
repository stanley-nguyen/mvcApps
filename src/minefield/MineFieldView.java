package minefield;

/*
Edits:
    Stanley 3/16/23: created file
    Stanley 3/16/23: partially implemented mine grid and created test main() function
    Stanley 3/19/23: nested Cell class inside MineFieldView
    Stanley 3/20/23: implemented constructor to create Patch grid, added update() for propertyChange(), added New and Open case for propertyChange
    Stanley 3/21/23: highlight current player position in light_gray
*/

import mvc.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import javax.swing.*;

public class MineFieldView extends View {

    private Cell[][] cells;
    private static int DIM = 20;

    public MineFieldView(Model m) {
        super(m);

        MineField model = (MineField) m;

        Patch[][] mf = model.getMineField();

        cells = new Cell[DIM][DIM];
        setLayout(new GridLayout(DIM, DIM));
        setBackground(Color.GRAY);

        // row iterator
        for(int r = 0; r < cells.length; r++) {
            // column iterator
            for (int c = 0; c < cells[r].length; c++) {
                cells[r][c] = new Cell(mf[r][c]);
                cells[r][c].setText(" ? ");
                cells[r][c].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                cells[0][0].getPatch().setDiscovered(true);
                cells[0][0].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                cells[0][0].setText("" + mf[r][c].getNumMines());

                // if cell is goal (in bottom-right corner)
                if(r == DIM-1 & c == DIM-1)
                {
                    cells[r][c].setBorder(BorderFactory.createLineBorder(Color.GREEN));
                }
                this.add(cells[r][c]);
            }
        }
    }

    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
        if(evt.getPropertyName().equals("New") || evt.getPropertyName().equals("Open"))
        {
            removeAll();
            MineField m = (MineField) model;

            Patch[][] mf = m.getMineField();

            cells = new Cell[DIM][DIM];
            setLayout(new GridLayout(DIM, DIM));

            // row iterator
            for(int r = 0; r < cells.length; r++) {
                // column iterator
                for (int c = 0; c < cells[r].length; c++) {
                    cells[r][c] = new Cell(mf[r][c]);
                    cells[r][c].setText(" ? ");
                    cells[r][c].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                    cells[0][0].getPatch().setDiscovered(true);
                    cells[0][0].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    cells[0][0].setText("" + mf[r][c].getNumMines());

                    // if cell is goal (in bottom-right corner)
                    if(r == DIM-1 & c == DIM-1)
                    {
                        cells[r][c].setBorder(BorderFactory.createLineBorder(Color.GREEN));
                    }
                    this.add(cells[r][c]);
                }
            }
        }
        update();
    }

    public void update()
    {
        MineField field = (MineField) model;

        for(int r = 0; r < cells.length; r++)
        {
            for(int c = 0; c < cells[r].length; c++)
            {
                if(field.getX() == r && field.getY() == c)
                {
                    cells[r][c].getPatch().setDiscovered(true);
                    cells[r][c].setText("" + field.getMineField()[r][c].getNumMines());
                }

                // path of player
                if(field.getX() == r && field.getY() == c && !cells[r][c].getPatch().isGoal())
                {
                    cells[r][c].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

                }
                else if (cells[r][c].getPatch().isDiscovered() && !cells[r][c].getPatch().isGoal())
                {
                    cells[r][c].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                }

                if(cells[r][c].getPatch().isBomb() && field.getX() == r && field.getY() == c)
                {
                    cells[r][c].setText("M");
                    field.setGameOver(true);
                }

                // win
                if(cells[r][c].getPatch().isGoal() && field.getX() == r && field.getY() == c)
                {
                    cells[r][c].setText("W");
                    field.setGameOver(true);
                }

                this.add(cells[r][c]);
            }
        }
    }

    // test minefieldview
//    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        frame.setSize(500, 500);
//        frame.setTitle("MineField");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JPanel panel = new JPanel();
//        MineFieldView view = new MineFieldView(new Model());
//        panel.add(view);
//        frame.add(panel);
//        frame.setVisible(true);
//    }
}
class Cell extends JLabel
{
    Patch patch;
    public Cell(Patch patch)
    {
        this.patch = patch;
    }

    public Patch getPatch() {
        return patch;
    }
}

