package NoteBook.Validators;

import NoteBook.Exception.ArrLengthValidateException;

/**
 * Created by Маша on 08.06.2017.
 */
public class ArrLengthValidator {
    public void validate(Object[] arr, int expectedLength) throws ArrLengthValidateException {
        if(arr.length < expectedLength) {
            throw new ArrLengthValidateException();
        }
    }
}
