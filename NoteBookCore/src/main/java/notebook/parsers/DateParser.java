package notebook.parsers;

import notebook.exception.DateParseException;
import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;
import notebook.GlobalParamsExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Маша on 21.06.2017.
 */
public class DateParser implements Parser<Date> {
    private Logger logger = LoggerFactory.getLogger(DateParser.class);

    @Override
    public Date parse(Object parsedObject) throws DateParseException {
        DateFormat formatter = getDateFormatter();
        try {
            return formatter.parse(parsedObject.toString());
        } catch (ParseException ex) {
            throw new DateParseException(ex);
        }
    }

    DateFormat getDateFormatter() {
        String dateFormat;
        try {
            dateFormat = GlobalParamsExtractor.getProperty("Date_Format");
        } catch(PropFileLoadException | ResourceNotFoundException ex) {
            dateFormat = "dd.MM.yyyy";
            logger.error(ex.getMessage(), ex);
        }
        return new SimpleDateFormat(dateFormat);
    }
}
