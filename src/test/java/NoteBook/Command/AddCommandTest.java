package NoteBook.Command;

import NoteBook.Command.Command.AddCommand;
import NoteBook.Entity.Record;
import NoteBook.IDGen.IDGen;
import NoteBook.Services.NoteBookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Маша on 14.06.2017.
 */
@RunWith(JUnit4.class)
public class AddCommandTest {
    @Test
    public void executeInvokeAddRecordAllFieldsAreSpecified() throws Exception {
        //given
        AddCommand addCommand = new AddCommand();
        addCommand.setText("test text");
        addCommand.setTitle("test title");
        addCommand.setAuthor("test author");
        addCommand.setType("test type");

        NoteBookService noteBookService = mock(NoteBookService.class);
        addCommand.setNoteBookService(noteBookService);

        //when
        addCommand.execute();

        //then
        ArgumentCaptor<Record> recordArgument = ArgumentCaptor.forClass(Record.class);
        verify(noteBookService).addRecord(recordArgument.capture());
        assertEquals("test text", recordArgument.getValue().getRecordText());
        assertEquals("test title", recordArgument.getValue().getTitle());
        assertEquals("test author", recordArgument.getValue().getAuthor());
        assertEquals("test type", recordArgument.getValue().getType());
    }

}