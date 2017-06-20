package NoteBook.Command.CommandFactory;

import NoteBook.Command.Command.Command;
import NoteBook.Command.Command.CommandDecorator.AddProgramNameDecorator;
import NoteBook.Command.Command.CommandWorkedWithNoteBook;
import NoteBook.Command.Command.FindByIDCommand;
import NoteBook.Services.NoteBookService;

import java.util.Map;

/**
 * Created by Маша on 15.06.2017.
 */
public class FindByIDCommandFactory implements CommandFactory {

    public static Class commandClass = FindByIDCommand.class;

    @Override
    public Command createCommand(NoteBookService noteBookService, Map<String, Object> commandParams, Map<String, String> globalParams) {
        FindByIDCommand command = new FindByIDCommand();
        command.setNoteBookService(noteBookService);

        command.setRecordID((int)commandParams.get("recordID"));
        command.setGlobalParams(globalParams);

        return new AddProgramNameDecorator(command);
    }

    public static Class getCommandClass() {
        return commandClass;
    }
}
