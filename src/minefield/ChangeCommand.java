package minefield;

/*
Edits:
    Bryant 3/17/23: created file
*/

import mvc.*;

public class ChangeCommand extends Command {
    private Heading heading;

    public ChangeCommand(Model model, Heading heading) {
        super(model);
        this.heading = heading;
    }

    public void execute() {
        Minefield minefield = (Minefield)model;
        // change() method to be implemented on Minefield.java
        // moves player based on specified heading
        minefield.change(heading);
    }
}
