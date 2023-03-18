package minefield;

/*
Edits:
    Stanley 3/16/23: created file
    Stanley 3/16/23: implemented makeModel, makeView, getTitle, getEditCommmands, makeEditCommands
    Stanley 3/17/23: implemented getHelp, about, updated makeEditCommands to return ChangeCommand
*/

import mvc.*;
public class MineFieldFactory implements AppFactory {

    @Override
    public Model makeModel() {
        return new MineField();
    }

    @Override
    public View makeView(Model m) {
        return new MineFieldView(m);
    }

    @Override
    public String getTitle() {
    return "MineField";
    }

    @Override
    public String[] getHelp() {
        return new String[]{"Click a button to move the player in the specified direction."};
    }

    @Override
    public String about() {
        return "MineField, 2023. All rights reserved.\nTeam 6: Stanley Nguyen, Bryant Bautista, Sanjana Jagarlapudi\nMarch 21, 2023";
    }

    @Override
    public String[] getEditCommands() {
        return new String[]{"NW", "N", "NE", "W", "E", "SW", "S", "SE"};
    }

    @Override
    public Command makeEditCommand(Model m, String s, Object o) {
        try
        {
            Heading heading = Heading.valueOf(s);
            return new ChangeCommand(m, heading);
        }
        catch (IllegalArgumentException e) { return null; }
    }
}
