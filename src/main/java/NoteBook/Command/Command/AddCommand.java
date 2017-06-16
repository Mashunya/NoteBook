package NoteBook.Command.Command;

import NoteBook.Command.CommandResult.CommandResult;
import NoteBook.Entity.Record;

import java.util.List;

/**
 * Created by Маша on 08.06.2017.
 */
public class AddCommand extends Command {

    private String text;
    private String author;
    private String type;
    private String title;

    @Override
    public List<CommandResult> execute() {

        addProgramNameToResult();

        Record record = new Record(text, author, title, type);
        results.add(noteBookService.addRecord(record));

        addCheckOSToResult();
        return results;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
