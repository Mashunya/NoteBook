package NoteBook.Validators;

import NoteBook.Exception.NegativeNumberException;

/**
 * Created by Маша on 09.06.2017.
 */
public class NotNegativeNumberValidator {
    public void validate(int number) throws NegativeNumberException {
        if(number < 0) {
            throw new NegativeNumberException();
        }
    }
}
