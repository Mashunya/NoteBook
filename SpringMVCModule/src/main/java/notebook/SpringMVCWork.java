package notebook;

import notebook.args_converter.ArgsConverter;
import notebook.dao.MySQLDAOFactory;
import notebook.dao.exception.ContextException;
import notebook.exception.IllegalCommandParamException;
import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;
import notebook.model.Message;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

/**
 * Created by Маша on 23.06.2017.
 */
public class SpringMVCWork extends WorkWithNotebook<MultiValueMap<String, String>> {

    @Override
    protected void initDAOFactory() throws ContextException, ResourceNotFoundException, PropFileLoadException {
        daoFactory = new MySQLDAOFactory();
    }

    @Override
    protected String getCommandName(MultiValueMap<String, String> params, List<Message> errorMessages) {
        List<String> commandName = params.get("command");
        if (commandName == null) {
            return setDefaultCommandName(errorMessages);
        }
        return commandName.get(0);
    }

    @Override
    protected Map<String, Object> convertParams(MultiValueMap<String, String> params) throws IllegalCommandParamException {
        ArgsConverter argsConverter = new ArgsConverter();
        return argsConverter.convert(params);
    }
}
