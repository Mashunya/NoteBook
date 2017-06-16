package NoteBook.Command.Command;

import NoteBook.Command.CommandResult.CommandResult;
import NoteBook.Entity.Record;

import java.util.List;
import java.util.Map;

/**
 * Created by Маша on 08.06.2017.
 */
//TODO: try in branch to use command with execute(Map<String, String>) instead of fields
public class AddCommand extends Command {

    private String text;
    private String author;
    private String type;
    private String title;
    private String OSName;

    @Override
    public List<CommandResult> execute(Map<String, String>) {
        results

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
