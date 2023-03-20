package mvc;

/*
Edits:
    Stanley 3/8/23: created file
    Stanley 3/8/23: completed file
*/


public interface AppFactory {
    public Model makeModel();
    public View makeView();
    public String getTitle();
    public String[] getHelp();
    public String about();
    public String[] getEditCommands();
    public Command makeEditCommand(String s);
}
