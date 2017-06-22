package notebook.args_converter;

import notebook.exception.IllegalCommandParamException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Маша on 13.06.2017.
 */
@RunWith(JUnit4.class)
public class ArgsConverterTest {

    @Test
    public void convertAllowableParams() throws IllegalCommandParamException {
        //given
        String[] args = {"commandName", "rec_text=some text", "rec_title=some title", "rec_author=some author", "rec_type=some type"};

        Map<String, Object> expectedResultMap = new HashMap<>();
        expectedResultMap.put("text", "some text");
        expectedResultMap.put("title", "some title");
        expectedResultMap.put("author", "some author");
        expectedResultMap.put("type", "some type");

        ArgsConverter converter = new ArgsConverter();

        //when
        Map<String, Object> resultMap = converter.convert(args);

        //then
        assertEquals(expectedResultMap, resultMap);
    }

    @Test(expected = IllegalCommandParamException.class)
    public void convert_illegalParamName() throws IllegalCommandParamException {
        //given
        String[] args = {"commandName", "illegalParam=something"};
        ArgsConverter converter = new ArgsConverter();

        //when
        converter.convert(args);

        //then
        //expected exception
    }

    @Test(expected = IllegalCommandParamException.class)
    public void convert_paramWithoutValue() throws IllegalCommandParamException {
        //given
        String[] args = {"commandName", "rec_ID"};
        ArgsConverter converter = new ArgsConverter();

        //when
        converter.convert(args);

        //then
        //expected exception
    }
}