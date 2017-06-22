package notebook.view;

import notebook.entity.Record;
import notebook.model.Message;
import notebook.model.MessageStatus;
import notebook.model.RecordListModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Маша on 21.06.2017.
 */
@RunWith(JUnit4.class)
public class RecordListViewTest {
    @Test
    public void showMessages() {
        //given
        List<Record> records = new ArrayList<>();
        records.add(new Record("text1", "author1", "title1", "type1", new Date()));
        records.add(new Record("text2", "author2", "title2", "type2", new Date()));

        List<Message> messages = new ArrayList<>();
        messages.add(new Message("first message", MessageStatus.SUCCESS));
        messages.add(new Message("second message", MessageStatus.ERROR));

        RecordListModel model = mock(RecordListModel.class);
        when(model.getMessages()).thenReturn(messages);
        when(model.getRecords()).thenReturn(records);

        ConsoleView consoleView = mock(ConsoleView.class);
        RecordListView recordsView = Mockito.spy(new RecordListView());
        when(recordsView.getNewConsoleView()).thenReturn(consoleView);

        //when
        recordsView.show(model);

        //then
        verify(consoleView).show(records);
        verify(consoleView).show(messages.get(0));
        verify(consoleView).show(messages.get(1));
    }
}