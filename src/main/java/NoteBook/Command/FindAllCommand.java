package NoteBook.Command;

import NoteBook.Services.NoteBookService;

/**
 * Created by Маша on 08.06.2017.
 */
public class FindAllCommand extends Command {

    public FindAllCommand(NoteBookService noteBookService) {
        this.noteBookService = noteBookService;
    }

    @Override
    public void execute() {
        noteBookService.findAll();
    }
}
