package notebook.exception;

/**
 * Created by Маша on 09.06.2017.
 */
public class TypeValidateException extends ValidateException {
    private static final String message = "Несоответствие типов";

    public String getMessage() {
        return message;
    }
}
