package notebook;

import notebook.args_converter.ArgsConverter;
import notebook.exception.IllegalCommandParamException;
import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;
import notebook.id.IDGen;
import notebook.id.SimpleIDGen;
import notebook.model.Message;
import notebook.model.MessageStatus;
import notebook.store.FileStore;
import notebook.store.RecordStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Маша on 23.06.2017.
 */
public class ConsoleWork extends WorkWithNotebook<String[]> {

    private Logger logger = LoggerFactory.getLogger(ConsoleWork.class);

    public List<Message> init() {
        List<Message> initErrorMessages = new ArrayList<>();

        String fileName;
        try {
            PropsLoader propsLoader = new PropsLoader("config.properties");
            fileName = propsLoader.loadProp("filename");
        } catch (PropFileLoadException | ResourceNotFoundException ex) {
            fileName = System.getProperty("user.dir");
            initErrorMessages.add(new Message(ex.getMessage(), MessageStatus.ERROR));
            logger.error(ex.getMessage(), ex);
        }
        RecordStore recordStore = new FileStore(fileName);
        IDGen idGen = new SimpleIDGen();

        initErrorMessages.addAll(super.init(recordStore, idGen));
        return initErrorMessages;
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
