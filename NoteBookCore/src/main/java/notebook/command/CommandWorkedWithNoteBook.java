package notebook.command;

import notebook.services.NoteBookService;

/**
 * Created by Маша on 08.06.2017.
 */
public abstract class CommandWorkedWithNoteBook implements Command {

    protected NoteBookService noteBookService;

    public void setNoteBookService(NoteBookService noteBookService) {
        this.noteBookService = noteBookService;
    }
}
