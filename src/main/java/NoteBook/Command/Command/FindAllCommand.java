package NoteBook.Command.Command;

import NoteBook.ModelAndView.ModelAndView;

import java.util.Map;

/**
 * Created by Маша on 08.06.2017.
 */
public class FindAllCommand extends CommandWorkedWithNoteBook {

    private Map<String, String> globalParams;

    @Override
    public ModelAndView execute() {
        return noteBookService.findAll();
    }

    @Override
    public Map<String, String> getGlobalParams() {
        return null;
    }

    public void setGlobalParams(Map<String, String> globalParams) {
        this.globalParams = globalParams;
    }
}
