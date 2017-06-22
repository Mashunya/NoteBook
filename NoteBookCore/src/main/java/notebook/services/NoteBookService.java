package notebook.services;

import notebook.entity.NoteBook;
import notebook.entity.Record;
import notebook.exception.NoteBookLoadException;
import notebook.exception.SaveRecordsException;
import notebook.id.IDGen;
import notebook.id.SimpleIDGen;
import notebook.store.RecordStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Маша on 11.06.2017.
 */
public class NoteBookService {

    private IDGen idGen;
    private RecordStore recordStore;

    private NoteBook noteBook;

    public NoteBookService(NoteBook noteBook, IDGen idGen, RecordStore recordStore) {
        this.noteBook = noteBook;
        this.idGen = idGen;
        this.recordStore = recordStore;
    }

    public void addRecord(Record record) throws SaveRecordsException {
        record.setRecordID(idGen.getNextID());
        noteBook.getRecords().add(record);
        recordStore.saveAllRecords(noteBook.getRecords());
    }

    public boolean deleteRecord(int recordID) throws SaveRecordsException {
        for(Record record: noteBook.getRecords()) {
            if(record.getRecordID() == recordID) {
                noteBook.getRecords().remove(record);
                recordStore.saveAllRecords(noteBook.getRecords());
                return true;
            }
        }
        return false;
    }

    public List<Record> findAll() {
        return noteBook.getRecords();
    }

    public Record findByID(int recordID) {
        for(Record record: noteBook.getRecords()) {
            if(record.getRecordID() == recordID) {
                return record;
            }
        }
        return null;
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
}
