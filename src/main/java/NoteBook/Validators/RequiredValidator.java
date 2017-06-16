package NoteBook.Validators;

import NoteBook.Command.ParamDescription.ParamDescription;
import NoteBook.Exception.RequiredParamValidateException;

/**
 * Created by Маша on 15.06.2017.
 */
public class RequiredValidator implements Validator {
    @Override
    public void validate(Object validatedParam, ParamDescription paramDescription) throws RequiredParamValidateException {
        if(validatedParam == null && paramDescription.isRequired()) {
            throw new RequiredParamValidateException();
        }
    }
}
