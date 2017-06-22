package notebook.command.description;

import notebook.command.*;
import notebook.command.params.ParamDescription;
import notebook.exception.ExpectedDefaultValueException;
import notebook.validators.ValidatorNames;
import notebook.validators.ValidatorRegistry;

import java.util.*;

/**
 * Created by Маша on 15.06.2017.
 */
public class CommandDescriptionRegistry {
    private Map<String, CommandDescription> commandDescriptionMap;

    public CommandDescriptionRegistry() throws ExpectedDefaultValueException {
        commandDescriptionMap = new HashMap<>();

        Collection<ParamDescription> recordFieldsParamsDescription = generateRecordFieldsParamsDescription();
        Collection<ParamDescription> onlyRecordIDParamsDescription = generateOnlyRecordIDParamsDescription();

        commandDescriptionMap.put("add", new CommandDescription(AddCommand.class, recordFieldsParamsDescription));
        commandDescriptionMap.put("delete", new CommandDescription(DeleteCommand.class, onlyRecordIDParamsDescription));
        commandDescriptionMap.put("findAll", new CommandDescription(FindAllCommand.class));
        commandDescriptionMap.put("findByID", new CommandDescription(FindByIDCommand.class, onlyRecordIDParamsDescription));
        commandDescriptionMap.put("help", new CommandDescription(HelpCommand.class));
        commandDescriptionMap.put("about", new CommandDescription(AboutCommand.class));
    }

    public CommandDescription getCommandDescription(String commandName) {
        return commandDescriptionMap.get(commandName);
    }

    private Collection<ParamDescription> generateRecordFieldsParamsDescription() throws ExpectedDefaultValueException {
        Collection<ParamDescription> paramsDescription = new ArrayList<>();
        ParamDescription.Builder builder;

        builder = ParamDescription.newBuilder();
        paramsDescription.add(builder.paramName("text").paramClass(String.class).required(true).build());

        builder = ParamDescription.newBuilder();
        paramsDescription.add(builder.paramName("author").paramClass(String.class).required(false).defaultValue("anonymous").length(200).build());

        builder = ParamDescription.newBuilder();
        paramsDescription.add(builder.paramName("type").paramClass(String.class).required(false).defaultValue("no type").length(100).build());

        builder = ParamDescription.newBuilder();
        paramsDescription.add(builder.paramName("title").paramClass(String.class).required(false).defaultValue("no title").length(200).build());

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.set(calendar.get(Calendar.YEAR) + 1, 0, 1);
        builder = ParamDescription.newBuilder();
        paramsDescription.add(builder.paramName("deadline").paramClass(Date.class).required(false).defaultValue(calendar.getTime()).build());

        return Collections.unmodifiableCollection(paramsDescription);
    }

    private Collection<ParamDescription> generateOnlyRecordIDParamsDescription() throws ExpectedDefaultValueException {
        Collection<ParamDescription> paramsDescription = new ArrayList<>();

        ParamDescription.Builder builder = ParamDescription.newBuilder();
        paramsDescription.add(builder.paramName("recordID").paramClass(Integer.class).required(true)
                .validators(ValidatorRegistry.getInstance().getValidator(ValidatorNames.NotNegativeNumber)).build());

        return Collections.unmodifiableCollection(paramsDescription);
    }
}
