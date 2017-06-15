package NoteBook.Command.CommandDefinition;

import NoteBook.Command.ParamDescription.ParamDescription;
import NoteBook.Exception.ParseException;
import NoteBook.Exception.ValidateException;

import java.util.Map;

/**
 * Created by Маша on 15.06.2017.
 */
public abstract class ParamsConfig {
    protected Map<String, ParamDescription> paramDefinition;
    protected Map<String, Object> preparedParams;

    public abstract void initPreparedParams(Map<String, String> inputData) throws ValidateException, ParseException;
    public void addGlobalParams() {}

    public Map<String, Object> getPreparedParams() {
        return preparedParams;
    }
}
