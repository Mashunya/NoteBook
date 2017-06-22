package notebook.command;

import notebook.model.ModelAndView;

import java.util.Map;

/**
 * Created by Маша on 16.06.2017.
 */
public interface Command {
    ModelAndView execute(Map<String, Object> params);
}
