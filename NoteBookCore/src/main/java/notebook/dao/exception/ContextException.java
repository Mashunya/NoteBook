package notebook.dao.exception;

/**
 * Created by Маша on 25.06.2017.
 */
public class ContextException extends DAOException {
    private static final String message = "Не удалось установить соединение с источником данных";

    public ContextException(Exception ex) {
        initCause(ex);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
