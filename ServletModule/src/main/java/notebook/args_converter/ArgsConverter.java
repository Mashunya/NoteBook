package notebook.args_converter;

import notebook.DateFormatExtractor;
import notebook.GlobalParamsExtractor;
import notebook.exception.IllegalCommandParamException;
import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Маша on 22.06.2017.
 */
public class ArgsConverter {
    private static final Logger logger = LoggerFactory.getLogger(ArgsConverter.class);
    private Map<String, String> paramConvertMap;

    public ArgsConverter() {
        paramConvertMap = new HashMap<>();
        paramConvertMap.put("serv_text", "text");
        paramConvertMap.put("serv_title", "title");
        paramConvertMap.put("serv_author", "author");
        paramConvertMap.put("serv_type", "type");
        paramConvertMap.put("serv_deadline", "deadline");
        paramConvertMap.put("serv_ID", "recordID");
        paramConvertMap.put("command", "command");
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
        String sourceDate = (String)convertedParams.get("deadline");
        if(sourceDate != null) {
            String dateFormat = DateFormatExtractor.getDateFormat();

            DateFormat targetFormat = new SimpleDateFormat(dateFormat);
            DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                convertedParams.put("deadline", targetFormat.format(sourceFormat.parse(sourceDate)));
            } catch(ParseException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }

        convertedParams.remove("command");
        return convertedParams;
    }
}
