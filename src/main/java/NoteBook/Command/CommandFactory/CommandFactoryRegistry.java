package NoteBook.Command.CommandFactory;

import NoteBook.Exception.CommandFactoryException;
import NoteBook.Exception.CommandFactoryLoadException;
import NoteBook.Exception.CommandFactoryNotFoundException;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by Маша on 15.06.2017.
 */
public class CommandFactoryRegistry {
    private static final String factoryPackage = "NoteBook.CommandWorkedWithNoteBook.CommandFactory";

    public CommandFactory getCommandFactory(Class commandClass) throws CommandFactoryException {
        Set<Class<? extends CommandFactory>> factories
                = (new Reflections(factoryPackage)).getSubTypesOf(CommandFactory.class);
        try {
            Class factoryCommandClass, foundFactoryClass = null;
            Method method;
            for (Class factoryClass : factories) {
                method = factoryClass.getMethod("getCommandClass");
                factoryCommandClass = (Class) method.invoke(null);
                if (commandClass.equals(factoryCommandClass)) {
                    foundFactoryClass = factoryClass;
                    break;
                }
            }
            if(foundFactoryClass == null) {
                throw new CommandFactoryNotFoundException(commandClass);
            }
            return (CommandFactory) foundFactoryClass.newInstance();

        } catch (IllegalAccessException | InstantiationException |  NoSuchMethodException | InvocationTargetException ex) {
            throw new CommandFactoryLoadException(ex, commandClass);
        }
    }
}
