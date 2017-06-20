package NoteBook.Command.CommandDescription;

import NoteBook.Command.Command.*;
import NoteBook.Command.ParamDescription.ParamDescription;
import NoteBook.Validators.ValidatorNames;
import NoteBook.Validators.ValidatorRegistry;

import java.util.*;

/**
 * Created by Маша on 15.06.2017.
 */
public class CommandDescriptionRegistry {

    private Map<String, CommandDescription> commandDescriptionMap;
    private static CommandDescriptionRegistry factory;
//TODO use single class for command descriptions
    private CommandDescriptionRegistry() {
        commandDescriptionMap = new HashMap<>();

        List<ParamDescription> recordFieldsParamsDescription = generateRecordFieldsParamsDescription();
        List<ParamDescription> onlyRecordIDParamsDescription = generateOnlyRecordIDParamsDescription();

        registerCommand("add", AddCommand.class, recordFieldsParamsDescription);
        registerCommand("delete", DeleteCommand.class, onlyRecordIDParamsDescription);
        registerCommand("findAll", FindAllCommand.class);
        registerCommand("findByID", FindByIDCommand.class, onlyRecordIDParamsDescription);
        registerCommand("help", HelpCommand.class);
        registerCommand("about", AboutCommand.class);
    }

    private void registerCommand(String commandName, Class commandClass) {
        commandDescriptionMap.put(commandName, new CommandDescription(commandName, commandClass));
    }

    private void registerCommand(String commandName, Class commandClass, List<ParamDescription> paramDescriptions) {
        commandDescriptionMap.put(commandName, new CommandDescription(commandName, commandClass, paramDescriptions));
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

    private List<ParamDescription> generateRecordFieldsParamsDescription() {
        List<ParamDescription> paramsDescription = new ArrayList<>();
        ParamDescription.Builder builder;

        builder = ParamDescription.newBuilder();
        paramsDescription.add(builder.paramName("text").paramClass(String.class).required(true).build());

        builder = ParamDescription.newBuilder();
        paramsDescription.add(builder.paramName("author").paramClass(String.class).required(false).defaultValue("anonymous").length(200).build());

        builder = ParamDescription.newBuilder();
        paramsDescription.add(builder.paramName("type").paramClass(String.class).required(false).defaultValue("no type").length(100).build());

        builder = ParamDescription.newBuilder();
        paramsDescription.add(builder.paramName("title").paramClass(String.class).required(false).defaultValue("no title").length(200).build());

        return Collections.unmodifiableList(paramsDescription);
    }

    private List<ParamDescription> generateOnlyRecordIDParamsDescription() {
        List<ParamDescription> paramsDescription = new ArrayList<>();

        ParamDescription.Builder builder = ParamDescription.newBuilder();
        paramsDescription.add(builder.paramName("recordID").paramClass(Integer.class).required(true)
                .validators(ValidatorRegistry.getInstance().getValidator(ValidatorNames.NotNegativeNumber)).build());

        return paramsDescription;
    }
}
