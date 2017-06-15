package NoteBook.Command;

import NoteBook.Entity.Record;
import NoteBook.Validators.Required;

/**
 * Created by Маша on 08.06.2017.
 */
public class AddCommand extends Command {

    @Required
    private String text;

    private String author = "";

    private String type = "";

    private String title = "";

    @Override
    public void execute() {
        Record record = new Record(text, author, title, type); //TODO: нужно ли тестировать?
        noteBookService.addRecord(record);
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }
}
