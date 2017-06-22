package notebook.store;

import notebook.exception.NoteBookLoadException;
import notebook.entity.Record;
import notebook.exception.SaveRecordsException;

import java.util.ArrayList;

/**
 * Created by Маша on 08.06.2017.
 */
public interface RecordStore {
    ArrayList<Record> loadAllRecords() throws NoteBookLoadException;
    void saveAllRecords(ArrayList<Record> records) throws SaveRecordsException;
}
