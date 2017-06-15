package NoteBook.Command;

import NoteBook.Validators.Required;
import NoteBook.Validators.NotNegative;

import java.util.Map;

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

    @Override
    public Command newCommand(Map<String, Object> preparedParams) {
        return new DeleteCommand();
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public Integer getRecordID() {
        return recordID;
    }
}
