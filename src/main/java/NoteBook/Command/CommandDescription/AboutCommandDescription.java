package NoteBook.Command.CommandDescription;

import NoteBook.Command.Command.AboutCommand;
import NoteBook.Command.ParamDescription.ParamDescription;

/**
 * Created by Маша on 16.06.2017.
 */
public class AboutCommandDescription extends CommandDescription {
    public AboutCommandDescription(String commandName) {
        super(commandName, AboutCommand.class);
    }
}
