package notebook;

import notebook.args_converter.ArgsConverter;
import notebook.command.Command;
import notebook.command.description.CommandDescription;
import notebook.command.description.CommandDescriptionRegistry;
import notebook.command.factory.CommandFactory;
import notebook.command.params.InputDataPreparator;
import notebook.entity.NoteBook;
import notebook.exception.IllegalCommandException;
import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;
import notebook.exception.WorkWithFileException;
import notebook.id.IDGen;
import notebook.id.SimpleIDGen;
import notebook.model.*;
import notebook.services.NoteBookService;
import notebook.store.FileStore;
import notebook.store.RecordStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Маша on 22.06.2017.
 */
@WebServlet("/command")
public class NoteBookServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(NoteBookServlet.class);
    private static final String DEFAULT_DATE_FORMAT = "dd.MM.yyyy";

    private List<Message> initErrorMessages;
    private ServletWork servletWork;

    @Override
    public void init() throws ServletException {
        servletWork = new ServletWork();
        initErrorMessages = servletWork.init();
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ModelAndView commandResult = servletWork.workWithNotebookService(req.getParameterMap(), initErrorMessages);

        req.setAttribute("result", commandResult.getModel());
        try {
            String dateFormat = GlobalParamsExtractor.getProperty("Date_Format");
            if(dateFormat == null) {
                dateFormat = DEFAULT_DATE_FORMAT;
            }
            req.setAttribute("dateFormat", dateFormat);
        } catch (PropFileLoadException | ResourceNotFoundException ex) {
            logger.error(ex.getMessage(), ex);
            req.setAttribute("dateFormat", DEFAULT_DATE_FORMAT);
        }
        req.getRequestDispatcher(commandResult.getViewName() + ".jsp").forward(req, resp);
    }
}
