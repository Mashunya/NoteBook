package NoteBook;

import NoteBook.ArgsConverter.ConsoleArgsConverter;
import NoteBook.Command.Command.Command;
import NoteBook.Command.CommandDescription.CommandDescription;
import NoteBook.Command.CommandDescription.CommandDescriptionRegistry;
import NoteBook.Command.CommandFactory.CommandFactory;
import NoteBook.Command.CommandFactory.CommandFactoryRegistry;
import NoteBook.Command.CommandResult.CommandResult;
import NoteBook.Command.InputDataPreparator;
import NoteBook.Entity.NoteBook;
import NoteBook.Exception.*;
import NoteBook.IDGen.SimpleIDGen;
import NoteBook.RecordStore.FileStore;
import NoteBook.Services.NoteBookService;
import NoteBook.View.ConsoleView;
import NoteBook.View.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Маша on 08.06.2017.
 */
public class NoteBookProj {
    private static final Logger logger = LoggerFactory.getLogger(NoteBookProj.class);

    private NoteBookService noteBookService;
    private View view;

    public void init() throws PropFileLoadException {

        PropsLoader propsLoader = new PropsLoader("config.properties");
        String fileName = propsLoader.loadProp("filename");
        view = new ConsoleView();

        noteBookService = new NoteBookService(new NoteBook(), new SimpleIDGen(), new FileStore(fileName));

        try {
            noteBookService.init();
        } catch (WorkWithFileException ex) {
            view.show(ex.getMessage(), CommandResult.ERROR);
            logger.error(ex.getMessage(), ex);
        }
    }

    public void workWithNoteBook(String[] args) {

        ConsoleArgsConverter argsConverter = new ConsoleArgsConverter();
        CommandFactoryRegistry commandFactoryRegistry = new CommandFactoryRegistry();

        try {
            Map<String, Object> params = argsConverter.convert(args);

            String commandName;
            if (args.length == 0) {
                view.show("Команда не выбрана", CommandResult.ERROR);
                commandName = "help";
            } else {
                commandName = args[0];
            }

            CommandDescription commandDescription = CommandDescriptionRegistry.getInstanse().getCommandDescription(commandName);

            InputDataPreparator inputDataPreparator = new InputDataPreparator();
            Map<String, Object> preparedParams = inputDataPreparator.prepareData(params, commandDescription.getParamsDescription());

            CommandFactory commandFactory = commandFactoryRegistry.getCommandFactory(commandDescription.getCommandClass());
            Command command = commandFactory.createCommand(noteBookService, preparedParams);
            view.show(command.execute());

        } catch(IllegalCommandParamException | CommandFactoryException | ValidateException | ParseException ex) {
            view.show(ex.getMessage(), CommandResult.ERROR);
            logger.error(ex.getMessage(), ex);
        }
    }

    public View getView() {
        return view;
    }

    public static void main(String[] args) {
        args = new String[]{"help", "rec_ID=1"};

        NoteBookProj nodeBookProj = new NoteBookProj();

        try {
            nodeBookProj.init();
            nodeBookProj.workWithNoteBook(args);
        } catch(PropFileLoadException ex) {
            nodeBookProj.getView().show(ex.getMessage(), CommandResult.ERROR);
            logger.error(ex.getMessage(), ex);
        }
    }
}
