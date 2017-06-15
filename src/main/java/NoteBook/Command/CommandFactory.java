package NoteBook.Command;

import NoteBook.Command.CommandDefinition.AddCommandParamsConfig;
import NoteBook.Command.CommandDefinition.ParamsConfig;
import NoteBook.Exception.IllegalCommandParamException;
import NoteBook.Exception.ParseException;
import NoteBook.Exception.ValidateException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Маша on 15.06.2017.
 */
public class CommandFactory {

    private Map<String, Command> commandMap;

    public CommandFactory() {
        commandMap = new HashMap<>();
        commandMap.put("add", new AddCommand());
        commandMap.put("delete", new DeleteCommand());
        commandMap.put("findAll", new FindAllCommand());
        commandMap.put("findByID", new FindByIDCommand());
        commandMap.put("help", new HelpCommand());
    }

    public Command createCommand(String commandName, Map<String, String> inputParams)
            throws ValidateException, ParseException, IllegalCommandParamException {

        ParamsConfig paramsConfig = new AddCommandParamsConfig(); //TODO: переделать
        paramsConfig.initPreparedParams(inputParams);
        paramsConfig.addGlobalParams();
        Map<String, Object> preparedParams = paramsConfig.getPreparedParams();

        Command prototype = commandMap.get(commandName);
        if(prototype == null) {
            throw new IllegalCommandParamException(null, commandName);
        }
        return commandMap.get(commandName).newCommand(preparedParams);
    }
}
