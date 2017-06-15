package NoteBook.Validators;

import NoteBook.Exception.RequiredParamValidateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by Маша on 13.06.2017.
 */
@RunWith(JUnit4.class)
public class NotNullValidatorTest {

    @Test
    public void validate_notNullObject() throws RequiredParamValidateException {
        NotNullValidator notNullValidator = new NotNullValidator();
        notNullValidator.validate(new String("test"));
    }

    @Test(expected = RequiredParamValidateException.class)
    public void validate_nullObject() throws RequiredParamValidateException {
        NotNullValidator notNullValidator = new NotNullValidator();
        notNullValidator.validate(null);
    }
}
