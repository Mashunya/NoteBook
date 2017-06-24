package notebook.args_converter;

import notebook.GlobalParamsExtractor;
import notebook.exception.IllegalCommandParamException;
import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Маша on 22.06.2017.
 */
public class ArgsConverter {

    private static final Logger logger = LoggerFactory.getLogger(ArgsConverter.class);
    private Map<String, String> paramConvertMap;

    public ArgsConverter() {
        paramConvertMap = new HashMap<>();
        paramConvertMap.put("spr_text", "text");
        paramConvertMap.put("spr_title", "title");
        paramConvertMap.put("spr_author", "author");
        paramConvertMap.put("spr_type", "type");
        paramConvertMap.put("spr_deadline", "deadline");
        paramConvertMap.put("spr_ID", "recordID");
        paramConvertMap.put("command", "command");
    }

    public Map<String, Object> convert(MultiValueMap<String, String> params) throws IllegalCommandParamException {

        Map<String, Object> convertedParams = new HashMap<>();

        String newFieldName, oldFieldName, value;
        for(Map.Entry<String, List<String>> param: params.entrySet()) {
            oldFieldName = param.getKey();
            value = param.getValue().get(0);
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
            String dateFormat;
            try {
                dateFormat = GlobalParamsExtractor.getProperty("Date_Format");
            } catch(PropFileLoadException | ResourceNotFoundException ex) {
                logger.error(ex.getMessage(), ex);
                dateFormat = "dd.MM.yyyy";
            }
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
