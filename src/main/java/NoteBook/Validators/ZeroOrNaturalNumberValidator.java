package NoteBook.Validators;

import NoteBook.Exception.ZeroOrNaturalNumberFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Маша on 09.06.2017.
 */
public class ZeroOrNaturalNumberValidator {
    public void validate(int number) throws ZeroOrNaturalNumberFormatException {
        if(number < 0) {
            throw new ZeroOrNaturalNumberFormatException();
        }
    }
}
