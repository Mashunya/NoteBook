package notebook;

import notebook.dao.exception.ContextException;
import notebook.entity.Record;
import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;
import notebook.model.Message;
import notebook.model.MessageListModel;
import notebook.model.MessageStatus;
import notebook.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    private SpringMVCWork springMVCWork;
    private Message initError;

    public CommandController() {
        springMVCWork = new SpringMVCWork();
        try {
            springMVCWork.initDAOFactory();
        } catch(ContextException | ResourceNotFoundException | PropFileLoadException ex) {
            logger.error(ex.getMessage());
            initError = new Message(ex.getMessage(), MessageStatus.ERROR);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("index", new HashMap<>());
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView init(@RequestParam String commandName) {
        Map<String, String> data = new HashMap<>();
        data.put("dateFormat", DateFormatExtractor.getDateFormat());
        return new ModelAndView(commandName + "CommandInit", data);
    }

    @RequestMapping(value = "/command", method = RequestMethod.POST)
    public ModelAndView workWithNoteBook(@RequestBody MultiValueMap<String,String> formData) {

        if(initError != null) {
            Model model = new MessageListModel();
            model.addMessage(initError);
            ModelAndView springResultModel = new ModelAndView("MessagesView", "result", model);
            return springResultModel;
        }

        notebook.model.ModelAndView commandResult = springMVCWork.workWithNotebookService(formData);

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
