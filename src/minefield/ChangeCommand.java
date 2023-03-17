package minefield;

import mvc.*;
public class ChangeCommand extends Command {
    private Heading heading;

    public ChangeCommand(Model model, Heading heading) {
        super(model);
        this.heading = heading;
    }

    public void execute() {
        Minefield minefield = (Minefield)model;
        // change() method from Minefield.java calls the changed() method from Model.java
        // moves player based on specified heading
        minefield.change(heading);
    }
}
