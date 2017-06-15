package NoteBook;

import NoteBook.Exception.PropFileLoadException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Created by Маша on 13.06.2017.
 */
@RunWith(JUnit4.class)
public class PropsLoaderTest {

    @Test
    public void loadPropFromConfig_existProp() throws PropFileLoadException {
        PropsLoader propsLoader = new PropsLoader("config.properties");
        String propValue = propsLoader.loadPropFromConfig("filename");
        assertNotNull(propValue);
    }

    @Test
    public void loadPropFromConfig_notExistProp() throws PropFileLoadException {
        PropsLoader propsLoader = new PropsLoader("config.properties");
        String propValue = propsLoader.loadPropFromConfig("notExist");
        assertNull(propValue);
    }
}