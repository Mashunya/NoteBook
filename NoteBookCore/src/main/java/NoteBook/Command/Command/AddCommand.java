package NoteBook.Command.Command;

import NoteBook.Entity.Record;
import NoteBook.ModelAndView.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Маша on 08.06.2017.
 */
//TODO: try in branch to use command with execute(Map<String, String>) instead of fields
public class AddCommand extends CommandWorkedWithNoteBook {

    @Override
    public ModelAndView execute(Map<String, Object> params) {
        Record record = new Record((String)params.get("text"), (String)params.get("author"), (String)params.get("title"), (String)params.get("type"));
        return noteBookService.addRecord(record);
    }
}
