package NoteBook.Exception;

/**
 * Created by Маша on 08.06.2017.
 */
public class NoteBookLoadException extends Exception {

    private static final String message = "При загрузке сохраненных записей блокнота возникла ошибка";

    public NoteBookLoadException(Throwable ex) {
        initCause(ex);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
