package NoteBook.Command.CommandDescription;

import NoteBook.Command.Command.AboutCommand;
import NoteBook.Command.Command.DeleteCommand;
import NoteBook.Command.ParamDescription.ParamDescription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Маша on 15.06.2017.
 */
public class CommandDescriptionRegistry {

    private Map<String, CommandDescription> commandDescriptionMap;
    private static CommandDescriptionRegistry factory;
//TODO use single class for command descriptionc
    private CommandDescriptionRegistry() {
        commandDescriptionMap = new HashMap<>();
        ParamDescription.Builder builder;

        builder = ParamDescription.newBuilder();
        List<> paramsDescriptions;
        paramsDescriptions.add(builder.paramName("text").paramClass(String.class).required(true).build());

        builder = ParamDescription.newBuilder();
        paramsDescription.add(builder.paramName("author").paramClass(String.class).required(false).defaultValue("anonymous").length(200).build());

        builder = ParamDescription.newBuilder();
        paramsDescription.add(builder.paramName("type").paramClass(String.class).required(false).defaultValue("no type").length(100).build());

        builder = ParamDescription.newBuilder();
        paramsDescription.add(builder.paramName("title").paramClass(String.class).required(false).defaultValue("no title").length(200).build());
        registerCommand(commnadName, commandClass);
        commandDescriptionMap.put("delete", new CommandDescription("delete", DeleteCommand.class));
        commandDescriptionMap.put("findAll", new FindAllCommandDescription("findAll"));
        commandDescriptionMap.put("findByID", new FindByIDCommandDescription("findByID"));
        commandDescriptionMap.put("help", new HelpCommandDescription("help"));
        commandDescriptionMap.put("about", new CommandDescription("about", AboutCommand.class));
        commandDescriptionMap.put("about", new CommandDescription("second", AboutCommand.class));
    }

    private void registerCommand(String commandName, Class commandClass) {
        commandDescriptionMap.put(commandName, new CommandDescription(commandName, commandClass));
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
