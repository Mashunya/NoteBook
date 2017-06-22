package notebook.validators;

import notebook.command.params.ParamDescription;
import notebook.exception.NegativeNumberException;
import notebook.exception.ValidateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.mockito.Mockito.mock;

/**
 * Created by Маша on 20.06.2017.
 */
@RunWith(JUnit4.class)
public class NotNegativeNumberValidatorTest {
    @Test
    public void validatePositiveNumber() throws ValidateException {
        //given
        NotNegativeNumberValidator validator = new NotNegativeNumberValidator();
        ParamDescription paramDescription = mock(ParamDescription.class);

        //when
        validator.validate(5, paramDescription);

        //then
        //work without exception
    }

    @Test
    public void validateZero() throws ValidateException {
        //given
        NotNegativeNumberValidator validator = new NotNegativeNumberValidator();
        ParamDescription paramDescription = mock(ParamDescription.class);

        //when
        validator.validate(0, paramDescription);

        //then
        //work without exception
    }

    @Test(expected = NegativeNumberException.class)
    public void validateNegativeNumber() throws ValidateException {
        //given
        NotNegativeNumberValidator validator = new NotNegativeNumberValidator();
        ParamDescription paramDescription = mock(ParamDescription.class);

        //when
        validator.validate(-5, paramDescription);

        //then
        //work without exception
    }
}