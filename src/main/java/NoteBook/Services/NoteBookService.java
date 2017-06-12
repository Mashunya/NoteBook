package NoteBook.Services;

import NoteBook.Entity.NoteBook;
import NoteBook.Entity.Record;
import NoteBook.Exception.NoteBookLoadException;
import NoteBook.Exception.SaveRecordsException;
import NoteBook.IDGen.IDGen;
import NoteBook.IDGen.SimpleIDGen;
import NoteBook.RecordStore.RecordStore;
import NoteBook.View.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by Маша on 11.06.2017.
 */
public class NoteBookService {

    private Logger logger = LoggerFactory.getLogger(NoteBook.class);
    private IDGen idGen;
    private RecordStore recordStore;
    private View view;

    private NoteBook noteBook;

    public NoteBookService(NoteBook noteBook, IDGen idGen, RecordStore recordStore, View view) {
        this.noteBook = noteBook;
        this.idGen = idGen;
        this.recordStore = recordStore;
        this.view = view;
    }

    public void addRecord(String recordText) {
        Record record = new Record(idGen.getNextID(), recordText);
        noteBook.getRecords().add(record);
        try {
            recordStore.saveAllRecords(noteBook.getRecords());
            view.showInfoMessage("Запись " + recordText + " успешно добавлена");
            logger.info("Запись " + recordText + " успешно добавлена");
        } catch(SaveRecordsException ex) {
            view.showErrorMessage(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
    }

    public void deleteRecord(int recordID) {
        for(Record record: noteBook.getRecords()) {
            if(record.getRecordID() == recordID) {
                noteBook.getRecords().remove(record);
                try {
                    recordStore.saveAllRecords(noteBook.getRecords());
                    view.showInfoMessage("Запись c ID: " + recordID + " успешно удалена");
                    logger.info("Запись c ID: " + recordID + " успешно удалена");
                } catch (SaveRecordsException ex) {
                    view.showErrorMessage(ex.getMessage());
                    logger.error(ex.getMessage(), ex);
                }
                return;
            }
        }
        view.showInfoMessage("Запись c ID: " + recordID + " не найдена");
        logger.warn("DeleteRecord: запись c ID: " + recordID + " не найдена");
    }

    public void findAll() {
        if(noteBook.getRecords().size() == 0) {
            view.showInfoMessage("Не создано ни одной записи");
            logger.warn("FindAll: не создано ни одной записи");
        }
        for(Record record: noteBook.getRecords()) {
            view.shoeRecord(record);
        }
    }

    public void findByID(int recordID) {
        for(Record record: noteBook.getRecords()) {
            if(record.getRecordID() == recordID) {
                view.shoeRecord(record);
                return;
            }
        }
        view.showInfoMessage("Запись c ID: " + recordID + " не найдена");
        logger.warn("FindByID: запись c ID: " + recordID + " не найдена");
    }

    public void init() throws NoteBookLoadException {
        ArrayList<Record> records = recordStore.loadAllRecords();
        noteBook.setRecords(records);
        if(records.size() > 0) {
            SimpleIDGen.setLastID(records.get(records.size() - 1).getRecordID());
        }
    }

    public NoteBook getNoteBook() {
        return noteBook;
    }

    public void setNoteBook(NoteBook noteBook) {
        this.noteBook = noteBook;
    }
}
