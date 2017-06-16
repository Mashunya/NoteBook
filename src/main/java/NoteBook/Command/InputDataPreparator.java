package NoteBook.Command;

import NoteBook.Command.ParamDescription.ParamDescription;
import NoteBook.Exception.ParseException;
import NoteBook.Exception.RequiredParamValidateException;
import NoteBook.Exception.ValidateException;
import NoteBook.Parsers.IntParser;
import NoteBook.Validators.Validator;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Маша on 15.06.2017.
 */
public class InputDataPreparator {

    private Map<String, Object> params;
    private Collection<ParamDescription> paramsDescription;

    private void parseParams() throws ParseException {
        for(ParamDescription paramDescription: paramsDescription) {
            String paramName = paramDescription.getParamName();
            Object paramValue = params.get(paramName);
            if(paramDescription.getParamClass().equals(Integer.class)) {
                params.put(paramName, new IntParser().parse(paramValue));
            }
        }
    }

    private void validateParams() throws ValidateException {

        for(ParamDescription paramDescription: paramsDescription) {
            String paramName = paramDescription.getParamName();
            Object paramValue = params.get(paramName);
            for(Validator validator: paramDescription.getValidators()) {
                validator.validate(paramValue, paramDescription);
            }
        }
    }

    private void initParams() {
        for(ParamDescription paramDescription: paramsDescription) {
            String paramName = paramDescription.getParamName();
            Object paramValue = params.get(paramName);
            if(paramValue == null) {
                params.put(paramName, paramDescription.getDefaultValue());
            }
        }
    }

    public Map<String, Object> prepareData(Map<String, Object> inputParams,
                                           Collection<ParamDescription> paramsDescription) throws ValidateException, ParseException {
        this.paramsDescription = paramsDescription;
        this.params = inputParams;

        parseParams();
        validateParams();
        initParams();

        return params;
    }
}
