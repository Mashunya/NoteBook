package NoteBook.Command.Command;

import NoteBook.ModelAndView.ModelAndView;

import java.util.Map;

/**
 * Created by Маша on 16.06.2017.
 */
public interface Command {
    ModelAndView execute();
    void setGlobalParams(Map<String, String> globalParams);
    Map<String, String> getGlobalParams();
}
