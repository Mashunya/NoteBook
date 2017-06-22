package notebook;

import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;

import java.util.Map;

/**
 * Created by Маша on 16.06.2017.
 */
public class GlobalParamsExtractor {
    private static Map<String, String> globalParams;

    public static String getProperty(String propName) throws PropFileLoadException, ResourceNotFoundException {
        if(globalParams == null) {
            globalParams = extractProps("global.properties");
        }
        return globalParams.get(propName);
    }

    public static Map<String, String> getProps() throws PropFileLoadException, ResourceNotFoundException {
        if(globalParams == null) {
            globalParams = extractProps("global.properties");
        }
        return globalParams;
    }

    private static Map<String, String> extractProps(String filename) throws PropFileLoadException, ResourceNotFoundException {
        PropsLoader propsLoader = new PropsLoader(filename);
        return propsLoader.loadAllProps();
    }
}
