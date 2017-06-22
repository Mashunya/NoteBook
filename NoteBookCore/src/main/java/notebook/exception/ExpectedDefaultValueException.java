package notebook.exception;

/**
 * Created by Маша on 16.06.2017.
 */
public class ExpectedDefaultValueException extends Exception {
    private String message = "Для необязательного параметра не задано значение по умолчанию: ";

    public ExpectedDefaultValueException(String paramName) {
        message += paramName;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
