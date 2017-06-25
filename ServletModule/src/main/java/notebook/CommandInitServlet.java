package notebook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Маша on 25.06.2017.
 */
@WebServlet("/init")
public class CommandInitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commandName = req.getAttribute("command").toString();
        req.setAttribute("dateFormat", DateFormatExtractor.getDateFormat());
        req.getRequestDispatcher(commandName + "CommandInit.jsp").forward(req, resp);
    }
}
