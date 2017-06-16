package NoteBook.Command.CommandFactory;

import NoteBook.Command.Command.AddCommand;
import NoteBook.Command.Command.Command;
import NoteBook.Services.NoteBookService;

import java.util.Map;

/**
 * Created by Маша on 15.06.2017.
 */
public class AddCommandFactory implements CommandFactory {

    public static Class commandClass = AddCommand.class;

    @Override
    public Command createCommand(NoteBookService noteBookService, Map<String, Object> commandParams) {
        AddCommand addCommand = new AddCommand();
        addCommand.setNoteBookService(noteBookService);

        addCommand.setText((String)commandParams.get("text"));
        addCommand.setAuthor((String)commandParams.get("author"));
        addCommand.setTitle((String)commandParams.get("title"));
        addCommand.setType((String)commandParams.get("type"));

        return addCommand;
    }

    public static Class getCommandClass() {
        return commandClass;
    }
}
