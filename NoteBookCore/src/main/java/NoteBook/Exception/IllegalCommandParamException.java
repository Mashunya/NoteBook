package NoteBook.Exception;

/**
 * Created by Маша on 13.06.2017.
 */
public class IllegalCommandParamException extends Exception {
    private String message = "Недопустимый параметр команды: ";

    public IllegalCommandParamException(Throwable ex, String fieldName) {
        message += fieldName;
        initCause(ex);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
