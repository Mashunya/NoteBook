package NoteBook.View;

import NoteBook.Command.CommandResult.CommandResult;
import NoteBook.Entity.Record;

import java.util.List;

/**
 * Created by Маша on 11.06.2017.
 */
public interface View {
    void show(List<CommandResult> results);
    void show(String message, int status);
    void show(Record record, int status);
    void show(List<Record> records, int status);
}
