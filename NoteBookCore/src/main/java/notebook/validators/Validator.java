package notebook.validators;

import notebook.command.params.ParamDescription;
import notebook.exception.ValidateException;

/**
 * Created by Маша on 15.06.2017.
 */
public interface Validator {
    void validate(Object validatedParam, ParamDescription paramDescription) throws ValidateException;
}
