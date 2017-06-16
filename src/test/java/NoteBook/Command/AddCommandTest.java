package NoteBook.Command;

import NoteBook.Command.Command.AddCommand;
import NoteBook.Entity.Record;
import NoteBook.Services.NoteBookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

/**
 * Created by Маша on 14.06.2017.
 */
@RunWith(JUnit4.class)
public class AddCommandTest {
    @Test
    public void execute_invokeAddRecord() throws Exception {
        AddCommand addCommand = new AddCommand();

        NoteBookService noteBookService = mock(NoteBookService.class);
        addCommand.setNoteBookService(noteBookService);

        addCommand.execute();

        verify(noteBookService).addRecord(any(Record.class));
    }

}