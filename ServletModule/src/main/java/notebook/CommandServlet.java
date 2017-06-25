package notebook;

import notebook.dao.exception.ContextException;
import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;
import notebook.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Маша on 22.06.2017.
 */
@WebServlet("/command")
public class CommandServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(CommandServlet.class);

    private Message initError;
    private ServletWork servletWork;

    @Override
    public void init() throws ServletException {
        servletWork = new ServletWork();
        try {
            servletWork.initDAOFactory();
        } catch(ContextException | ResourceNotFoundException | PropFileLoadException ex) {
            logger.error(ex.getMessage(), ex);
            initError = new Message(ex.getMessage(), MessageStatus.ERROR);
        }
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if(initError != null) {
            Model model = new MessageListModel();
            model.addMessage(initError);
            req.setAttribute("result", model);
            req.getRequestDispatcher("MessagesView.jsp").forward(req, resp);
            return;
        }

        ModelAndView commandResult = servletWork.workWithNotebookService(req.getParameterMap());

        req.setAttribute("result", commandResult.getModel());
        req.setAttribute("dateFormat", DateFormatExtractor.getDateFormat());
        req.getRequestDispatcher(commandResult.getViewName() + ".jsp").forward(req, resp);
    }
}
