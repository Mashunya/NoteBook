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
import notebook.model.Message;
import notebook.model.MessageStatus;
import notebook.model.ModelAndView;
import notebook.services.NoteBookService;
import notebook.store.FileStore;
import notebook.view.ConsoleView;
import notebook.view.View;
import notebook.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Маша on 08.06.2017.
 */
public class NoteBookProj {
    private static final Logger logger = LoggerFactory.getLogger(NoteBookProj.class);

    private NoteBookService noteBookService;

    public void init() throws PropFileLoadException, ResourceNotFoundException {

        ConsoleView consoleView = new ConsoleView();
        PropsLoader propsLoader = new PropsLoader("config.properties");
        String fileName = propsLoader.loadProp("filename");

        noteBookService = new NoteBookService(new NoteBook(), new SimpleIDGen(), new FileStore(fileName));

        try {
            noteBookService.init();
        } catch (WorkWithFileException ex) {
            consoleView.show(new Message(ex.getMessage(), MessageStatus.ERROR));
            logger.error(ex.getMessage(), ex);
        }
    }

    public void workWithNoteBook(String[] args) {

        ConsoleView consoleView = new ConsoleView();
        ArgsConverter argsConverter = new ArgsConverter();

        try {
            Map<String, Object> params = argsConverter.convert(args);

            String commandName;
            if (args.length == 0) {
                consoleView.show(new Message("Команда не выбрана", MessageStatus.ERROR));
                commandName = "help";
            } else {
                commandName = args[0];
            }

            CommandDescriptionRegistry commandDescriptionRegistry = new CommandDescriptionRegistry();
            CommandDescription commandDescription = commandDescriptionRegistry.getCommandDescription(commandName);
            if(commandDescription == null) {
                throw new IllegalCommandException(commandName);
            }

            InputDataPreparator inputDataPreparator = new InputDataPreparator();
            Map<String, Object> preparedParams = inputDataPreparator.prepareData(params, commandDescription.getParamsDescription());

            CommandFactory commandFactory = new CommandFactory();

            Map<String, String> globalParams = null;
            try {
                globalParams = GlobalParamsExtractor.getProps();
            } catch(PropFileLoadException | ResourceNotFoundException ex) {
                consoleView.show(new Message(ex.getMessage(), MessageStatus.ERROR));
                logger.error(ex.getMessage(), ex);
            }
            Command command = commandFactory.createCommand(commandDescription.getCommandClass(), noteBookService);
            preparedParams.putAll(globalParams);
            ModelAndView resultModelAndView = command.execute(preparedParams);

            ViewResolver viewResolver = new ViewResolver();
            View view = viewResolver.getView(resultModelAndView.getViewName());
            if(view == null) {
                consoleView.show(new Message("Представление не найдено", MessageStatus.ERROR));
            } else {
                view.show(resultModelAndView.getModel());
            }

        } catch(Exception ex) {
            consoleView.show(new Message(ex.getMessage(), MessageStatus.ERROR));
            logger.error(ex.getMessage(), ex);
        }
    }

    public static void main(String[] args) {
        NoteBookProj nodeBookProj = new NoteBookProj();

        try {
            nodeBookProj.init();
            nodeBookProj.workWithNoteBook(args);
        } catch(PropFileLoadException | ResourceNotFoundException ex) {
            new ConsoleView().show(new Message(ex.getMessage(), MessageStatus.ERROR));
            logger.error(ex.getMessage(), ex);
        }
    }
}
