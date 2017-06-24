package notebook.command.description;

import notebook.command.*;
import notebook.command.decorator.AddProgramNameDecorator;
import notebook.command.decorator.CheckOSDecorator;
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

        List<Class> decorators = new ArrayList<>();
        decorators.add(AddProgramNameDecorator.class);
        decorators.add(CheckOSDecorator.class);
        decorators = Collections.unmodifiableList(decorators);

        commandDescriptionMap.put("add", CommandDescription.newBuilder(AddCommand.class)
                .paramDescription(recordFieldsParamsDescription).decorators(decorators).build());
        commandDescriptionMap.put("delete", CommandDescription.newBuilder(DeleteCommand.class)
                .paramDescription(onlyRecordIDParamsDescription).decorators(decorators).build());
        commandDescriptionMap.put("findAll", CommandDescription.newBuilder(FindAllCommand.class)
                .decorator(AddProgramNameDecorator.class).build());
        commandDescriptionMap.put("findByID", CommandDescription.newBuilder(FindByIDCommand.class)
                .paramDescription(onlyRecordIDParamsDescription).decorator(AddProgramNameDecorator.class).build());
        commandDescriptionMap.put("help", CommandDescription.newBuilder(HelpCommand.class).build());
        commandDescriptionMap.put("about", CommandDescription.newBuilder(AboutCommand.class).build());
    }

    public CommandDescription getCommandDescription(String commandName) {
        return commandDescriptionMap.get(commandName);
    }

    private Collection<ParamDescription> generateRecordFieldsParamsDescription() throws ExpectedDefaultValueException {
        Collection<ParamDescription> paramsDescription = new ArrayList<>();

        paramsDescription.add(ParamDescription.newBuilder().paramName("text").paramClass(String.class).required(true).build());
        paramsDescription.add(ParamDescription.newBuilder().paramName("author").paramClass(String.class).required(false).defaultValue("anonymous").length(200).build());
        paramsDescription.add(ParamDescription.newBuilder().paramName("type").paramClass(String.class).required(false).defaultValue("no type").length(100).build());
        paramsDescription.add(ParamDescription.newBuilder().paramName("title").paramClass(String.class).required(false).defaultValue("no title").length(200).build());

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.set(calendar.get(Calendar.YEAR) + 1, 0, 1);
        paramsDescription.add(ParamDescription.newBuilder().paramName("deadline").paramClass(Date.class).required(false).defaultValue(calendar.getTime()).build());

        return Collections.unmodifiableCollection(paramsDescription);
    }

    private Collection<ParamDescription> generateOnlyRecordIDParamsDescription() throws ExpectedDefaultValueException {
        Collection<ParamDescription> paramsDescription = new ArrayList<>();

        paramsDescription.add(ParamDescription.newBuilder().paramName("recordID").paramClass(Integer.class).required(true)
                .validators(ValidatorRegistry.getInstance().getValidator(ValidatorNames.NotNegativeNumber)).build());

        return Collections.unmodifiableCollection(paramsDescription);
    }
}
