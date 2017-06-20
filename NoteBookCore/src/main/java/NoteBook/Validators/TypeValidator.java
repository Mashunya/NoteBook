package NoteBook.Validators;

import NoteBook.Command.ParamDescription.ParamDescription;
import NoteBook.Exception.TypeValidateException;
import NoteBook.Exception.ValidateException;

/**
 * Created by Маша on 15.06.2017.
 */
public class TypeValidator implements Validator {

    @Override
    public void validate(Object validatedParam, ParamDescription paramDescription) throws TypeValidateException {

        if(paramDescription.getParamClass().equals(int.class)) {
            try {
                Integer.parseInt((String)validatedParam);
            } catch(NumberFormatException ex) {
                throw new TypeValidateException();
            }
        }
        // else if()...
    }
}
