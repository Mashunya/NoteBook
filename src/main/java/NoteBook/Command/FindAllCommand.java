package NoteBook.Command;

import NoteBook.Services.NoteBookService;
import NoteBook.View.View;

/**
 * Created by Маша on 08.06.2017.
 */
public class FindAllCommand implements Command {

    private NoteBookService noteBookService;

    public FindAllCommand(NoteBookService noteBookService) {
        this.noteBookService = noteBookService;
    }

    @Override
    public void execute(String... params) {
        noteBookService.findAll();
    }
}
