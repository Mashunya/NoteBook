package NoteBook.Command.CommandFactory;

import NoteBook.Command.Command.AboutCommand;
import NoteBook.Command.Command.Command;
import NoteBook.Services.NoteBookService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Маша on 16.06.2017.
 */
public class CommandFactory {

    public Command createCommand(Class commandClass, NoteBookService noteBookService) {
        Command command = null;
        try {
            command = (Command)commandClass.newInstance();
            Method method = commandClass.getMethod("setNoteBookService", NoteBookService.class);
            method.invoke(command, noteBookService);
        } catch (InstantiationException | IllegalAccessException |InvocationTargetException | NoSuchMethodException ex) {}

        return command;
    }
}
