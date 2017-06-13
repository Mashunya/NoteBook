package NoteBook;

import NoteBook.ArgsConverter.ConsoleArgsConverter;
import NoteBook.Entity.NoteBook;
import NoteBook.Exception.IllegalCommandParamException;
import NoteBook.Exception.NoteBookLoadException;
import NoteBook.Exception.PropFileLoadException;
import NoteBook.Exception.ValidateException;
import NoteBook.IDGen.SimpleIDGen;
import NoteBook.RecordStore.FileStore;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import NoteBook.Command.*;
import NoteBook.Services.NoteBookService;
import NoteBook.View.ConsoleView;
import NoteBook.View.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Маша on 08.06.2017.
 */
public class NoteBookProj {
    private static final Logger logger = LoggerFactory.getLogger(NoteBookProj.class);

    private NoteBookService noteBookService;
    private View view;
    private Map<String, Class> commandMap = new HashMap<>();

    public void init() throws PropFileLoadException {

        PropsLoader propsLoader = new PropsLoader();
        String fileName = propsLoader.loadPropFromConfig("filename");
        view = new ConsoleView();
        noteBookService = new NoteBookService(new NoteBook(), new SimpleIDGen(), new FileStore(fileName), view);

        try {
            noteBookService.init();
        } catch (NoteBookLoadException ex) {
            view.showErrorMessage(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }

        commandMap.put("add", AddCommand.class);
        commandMap.put("delete", DeleteCommand.class);
        commandMap.put("findAll", FindAllCommand.class);
        commandMap.put("findByID", FindByIDCommand.class);
        commandMap.put("help", HelpCommand.class);
    }

    public void workWithNoteBook(String[] args) {

        ConsoleArgsConverter argsConverter = new ConsoleArgsConverter();
        CommandInit commandInit = new CommandInit();

        try {
            Map<String, String> params = argsConverter.convert(args);

            if (args.length == 0) {
                view.showErrorMessage("Команда не выбрана");
                commandInit.createCommand(commandMap.get("help"), params).execute();
                return;
            }

            Command command = commandInit.createCommand(commandMap.get(args[0]), params);
            command.setNoteBookService(noteBookService);
            command.setView(view);
            command.execute();

        } catch(IllegalCommandParamException | ValidateException ex) {
            view.showErrorMessage(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
    }

    public View getView() {
        return view;
    }

    public static void main(String[] args) {
        NoteBookProj nodeBookProj = new NoteBookProj();

        try {
            nodeBookProj.init();
            nodeBookProj.workWithNoteBook(args);
        } catch(PropFileLoadException ex) {
            nodeBookProj.getView().showErrorMessage(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
    }
}
