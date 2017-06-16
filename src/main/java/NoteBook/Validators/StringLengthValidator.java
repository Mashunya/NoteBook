package NoteBook.Validators;

import NoteBook.Command.ParamDescription.ParamDescription;
import NoteBook.Exception.StringLengthValidateException;
import NoteBook.Exception.ValidateException;

/**
 * Created by Маша on 15.06.2017.
 */
public class StringLengthValidator implements Validator {

    @Override
    public void validate(Object validatedParamValue, ParamDescription paramDescription) throws StringLengthValidateException {
        int expectedLength = paramDescription.getStringLength();
        if(expectedLength != 0 && validatedParamValue != null && ((String)validatedParamValue).length() > expectedLength) {
            throw new StringLengthValidateException(expectedLength);
        }
    }
}
