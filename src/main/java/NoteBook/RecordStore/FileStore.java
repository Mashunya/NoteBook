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
    private FileInputStream fileIn;
    private FileOutputStream fileOut;

    public FileStore(){}

    public FileStore(String fileName) {
        this.fileName = fileName;
    }

    //TODO: используется FileInput(Output)Stream, который нельзя вынести в конструктор или устанавливать с помощью set (есть close)
    @Override
    public ArrayList<Record> loadAllRecords() throws NoteBookLoadException {
        ArrayList<Record> records;
        try {
            fileIn = new FileInputStream(fileName);
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
            fileOut = new FileOutputStream(fileName);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOut);
            outputStream.writeObject(records);
            outputStream.close();
            fileOut.close();
        } catch (IOException ex) {
            throw new SaveRecordsException(ex);
        }
    }

    //TODO: можно ли добавлять методы, которые нужны только для написания тестов?
    public void setFileIn(FileInputStream fileIn) {
        this.fileIn = fileIn;
    }

    public void setFileOut(FileOutputStream fileOut) {
        this.fileOut = fileOut;
    }
}
