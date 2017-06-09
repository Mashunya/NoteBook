package NoteBook.RecordStore;

import NoteBook.Exception.NoteBookLoadException;
import NoteBook.Entity.Record;
import NoteBook.Exception.SaveRecordsException;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Маша on 08.06.2017.
 */
public class FileStore implements RecordStore {

    private String fileName;

    public FileStore(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public ArrayList<Record> loadAllRecords() throws NoteBookLoadException {
        ArrayList<Record> records;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            records = (ArrayList<Record>) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException | ClassNotFoundException ex) {
            throw new NoteBookLoadException(ex);
        }
        return records;
    }

    @Override
    public void saveAllRecords(ArrayList<Record> records) throws SaveRecordsException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(records);
            outputStream.close();
            fileOutputStream.close();
        } catch (IOException ex) {
            throw new SaveRecordsException(ex);
        }

    }
}
