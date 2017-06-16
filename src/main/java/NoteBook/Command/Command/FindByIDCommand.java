package NoteBook.Command.Command;

import NoteBook.Command.CommandResult.CommandResult;

import java.util.List;

/**
 * Created by Маша on 08.06.2017.
 */
public class FindByIDCommand extends Command {

    private Integer recordID;

    @Override
    public List<CommandResult> execute() {
        results.add(noteBookService.findByID(recordID));
        return results;
    }

    public void setRecordID(Integer recordID) {
        this.recordID = recordID;
    }
}
