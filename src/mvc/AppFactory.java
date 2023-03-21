package mvc;

/*
Edits:
    Stanley 3/8/23: created file
    Stanley 3/8/23: completed file
    Stanley 3/16/23: updated makeView() and makeEditCommand() with new parameters
*/

public interface AppFactory {
    public Model makeModel();
    public View makeView(Model m);
    public String getTitle();
    public String[] getHelp();
    public String about();
    public String[] getEditCommands();
    public Command makeEditCommand(Model m, String s, Object o);
}
