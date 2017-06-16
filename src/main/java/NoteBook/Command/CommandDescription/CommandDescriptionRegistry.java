package NoteBook.Command.CommandDescription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Маша on 15.06.2017.
 */
public class CommandDescriptionRegistry {

    private Map<String, CommandDescription> commandDescriptionMap;
    private static CommandDescriptionRegistry factory;

    private CommandDescriptionRegistry() {
        commandDescriptionMap = new HashMap<>();
        commandDescriptionMap.put("add", new AddCommandDescription("add"));
        commandDescriptionMap.put("delete", new DeleteCommandDescription("delete"));
//        commandDescriptionMap.put("findAll", new FindAllCommand());
//        commandDescriptionMap.put("findByID", new FindByIDCommand());
//        commandDescriptionMap.put("help", new HelpCommand());
    }

    public static CommandDescriptionRegistry getInstanse() {
        if(factory == null) {
            factory = new CommandDescriptionRegistry();
        }
        return factory;
    }

    public CommandDescription getCommandDescription(String commandName) {
        return commandDescriptionMap.get(commandName);
    }
}
