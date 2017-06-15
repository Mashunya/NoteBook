package NoteBook.Command;

import NoteBook.Validators.Required;
import NoteBook.Validators.NotNegative;

import java.util.Map;

/**
 * Created by Маша on 08.06.2017.
 */
public class FindByIDCommand extends Command {

    @Required
    @NotNegative
    private Integer recordID;

    @Override
    public void execute() {
        noteBookService.findByID(recordID);
    }

    @Override
    public Command newCommand(Map<String, Object> preparedParams) {
        return new FindByIDCommand();
    }

    public Integer getRecordID() {
        return recordID;
    }
}
