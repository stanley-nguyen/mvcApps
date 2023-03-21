package minefield;

/*
Edits:
    Bryant 3/17/23: created file
    Bryant 3/20/23: renamed change() method to move() method to match move() method in Minefield.java
    Stanley 3/21/23: execute() throws exception when model is not MineField
*/

import mvc.*;

public class MineFieldCommand extends Command {
    private Heading heading;

    public MineFieldCommand(Model model, Heading heading) {
        super(model);
        this.heading = heading;
    }

    public void execute() throws Exception {
        if (!(model instanceof MineField)) {
            throw new Exception("Model must instantiate MineField");
        }
        MineField minefield = (MineField)model;
        // change() or move() method to be implemented on Minefield.java
        // moves player based on specified heading
        minefield.move(heading);
    }
}
