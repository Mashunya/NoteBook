package NoteBook.Command;

import NoteBook.Exception.IllegalCommandParamException;
import NoteBook.Exception.ValidateException;
import NoteBook.Parsers.IntegerParser;
import NoteBook.Services.NoteBookService;
import NoteBook.Validators.NotNullValidator;
import NoteBook.Validators.Required;
import NoteBook.Validators.ZeroOrNatural;
import NoteBook.Validators.ZeroOrNaturalNumberValidator;
import NoteBook.View.View;
import org.slf4j.Logger;

import java.lang.reflect.Field;

/**
 * Created by Маша on 08.06.2017.
 */
public abstract class Command {

    protected Logger logger;
    protected NoteBookService noteBookService;
    protected View view;

    public void initParams(String... params) throws IllegalCommandParamException {

        Class commandClass = this.getClass();
        IntegerParser integerParser = new IntegerParser();

        for(int i = 1; i < params.length; i++) {
            String[] buffer = params[i].split("=");
            String fieldName = buffer[0];
            Object fieldValue = buffer[1];
            try {
                Field field = commandClass.getDeclaredField(fieldName);
                field.setAccessible(true);

                if(field.getType() == int.class) {
                    fieldValue = integerParser.parse(fieldValue);
                }

                field.set(this, fieldValue);
            } catch (IllegalAccessException | NoSuchFieldException | NumberFormatException ex) {
                throw new IllegalCommandParamException(ex, fieldName);
            }
        }
    }

    public void validate() throws ValidateException {

        NotNullValidator notNullValidator = new NotNullValidator();
        ZeroOrNaturalNumberValidator zeroOrNaturalNumberValidator = new ZeroOrNaturalNumberValidator();

        Class commandClass = this.getClass();

        Field[] fields = commandClass.getDeclaredFields();

        try {
            for (Field field : fields) {
                field.setAccessible(true);

                if (field.getAnnotation(Required.class) != null) {
                    notNullValidator.validate(field.get(this));
                }
                if(field.getAnnotation(ZeroOrNatural.class) != null) {
                    zeroOrNaturalNumberValidator.validate((int)field.get(this));
                }
            }
        } catch(IllegalAccessException ex) {}
    }

    public abstract void execute();
}
