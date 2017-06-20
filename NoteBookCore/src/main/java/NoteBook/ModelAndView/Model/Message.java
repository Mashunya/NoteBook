package NoteBook.ModelAndView.Model;

/**
 * Created by Маша on 19.06.2017.
 */
public class Message {
    private String message;
    private Enum status;

    public Message(String message, Enum status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public Enum getStatus() {
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
