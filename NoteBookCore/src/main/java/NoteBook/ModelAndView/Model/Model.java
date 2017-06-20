package NoteBook.ModelAndView.Model;

import java.util.List;

/**
 * Created by Маша on 19.06.2017.
 */
public interface Model {
    void addMessage(Message message);
    List<Message> getMessages();
}
