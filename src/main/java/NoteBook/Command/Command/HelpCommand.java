package NoteBook.Command.Command;

import NoteBook.Command.CommandResult.CommandResult;

import java.util.List;

/**
 * Created by Маша on 08.06.2017.
 */
public class HelpCommand extends Command {

    @Override
    public List<CommandResult> execute() {
       results.add(new CommandResult("Возможные команды:\n" +
                "1. add (rec_text (required), rec_title, rec_author, rec_type) - добавить запись\n" +
                "2. delete (recordID (required)) - удаление записи по ID\n" +
                "3. findAll - вывести все записи\n" +
                "4. findByID (recordID (required)) - вывести запись по ID\n" +
                "5. help - справка\n" +
                "6. about - о программе", CommandResult.INFO));
       return results;
    }
}
