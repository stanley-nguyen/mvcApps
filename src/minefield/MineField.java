package minefield;

import java.util.ArrayList;
import java.util.Random;
import mvc.*;

/*
    Edits:
    Sanjana 3/18/23: Created Class
    Sanjana 3/18/23: Created instance variables and constructor
    Sanjana 3/19/23: Implemented the makeGrid method that randomly assigns bombs in the grid
    Sanjana 3/19/23: Implemented makeGrid() and setPatchNums()
    Bryant 3/20/23: Modified move() method and Utilties.error for the given scenarios
    Bryant 3/20/23: Added goal location in public Minefield() for isGoal() method
 */
public class MineField extends Model{
    private Patch[][] grid = new Patch[mineFieldLength][mineFieldWidth];
    private int xLoc;
    private int yLoc;
    private final Random generator = new Random();

    public static int percentMined = 5;
    public static int mineFieldWidth;
    public static int mineFieldLength;
    private int goalX;
    private int goalY;


    public MineField(){
        //Initializing the grid array
        for(int i = 0; i < mineFieldLength; i++) {
            for (int j = 0; j < mineFieldWidth; j++) {
                grid[i][j] = new Patch();
            }
        }
        this.xLoc = 0;
        this.yLoc = 0;
        
        // Setting the goal location (bottom-right corner of Minefield)
        goalX = mineFieldWidth - 1;
        goalY = mineFieldLength -1;
        grid[goalY][goalX].setGoal(true);
    }
    //Method to set up random mines around the grid
     public void setUpMines(){
       for(int i = 0; i < (mineFieldWidth * mineFieldLength) * (.01 * percentMined); i++){
           int x = generator.nextInt(0, mineFieldWidth);
           int y = generator.nextInt(0, mineFieldLength);
           if(!grid[x][y].isBomb()){
               grid[x][y].setBomb(true);
           }
           else{ //If the randomly chosen patch already happens to have a bomb, then we want the method to reassign the bomb
                        //to another patch, thus decrement i, so it gains another iteration.
               i = i-1;
           }
       }
     }

    //method to initialize patch numbers
     public void setPatchNums(){
        //In order to find how many mines a patch touches, we must check all 8 adjacent patches
         //If any one of them has a mine, increment the "numMines" field of the current patch
        for(int i = 0; i < mineFieldLength; i++){
            for(int j = 0; j < mineFieldWidth; j++){
                Patch current = grid[i][j];
                if(i - 1 >= 0 && j - 1 >= 0){ //Checking for mine in patch in top left corner
                    if (grid[i - 1][j - 1].isBomb()){
                        current.setNumMines(current.getNumMines() + 1);
                    }
                }
                if(i - 1 >= 0){ //Checking for mine in patch on top
                    if (grid[i - 1][j].isBomb()){
                        current.setNumMines(current.getNumMines() + 1);
                    }
                }
                if(i - 1 >= 0 && j + 1 < mineFieldWidth){ //Checking for mine in patch on top right corner
                    if (grid[i - 1][j + 1].isBomb()){
                        current.setNumMines(current.getNumMines() + 1);
                    }
                }
                if(j - 1 >= 0){ //Checking for mine in patch on the left side
                    if (grid[i][j - 1].isBomb()){
                        current.setNumMines(current.getNumMines() + 1);
                    }
                }
                if(j + 1 < mineFieldWidth){ //Checking for mine in patch on the right side
                    if (grid[i][j + 1].isBomb()){
                        current.setNumMines(current.getNumMines() + 1);
                    }
                }
                if(i + 1 < mineFieldLength && j - 1 >= 0){ //Checking for mine in patch on bottom left corner
                    if (grid[i + 1][j - 1].isBomb()){
                        current.setNumMines(current.getNumMines() + 1);
                    }
                }
                if(i + 1 < mineFieldLength){ //Checking for mine in patch on bottom
                    if (grid[i + 1][j].isBomb()){
                        current.setNumMines(current.getNumMines() + 1);
                    }
                }
                if(i + 1 < mineFieldLength && j + 1 < mineFieldWidth){ //Checking for mine in patch on bottom left corner
                    if (grid[i + 1][j + 1].isBomb()){
                        current.setNumMines(current.getNumMines() + 1);
                    }
                }
            }
        }
     }

    public void move(Heading h) {
        int newXLoc = xLoc;
        int newYLoc = yLoc;

        // switch statement (based on heading)
        switch(h) {
            case N: {
                newYLoc--;
                break;
            }
            case NE: {
                newYLoc--;
                newXLoc++;
                break;
            }
            case E: {
                newXLoc++;
                break;
            }
            case SE: {
                newYLoc++;
                newXLoc++;
                break;
            }
            case S: {
                newYLoc++;
                break;
            }
            case SW: {
                newYLoc++;
                newXLoc--;
                break;
            }
            case W: {
                newXLoc--;
                break;
            }
            case NW: {
                newYLoc--;
                newXLoc--;
                break;
            }
        }

        // When player tries to move off of the grid (location out of bounds)
        if (newXLoc < 0 || newXLoc >= mineFieldWidth || newYLoc < 0 || newYLoc >= mineFieldLength) {
            Utilities.error("Player has moved off of the grid.");
        }

        // When player steps on a mine
        if (grid[newYLoc][newXLoc].isBomb()) {
            Utilities.error("Player has stepped on a mine.");
        }
        /*
        // When player reaches the goal
        if (grid[newYLoc][newXLoc].isGoal()) {
            Utilities.error("Player has reached the goal.");
        }
        */
        // Player's updated location
        xLoc = newXLoc;
        yLoc = newYLoc;
    }

}
