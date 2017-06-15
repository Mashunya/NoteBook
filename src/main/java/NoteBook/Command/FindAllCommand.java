package NoteBook.Command;

import java.util.Map;

/**
 * Created by Маша on 08.06.2017.
 */
public class FindAllCommand extends Command {

    @Override
    public void execute() {
        noteBookService.findAll();
    }

    @Override
    public Command newCommand(Map<String, Object> preparedParams) {
        return new FindAllCommand();
    }
}
