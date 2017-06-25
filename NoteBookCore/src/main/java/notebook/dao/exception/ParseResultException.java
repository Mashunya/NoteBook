package notebook.dao.exception;

/**
 * Created by Маша on 25.06.2017.
 */
public class ParseResultException extends DAOException {
    private final static String message = "При преобразовании результата запроса произошла ошибка";

    public ParseResultException(Exception ex) {
        initCause(ex);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
