package NoteBook.Command.CommandDescription;

import NoteBook.Command.Command.FindAllCommand;

/**
 * Created by Маша on 16.06.2017.
 */
public class FindAllCommandDescription extends CommandDescription {
    public FindAllCommandDescription(String commandName) {
        super(commandName, FindAllCommand.class);
    }
}
