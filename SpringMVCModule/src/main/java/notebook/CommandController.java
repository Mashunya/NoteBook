package notebook;

import notebook.entity.Record;
import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;
import notebook.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Маша on 23.06.2017.
 */
@Controller
public class CommandController {

    private static final Logger logger = LoggerFactory.getLogger(CommandController.class);
    private static final String DEFAULT_DATE_FORMAT = "dd.MM.yyyy";

    private List<Message> initErrorMessages;
    private SpringMVCWork springMVCWork;

    public CommandController() {
        springMVCWork = new SpringMVCWork();
        initErrorMessages = springMVCWork.init();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("index", new HashMap<>());
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addInit() {
        Map<String, String> data = new HashMap<>();
        String dateFormat;
        try {
            dateFormat = GlobalParamsExtractor.getProperty("Date_Format");
        } catch(PropFileLoadException | ResourceNotFoundException ex) {
            logger.error(ex.getMessage(), ex);
            dateFormat = "dd.MM.yyyy";
        }
        data.put("dateFormat", dateFormat);
        return new ModelAndView("addCommandInit", new HashMap<>());
    }

    @RequestMapping(value = "/command", method = RequestMethod.POST)
    public ModelAndView workWithNoteBook(@RequestBody MultiValueMap<String,String> formData) {

        notebook.model.ModelAndView commandResult = springMVCWork.workWithNotebookService(formData, initErrorMessages);

        ModelAndView springResultModel = new ModelAndView(commandResult.getViewName(), "result", commandResult.getModel());
        try {
            String dateFormat = GlobalParamsExtractor.getProperty("Date_Format");
            if(dateFormat == null) {
                dateFormat = DEFAULT_DATE_FORMAT;
            }
            springResultModel.addObject("dateFormat", dateFormat);
        } catch (PropFileLoadException | ResourceNotFoundException ex) {
            logger.error(ex.getMessage(), ex);
            springResultModel.addObject("dateFormat", DEFAULT_DATE_FORMAT);
        }
        return springResultModel;
    }
}
