package NoteBook.Validators;

import NoteBook.Command.ParamDescription.ParamDescription;
import NoteBook.Exception.NegativeNumberException;

/**
 * Created by Маша on 09.06.2017.
 */
public class NotNegativeNumberValidator implements Validator {
    public void validate(Object validatedParam, ParamDescription paramDescription) throws NegativeNumberException {
        if((int)validatedParam < 0) {
            throw new NegativeNumberException();
        }
    }
}
