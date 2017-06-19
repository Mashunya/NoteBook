package NoteBook.ModelAndView.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Маша on 19.06.2017.
 */
public class MessageListModel implements Model {
    private List<Message> messages = new ArrayList<>();

    @Override
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public void addMessage(Message message) {
        messages.add(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageListModel that = (MessageListModel) o;

        return messages.equals(that.messages);
    }
}
