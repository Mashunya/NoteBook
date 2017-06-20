package NoteBook.Command.Command;

import NoteBook.ModelAndView.ModelAndView;
import NoteBook.Services.NoteBookService;

import java.util.Map;

/**
 * Created by Маша on 16.06.2017.
 */
public interface Command {
    ModelAndView execute(Map<String, Object> params);
}
