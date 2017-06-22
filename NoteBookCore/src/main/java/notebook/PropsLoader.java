package notebook;

import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;

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

    public Map<String, String> loadAllProps() throws PropFileLoadException, ResourceNotFoundException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        Map<String, String> propsMap = new HashMap<>();
        try {
            InputStream fis = loader.getResourceAsStream(filename);
            if(fis == null) {
                throw new ResourceNotFoundException(filename);
            }
            properties.load(fis);
            fis.close();
            for (String name: properties.stringPropertyNames()) {
                propsMap.put(name, properties.getProperty(name));
            }
            return propsMap;
        } catch (IOException ex) {
            throw new PropFileLoadException(ex);
        }
    }

    public String loadProp(String propName) throws PropFileLoadException, ResourceNotFoundException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try {
            InputStream fis = loader.getResourceAsStream(filename);
            if(fis == null) {
                throw new ResourceNotFoundException(filename);
            }
            properties.load(fis);
            fis.close();
            return properties.getProperty(propName);
        } catch (IOException ex) {
            throw new PropFileLoadException(ex);
        }
    }
}
