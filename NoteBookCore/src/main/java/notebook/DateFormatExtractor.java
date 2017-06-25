package notebook;

import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Маша on 25.06.2017.
 */
public class DateFormatExtractor {

    private static final String DEFAULT_DATE_FORMAT = "dd.MM.yyyy";
    private static final Logger logger = LoggerFactory.getLogger(DateFormatExtractor.class);

    private static String dateFormat = null;

    public static String getDateFormat() {
        if(dateFormat == null) {
            try {
                dateFormat = GlobalParamsExtractor.getProperty("Date_Format");
                if (dateFormat == null) {
                    dateFormat = DEFAULT_DATE_FORMAT;
                }
            } catch (PropFileLoadException | ResourceNotFoundException ex) {
                logger.error(ex.getMessage(), ex);
                dateFormat = DEFAULT_DATE_FORMAT;
            }
        }
        return dateFormat;
    }
}
