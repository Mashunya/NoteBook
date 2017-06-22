package notebook.command.factory;

import notebook.command.Command;
import notebook.command.CommandWorkedWithNoteBook;
import notebook.services.NoteBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Маша on 16.06.2017.
 */
public class CommandFactory {

    private final static Logger logger = LoggerFactory.getLogger(CommandFactory.class);

    public Command createCommand(Class commandClass, NoteBookService noteBookService) {
        Command command = null;
        try {
            command = (Command)commandClass.newInstance();
            if(commandClass.getSuperclass().equals(CommandWorkedWithNoteBook.class)) {
                ((CommandWorkedWithNoteBook)command).setNoteBookService(noteBookService);
            }
        } catch (InstantiationException | IllegalAccessException ex) {
            logger.error(ex.getMessage(), ex);
        }

        return command;
    }
}
