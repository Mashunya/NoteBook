package NoteBook.Entity;

import NoteBook.Exception.NoteBookLoadException;
import NoteBook.Exception.SaveRecordsException;
import NoteBook.IDGen.SimpleIDGen;
import NoteBook.IDGen.IDGen;
import NoteBook.RecordStore.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by Маша on 08.06.2017.
 */
public class NoteBook {
    private Logger logger = LoggerFactory.getLogger(NoteBook.class);
    private ArrayList<Record> records = new ArrayList<>();

    private IDGen idGen;
    private RecordStore recordStore;

    public NoteBook(IDGen idGen, RecordStore recordStore) {
        this.idGen = idGen;
        this.recordStore = recordStore;
    }

    public void addRecord(String recordText) {
        Record record = new Record(idGen.getNextID(), recordText);
        records.add(record);
        try {
            recordStore.saveAllRecords(records);
            System.out.println("Запись " + recordText + " успешно добавлена");
            logger.info("Запись " + recordText + " успешно добавлена");
        } catch(SaveRecordsException ex) {
            System.out.println(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
    }

    public void deleteRecord(int recordID) {
        for(Record record: records) {
            if(record.getRecordID() == recordID) {
                records.remove(record);
                try {
                    recordStore.saveAllRecords(records);
                    System.out.println("Запись c ID: " + recordID + " успешно удалена");
                    logger.info("Запись c ID: " + recordID + " успешно удалена");
                } catch (SaveRecordsException ex) {
                    System.out.println(ex.getMessage());
                    logger.error(ex.getMessage(), ex);
                }
                return;
            }
        }
        System.out.println("Запись c ID: " + recordID + " не найдена");
        logger.warn("DeleteRecord: запись c ID: " + recordID + " не найдена");
    }

    public void findAll() {
        if(records.size() == 0) {
            System.out.println("Не создано ни одной записи");
            logger.warn("FindAll: не создано ни одной записи");
        }
        for(Record record: records) {
            System.out.println(record.getRecordID() + ": " + record.getRecordText());
        }
    }

    public void findByID(int recordID) {
        for(Record record: records) {
            if(record.getRecordID() == recordID) {
                System.out.println(record.getRecordID() + ": " + record.getRecordText());
                return;
            }
        }
        System.out.println("Запись c ID: " + recordID + " не найдена");
        logger.warn("FindByID: запись c ID: " + recordID + " не найдена");
    }

    public void init() throws NoteBookLoadException {
       records = recordStore.loadAllRecords();
       if(records.size() > 0) {
           SimpleIDGen.setLastID(records.get(records.size() - 1).getRecordID());
       }
    }
}
