package NoteBook.Command.Command;

import NoteBook.Command.CommandResult.CommandResult;

import java.util.List;

/**
 * Created by Маша on 08.06.2017.
 */
public class DeleteCommand extends Command {

    private Integer recordID;

    @Override
    public List<CommandResult> execute() {
        results.add(noteBookService.deleteRecord(recordID));
        return results;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }
}
