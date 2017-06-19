package NoteBook.Command.CommandFactory;

import NoteBook.Command.Command.Command;
import NoteBook.Command.Command.CommandDecorator.AddProgramNameDecorator;
import NoteBook.Command.Command.CommandDecorator.CheckOSDecorator;
import NoteBook.Command.Command.CommandWorkedWithNoteBook;
import NoteBook.Command.Command.DeleteCommand;
import NoteBook.Services.NoteBookService;

import java.util.Map;

/**
 * Created by Маша on 15.06.2017.
 */
public class DeleteCommandFactory implements CommandFactory {

    public static Class commandClass = DeleteCommand.class;

    @Override
    public Command createCommand(NoteBookService noteBookService, Map<String, Object> commandParams, Map<String, String> globalParams) {
        DeleteCommand deleteCommand = new DeleteCommand();
        deleteCommand.setNoteBookService(noteBookService);

        deleteCommand.setRecordID((int)commandParams.get("recordID"));
        deleteCommand.setGlobalParams(globalParams);

        return new AddProgramNameDecorator(new CheckOSDecorator(deleteCommand));
    }

    public static Class getCommandClass() {
        return commandClass;
    }
}
