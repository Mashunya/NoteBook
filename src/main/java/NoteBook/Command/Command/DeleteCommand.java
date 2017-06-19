package NoteBook.Command.Command;

import NoteBook.ModelAndView.ModelAndView;

import java.util.Map;

/**
 * Created by Маша on 08.06.2017.
 */
public class DeleteCommand extends CommandWorkedWithNoteBook {

    private Integer recordID;

    private Map<String, String> globalParams;

    @Override
    public ModelAndView execute() {
        return noteBookService.deleteRecord(recordID);
    }

    @Override
    public Map<String, String> getGlobalParams() {
        return globalParams;
    }

    public void setRecordID(Integer recordID) {
        this.recordID = recordID;
    }

    public void setGlobalParams(Map<String, String> globalParams) {
        this.globalParams = globalParams;
    }
}
