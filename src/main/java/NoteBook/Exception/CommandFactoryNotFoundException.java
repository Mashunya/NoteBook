package NoteBook.Exception;

/**
 * Created by Маша on 16.06.2017.
 */
public class CommandFactoryNotFoundException extends CommandFactoryException {
    private String message = "Фабрика не найдена. Команда: ";

    public CommandFactoryNotFoundException(Class commandClass) {
        message += commandClass.getName();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
