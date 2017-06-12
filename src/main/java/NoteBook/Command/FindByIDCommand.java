package NoteBook.Command;

import NoteBook.Exception.ValidateException;
import NoteBook.Parsers.IntegerParser;
import NoteBook.Services.NoteBookService;
import NoteBook.Validators.ArrLengthValidator;
import NoteBook.Validators.ZeroOrNaturalNumberValidator;
import NoteBook.View.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Маша on 08.06.2017.
 */
public class FindByIDCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(FindByIDCommand.class);
    private NoteBookService noteBookService;
    private View view;

    public FindByIDCommand(NoteBookService noteBookService, View view) {
        this.noteBookService = noteBookService;
        this.view = view;
    }

    @Override
    public void execute(String... params) {
        ArrLengthValidator arrValidator = new ArrLengthValidator();
        ZeroOrNaturalNumberValidator numberValidator = new ZeroOrNaturalNumberValidator();
        IntegerParser integerParser = new IntegerParser();
        try {
            arrValidator.validate(params, 2);
            numberValidator.validate(params[1]);
            noteBookService.findByID(integerParser.parse(params[1]));
        } catch (ValidateException ex) {
            view.showErrorMessage(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
    }
}
