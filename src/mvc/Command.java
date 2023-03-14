package mvc;

/*
Edits:
    Bryant 3/13/23: Implemented Command.java class
*/

public abstract class Command {
    protected Model model;
    public Command (Model model) {
        this.model = model;
    }
    public abstract void execute();
}
