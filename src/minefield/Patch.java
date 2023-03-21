package minefield;
/*
    Edits:
    Sanjana 3/18/23: Created Class
    Sanjana 3/18/23: Created instance variables and constructor
    Sanjana 3/18/23: Created Getters and Setters for instance variables
    Bryant 3/20/23: Added boolean attribute, getter, and setter for goal
    Stanley 3/20/23: Added getter and setter for discovered variable, implemented Serializable
 */

import java.io.Serializable;

/**
 * This class is a representation of each cell in the 2D array that makes up the mineField grid. Each "patch" is a cell
 * and has certain characteristics, such as containing bombs.
 */
public class Patch implements Serializable {
    private boolean bomb;
    private int numMines;
    private boolean goal;
    private boolean discovered;

    public Patch(){
        this.bomb = false;
        numMines = 0;
        discovered = false;
    }

    public boolean isDiscovered() { return discovered; }

    public void setDiscovered(boolean discovered) { this.discovered = discovered; }

    public boolean isBomb() {
        return bomb;
    }
    
    public boolean isGoal() {
        return goal;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }
    
    public void setGoal(boolean goal) {
        this.goal = goal;
    }

    public int getNumMines() {
        return numMines;
    }

    public void setNumMines(int numMines) {
        this.numMines = numMines;
    }
}
