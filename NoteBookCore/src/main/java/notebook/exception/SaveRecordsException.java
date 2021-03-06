package notebook.exception;

/**
 * Created by Маша on 08.06.2017.
 */
public class SaveRecordsException extends WorkWithFileException {
    private static final String message = "При загрузке сохраненных записей блокнота возникла ошибка";

    public SaveRecordsException(Throwable ex) {
        initCause(ex);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
