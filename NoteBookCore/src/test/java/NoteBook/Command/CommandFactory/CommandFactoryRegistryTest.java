package NoteBook.Command.CommandFactory;

import NoteBook.Command.Command.*;
import NoteBook.Exception.CommandFactoryException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Created by Маша on 16.06.2017.
 */
@RunWith(JUnit4.class)
public class CommandFactoryRegistryTest {
    @Test
    public void getCommandFactoryGetAddCommandFactory() throws CommandFactoryException {
        CommandFactoryRegistry commandFactoryRegistry = new CommandFactoryRegistry();
        CommandFactory factory = commandFactoryRegistry.getCommandFactory(AddCommand.class);
        assertEquals(AddCommandFactory.class, factory.getClass());
    }

    @Test
    public void getCommandFactoryGetDeleteCommandFactory() throws CommandFactoryException {
        CommandFactoryRegistry commandFactoryRegistry = new CommandFactoryRegistry();
        CommandFactory factory = commandFactoryRegistry.getCommandFactory(DeleteCommand.class);
        assertEquals(DeleteCommandFactory.class, factory.getClass());
    }

    @Test
    public void getCommandFactoryGetFindByIDCommandFactory() throws CommandFactoryException {
        CommandFactoryRegistry commandFactoryRegistry = new CommandFactoryRegistry();
        CommandFactory factory = commandFactoryRegistry.getCommandFactory(FindByIDCommand.class);
        assertEquals(FindByIDCommandFactory.class, factory.getClass());
    }

    @Test
    public void getCommandFactoryGetFindAllCommandFactory() throws CommandFactoryException {
        CommandFactoryRegistry commandFactoryRegistry = new CommandFactoryRegistry();
        CommandFactory factory = commandFactoryRegistry.getCommandFactory(FindAllCommand.class);
        assertEquals(FindAllCommandFactory.class, factory.getClass());
    }

    @Test
    public void getCommandFactoryGetHelpCommandFactory() throws CommandFactoryException {
        CommandFactoryRegistry commandFactoryRegistry = new CommandFactoryRegistry();
        CommandFactory factory = commandFactoryRegistry.getCommandFactory(HelpCommand.class);
        assertEquals(HelpCommandFactory.class, factory.getClass());
    }

    @Test
    public void getCommandFactoryGetAboutCommandFactory() throws CommandFactoryException {
        CommandFactoryRegistry commandFactoryRegistry = new CommandFactoryRegistry();
        CommandFactory factory = commandFactoryRegistry.getCommandFactory(AboutCommand.class);
        assertEquals(AboutCommandFactory.class, factory.getClass());
    }
}