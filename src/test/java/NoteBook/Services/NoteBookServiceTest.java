package NoteBook.Services;

import NoteBook.Entity.NoteBook;
import NoteBook.Entity.Record;
import NoteBook.Exception.SaveRecordsException;
import NoteBook.IDGen.IDGen;
import NoteBook.IDGen.SimpleIDGen;
import NoteBook.RecordStore.FileStore;
import NoteBook.RecordStore.RecordStore;
import NoteBook.View.ConsoleView;
import NoteBook.View.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Маша on 13.06.2017.
 */
@RunWith(JUnit4.class)
public class NoteBookServiceTest {

    private NoteBook noteBook;
    private Logger logger;
    private IDGen idGen;
    private RecordStore recordStore;
    private View view;

    @Before
    public void init() {
        noteBook = new NoteBook();

        logger = mock(Logger.class);
        idGen = mock(SimpleIDGen.class);
        recordStore = mock(FileStore.class);
        view = mock(ConsoleView.class);
    }

    @Test
    public void addRecord_allRight() {

        NoteBookService noteBookService = new NoteBookService(noteBook, idGen, recordStore, view);
        noteBookService.setLogger(logger);

        Record record = new Record("text", "author", "", "");

        noteBookService.addRecord(record);

        verify(view).showInfoMessage("Запись успешно добавлена");
        verify(logger).info("Запись успешно добавлена");
    }

    @Test
    public void addRecord_saveRecordInFileException() throws SaveRecordsException {

        doThrow(SaveRecordsException.class).when(recordStore).saveAllRecords(any(ArrayList.class));

        NoteBookService noteBookService = new NoteBookService(noteBook, idGen, recordStore, view);
        noteBookService.setLogger(logger);

        Record record = new Record("text", "author", "", "");

        noteBookService.addRecord(record);

        verify(view).showErrorMessage(anyString());
        verify(logger).error(anyString(), any(Exception.class));
    }

    @Test
    public void deleteRecord_recordExist() {
        Record existRecord = new Record("text", "author", "", "");
        Record record = spy(existRecord);
        noteBook.getRecords().add(record);

        when(record.getRecordID()).thenReturn(0);

        NoteBookService noteBookService = new NoteBookService(noteBook, idGen, recordStore, view);
        noteBookService.setLogger(logger);

        noteBookService.deleteRecord(0);

        verify(view).showInfoMessage("Запись c ID: 0 успешно удалена");
        verify(logger).info("Запись c ID: 0 успешно удалена");
    }

    @Test
    public void deleteRecord_recordNotExist() {
        Record existRecord = new Record("text", "author", "", "");
        Record record = spy(existRecord);
        noteBook.getRecords().add(record);

        when(record.getRecordID()).thenReturn(1);

        NoteBookService noteBookService = new NoteBookService(noteBook, idGen, recordStore, view);
        noteBookService.setLogger(logger);

        noteBookService.deleteRecord(0);

        verify(view).showInfoMessage("Запись c ID: 0 не найдена");
        verify(logger).warn("DeleteRecord: запись c ID: 0 не найдена");
    }

    @Test
    public void deleteRecord_saveRecordInFileException() throws SaveRecordsException {

        Record existRecord = new Record("text", "author", "", "");
        Record record = spy(existRecord);
        noteBook.getRecords().add(record);

        when(record.getRecordID()).thenReturn(0);

        doThrow(SaveRecordsException.class).when(recordStore).saveAllRecords(any(ArrayList.class));

        NoteBookService noteBookService = new NoteBookService(noteBook, idGen, recordStore, view);
        noteBookService.setLogger(logger);

        noteBookService.deleteRecord(0);

        verify(view).showErrorMessage(anyString());
        verify(logger).error(anyString(), any(Exception.class));
    }
}