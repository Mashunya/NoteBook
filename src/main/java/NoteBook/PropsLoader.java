package NoteBook;

import NoteBook.Exception.PropFileLoadException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Маша on 09.06.2017.
 */
public class PropsLoader {
    public String loadPropFromConfig(String propName) throws PropFileLoadException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream fis;
        Properties properties = new Properties();
        try {
            fis = loader.getResourceAsStream("config.properties");
            properties.load(fis);
            return properties.getProperty(propName);
        } catch (IOException ex) {
            throw new PropFileLoadException(ex);
        }
    }
}
