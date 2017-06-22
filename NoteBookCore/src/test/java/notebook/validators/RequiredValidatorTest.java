package notebook.validators;

import notebook.command.params.ParamDescription;
import notebook.exception.RequiredParamValidateException;
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
public class RequiredValidatorTest {
    @Test
    public void validateRequiredFieldIsNotNull() throws ValidateException {
        //given
        Validator validator = new RequiredValidator();
        ParamDescription paramDescription = mock(ParamDescription.class);
        when(paramDescription.isRequired()).thenReturn(true);

        //when
        validator.validate(new Object(), paramDescription);

        //then
        //work without exception
    }

    @Test
    public void validateOptionalField() throws ValidateException {
        //given
        Validator validator = new RequiredValidator();
        ParamDescription paramDescription = mock(ParamDescription.class);
        when(paramDescription.isRequired()).thenReturn(false);

        //when
        validator.validate(null, paramDescription);

        //then
        //work without exception
    }

    @Test(expected = RequiredParamValidateException.class)
    public void validateRequiredFieldIsNull() throws ValidateException {
        //given
        Validator validator = new RequiredValidator();
        ParamDescription paramDescription = mock(ParamDescription.class);
        when(paramDescription.isRequired()).thenReturn(true);

        //when
        validator.validate(null, paramDescription);

        //then
        //generate exception
    }
}