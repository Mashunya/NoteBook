package NoteBook.Command;

import NoteBook.Exception.ValidateException;
import NoteBook.Services.NoteBookService;
import NoteBook.Validators.ArrLengthValidator;
import NoteBook.View.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Маша on 08.06.2017.
 */
public class AddCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(AddCommand.class);
    private NoteBookService noteBookService;
    private View view;

    public AddCommand(NoteBookService noteBookService, View view) {
        this.noteBookService = noteBookService;
        this.view = view;
    }

    @Override
    public void execute(String... params) {

        ArrLengthValidator validator = new ArrLengthValidator();
        try {
            validator.validate(params, 2);
            noteBookService.addRecord(params[1]);
        } catch(ValidateException ex) {
            view.showErrorMessage(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
    }
}
