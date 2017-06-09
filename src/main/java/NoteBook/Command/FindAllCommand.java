package NoteBook.Command;

import NoteBook.Entity.NoteBook;

/**
 * Created by Маша on 08.06.2017.
 */
public class FindAllCommand implements Command {

    private NoteBook noteBook;

    public FindAllCommand(NoteBook noteBook) {
        this.noteBook = noteBook;
    }

    @Override
    public void execute(String... params) {
        noteBook.findAll();
    }
}
