package NoteBook.Command.CommandFactory;

import NoteBook.Command.Command.Command;
import NoteBook.Command.Command.DeleteCommand;
import NoteBook.Command.Command.FindByIDCommand;
import NoteBook.Services.NoteBookService;

import java.util.Map;

/**
 * Created by Маша on 15.06.2017.
 */
public class FindByIDCommandFactory implements CommandFactory {

    public static Class commandClass = FindByIDCommand.class;

    @Override
    public Command createCommand(NoteBookService noteBookService, Map<String, Object> commandParams) {
        FindByIDCommand findByIDCommand = new FindByIDCommand();
        findByIDCommand.setNoteBookService(noteBookService);

        findByIDCommand.setRecordID((int)commandParams.get("recordID"));

        return findByIDCommand;
    }

    public static Class getCommandClass() {
        return commandClass;
    }
}
