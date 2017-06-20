package NoteBook;

import NoteBook.ArgsConverter.ConsoleArgsConverter;
import NoteBook.Command.Command.Command;
import NoteBook.Command.CommandDescription.CommandDescription;
import NoteBook.Command.CommandDescription.CommandDescriptionRegistry;
import NoteBook.Command.CommandFactory.CommandFactory;
import NoteBook.Command.CommandFactory.CommandFactoryRegistry;
import NoteBook.Command.InputDataPreparator;
import NoteBook.Entity.NoteBook;
import NoteBook.Exception.*;
import NoteBook.IDGen.SimpleIDGen;
import NoteBook.ModelAndView.Model.Message;
import NoteBook.ModelAndView.Model.MessageStatus;
import NoteBook.ModelAndView.ModelAndView;
import NoteBook.ModelAndView.View.ConsoleView;
import NoteBook.ModelAndView.View.View;
import NoteBook.ModelAndView.View.ViewResolver;
import NoteBook.RecordStore.FileStore;
import NoteBook.Services.NoteBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Маша on 08.06.2017.
 */
public class NoteBookProj {
    private static final Logger logger = LoggerFactory.getLogger(NoteBookProj.class);

    private NoteBookService noteBookService;

    public void init() throws PropFileLoadException {

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
        ConsoleArgsConverter argsConverter = new ConsoleArgsConverter();
        CommandFactoryRegistry commandFactoryRegistry = new CommandFactoryRegistry();

        try {
            Map<String, Object> params = argsConverter.convert(args);

            String commandName;
            if (args.length == 0) {
                consoleView.show(new Message("Команда не выбрана", MessageStatus.ERROR));
                commandName = "help";
            } else {
                commandName = args[0];
            }

            CommandDescription commandDescription = CommandDescriptionRegistry.getInstanse().getCommandDescription(commandName);

            InputDataPreparator inputDataPreparator = new InputDataPreparator();
            Map<String, Object> preparedParams = inputDataPreparator.prepareData(params, commandDescription.getParamsDescription());

            CommandFactory commandFactory = commandFactoryRegistry.getCommandFactory(commandDescription.getCommandClass());

            Map<String, String> globalParams = null;
            try {
                globalParams = GlobalParamsExtractor.getProps();
            } catch(PropFileLoadException ex) {
                consoleView.show(new Message(ex.getMessage(), MessageStatus.ERROR));
                logger.error(ex.getMessage(), ex);
            }
            Command command = commandFactory.createCommand(noteBookService, preparedParams, globalParams);
            ModelAndView resultModelAndView = command.execute();

            //TODO: not static, also for other registries
            View view = ViewResolver.getView(resultModelAndView.getViewName());
            if(view == null) {
                consoleView.show(new Message("Представление не найдено", MessageStatus.ERROR));
            } else {
                // TODO try to generify
                view.show(resultModelAndView.getModel());
            }

        } catch(IllegalCommandParamException | CommandFactoryException | ValidateException | ParseException ex) {
            consoleView.show(new Message(ex.getMessage(), MessageStatus.ERROR));
            logger.error(ex.getMessage(), ex);
        }
    }

    public static void main(String[] args) {
        NoteBookProj nodeBookProj = new NoteBookProj();

        try {
            nodeBookProj.init();
            nodeBookProj.workWithNoteBook(args);
        } catch(PropFileLoadException ex) {
            new ConsoleView().show(new Message(ex.getMessage(), MessageStatus.ERROR));
            logger.error(ex.getMessage(), ex);
        }
    }
}
