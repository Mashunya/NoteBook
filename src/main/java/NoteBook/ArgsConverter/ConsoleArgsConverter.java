package NoteBook.ArgsConverter;

import NoteBook.Exception.IllegalCommandParamException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Маша on 13.06.2017.
 */
public class ConsoleArgsConverter {

    private Map<String, String> paramConvertMap;

    public ConsoleArgsConverter() {
        paramConvertMap = new HashMap<>();
        paramConvertMap.put("rec_text", "text");
        paramConvertMap.put("rec_title", "title");
        paramConvertMap.put("rec_author", "author");
        paramConvertMap.put("rec_type", "type");
        paramConvertMap.put("rec_ID", "recordID");
    }

    public Map<String, String> convert(String[] args) throws IllegalCommandParamException {

        Map<String, String> convertedParams = new HashMap<>();

        String oldFieldName, newFieldName, fieldValue;
        String[] buffer;

        for(int i = 1; i < args.length; i++) {
            buffer = args[i].split("=");
            oldFieldName = buffer[0];
            fieldValue = buffer[1];

            newFieldName = paramConvertMap.get(oldFieldName);
            if(newFieldName == null) {
                throw new IllegalCommandParamException(null, oldFieldName);
            } else {
                convertedParams.put(newFieldName, fieldValue);
            }
        }

        return convertedParams;
    }
}
