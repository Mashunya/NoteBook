package NoteBook.Command.Command;

import NoteBook.Services.NoteBookService;

/**
 * Created by Маша on 08.06.2017.
 */
//TODO interface with only execute + abstract with only notebookservice for commands that going to use it
public abstract class CommandWorkedWithNoteBook implements Command {

    protected NoteBookService noteBookService;

    public void setNoteBookService(NoteBookService noteBookService) {
        this.noteBookService = noteBookService;
    }
}
