package notebook.dao.exception;

/**
 * Created by Маша on 25.06.2017.
 */
public class ExecuteException extends DAOException {
    private final static String message = "При выполнении запроса произошла ошибка";

    public ExecuteException(Exception ex) {
        initCause(ex);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
