package NoteBook.Validators;

import NoteBook.Exception.NegativeNumberException;
import NoteBook.Exception.ValidateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Created by Маша on 20.06.2017.
 */
@RunWith(JUnit4.class)
public class NotNegativeNumberValidatorTest {
    @Test
    public void validate_positiveNumber() throws ValidateException {
        NotNegativeNumberValidator validator = new NotNegativeNumberValidator();
        validator.validate(5, null);
    }

    @Test
    public void validate_zero() throws ValidateException {
        NotNegativeNumberValidator validator = new NotNegativeNumberValidator();
        validator.validate(0, null);
    }

    @Test(expected = NegativeNumberException.class)
    public void validate_negativeNumber() throws ValidateException {
        NotNegativeNumberValidator validator = new NotNegativeNumberValidator();
        validator.validate(-5, null);
    }
}