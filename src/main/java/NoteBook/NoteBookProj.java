package NoteBook;

import NoteBook.Entity.NoteBook;
import NoteBook.Exception.NoteBookLoadException;
import NoteBook.Exception.PropFileLoadException;
import NoteBook.IDGen.SimpleIDGen;
import NoteBook.RecordStore.FileStore;

import java.util.HashMap;
import java.util.Map;

import NoteBook.Command.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Маша on 08.06.2017.
 */
public class NoteBookProj {
    private static final Logger logger = LoggerFactory.getLogger(NoteBookProj.class);

    private NoteBook noteBook;
    private Map<String, Command> commandMap = new HashMap<>();

    public void init() throws PropFileLoadException {

        PropsLoader propsLoader = new PropsLoader();
        String fileName = propsLoader.loadPropFromConfig("filename");
        noteBook = new NoteBook(new SimpleIDGen(), new FileStore(fileName));

        try {
            noteBook.init();
        } catch (NoteBookLoadException ex) {
            System.out.println(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }

        commandMap.put("add", new AddCommand(noteBook));
        commandMap.put("delete", new DeleteCommand(noteBook));
        commandMap.put("findAll", new FindAllCommand(noteBook));
        commandMap.put("findByID", new FindByIDCommand(noteBook));
        commandMap.put("help", new HelpCommand());
    }

    public void workWithNoteBook(String[] args) {

        if(args.length == 0) { 
            System.out.println("Команда не выбрана");
            commandMap.get("help").execute();
            return;
        }

        commandMap.get(args[0]).execute(args);
    }

    public static void main(String[] args) {
        NoteBookProj nodeBookProj = new NoteBookProj();

        try {
            nodeBookProj.init();
            nodeBookProj.workWithNoteBook(args);
        } catch(PropFileLoadException ex) {
            System.out.println(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
    }
}
