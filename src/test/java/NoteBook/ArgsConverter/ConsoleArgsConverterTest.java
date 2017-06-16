package NoteBook.ArgsConverter;

import NoteBook.Exception.IllegalCommandParamException;
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
public class ConsoleArgsConverterTest {

    @Test
    public void convert_allParamsRight() throws IllegalCommandParamException {
        String[] args = {"commandName", "rec_text=some text", "rec_title=some title", "rec_author=some author", "rec_type=some type"};

        Map<String, Object> expectedResultMap = new HashMap<>();
        expectedResultMap.put("text", "some text");
        expectedResultMap.put("title", "some title");
        expectedResultMap.put("author", "some author");
        expectedResultMap.put("type", "some type");

        ConsoleArgsConverter converter = new ConsoleArgsConverter();

        Map<String, Object> resultMap = converter.convert(args);

        assertEquals(expectedResultMap, resultMap);
    }

    @Test(expected = IllegalCommandParamException.class)
    public void convert_illegalParamName() throws IllegalCommandParamException {
        String[] args = {"commandName", "illegalParam=something"};

        ConsoleArgsConverter converter = new ConsoleArgsConverter();

        converter.convert(args);
    }

    @Test(expected = IllegalCommandParamException.class)
    public void convert_paramWithoutValue() throws IllegalCommandParamException {
        String[] args = {"commandName", "rec_ID"};

        ConsoleArgsConverter converter = new ConsoleArgsConverter();

        converter.convert(args);
    }
}