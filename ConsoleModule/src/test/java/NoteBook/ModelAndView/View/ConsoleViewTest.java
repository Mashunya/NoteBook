package NoteBook.ModelAndView.View;

import NoteBook.Entity.Record;
import NoteBook.ModelAndView.Model.Message;
import NoteBook.ModelAndView.Model.MessageStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
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
        consoleView = spy(new ConsoleView());
        doNothing().when(consoleView).printInConsole(anyString());
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
        Date curDate = new Date();
        Record record = new Record("test text", "test author", "test title", "test type");
        record.setRecordID(10);
        record.setCreatedDate(curDate);
        record.setUpdatedDate(curDate);

        String expectedMessage = "ID: 10\n" +
                "Title: test title\n" +
                "Text: test text\n" +
                "Author: test author\n" +
                "Type: test type\n" +
                "CreatedDate: " + curDate.toString() + "\n" +
                "UpdateDate: " + curDate.toString() + "\n";

        //when
        consoleView.show(record);

        //then
        verify(consoleView).printInConsole(expectedMessage);
    }

    @Test
    public void showRecordList() {
        //given
        List<Record> records = new ArrayList<>();

        Date curDate;
        Record record;
        for(int i = 0; i < 3; i++) {
            curDate = new Date();
            record = new Record("test text", "test author", "test title", "test type");
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