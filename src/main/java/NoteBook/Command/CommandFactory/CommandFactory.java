package NoteBook.Command.CommandFactory;

import NoteBook.Command.Command.Command;
import NoteBook.Services.NoteBookService;
import NoteBook.View.View;

import java.util.Map;

/**
 * Created by Маша on 15.06.2017.
 */
public interface CommandFactory {

    Command createCommand(NoteBookService noteBookService, Map<String, Object> commandParams);
}
