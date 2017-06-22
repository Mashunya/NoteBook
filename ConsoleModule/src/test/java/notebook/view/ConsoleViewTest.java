package notebook.view;

import notebook.entity.Record;
import notebook.model.Message;
import notebook.model.MessageStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by Маша on 20.06.2017.
 */
@RunWith(JUnit4.class)
public class ConsoleViewTest {

    ConsoleView consoleView;

    @Before
    public void init() {
        consoleView = Mockito.spy(new ConsoleView());
        doNothing().when(consoleView).printInConsole(anyString());
        doReturn(new SimpleDateFormat("dd.MM.yyyy")).when(consoleView).getDateFormatter();
    }

    @Test
    public void showSuccessMessage() {
        //given
        Message message = new Message("test text", MessageStatus.SUCCESS);

        //when
        consoleView.show(message);

        //then
        verify(consoleView).printInConsole("SUCCESS: test text");
    }

    @Test
    public void showInfoMessage() {
        //given
        Message message = new Message("test text", MessageStatus.INFO);

        //when
        consoleView.show(message);

        //then
        verify(consoleView).printInConsole("INFO: test text");
    }

    @Test
    public void showWarningMessage() {
        //given
        Message message = new Message("test text", MessageStatus.WARNING);

        //when
        consoleView.show(message);

        //then
        verify(consoleView).printInConsole("WARNING: test text");
    }

    @Test
    public void showErrorMessage() {
        //given
        Message message = new Message("test text", MessageStatus.ERROR);

        //when
        consoleView.show(message);

        //then
        verify(consoleView).printInConsole("ERROR: test text");
    }

    @Test
    public void showRecord() {
        //given
        Calendar calendar = new GregorianCalendar();
        calendar.set(2017, 3, 10);
        Date date = calendar.getTime();
        Record record = new Record("test text", "test author", "test title", "test type", date);
        record.setRecordID(10);
        record.setCreatedDate(date);
        record.setUpdatedDate(date);

        String expectedMessage = "ID: 10\n" +
                "Title: test title\n" +
                "Text: test text\n" +
                "Author: test author\n" +
                "Type: test type\n" +
                "DeadlineDate: 10.04.2017\n" +
                "CreatedDate: 10.04.2017\n" +
                "UpdateDate: 10.04.2017\n";

        //when
        consoleView.show(record);

        //then
        verify(consoleView).printInConsole(expectedMessage);
    }

    @Test
    public void showRecordList() {
        //given
        List<Record> records = new ArrayList<>();

        Date curDate = new Date();
        Record record;
        Calendar calendar = new GregorianCalendar();
        for(int i = 0; i < 3; i++) {
            calendar.set(2017, i, 10 + i);
            record = new Record("test text", "test author", "test title", "test type", calendar.getTime());
            record.setRecordID(10);
            record.setCreatedDate(curDate);
            record.setUpdatedDate(curDate);
            records.add(record);
        }

        //when
        consoleView.show(records);

        //then
        verify(consoleView, times(1)).show(records.get(0));
        verify(consoleView, times(1)).show(records.get(1));
        verify(consoleView, times(1)).show(records.get(2));
    }
}