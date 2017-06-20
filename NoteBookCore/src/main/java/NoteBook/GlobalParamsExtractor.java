package NoteBook;

import NoteBook.Exception.PropFileLoadException;

import java.util.Map;

/**
 * Created by Маша on 16.06.2017.
 */
public class GlobalParamsExtractor {
    private static Map<String, String> globalParams;

    public static Map<String, String> getProps() throws PropFileLoadException {
        if(globalParams == null) {
            extractProps();
        }
        return globalParams;
    }

    private static void extractProps() throws PropFileLoadException {
        PropsLoader propsLoader = new PropsLoader("global.properties");
        globalParams = propsLoader.loadAllProps();
    }
}
