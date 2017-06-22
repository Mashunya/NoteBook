package notebook.exception;

/**
 * Created by Маша on 20.06.2017.
 */
public class ResourceNotFoundException extends Exception {
    private String message = "При загрузке файла ресурса произошла ошибка. Ресурс: ";

    public ResourceNotFoundException(String resourceName) {
        message += resourceName;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
