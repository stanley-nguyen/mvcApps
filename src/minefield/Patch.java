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
    private int xLoc;
    private int yLoc;
    private boolean selected;

    private int numMines;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Patch(int x, int y){
        this.bomb = false;
        this.xLoc = x;
        this.yLoc = y;
        numMines = 0;
        selected = false;
    }

    public boolean isBomb() {
        return bomb;
    }

    public int getXLoc() {
        return xLoc;
    }

    public int getYLoc() {
        return yLoc;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public void setXLoc(int xLoc) {
        this.xLoc = xLoc;
    }

    public void setYLoc(int yLoc) {
        this.yLoc = yLoc;
    }

    public int getNumMines() {
        return numMines;
    }

    public void setNumMines(int numMines) {
        this.numMines = numMines;
    }
}
