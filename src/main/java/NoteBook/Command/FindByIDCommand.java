package NoteBook.Command;

import NoteBook.Entity.NoteBook;
import NoteBook.Exception.ValidateException;
import NoteBook.Parsers.IntegerParser;
import NoteBook.Validators.ArrLengthValidator;
import NoteBook.Validators.ZeroOrNaturalNumberValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Маша on 08.06.2017.
 */
public class FindByIDCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(FindByIDCommand.class);
    private NoteBook noteBook;

    public FindByIDCommand(NoteBook noteBook) {
        this.noteBook = noteBook;
    }

    @Override
    public void execute(String... params) {
        ArrLengthValidator arrValidator = new ArrLengthValidator();
        ZeroOrNaturalNumberValidator numberValidator = new ZeroOrNaturalNumberValidator();
        IntegerParser integerParser = new IntegerParser();
        try {
            arrValidator.validate(params, 2);
            numberValidator.validate(params[1]);
            noteBook.findByID(integerParser.parse(params[1]));
        } catch (ValidateException ex) {
            System.out.println(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
    }
}
