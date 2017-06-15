package NoteBook.RecordStore;

import NoteBook.Exception.NoteBookLoadException;
import NoteBook.Exception.PropFileLoadException;
import NoteBook.PropsLoader;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.FileInputStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by Маша on 13.06.2017.
 */
@RunWith(JUnit4.class)
public class FileStoreTest {

    @Ignore
    @Test
    public void loadAllRecords_fileExist() throws NoteBookLoadException {
        FileInputStream fileIn = mock(FileInputStream.class);

        FileStore fileStore = new FileStore();
        fileStore.setFileIn(fileIn);

        fileStore.loadAllRecords();
    }
}