package notebook.command.params;

import notebook.exception.ParseException;
import notebook.exception.ValidateException;
import notebook.parsers.Parser;
import notebook.parsers.ParserRegistry;
import notebook.validators.Validator;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Маша on 15.06.2017.
 */
public class InputDataPreparator {

    private Map<String, Object> parseParams(Map<String, Object> inputParams, Collection<ParamDescription> paramsDescription) throws ParseException {

        ParserRegistry parserRegistry = new ParserRegistry();
        Parser parser;
        for(ParamDescription paramDescription: paramsDescription) {
            String paramName = paramDescription.getParamName();
            Object paramValue = inputParams.get(paramName);
            parser = parserRegistry.getParser(paramDescription.getParamClass());
            if(parser != null && paramValue != null) {
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
        inputParams = parseParams(inputParams, paramsDescription);
        validateParams(inputParams, paramsDescription);
        inputParams = setDefaultValues(inputParams, paramsDescription);

        return inputParams;
    }
}
