package NoteBook.Parsers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by Маша on 13.06.2017.
 */
@RunWith(JUnit4.class)
public class IntegerParserTest {

    @Test
    public void parse_integerFormatString() {
        IntegerParser parser = new IntegerParser();
        int result = parser.parse("123");
        assertEquals(123, result);
    }

    @Test(expected = NumberFormatException.class)
    public void parse_notIntegerFormatString() {
        IntegerParser parser = new IntegerParser();
        parser.parse("abc");
    }
}
