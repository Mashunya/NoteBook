package NoteBook.Exception;

/**
 * Created by Маша on 15.06.2017.
 */
public class StringLengthValidateException extends ValidateException {
    private String message = "Слишком большая длина строки";

    public StringLengthValidateException(){}

    public StringLengthValidateException(int expectedLength) {
        message = message + ": ожидалось не более " + expectedLength;
    }

    public String getMessage() {
        return message;
    }
}
