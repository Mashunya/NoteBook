package notebook.validators;

import notebook.command.params.ParamDescription;
import notebook.exception.StringLengthValidateException;
import notebook.exception.ValidateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Маша on 20.06.2017.
 */
@RunWith(JUnit4.class)
public class StringLengthValidatorTest {
    @Test
    public void validateStringLengthIsLessThenMax() throws ValidateException {
        //given
        Validator validator = new StringLengthValidator();
        ParamDescription paramDescription = mock(ParamDescription.class);
        when(paramDescription.getMaxStringLength()).thenReturn(10);

        String paramString = "12345";

        //when
        validator.validate(paramString, paramDescription);

        //then
        //work without exception
    }

    @Test
    public void validateStringLengthIsEqualToMax() throws ValidateException {
        //given
        Validator validator = new StringLengthValidator();
        ParamDescription paramDescription = mock(ParamDescription.class);
        when(paramDescription.getMaxStringLength()).thenReturn(10);

        String paramString = "1234567890";

        //when
        validator.validate(paramString, paramDescription);

        //then
        //work without exception
    }

    @Test(expected = StringLengthValidateException.class)
    public void validateStringLengthIsMoreThenMax() throws ValidateException {
        //given
        Validator validator = new StringLengthValidator();
        ParamDescription paramDescription = mock(ParamDescription.class);
        when(paramDescription.getMaxStringLength()).thenReturn(10);

        String paramString = "12345678901234567890";

        //when
        validator.validate(paramString, paramDescription);

        //then
        //generate exception
    }

    @Test
    public void validateStringParamIsNull() throws ValidateException {
        //given
        Validator validator = new StringLengthValidator();
        ParamDescription paramDescription = mock(ParamDescription.class);
        when(paramDescription.getMaxStringLength()).thenReturn(10);

        //when
        validator.validate(null, paramDescription);

        //then
        //work without exception
    }
}