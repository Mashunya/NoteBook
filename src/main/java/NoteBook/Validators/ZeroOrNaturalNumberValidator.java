package NoteBook.Validators;

import NoteBook.Exception.ZeroOrNaturalNumberFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Маша on 09.06.2017.
 */
public class ZeroOrNaturalNumberValidator {
    public void validate(String number) throws ZeroOrNaturalNumberFormatException {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(number);
        if(!m.matches()) {
            throw new ZeroOrNaturalNumberFormatException();
        }
    }
}
