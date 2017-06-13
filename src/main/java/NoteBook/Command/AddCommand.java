package NoteBook.Command;

import NoteBook.Entity.Record;
import NoteBook.Exception.ValidateException;
import NoteBook.Services.NoteBookService;
import NoteBook.Validators.Required;
import NoteBook.View.View;
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

    public AddCommand(NoteBookService noteBookService, View view) {
        this.logger = LoggerFactory.getLogger(AddCommand.class);
        this.noteBookService = noteBookService;
        this.view = view;
    }

    @Override
    public void execute() {
        try {
            validate();

            Record record = new Record(text, author, title, type);
            noteBookService.addRecord(record);
        } catch(ValidateException ex) {
            view.showErrorMessage(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
    }
}
