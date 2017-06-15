package NoteBook.Command;

import NoteBook.Exception.IllegalCommandParamException;
import NoteBook.Exception.ValidateException;
import NoteBook.Parsers.IntegerParser;
import NoteBook.Validators.NotNullValidator;
import NoteBook.Validators.Required;
import NoteBook.Validators.NotNegative;
import NoteBook.Validators.NotNegativeNumberValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by Маша on 13.06.2017.
 */
public class CommandInit {

    //TODO: createCommand вызывает initParams и validate, если проверили их нужно ли проверять createCommand
    public Command createCommand(Class commandClass, Map<String, String> params) throws IllegalCommandParamException, ValidateException {
        Command command = null;
        try {
            command = (Command) commandClass.newInstance();
            initParams(command, params);
            validate(command);
        } catch(IllegalAccessException | InstantiationException ex) {}

        return command;
    }

    public void initParams(Command command, Map<String, String> params) throws IllegalCommandParamException {

        Class commandClass = command.getClass();
        IntegerParser integerParser = new IntegerParser();

        Object fieldValue;
        for(Map.Entry param: params.entrySet()) {
            try {
                fieldValue = param.getValue();
                Field field = commandClass.getDeclaredField((String)param.getKey());
                field.setAccessible(true);

                if(field.getType() == Integer.class) {
                    fieldValue = integerParser.parse(fieldValue);
                }

                field.set(command, fieldValue);
            } catch (IllegalAccessException | NoSuchFieldException | NumberFormatException ex) {
                throw new IllegalCommandParamException(ex, (String)param.getKey());
            }
        }
    }

    //TODO: нужно проверять для всех классов команд или можно проверить для одного (например, DeleteCommand), где есть все аннотации?
    //TODO: классы ..Validator уже проверены, можно заменить на mock, но для этого их нужно вынести в поля класса, нужно ли это?
    public void validate(Command command) throws ValidateException {

        NotNullValidator notNullValidator = new NotNullValidator();
        NotNegativeNumberValidator notNegativeNumberValidator = new NotNegativeNumberValidator();

        Class commandClass = command.getClass();

        Field[] fields = commandClass.getDeclaredFields();

        try {
            for (Field field : fields) {
                field.setAccessible(true);

                if (field.getAnnotation(Required.class) != null) {
                    notNullValidator.validate(field.get(command));
                }
                if(field.getAnnotation(NotNegative.class) != null) {
                    notNegativeNumberValidator.validate((int)field.get(command));
                }
            }
        } catch(IllegalAccessException ex) {}
    }
}
