package notebook.command;

import notebook.entity.Record;
import notebook.services.NoteBookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Маша on 14.06.2017.
 */
@RunWith(JUnit4.class)
public class AddCommandTest {
    @Test
    public void executeInvokeAddRecordAllFieldsAreSpecified() throws Exception {
        //given
        AddCommand addCommand = new AddCommand();
        Map<String, Object> params = new HashMap<>();
        params.put("text", "test text");
        params.put("title", "test title");
        params.put("author", "test author");
        params.put("type", "test type");

        NoteBookService noteBookService = mock(NoteBookService.class);
        addCommand.setNoteBookService(noteBookService);

        //when
        addCommand.execute(params);

        //then
        ArgumentCaptor<Record> recordArgument = ArgumentCaptor.forClass(Record.class);
        verify(noteBookService).addRecord(recordArgument.capture());
        assertEquals("test text", recordArgument.getValue().getRecordText());
        assertEquals("test title", recordArgument.getValue().getTitle());
        assertEquals("test author", recordArgument.getValue().getAuthor());
        assertEquals("test type", recordArgument.getValue().getType());
    }

}