package NoteBook.Command.CommandFactory;

import NoteBook.Command.Command.AboutCommand;
import NoteBook.Command.Command.Command;
import NoteBook.Services.NoteBookService;

import java.util.Map;

/**
 * Created by Маша on 16.06.2017.
 */
public class AboutCommandFactory implements CommandFactory {

    private static Class commandClass = AboutCommand.class;

    @Override
    public Command createCommand(NoteBookService noteBookService, Map<String, Object> commandParams) {
        AboutCommand command = new AboutCommand();
        command.setNoteBookService(noteBookService);

        return command;
    }

    public static Class getCommandClass() {
        return commandClass;
    }
}
