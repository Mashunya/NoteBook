package notebook;

import notebook.command.Command;
import notebook.command.description.CommandDescription;
import notebook.command.description.CommandDescriptionRegistry;
import notebook.command.factory.CommandFactory;
import notebook.command.params.InputDataPreparator;
import notebook.dao.DAOFactory;
import notebook.dao.exception.ContextException;
import notebook.dao.exception.FindDAOException;
import notebook.entity.Record;
import notebook.exception.*;
import notebook.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Маша on 23.06.2017.
 */
public abstract class WorkWithNotebook<T> {

    private Logger logger = LoggerFactory.getLogger(WorkWithNotebook.class);
    private static final String DEFAULT_COMMAND_NAME = "help";

    protected DAOFactory daoFactory;

    protected abstract void initDAOFactory() throws ContextException, ResourceNotFoundException, PropFileLoadException;

    public final ModelAndView workWithNotebookService(T params) {
        List<Message> errorMessages = new ArrayList<>();
        ModelAndView commandResult = new ModelAndView("MessagesView", new MessageListModel());
        try {
            String commandName = getCommandName(params, errorMessages);
            Map<String, Object> convertedParams = convertParams(params);
            CommandDescription commandDescription = findCommandDescription(commandName);
            Map<String, Object> preparedParams = prepareData(convertedParams, commandDescription, errorMessages);
            commandResult = executeCommand(preparedParams, commandDescription);
        } catch(Exception ex) {
            errorMessages.add(new Message(ex.getMessage(), MessageStatus.ERROR));
            logger.error(ex.getMessage(), ex);
        }
        addErrorsToResult(commandResult, errorMessages);
        return commandResult;
    }

    protected abstract Map<String, Object> convertParams(T params) throws IllegalCommandParamException;

    protected abstract String getCommandName(T params, List<Message> errorMessages);

    protected String setDefaultCommandName(List<Message> errorMessages) {
        errorMessages.add(new Message("Команда не выбрана", MessageStatus.ERROR));
        return DEFAULT_COMMAND_NAME;
    }

    private CommandDescription findCommandDescription(String commandName) throws ExpectedDefaultValueException, IllegalCommandException {
        CommandDescriptionRegistry commandDescriptionRegistry = new CommandDescriptionRegistry();
        CommandDescription commandDescription = commandDescriptionRegistry.getCommandDescription(commandName);
        if(commandDescription == null) {
            throw new IllegalCommandException(commandName);
        }
        return commandDescription;
    }

    private Map<String, Object> prepareData(Map<String, Object> convertedParams, CommandDescription commandDescription, List<Message> errorMessages)
            throws ValidateException, ParseException {
        InputDataPreparator inputDataPreparator = new InputDataPreparator();
        Map<String, Object> preparedParams = inputDataPreparator.prepareData(convertedParams, commandDescription.getParamsDescription());

        try {
            preparedParams.putAll(GlobalParamsExtractor.getProps());
        } catch(PropFileLoadException | ResourceNotFoundException ex) {
            errorMessages.add(new Message(ex.getMessage(), MessageStatus.ERROR));
            logger.error(ex.getMessage(), ex);
        }

        return preparedParams;
    }

    private ModelAndView executeCommand(Map<String, Object> preparedParams, CommandDescription commandDescription) throws FindDAOException {
        CommandFactory commandFactory = new CommandFactory();
        Command command = commandFactory.createCommand(commandDescription, daoFactory.getDAO(Record.class));
        return command.execute(preparedParams);
    }

    private void addErrorsToResult(ModelAndView commandResult, List<Message> errorMessages) {
        Model model = commandResult.getModel();
        model.addAllMessages(errorMessages);
    }
}
