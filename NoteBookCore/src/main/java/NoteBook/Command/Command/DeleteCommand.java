package NoteBook.Command.Command;

import NoteBook.ModelAndView.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Маша on 08.06.2017.
 */
public class DeleteCommand extends CommandWorkedWithNoteBook {

    @Override
    public ModelAndView execute(Map<String, Object> params) {
        return noteBookService.deleteRecord((Integer)params.get("recordID"));
    }
}
