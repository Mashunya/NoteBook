package NoteBook.Validators;

import NoteBook.Exception.NullObjectValidateException;
import NoteBook.Exception.ValidateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by Маша on 13.06.2017.
 */
@RunWith(JUnit4.class)
public class NotNullValidatorTest {

    @Test
    public void validate_notNullObject() throws NullObjectValidateException {
        NotNullValidator notNullValidator = new NotNullValidator();
        notNullValidator.validate(new String("test"));
    }

    @Test(expected = NullObjectValidateException.class)
    public void validate_nullObject() throws NullObjectValidateException {
        NotNullValidator notNullValidator = new NotNullValidator();
        notNullValidator.validate(null);
    }
}
