package notebook.exception;

/**
 * Created by Маша on 13.06.2017.
 */
public class IllegalCommandException extends Exception {
    private String message = "Недопустимая команда: ";

    public IllegalCommandException(String commandName) {
        message += commandName;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
