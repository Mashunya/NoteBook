package NoteBook.Command;

import NoteBook.Services.NoteBookService;
import NoteBook.View.View;
import org.slf4j.Logger;

/**
 * Created by Маша on 08.06.2017.
 */
public abstract class Command {

    protected Logger logger;
    protected NoteBookService noteBookService;
    protected View view;

    public void setNoteBookService(NoteBookService noteBookService) {
        this.noteBookService = noteBookService;
    }

    public void setView(View view) {
        this.view = view;
    }

    public abstract void execute();
}
