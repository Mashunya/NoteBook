package notebook.view;

import notebook.model.Message;
import notebook.model.MessageListModel;
import notebook.model.MessageStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by Маша on 20.06.2017.
 */
@RunWith(JUnit4.class)
public class MessagesViewTest {

    @Test
    public void showMessages() {
        //given
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("first message", MessageStatus.SUCCESS));
        messages.add(new Message("second message", MessageStatus.ERROR));
        messages.add(new Message("third message", MessageStatus.WARNING));

        MessageListModel model = mock(MessageListModel.class);
        when(model.getMessages()).thenReturn(messages);

        ConsoleView consoleView = mock(ConsoleView.class);
        MessagesView messagesView = Mockito.spy(new MessagesView());
        when(messagesView.getNewConsoleView()).thenReturn(consoleView);

        //when
        messagesView.show(model);

        //then
        verify(consoleView).show(messages.get(0));
        verify(consoleView).show(messages.get(1));
        verify(consoleView).show(messages.get(2));
    }
}