package NoteBook.Command;

import NoteBook.Services.NoteBookService;
import NoteBook.View.View;

import java.util.Map;

/**
 * Created by Маша on 08.06.2017.
 */
public abstract class Command {

    protected NoteBookService noteBookService;
    protected View view;

    public void setNoteBookService(NoteBookService noteBookService) {
        this.noteBookService = noteBookService;
    }

    public void setView(View view) {
        this.view = view;
    }

    public abstract void execute();

    public abstract Command newCommand(Map<String, Object> preparedParams);
}
