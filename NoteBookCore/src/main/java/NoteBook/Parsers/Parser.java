package NoteBook.Parsers;

import NoteBook.Exception.ParseException;

/**
 * Created by Маша on 15.06.2017.
 */
public interface Parser<T> {
    T parse(Object parsedObject) throws ParseException;
}
