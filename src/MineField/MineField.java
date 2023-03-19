package MineField;

import java.util.ArrayList;
import java.util.Random;
import java.util.random.RandomGenerator;

/*
    Edits:
    Sanjana 3/18/23: Created Class
    Sanjana 3/18/23: Created instance variables and constructor
    Sanjana 3/19/23: Implemented the makeGrid method that randomly assigns bombs in the grid
 */
public class MineField {
    private ArrayList<ArrayList<Patch>> grid = new ArrayList<ArrayList<Patch>>();
    private final Random generator = new Random();
    private Patch current; //The current patch that the gamer player is on
    public static int percentMined = 5;
    public static int mineFieldWidth;
    public static int mineFieldLength;


    public MineField(Patch location){
        this.current = location;
    }
     public void makeGrid(){
       for(int i = 0; i < (mineFieldWidth * mineFieldLength) * (.01 * percentMined); i++){
           int x = generator.nextInt(0, mineFieldWidth);
           int y = generator.nextInt(0, mineFieldLength);
           if(!grid.get(x).get(y).isBomb()){
               grid.get(x).get(y).setBomb(true);
           }
           else{ //If the randomly chosen patch already happens to have a bomb, then we want the method to reassign the bomb
                        //to another patch, thus decrement i, so it gains another iteration.
               i = i-1;
           }
       }
     }

}
