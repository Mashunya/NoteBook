package NoteBook.Exception;

/**
 * Created by Маша on 09.06.2017.
 */
public class NullObjectValidateException extends ValidateException {
    private static final String message = "Объект не проинициализирован (значение null)";

    public String getMessage() {
        return message;
    }
}
