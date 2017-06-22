package notebook.store;

import notebook.exception.NoteBookLoadException;
import notebook.entity.Record;
import notebook.exception.SaveRecordsException;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Маша on 08.06.2017.
 */
public class FileStore implements RecordStore {

    private String fileName;

    public FileStore(){}

    public FileStore(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public ArrayList<Record> loadAllRecords() throws NoteBookLoadException {
        ArrayList<Record> records;
        try {
            FileInputStream fileIn = getNewFileInputStream();
            ObjectInputStream in = getNewObjectInputStream(fileIn);
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
            FileOutputStream fileOut = getNewFileOutputStream();
            ObjectOutputStream outputStream = getNewObjectOutputStream(fileOut);
            outputStream.writeObject(records);
            outputStream.close();
            fileOut.close();
        } catch (IOException ex) {
            throw new SaveRecordsException(ex);
        }
    }

    FileInputStream getNewFileInputStream() throws FileNotFoundException {
        return new FileInputStream(fileName);
    }

    FileOutputStream getNewFileOutputStream() throws FileNotFoundException {
        return new FileOutputStream(fileName);
    }

    ObjectInputStream getNewObjectInputStream(FileInputStream fileInputStream) throws IOException {
        return new ObjectInputStream(fileInputStream);
    }

    ObjectOutputStream getNewObjectOutputStream(FileOutputStream fileOutputStream) throws IOException {
        return new ObjectOutputStream(fileOutputStream);
    }
}
