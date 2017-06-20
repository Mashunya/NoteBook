package NoteBook;

import NoteBook.Exception.PropFileLoadException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Created by Маша on 20.06.2017.
 */
@RunWith(JUnit4.class)
public class PropsLoaderTest {
    @Test
    public void loadPropFromConfig_existProp() throws PropFileLoadException {
        PropsLoader propsLoader = new PropsLoader("config.properties");
        String propValue = propsLoader.loadProp("filename");
        assertNotNull(propValue);
    }

    @Test
    public void loadPropFromConfig_notExistProp() throws PropFileLoadException {
        PropsLoader propsLoader = new PropsLoader("config.properties");
        String propValue = propsLoader.loadProp("notExist");
        assertNull(propValue);
    }
}