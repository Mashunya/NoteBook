package NoteBook.Command.CommandFactory;

import NoteBook.Command.Command.AboutCommand;
import NoteBook.Command.Command.Command;
import NoteBook.Command.Command.HelpCommand;
import NoteBook.Services.NoteBookService;

import java.util.Map;

/**
 * Created by Маша on 16.06.2017.
 */
public class HelpCommandFactory implements CommandFactory {

    private static Class commandClass = HelpCommand.class;

    @Override
    public Command createCommand(NoteBookService noteBookService, Map<String, Object> commandParams) {
        HelpCommand command = new HelpCommand();
        command.setNoteBookService(noteBookService);

        return command;
    }

    public static Class getCommandClass() {
        return commandClass;
    }
}
