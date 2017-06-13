package NoteBook.Validators;

import NoteBook.Exception.ArrLengthValidateException;

/**
 * Created by Маша on 13.06.2017.
 */
public class NotNullValidator {
    public void validate(Object obj) throws NullPointerException {
        if(obj == null) {
            throw new NullPointerException();
        }
    }
}
