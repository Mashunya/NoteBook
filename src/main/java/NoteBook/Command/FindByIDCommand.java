package NoteBook.Command;

import NoteBook.Exception.ValidateException;
import NoteBook.Services.NoteBookService;
import NoteBook.Validators.Required;
import NoteBook.Validators.ZeroOrNatural;
import NoteBook.View.View;
import org.slf4j.LoggerFactory;

/**
 * Created by Маша on 08.06.2017.
 */
public class FindByIDCommand extends Command {

    @Required
    @ZeroOrNatural
    private int recordID;

    public FindByIDCommand(NoteBookService noteBookService, View view) {
        this.logger = LoggerFactory.getLogger(FindByIDCommand.class);
        this.noteBookService = noteBookService;
        this.view = view;
    }

    @Override
    public void execute() {
        try {
            validate();
            noteBookService.findByID(recordID);
        } catch (ValidateException ex) {
            view.showErrorMessage(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
    }
}
