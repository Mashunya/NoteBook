package NoteBook.ModelAndView.Model;

/**
 * Created by Маша on 19.06.2017.
 */
public class Message {
    public static final int SUCCESS = 0;
    public static final int INFO = 1;
    public static final int WARNING = 2;
    public static final int ERROR = 3;

    private String message;
    private int status;

    public Message(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (status != message1.status) return false;
        return message.equals(message1.message);
    }
}
