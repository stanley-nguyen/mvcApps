package minefield;
/*
    Edits:
    Sanjana 3/18/23: Created Class
    Sanjana 3/18/23: Created instance variables and constructor
    Sanjana 3/18/23: Created Getters and Setters for instance variables
 */

/**
 * This class is a representation of each cell in the 2D array that makes up the mineField grid. Each "patch" is a cell
 * and has certain characteristics, such as containing bombs.
 */
public class Patch {
    private boolean bomb;

    private int numMines;


    public Patch(){
        this.bomb = false;
        numMines = 0;
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }


    public int getNumMines() {
        return numMines;
    }

    public void setNumMines(int numMines) {
        this.numMines = numMines;
    }
}
