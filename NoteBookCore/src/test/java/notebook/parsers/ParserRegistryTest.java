package notebook.parsers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by Маша on 20.06.2017.
 */
@RunWith(JUnit4.class)
public class ParserRegistryTest {
    @Test
    public void getIntegerParser() {
        //given
        ParserRegistry parserRegistry = new ParserRegistry();

        //when
        Parser parser = parserRegistry.getParser(Integer.class);

        //then
        assertEquals(IntParser.class, parser.getClass());
    }

    @Test
    public void getDateParser() {
        //given
        ParserRegistry parserRegistry = new ParserRegistry();

        //when
        Parser parser = parserRegistry.getParser(Date.class);

        //then
        assertEquals(DateParser.class, parser.getClass());
    }

    @Test
    public void getParserNotExist() {
        //given
        ParserRegistry parserRegistry = new ParserRegistry();

        //when
        Parser parser = parserRegistry.getParser(String.class);

        //then
        assertEquals(null, parser);
    }
}