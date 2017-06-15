package NoteBook.Validators;

import NoteBook.Exception.ValidateException;

/**
 * Created by Маша on 15.06.2017.
 */
public interface Validator {
    void validate(Object validatedParam) throws ValidateException;
}
