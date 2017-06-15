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
    private Integer recordID;

    @Override
    public void execute() {
        noteBookService.deleteRecord(recordID);
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public Integer getRecordID() {
        return recordID;
    }
}
