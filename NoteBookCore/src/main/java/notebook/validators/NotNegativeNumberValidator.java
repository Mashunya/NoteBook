package notebook.validators;

import notebook.command.params.ParamDescription;
import notebook.exception.NegativeNumberException;

/**
 * Created by Маша on 09.06.2017.
 */
public class NotNegativeNumberValidator implements Validator {
    public void validate(Object validatedParam, ParamDescription paramDescription) throws NegativeNumberException {
        if((int)validatedParam < 0) {
            throw new NegativeNumberException();
        }
    }
}
