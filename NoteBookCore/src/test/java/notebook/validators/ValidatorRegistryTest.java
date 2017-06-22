package notebook.validators;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Created by Маша on 20.06.2017.
 */
@RunWith(JUnit4.class)
public class ValidatorRegistryTest {
    @Test
    public void getValidatorReturnRequiredValidator() {
        //when
        Validator validator = ValidatorRegistry.getInstance().getValidator(ValidatorNames.Required);

        //then
        assertEquals(RequiredValidator.class, validator.getClass());
    }

    @Test
    public void getValidatorReturnNotNegativeNumberValidator() {
        //when
        Validator validator = ValidatorRegistry.getInstance().getValidator(ValidatorNames.NotNegativeNumber);

        //then
        assertEquals(NotNegativeNumberValidator.class, validator.getClass());
    }

    @Test
    public void getValidatorReturnStringLengthValidator() {
        //when
        Validator validator = ValidatorRegistry.getInstance().getValidator(ValidatorNames.StringLength);

        //then
        assertEquals(StringLengthValidator.class, validator.getClass());
    }
}