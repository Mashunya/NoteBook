package notebook.args_converter;

import notebook.exception.IllegalCommandParamException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Маша on 22.06.2017.
 */
public class ArgsConverter {
    private Map<String, String> paramConvertMap;

    public ArgsConverter() {
        paramConvertMap = new HashMap<>();
        paramConvertMap.put("serv_command", "command");
        paramConvertMap.put("serv_text", "text");
        paramConvertMap.put("serv_title", "title");
        paramConvertMap.put("serv_author", "author");
        paramConvertMap.put("serv_type", "type");
        paramConvertMap.put("serv_deadline", "deadline");
        paramConvertMap.put("serv_ID", "recordID");
    }

    public Map<String, Object> convert(Map<String, String[]> params) throws IllegalCommandParamException {

        Map<String, Object> convertedParams = new HashMap<>();

        String newFieldName, oldFieldName, value;
        for(Map.Entry<String, String[]> param: params.entrySet()) {
            oldFieldName = param.getKey();
            value = param.getValue()[0];
            newFieldName = paramConvertMap.get(oldFieldName);
            if(newFieldName == null) {
                throw new IllegalCommandParamException(null, oldFieldName);
            }
            if(!value.isEmpty()) {
                convertedParams.put(newFieldName, value);
            }
        }

        return convertedParams;
    }
}
