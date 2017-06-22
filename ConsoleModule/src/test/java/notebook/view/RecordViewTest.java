package notebook.view;

import notebook.entity.Record;
import notebook.model.Message;
import notebook.model.MessageStatus;
import notebook.model.RecordModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

/**
 * Created by Маша on 20.06.2017.
 */
@RunWith(JUnit4.class)
public class RecordViewTest {
    @Test
    public void showMessages() {
        //given
        Record record = new Record("text1", "author1", "title1", "type1", new Date());

        List<Message> messages = new ArrayList<>();
        messages.add(new Message("first message", MessageStatus.SUCCESS));
        messages.add(new Message("second message", MessageStatus.ERROR));

        RecordModel model = mock(RecordModel.class);
        when(model.getMessages()).thenReturn(messages);
        when(model.getRecord()).thenReturn(record);

        ConsoleView consoleView = mock(ConsoleView.class);
        RecordView recordView = spy(new RecordView());
        when(recordView.getNewConsoleView()).thenReturn(consoleView);

        //when
        recordView.show(model);

        //then
        verify(consoleView).show(record);
        verify(consoleView).show(messages.get(0));
        verify(consoleView).show(messages.get(1));
    }
}