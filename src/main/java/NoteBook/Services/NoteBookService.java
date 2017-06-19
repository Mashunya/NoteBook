package NoteBook.Services;

import NoteBook.Entity.NoteBook;
import NoteBook.Entity.Record;
import NoteBook.Exception.NoteBookLoadException;
import NoteBook.Exception.SaveRecordsException;
import NoteBook.IDGen.IDGen;
import NoteBook.IDGen.SimpleIDGen;
import NoteBook.ModelAndView.Model.*;
import NoteBook.ModelAndView.ModelAndView;
import NoteBook.RecordStore.RecordStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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

    public ModelAndView addRecord(Record record) {
        Model resultModel = new MessageListModel();

        record.setRecordID(idGen.getNextID());
        noteBook.getRecords().add(record);
        try {
            recordStore.saveAllRecords(noteBook.getRecords());
            logger.info("Запись успешно добавлена");
            resultModel.addMessage(new Message("Запись успешно добавлена", Message.SUCCESS));
        } catch(SaveRecordsException ex) {
            logger.error(ex.getMessage(), ex);
            resultModel.addMessage(new Message(ex.getMessage(), Message.ERROR));
        }
        return new ModelAndView("MessagesView", resultModel);
    }

    public ModelAndView deleteRecord(int recordID) {
        Model resultModel = new MessageListModel();
        for(Record record: noteBook.getRecords()) {
            if(record.getRecordID() == recordID) {
                noteBook.getRecords().remove(record);
                try {
                    recordStore.saveAllRecords(noteBook.getRecords());
                    logger.info("Запись c ID: " + recordID + " успешно удалена");
                    resultModel.addMessage(new Message("Запись c ID: " + recordID + " успешно удалена", Message.SUCCESS));
                } catch (SaveRecordsException ex) {
                    logger.error(ex.getMessage(), ex);
                    resultModel.addMessage(new Message(ex.getMessage(), Message.ERROR));
                }
                return new ModelAndView("MessagesModel", resultModel);
            }
        }
        logger.warn("DeleteRecord: запись c ID: " + recordID + " не найдена");
        resultModel.addMessage(new Message("Запись c ID: " + recordID + " не найдена", Message.WARNING));
        return new ModelAndView("MessagesModel", resultModel);
    }

    public ModelAndView findAll() {
        List<Record> records = noteBook.getRecords();
        if(records.size() == 0) {
            logger.warn("FindAll: не создано ни одной записи");
            MessageListModel resultModel = new MessageListModel();
            resultModel.addMessage(new Message("Не создано ни одной записи", Message.WARNING));
            return new ModelAndView("MessagesView", resultModel);
        }
        RecordListModel resultModel = new RecordListModel();
        resultModel.setRecords(records);
        return new ModelAndView("RecordsView", resultModel);
    }

    public ModelAndView findByID(int recordID) {
        for(Record record: noteBook.getRecords()) {
            if(record.getRecordID() == recordID) {
                RecordModel resultModel = new RecordModel();
                resultModel.setRecord(record);
                return new ModelAndView("RecordView", resultModel);
            }
        }
        logger.warn("FindByID: запись c ID: " + recordID + " не найдена");
        MessageListModel resultModel = new MessageListModel();
        resultModel.addMessage(new Message("Запись c ID: " + recordID + " не найдена", Message.WARNING));
        return new ModelAndView("RecordView", resultModel);
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

    public void setIdGen(IDGen idGen) {
        this.idGen = idGen;
    }
}
