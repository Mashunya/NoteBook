package notebook.exception;

/**
 * Created by Маша on 15.06.2017.
 */
public class DateParseException extends ParseException {
    private static final String message = "При преобразовании строки к дате произошла ошибка";

    public DateParseException(Throwable ex) {
        initCause(ex);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
