package notebook;

import notebook.dao.exception.ContextException;
import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;
import notebook.model.*;
import notebook.view.ConsoleView;
import notebook.view.MessagesView;
import notebook.view.View;
import notebook.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Маша on 08.06.2017.
 */
public class NoteBookProj {

    private static final Logger logger = LoggerFactory.getLogger(NoteBookProj.class);

    public static void main(String[] args) {

        ConsoleWork consoleWork = new ConsoleWork();
        ConsoleView consoleView = new ConsoleView();
        try {
            consoleWork.initDAOFactory();
            ModelAndView commandResult = consoleWork.workWithNotebookService(args);
            consoleView.show(commandResult.getViewName(), commandResult.getModel());
        } catch(ContextException | ResourceNotFoundException | PropFileLoadException ex) {
            logger.error(ex.getMessage(), ex);
            Model model = new MessageListModel();
            model.addMessage(new Message(ex.getMessage(), MessageStatus.ERROR));
            consoleView.show("MessagesView", model);
        }

    }
}
