package notebook.model;

import java.util.List;

/**
 * Created by Маша on 19.06.2017.
 */
public interface Model {
    void addMessage(Message message);
    void addAllMessages(List<Message> messages);
    List<Message> getMessages();
}
