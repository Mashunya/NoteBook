package NoteBook.Exception;

/**
 * Created by Маша on 09.06.2017.
 */
public class ArrLengthValidateException extends ValidateException {
    private static final String message = "Количесво аргументов массива меньше ожидаемого";

    public String getMessage() {
        return message;
    }
}
