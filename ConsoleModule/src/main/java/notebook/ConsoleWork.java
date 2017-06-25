package notebook;

import notebook.args_converter.ArgsConverter;
import notebook.dao.FileDAOFactory;
import notebook.dao.exception.ContextException;
import notebook.exception.IllegalCommandParamException;
import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;
import notebook.model.Message;

import java.util.List;
import java.util.Map;

/**
 * Created by Маша on 23.06.2017.
 */
public class ConsoleWork extends WorkWithNotebook<String[]> {

    @Override
    protected void initDAOFactory() throws ContextException, ResourceNotFoundException, PropFileLoadException {
        daoFactory = new FileDAOFactory();
    }

    @Override
    protected String getCommandName(String[] params, List<Message> errorMessages) {
        if(params.length == 0) {
            return setDefaultCommandName(errorMessages);
        }
        return params[0];
    }

    @Override
    protected Map<String, Object> convertParams(String[] params) throws IllegalCommandParamException {
        ArgsConverter argsConverter = new ArgsConverter();
        return argsConverter.convert(params);
    }
}
