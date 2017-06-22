package notebook.services;

import notebook.entity.NoteBook;
import notebook.entity.Record;
import notebook.exception.NoteBookLoadException;
import notebook.exception.SaveRecordsException;
import notebook.id.IDGen;
import notebook.id.SimpleIDGen;
import notebook.model.*;
import notebook.store.RecordStore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Маша on 20.06.2017.
 */
@RunWith(JUnit4.class)
public class NoteBookServiceTest {
    private NoteBook noteBook;
    private IDGen idGen;
    private RecordStore recordStore;

    @Before
    public void init() {
        noteBook = new NoteBook();

        idGen = mock(IDGen.class);
        when(idGen.getNextID()).thenReturn(10);
        recordStore = mock(RecordStore.class);
    }

    @Test
    public void addRecord() throws SaveRecordsException {
        //given
        NoteBookService noteBookService = new NoteBookService(noteBook, idGen, recordStore);

        Date deadlineDate = new Date();
        Record record = new Record("test text", "test author", "test title", "test type", deadlineDate);

        //when
        noteBookService.addRecord(record);

        //then
        List<Record> recordsAfterAdd = noteBookService.getNoteBook().getRecords();
        Record lastRecord = recordsAfterAdd.get(recordsAfterAdd.size() - 1);
        assertEquals(10, lastRecord.getRecordID());
        assertEquals("test text", lastRecord.getRecordText());
        assertEquals("test author", lastRecord.getAuthor());
        assertEquals("test title", lastRecord.getTitle());
        assertEquals("test type", lastRecord.getType());
        assertEquals(deadlineDate, lastRecord.getDeadlineDate());
        verify(recordStore).saveAllRecords(noteBook.getRecords());
    }

    @Test(expected = SaveRecordsException.class)
    public void addRecordSaveRecordInFileException() throws SaveRecordsException {
        //given
        SaveRecordsException ex = new SaveRecordsException(null);
        doThrow(SaveRecordsException.class).when(recordStore).saveAllRecords(any(ArrayList.class));

        NoteBookService noteBookService = new NoteBookService(noteBook, idGen, recordStore);

        Record record = new Record("test text", "test author", "test title", "test type", new Date());

        Model model = new MessageListModel();
        model.addMessage(new Message(ex.getMessage(), MessageStatus.ERROR));
        ModelAndView expectedModelAndView = new ModelAndView("MessagesView", model);

        //when
        noteBookService.addRecord(record);

        //then
        //generate exception
    }

    @Test
    public void deleteRecordWhenRecordExist() throws SaveRecordsException {
        //given
        Record existRecord = new Record("test text", "test author", "test title", "test type", new Date());
        Record record = spy(existRecord);
        when(record.getRecordID()).thenReturn(10);

        noteBook.getRecords().add(record);

        NoteBookService noteBookService = new NoteBookService(noteBook, idGen, recordStore);

        //when
        boolean result = noteBookService.deleteRecord(10);

        //then
        assertEquals(true, result);
        verify(recordStore).saveAllRecords(noteBook.getRecords());
    }

    @Test
    public void deleteRecordRecordNotExist() throws SaveRecordsException {
        //given
        Record existRecord = new Record("test text", "test author", "test title", "test type", new Date());
        Record record = spy(existRecord);
        when(record.getRecordID()).thenReturn(10);
        noteBook.getRecords().add(record);

        NoteBookService noteBookService = new NoteBookService(noteBook, idGen, recordStore);

        //when
        boolean result = noteBookService.deleteRecord(0);

        //then
        assertEquals(false, result);
    }

    @Test(expected = SaveRecordsException.class)
    public void deleteRecordSaveRecordInFileException() throws SaveRecordsException {
        //given
        Record existRecord = new Record("test text", "test author", "test title", "test type", new Date());
        Record record = spy(existRecord);
        when(record.getRecordID()).thenReturn(10);
        noteBook.getRecords().add(record);

        SaveRecordsException ex = new SaveRecordsException(null);
        doThrow(SaveRecordsException.class).when(recordStore).saveAllRecords(any(ArrayList.class));

        NoteBookService noteBookService = new NoteBookService(noteBook, idGen, recordStore);

        //when
        noteBookService.deleteRecord(10);

        //then
        //generate exception
    }

    @Test
    public void findAllReturnAllRecordsList() {
        //given
        ArrayList<Record> recordList = new ArrayList<>();
        Record record;
        for(int i = 0; i < 3; i++) {
            record = spy(new Record("test text " + i, "test author " + i, "test title " + i,
                    "test type " + i, new Date()));
            when(record.getRecordID()).thenReturn(i);
            recordList.add(record);
        }
        noteBook.setRecords(recordList);

        NoteBookService noteBookService = new NoteBookService(noteBook, idGen, recordStore);

        //when
        List<Record> result = noteBookService.findAll();

        //then
        assertEquals(recordList, result);
    }

    @Test
    public void findAllNoRecords() {
        //given
        NoteBookService noteBookService = new NoteBookService(noteBook, idGen, recordStore);

        MessageListModel model = new MessageListModel();
        model.addMessage(new Message("Не создано ни одной записи", MessageStatus.WARNING));
        ModelAndView expectedModelAndView = new ModelAndView("MessagesView", model);

        //when
        List<Record> result = noteBookService.findAll();

        //then
        assertEquals(0, result.size());
    }

    @Test
    public void findByIDWhenRecordExist() {
        //given
        Record existRecord = new Record("test text", "test author", "test title", "test type", new Date());
        Record record = spy(existRecord);
        when(record.getRecordID()).thenReturn(10);

        noteBook.getRecords().add(record);

        NoteBookService noteBookService = new NoteBookService(noteBook, idGen, recordStore);

        //when
        Record result = noteBookService.findByID(10);

        //then
        assertEquals(record, result);
    }

    @Test
    public void findByIDRecordNotExist() {
        //given
        Record existRecord = new Record("test text", "test author", "test title", "test type", new Date());
        Record record = spy(existRecord);
        when(record.getRecordID()).thenReturn(10);
        noteBook.getRecords().add(record);

        NoteBookService noteBookService = new NoteBookService(noteBook, idGen, recordStore);

        //when
        Record result = noteBookService.findByID(0);

        //then
        assertNull(result);
    }

    @Test
    public void initSomeRecordsIsSaved() throws NoteBookLoadException {
        //given
        ArrayList<Record> records = new ArrayList<>();
        Record record;
        for(int i = 0; i < 3; i++) {
            record = spy(new Record("test text " + i, "test author " + i, "test title " + i,
                    "test type " + i, new Date()));
            when(record.getRecordID()).thenReturn(i);
            records.add(record);
        }
        when(recordStore.loadAllRecords()).thenReturn(records);

        NoteBookService noteBookService = new NoteBookService(noteBook, idGen, recordStore);

        //when
        noteBookService.init();

        //then
        verify(recordStore).loadAllRecords();
        assertEquals(records, noteBookService.getNoteBook().getRecords());
        assertEquals(SimpleIDGen.getLastID(), 2);
    }
}