package NoteBook;

import NoteBook.Exception.PropFileLoadException;

import java.util.Map;

/**
 * Created by Маша on 16.06.2017.
 */
public class GlobalProps {
    private static Map<String, String> globalProps;

    public static Map<String, String> getProps() throws PropFileLoadException {
        if(globalProps == null) {
            extractProps();
        }
        return globalProps;
    }

    private static void extractProps() throws PropFileLoadException {
        PropsLoader propsLoader = new PropsLoader("global.properties");
        globalProps = propsLoader.loadAllProps();
    }
}
