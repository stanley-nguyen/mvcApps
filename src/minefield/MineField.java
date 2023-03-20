package minefield;

import java.util.ArrayList;
import java.util.Random;

/*
    Edits:
    Sanjana 3/18/23: Created Class
    Sanjana 3/18/23: Created instance variables and constructor
    Sanjana 3/19/23: Implemented the makeGrid method that randomly assigns bombs in the grid
    Sanjana 3/19/23: Implemented makeGrid() and setPatchNums()
 */
public class MineField {
    private Patch[][] grid = new Patch[mineFieldLength][mineFieldWidth];
    private int xLoc;
    private int yLoc;
    private final Random generator = new Random();

    public static int percentMined = 5;
    public static int mineFieldWidth;
    public static int mineFieldLength;


    public MineField(Patch patch){
        //Initializing the grid array
        for(int i = 0; i < mineFieldLength; i++) {
            for (int j = 0; j < mineFieldWidth; j++) {
                grid[i][j] = new Patch();
            }
        }
        this.xLoc = 0;
        this.yLoc = 0;

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

    public void move(Heading h){
        if (h == Heading.N){
            yLoc--;
        }
        if (h == Heading.NE){
            yLoc--;
            xLoc++;
        }
        if (h == Heading.E){
            xLoc++;
        }
        if (h == Heading.SE){
            yLoc++;
            xLoc++;
        }
        if (h == Heading.S){
            yLoc++;
        }
        if (h == Heading.SW){
            yLoc++;
            xLoc--;
        }
        if (h == Heading.W){
            xLoc--;
        }if (h == Heading.NW){
            yLoc--;
            xLoc--;
        }


    }

}
