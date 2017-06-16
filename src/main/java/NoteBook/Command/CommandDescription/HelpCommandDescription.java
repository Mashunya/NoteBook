package NoteBook.Command.CommandDescription;

import NoteBook.Command.Command.HelpCommand;

/**
 * Created by Маша on 16.06.2017.
 */
public class HelpCommandDescription extends CommandDescription {
    public HelpCommandDescription(String commandName) {
        super(commandName, HelpCommand.class);
    }
}
