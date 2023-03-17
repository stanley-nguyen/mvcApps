package minefield;

/*
Edits:
    Stanley 3/16/23: created file
    Stanley 3/16/23: added occupied variable getter and setter
*/

import javax.swing.*;

public class Cell extends JLabel {
    private Boolean occupied;

    public Cell()
    {
        occupied = false;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }
}
