package NoteBook.RecordStore;

import NoteBook.Exception.NoteBookLoadException;
import NoteBook.Entity.Record;
import NoteBook.Exception.SaveRecordsException;

import java.util.ArrayList;

/**
 * Created by Маша on 08.06.2017.
 */
public interface RecordStore {
    ArrayList<Record> loadAllRecords() throws NoteBookLoadException;
    void saveAllRecords(ArrayList<Record> records) throws SaveRecordsException;
}
