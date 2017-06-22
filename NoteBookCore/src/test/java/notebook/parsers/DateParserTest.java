package notebook.parsers;

import notebook.exception.DateParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Created by Маша on 21.06.2017.
 */
@RunWith(JUnit4.class)
public class DateParserTest {
    @Test
    public void parseDateFormatString() throws DateParseException {
        //given
        DateParser parser = spy(new DateParser());
        when(parser.getDateFormatter()).thenReturn(new SimpleDateFormat("dd.MM.yyyy"));
        Calendar calendar = new GregorianCalendar();
        calendar.set(2017, Calendar.AUGUST, 12, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        //when
        Date result = parser.parse("12.08.2017");

        //then
        assertEquals(calendar.getTime(), result);
    }

    @Test(expected = DateParseException.class)
    public void parseNotDateFormatString() throws DateParseException {
        //given
        DateParser parser = spy(new DateParser());
        when(parser.getDateFormatter()).thenReturn(new SimpleDateFormat("dd.MM.yyyy"));

        //when
        parser.parse("12/08/2017");

        //then
        //generate exception
    }
}