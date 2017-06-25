package notebook.dao;

import notebook.dao.exception.DAOException;
import notebook.dao.exception.ExecuteException;
import notebook.entity.Record;

import java.io.*;
import java.util.List;

/**
 * Created by Маша on 25.06.2017.
 */
public class FileRecordDAO implements GenericDAO<Record, Integer> {

    private File file;

    public FileRecordDAO(File file) {
        this.file = file;
    }

    @Override
    public void persist(Record obj) throws DAOException {
        List<Record> records = loadAllRecords();
        int lastID = records.size() == 0 ? 0 : records.get(records.size() - 1).getRecordID() + 1;
        obj.setRecordID(lastID);
        records.add(obj);
        saveAllRecords(records);
    }

    @Override
    public boolean delete(Integer keyValue) throws DAOException {
        List<Record> records = loadAllRecords();
        for(Record record : records) {
            if(record.getRecordID() == keyValue) {
                records.remove(record);
                saveAllRecords(records);
                return true;
            }
        }
        return false;
    }

    @Override
    public Record findByID(Integer keyValue) throws DAOException {
        List<Record> records = loadAllRecords();
        for(Record record : records) {
            if(record.getRecordID() == keyValue) {
                return record;
            }
        }
        return null;
    }

    @Override
    public List<Record> findAll() throws DAOException {
        return loadAllRecords();
    }

    public List<Record> loadAllRecords() throws ExecuteException {
        List<Record> records;
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            records = (List<Record>) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException | ClassNotFoundException ex) {
            throw new ExecuteException(ex);
        }
        return records;
    }

    public void saveAllRecords(List<Record> records) throws ExecuteException {
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOut);
            outputStream.writeObject(records);
            outputStream.close();
            fileOut.close();
        } catch (IOException ex) {
            throw new ExecuteException(ex);
        }
    }
}
