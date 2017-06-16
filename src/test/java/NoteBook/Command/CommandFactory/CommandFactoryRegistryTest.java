package NoteBook.Command.CommandFactory;

import NoteBook.Command.Command.AddCommand;
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
    public void getCommandFactory_addCommand() throws CommandFactoryException {
        CommandFactoryRegistry commandFactoryRegistry = new CommandFactoryRegistry();
        CommandFactory factory = commandFactoryRegistry.getCommandFactory(AddCommand.class);
        assertEquals(AddCommandFactory.class, factory.getClass());
    }

}