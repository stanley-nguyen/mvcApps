package minefield;

/*
Edits:
    Bryant 3/17/23: created file
*/

import mvc.*;

public class MineFieldCommand extends Command {
    private Heading heading;

    public MineFieldCommand(Model model, Heading heading) {
        super(model);
        this.heading = heading;
    }

    public void execute() {
        MineField minefield = (MineField) model;
        // change() method to be implemented on Minefield.java
        // moves player based on specified heading
        minefield.move(heading);
    }
}
