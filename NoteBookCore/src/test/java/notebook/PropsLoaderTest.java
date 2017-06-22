package notebook;

import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Маша on 20.06.2017.
 */
@RunWith(JUnit4.class)
public class PropsLoaderTest {
    @Test
    public void loadPropConfigFileExist() throws PropFileLoadException, ResourceNotFoundException {
        //given
        PropsLoader propsLoader = new PropsLoader("test.properties");

        //when
        String propValue = propsLoader.loadProp("first_param");

        //then
        assertEquals(propValue, "Some test param 1");
    }

    @Test
    public void loadAllPropsFileExist() throws PropFileLoadException, ResourceNotFoundException {
        //given
        PropsLoader propsLoader = new PropsLoader("test.properties");
        Map<String, String> expectedParamsMap = new HashMap<>();
        expectedParamsMap.put("first_param", "Some test param 1");
        expectedParamsMap.put("second_param", "Some test param 2");
        expectedParamsMap.put("third_param", "Some test param 3");

        //when
        Map<String, String> resultParamsMap = propsLoader.loadAllProps();

        //then
        assertEquals(expectedParamsMap, resultParamsMap);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void loadPropFileNotExist() throws PropFileLoadException, ResourceNotFoundException {
        //given
        PropsLoader propsLoader = new PropsLoader("NotExistFileName.properties");

        //when
        propsLoader.loadProp("first_param");

        //then
        //generate exception
    }

    @Test(expected = ResourceNotFoundException.class)
    public void loadAllPropFileNotExist() throws PropFileLoadException, ResourceNotFoundException {
        //given
        PropsLoader propsLoader = new PropsLoader("NotExistFileName.properties");

        //when
        propsLoader.loadProp("first_param");

        //then
        //generate exception
    }

    @Test
    public void loadNotExistProp() throws PropFileLoadException, ResourceNotFoundException {
        //given
        PropsLoader propsLoader = new PropsLoader("test.properties");

        //when
        String propValue = propsLoader.loadProp("notExist");

        //then
        assertNull(propValue);
    }
}