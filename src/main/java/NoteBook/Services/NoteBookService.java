package NoteBook.Services;

import NoteBook.Command.CommandResult.CommandResult;
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

    private Logger logger = LoggerFactory.getLogger(NoteBookService.class);
    private IDGen idGen;
    private RecordStore recordStore;

    private NoteBook noteBook;

    public NoteBookService(NoteBook noteBook, IDGen idGen, RecordStore recordStore) {
        this.noteBook = noteBook;
        this.idGen = idGen;
        this.recordStore = recordStore;
    }

    public CommandResult addRecord(Record record) {
        record.setRecordID(idGen.getNextID());
        noteBook.getRecords().add(record);
        try {
            recordStore.saveAllRecords(noteBook.getRecords());
            logger.info("Запись успешно добавлена");
            return new CommandResult("Запись успешно добавлена", CommandResult.SUCCESS);
        } catch(SaveRecordsException ex) {
            logger.error(ex.getMessage(), ex);
            return new CommandResult(ex.getMessage(), CommandResult.ERROR);
        }
    }

    public CommandResult deleteRecord(int recordID) {
        for(Record record: noteBook.getRecords()) {
            if(record.getRecordID() == recordID) {
                noteBook.getRecords().remove(record);
                try {
                    recordStore.saveAllRecords(noteBook.getRecords());
                    logger.info("Запись c ID: " + recordID + " успешно удалена");
                    return new CommandResult("Запись c ID: " + recordID + " успешно удалена", CommandResult.SUCCESS);
                } catch (SaveRecordsException ex) {
                    logger.error(ex.getMessage(), ex);
                    return new CommandResult(ex.getMessage(), CommandResult.ERROR);
                }
            }
        }
        logger.warn("DeleteRecord: запись c ID: " + recordID + " не найдена");
        return new CommandResult("Запись c ID: " + recordID + " не найдена", CommandResult.WARNING);
    }

    public CommandResult findAll() {
        if(noteBook.getRecords().size() == 0) {
            logger.warn("FindAll: не создано ни одной записи");
            return new CommandResult("Не создано ни одной записи", CommandResult.WARNING);
        }
        return new CommandResult(noteBook.getRecords(), CommandResult.SUCCESS);
    }

    public CommandResult findByID(int recordID) {
        for(Record record: noteBook.getRecords()) {
            if(record.getRecordID() == recordID) {
                return new CommandResult(record, CommandResult.SUCCESS);
            }
        }
        logger.warn("FindByID: запись c ID: " + recordID + " не найдена");
        return new CommandResult("Запись c ID: " + recordID + " не найдена", CommandResult.WARNING);
    }

    public void init() throws NoteBookLoadException {
        ArrayList<Record> records = recordStore.loadAllRecords();
        noteBook.setRecords(records);
        if(records.size() > 0) {
            SimpleIDGen.setLastID(records.get(records.size() - 1).getRecordID());
        }
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
