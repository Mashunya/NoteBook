package NoteBook.Validators;

import NoteBook.Exception.NegativeNumberException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by Маша on 13.06.2017.
 */
@RunWith(JUnit4.class)
public class NotNegativeNumberValidatorTest {

    @Test
    public void validate_positiveNumber() throws NegativeNumberException {
        NotNegativeNumberValidator validator = new NotNegativeNumberValidator();
        validator.validate(5);
    }

    @Test
    public void validate_zero() throws NegativeNumberException {
        NotNegativeNumberValidator validator = new NotNegativeNumberValidator();
        validator.validate(0);
    }

    @Test(expected = NegativeNumberException.class)
    public void validate_negativeNumber() throws NegativeNumberException {
        NotNegativeNumberValidator validator = new NotNegativeNumberValidator();
        validator.validate(-5);
    }
}
