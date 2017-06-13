package NoteBook.Command;

import NoteBook.Services.NoteBookService;

/**
 * Created by Маша on 08.06.2017.
 */
public class FindAllCommand extends Command {

    @Override
    public void execute() {
        noteBookService.findAll();
    }
}
