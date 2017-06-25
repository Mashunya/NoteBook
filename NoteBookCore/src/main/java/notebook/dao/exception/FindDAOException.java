package notebook.dao.exception;

/**
 * Created by Маша on 25.06.2017.
 */
public class FindDAOException extends DAOException {
    private String message = "Не удалось найти DAO, соответствующее классу ";

    public FindDAOException(Class DAOClass) {
        message += DAOClass.getName();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
