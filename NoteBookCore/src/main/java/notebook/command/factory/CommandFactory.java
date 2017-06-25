package notebook.command.factory;

import notebook.command.Command;
import notebook.command.CommandWorkedWithDAO;
import notebook.command.description.CommandDescription;
import notebook.dao.GenericDAO;
import notebook.entity.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Маша on 16.06.2017.
 */
public class CommandFactory {

    private final static Logger logger = LoggerFactory.getLogger(CommandFactory.class);

    public Command createCommand(CommandDescription commandDescription, GenericDAO<Record, Integer> recordDAO) {
        Command command = null;
        Class commandClass = commandDescription.getCommandClass();
        try {
            command = (Command)commandClass.newInstance();
            if(commandClass.getSuperclass().equals(CommandWorkedWithDAO.class)) {
                ((CommandWorkedWithDAO)command).setRecordDAO(recordDAO);
            }
            Constructor constructor;
            for(Class decoratorClass: commandDescription.getDecorators()) {
                constructor = decoratorClass.getConstructor(Command.class);
                command = (Command)constructor.newInstance(command);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            logger.error(ex.getMessage(), ex);
        }

        return command;
    }
}
