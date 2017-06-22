package notebook.parsers;

import notebook.exception.ParseException;

/**
 * Created by Маша on 15.06.2017.
 */
public interface Parser<T> {
    T parse(Object parsedObject) throws ParseException;
}
