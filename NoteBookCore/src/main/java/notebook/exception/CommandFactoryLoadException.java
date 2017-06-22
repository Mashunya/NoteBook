package notebook.exception;

/**
 * Created by Маша on 13.06.2017.
 */
public class CommandFactoryLoadException extends CommandFactoryException {
    private String message = "При загрузке фабрики команд произошла ошибка. Команда: ";

    public CommandFactoryLoadException(Throwable ex, Class commandClass) {
        message += commandClass.getName();
        initCause(ex);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
