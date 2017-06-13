package NoteBook.Command;

import NoteBook.Validators.Required;
import NoteBook.Validators.NotNegative;
import org.slf4j.LoggerFactory;

/**
 * Created by Маша on 08.06.2017.
 */
public class DeleteCommand extends Command {

    @Required
    @NotNegative
    private int recordID;

    public DeleteCommand() {
        this.logger = LoggerFactory.getLogger(DeleteCommand.class);
    }

    @Override
    public void execute() {
        noteBookService.deleteRecord(recordID);
    }
}
