package NoteBook.Exception;

/**
 * Created by Маша on 09.06.2017.
 */
public class NegativeNumberException extends NumberFormatException {
    private static final String message = "При преобразовании строки к неотрицаельному целому числу произошла ошибка";

    @Override
    public String getMessage() {
        return message;
    }
}
