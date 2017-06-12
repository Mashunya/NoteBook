package NoteBook;

import NoteBook.Entity.NoteBook;
import NoteBook.Exception.NoteBookLoadException;
import NoteBook.Exception.PropFileLoadException;
import NoteBook.IDGen.SimpleIDGen;
import NoteBook.RecordStore.FileStore;

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
    private Map<String, Command> commandMap = new HashMap<>();

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

        commandMap.put("add", new AddCommand(noteBookService, view));
        commandMap.put("delete", new DeleteCommand(noteBookService, view));
        commandMap.put("findAll", new FindAllCommand(noteBookService));
        commandMap.put("findByID", new FindByIDCommand(noteBookService, view));
        commandMap.put("help", new HelpCommand(view));
    }

    public void workWithNoteBook(String[] args) {

        if(args.length == 0) {
            view.showErrorMessage("Команда не выбрана");
            commandMap.get("help").execute();
            return;
        }

        commandMap.get(args[0]).execute(args);
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
