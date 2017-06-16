package NoteBook.Command.CommandFactory;

import NoteBook.Command.Command.Command;
import NoteBook.Command.Command.DeleteCommand;
import NoteBook.Services.NoteBookService;
import NoteBook.View.View;

import java.util.Map;

/**
 * Created by Маша on 15.06.2017.
 */
public class DeleteCommandFactory implements CommandFactory {

    @Override
    public Command createCommand(NoteBookService noteBookService, Map<String, Object> commandParams) {
        DeleteCommand deleteCommand = new DeleteCommand();
        deleteCommand.setNoteBookService(noteBookService);

        deleteCommand.setRecordID((int)commandParams.get("recordID"));

        return deleteCommand;
    }
}
