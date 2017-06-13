package NoteBook.Parsers;

/**
 * Created by Маша on 09.06.2017.
 */
public class IntegerParser {
    public int parse(Object numObj) throws NumberFormatException {
        return Integer.parseInt(numObj.toString());
    }
}
