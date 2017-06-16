package NoteBook.Exception;

/**
 * Created by Маша on 15.06.2017.
 */
public class NumberParseException extends ParseException {
    private static final String message = "При преобразовании строки к числу произошла ошибка";

    public NumberParseException(Throwable ex) {
        initCause(ex);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
