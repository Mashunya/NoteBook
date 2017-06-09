package NoteBook.Exception;

/**
 * Created by Маша on 08.06.2017.
 */
public class PropFileLoadException extends Exception {
    private static final String message = "При загрузке конфигурационного файла возникла ошибка";

    public PropFileLoadException(Throwable ex) {
        initCause(ex);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
