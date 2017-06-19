package NoteBook.Command;

import NoteBook.Command.ParamDescription.ParamDescription;
import NoteBook.Exception.ParseException;
import NoteBook.Exception.RequiredParamValidateException;
import NoteBook.Exception.ValidateException;
import NoteBook.Parsers.IntParser;
import NoteBook.Parsers.Parser;
import NoteBook.Parsers.ParserRegistry;
import NoteBook.Validators.Validator;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Маша on 15.06.2017.
 */
public class InputDataPreparator {

    private Map<String, Object> parseParams(Map<String, Object> inputParams, Collection<ParamDescription> paramsDescription) throws ParseException {

        Parser parser;
        for(ParamDescription paramDescription: paramsDescription) {
            String paramName = paramDescription.getParamName();
            Object paramValue = inputParams.get(paramName);
            //TODO registry?
            parser = ParserRegistry.getInstance().getParser(paramDescription.getParamClass());
            if(parser != null) {
                inputParams.put(paramName, parser.parse(paramValue));
            }
        }
        return inputParams;
    }

    private void validateParams(Map<String, Object> inputParams, Collection<ParamDescription> paramsDescription) throws ValidateException {

        for(ParamDescription paramDescription: paramsDescription) {
            String paramName = paramDescription.getParamName();
            Object paramValue = inputParams.get(paramName);
            for(Validator validator: paramDescription.getValidators()) {
                validator.validate(paramValue, paramDescription);
            }
        }
    }

    private Map<String, Object> setDefaultValues(Map<String, Object> inputParams, Collection<ParamDescription> paramsDescription) {
        for(ParamDescription paramDescription: paramsDescription) {
            String paramName = paramDescription.getParamName();
            Object paramValue = inputParams.get(paramName);
            if(paramValue == null) {
                inputParams.put(paramName, paramDescription.getDefaultValue());
            }
        }
        return inputParams;
    }

    public Map<String, Object> prepareData(Map<String, Object> inputParams,
                                           Collection<ParamDescription> paramsDescription) throws ValidateException, ParseException {
        // TODO: pass as params to functions
        inputParams = parseParams(inputParams, paramsDescription);
        validateParams(inputParams, paramsDescription);
        inputParams = setDefaultValues(inputParams, paramsDescription);

        return inputParams;
    }
}
