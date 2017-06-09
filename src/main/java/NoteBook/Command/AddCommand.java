package NoteBook.Command;

import NoteBook.Entity.NoteBook;
import NoteBook.Exception.ValidateException;
import NoteBook.Validators.ArrLengthValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Маша on 08.06.2017.
 */
public class AddCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(AddCommand.class);
    private NoteBook noteBook;

    public AddCommand(NoteBook noteBook) {
        this.noteBook = noteBook;
    }

    @Override
    public void execute(String... params) {

        ArrLengthValidator validator = new ArrLengthValidator();
        try {
            validator.validate(params, 2);
            noteBook.addRecord(params[1]);
        } catch(ValidateException ex) {
            System.out.println(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
    }
}
