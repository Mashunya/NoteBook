package NoteBook.Command.CommandResult;

/**
 * Created by Маша on 16.06.2017.
 */
public class CommandResult {
    public static final int SUCCESS = 0;
    public static final int INFO = 1;
    public static final int WARNING = 2;
    public static final int ERROR = 3;

    private Object resultMessage;
    private int status;

    public CommandResult(Object resultMessage, int status) {
        this.resultMessage = resultMessage;
        this.status = status;
    }

    public Object getResultMessage() {
        return resultMessage;
    }

    public int getStatus() {
        return status;
    }
}
