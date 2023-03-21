package mvc;

/*
Edits:
    Bryant 3/13/23: Implemented Command.java class
    Stanley 3/21/23: added Exception to execute()
*/

public abstract class Command {
    protected Model model;
    
    public Command (Model model) {
        this.model = model;
    }
    
    // execute() method will be implemented in the ChangeCommand class (in the Minefield package)
    public abstract void execute() throws Exception;
}
