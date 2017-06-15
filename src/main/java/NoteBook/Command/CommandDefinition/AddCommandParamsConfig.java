package NoteBook.Command.CommandDefinition;

import NoteBook.Command.ParamDescription.ParamDescription;
import NoteBook.Command.ParamDescription.ParamDescriptionRegistry;
import NoteBook.Exception.ParseException;
import NoteBook.Exception.RequiredParamValidateException;
import NoteBook.Exception.ValidateException;
import NoteBook.Validators.Validator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Маша on 15.06.2017.
 */
public class AddCommandParamsConfig extends ParamsConfig {

    public AddCommandParamsConfig() {
        ParamDescriptionRegistry paramDescriptionRegistry = ParamDescriptionRegistry.getInstance();

        paramDefinition = new HashMap<>();
        paramDefinition.put("text", paramDescriptionRegistry.getParamDescription("text"));
        paramDefinition.put("author", paramDescriptionRegistry.getParamDescription("author"));
        paramDefinition.put("type", paramDescriptionRegistry.getParamDescription("type"));
        paramDefinition.put("title", paramDescriptionRegistry.getParamDescription("title"));
    }

    public void initPreparedParams(Map<String, String> inputData) throws ValidateException, ParseException {

        preparedParams = new HashMap<>();

        for(Map.Entry param: paramDefinition.entrySet()) {

            String paramName = (String)param.getKey();
            Object paramValue = inputData.get(paramName);
            ParamDescription paramDescription = (ParamDescription)param.getValue();
            if(paramValue == null) {
                if(paramDescription.isRequired()) {
                    throw new RequiredParamValidateException();
                } else {
                    paramValue = paramDescription.getDefaultValue();
                }
            } else {
                paramValue = paramDescription.getParser().parse(paramValue);
                for(Validator validator: paramDescription.getValidators()) {
                    validator.validate(paramValue);
                }
            }
            preparedParams.put(paramName, paramValue);
        }
    }
}
