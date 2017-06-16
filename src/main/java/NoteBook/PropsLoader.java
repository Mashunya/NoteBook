package NoteBook;

import NoteBook.Exception.PropFileLoadException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Маша on 09.06.2017.
 */
public class PropsLoader {

    private String filename;

    public PropsLoader(String filename) {
        this.filename = filename;
    }

    public Map<String, String> loadAllProps() throws PropFileLoadException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream fis;
        Properties properties = new Properties();
        Map<String, String> propsMap = new HashMap<>();
        try {
            fis = loader.getResourceAsStream(filename);
            properties.load(fis);
            for (String name: properties.stringPropertyNames()) {
                propsMap.put(name, properties.getProperty(name));
            }
            return propsMap;
        } catch (IOException ex) {
            throw new PropFileLoadException(ex);
        }
    }

    public String loadProp(String propName) throws PropFileLoadException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream fis;
        Properties properties = new Properties();
        try {
            fis = loader.getResourceAsStream(filename);
            properties.load(fis);
            return properties.getProperty(propName);
        } catch (IOException ex) {
            throw new PropFileLoadException(ex);
        }
    }
}
