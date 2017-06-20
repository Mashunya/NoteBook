package NoteBook.Parsers;

import NoteBook.Exception.NumberParseException;
import NoteBook.Exception.ParseException;
import jdk.nashorn.internal.runtime.ParserException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Created by Маша on 20.06.2017.
 */
@RunWith(JUnit4.class)
public class IntParserTest {
    @Test
    public void parseIntegerFormatString() throws ParseException {
        IntParser parser = new IntParser();
        int result = parser.parse("123");
        assertEquals(123, result);
    }

    @Test(expected = NumberParseException.class)
    public void parseNotIntegerFormatString() throws ParseException {
        IntParser parser = new IntParser();
        parser.parse("abc");
    }
}