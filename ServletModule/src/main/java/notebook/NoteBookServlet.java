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
import notebook.id.SimpleIDGen;
import notebook.model.*;
import notebook.services.NoteBookService;
import notebook.store.FileStore;
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
import java.util.Map;

/**
 * Created by Маша on 22.06.2017.
 */
@WebServlet("/command")
public class NoteBookServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(NoteBookServlet.class);

    private NoteBookService noteBookService;
    private List<Message> initErrorMessages = new ArrayList<>();

    @Override
    public void init() {
        String fileName;
        try {
            PropsLoader propsLoader = new PropsLoader("config.properties");
            fileName = propsLoader.loadProp("filename");
        } catch (PropFileLoadException | ResourceNotFoundException ex) {
            fileName = System.getProperty("user.dir");
            initErrorMessages.add(new Message(ex.getMessage(), MessageStatus.ERROR));
            logger.error(ex.getMessage(), ex);
        }

        try {
            noteBookService = new NoteBookService(new NoteBook(), new SimpleIDGen(), new FileStore(fileName));
            noteBookService.init();
        } catch (WorkWithFileException ex) {
            initErrorMessages.add(new Message(ex.getMessage(), MessageStatus.ERROR));
            logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Message> errorMessages = new ArrayList<>();

        ArgsConverter argsConverter = new ArgsConverter();
        String resultView = "messages.jsp";
        Model resultModel = new MessageListModel();
        try {
            Map<String, Object> params = argsConverter.convert(req.getParameterMap());

            Object commandName = params.get("command");
            if (commandName == null) {
                errorMessages.add(new Message("Команда не выбрана", MessageStatus.ERROR));
                commandName = "help";
            }

            CommandDescriptionRegistry commandDescriptionRegistry = new CommandDescriptionRegistry();
            CommandDescription commandDescription = commandDescriptionRegistry.getCommandDescription((String)commandName);
            if(commandDescription == null) {
                throw new IllegalCommandException((String)commandName);
            }

            InputDataPreparator inputDataPreparator = new InputDataPreparator();
            Map<String, Object> preparedParams = inputDataPreparator.prepareData(params, commandDescription.getParamsDescription());

            CommandFactory commandFactory = new CommandFactory();

            Map<String, String> globalParams = null;
            try {
                globalParams = GlobalParamsExtractor.getProps();
            } catch(PropFileLoadException | ResourceNotFoundException ex) {
                errorMessages.add(new Message(ex.getMessage(), MessageStatus.ERROR));
                logger.error(ex.getMessage(), ex);
            }
            Command command = commandFactory.createCommand(commandDescription.getCommandClass(), noteBookService);
            preparedParams.putAll(globalParams);
            ModelAndView resultModelAndView = command.execute(preparedParams);
            resultModel = resultModelAndView.getModel();

            ViewResolver viewResolver = new ViewResolver();
            String view = viewResolver.getView(resultModelAndView.getViewName());
            if(view == null) {
                errorMessages.add(new Message("Представление не найдено", MessageStatus.ERROR));
            } else {
                resultView = view;
            }

        } catch(Exception ex) {
            errorMessages.add(new Message(ex.getMessage(), MessageStatus.ERROR));
            logger.error(ex.getMessage(), ex);
        }

        resultModel.addAllMessages(initErrorMessages);
        resultModel.addAllMessages(errorMessages);
        req.setAttribute("result", resultModel);
        req.getRequestDispatcher(resultView).forward(req, resp);
    }
}
