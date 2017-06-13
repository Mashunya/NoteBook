package NoteBook.Validators;

import NoteBook.Exception.NullObjectValidateException;

/**
 * Created by Маша on 13.06.2017.
 */
public class NotNullValidator {
    public void validate(Object obj) throws NullObjectValidateException {
        if(obj == null) {
            throw new NullObjectValidateException();
        }
    }
}
