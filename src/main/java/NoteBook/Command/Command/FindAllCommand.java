package NoteBook.Command.Command;

import NoteBook.Command.CommandResult.CommandResult;

import java.util.List;

/**
 * Created by Маша on 08.06.2017.
 */
public class FindAllCommand extends Command {

    @Override
    public List<CommandResult> execute() {
        results.add(noteBookService.findAll());
        return results;
    }
}
