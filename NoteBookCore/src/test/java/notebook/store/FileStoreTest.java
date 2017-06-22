package notebook.store;

import notebook.entity.Record;
import notebook.exception.NoteBookLoadException;
import notebook.exception.SaveRecordsException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Маша on 20.06.2017.
 */
@Ignore
@RunWith(JUnit4.class)
public class FileStoreTest {
    @Test
    public void loadAllRecordsFileExist() throws FileNotFoundException, NoteBookLoadException {
        //given
        FileStore fileStore = spy(new FileStore("filename"));
        FileInputStream fileInputStream = mock(FileInputStream.class);
        when(fileStore.getNewFileInputStream()).thenReturn(fileInputStream);

        //when
        ArrayList<Record> records = fileStore.loadAllRecords();

        //then
        assertNotNull(records);
    }

    @Test
    public void saveAllRecordsFileExist() throws IOException, SaveRecordsException {
        //given
        FileStore fileStore = spy(new FileStore("filename"));

        FileOutputStream fileOutputStream = mock(FileOutputStream.class);
        ObjectOutputStream objectOutputStream = mock(ObjectOutputStream.class);
        doReturn(fileOutputStream).when(fileStore).getNewFileOutputStream();
        doReturn(objectOutputStream).when(fileStore).getNewObjectOutputStream(any(FileOutputStream.class));

        ArrayList<Record> records = new ArrayList<>();

        //when
        fileStore.saveAllRecords(records);

        //then
        verify(objectOutputStream).writeObject(records);
    }
}