package NoteBook.Command;

import NoteBook.Validators.Required;
import NoteBook.Validators.NotNegative;
import org.slf4j.LoggerFactory;

/**
 * Created by Маша on 08.06.2017.
 */
public class FindByIDCommand extends Command {

    @Required
    @NotNegative
    private int recordID;

    public FindByIDCommand() {
        this.logger = LoggerFactory.getLogger(FindByIDCommand.class);
    }

    @Override
    public void execute() {
        noteBookService.findByID(recordID);
    }
}
