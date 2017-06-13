package NoteBook.Command;

import NoteBook.Entity.Record;
import NoteBook.Validators.Required;
import org.slf4j.LoggerFactory;

/**
 * Created by Маша on 08.06.2017.
 */
public class AddCommand extends Command {

    @Required
    private String text;

    private String author = "";

    private String type = "";

    private String title = "";

    public AddCommand() {
        this.logger = LoggerFactory.getLogger(AddCommand.class);
    }

    @Override
    public void execute() {
        Record record = new Record(text, author, title, type);
        noteBookService.addRecord(record);
    }
}
