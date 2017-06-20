package NoteBook.Parsers;

import NoteBook.Exception.NumberParseException;

/**
 * Created by Маша on 09.06.2017.
 */
public class IntParser implements Parser<Integer> {
    public Integer parse(Object parsedObj) throws NumberParseException {
        try {
            return Integer.parseInt(parsedObj.toString());
        } catch(NumberFormatException ex) {
            throw new NumberParseException(ex);
        }
    }
}
