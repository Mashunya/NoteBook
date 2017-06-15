package NoteBook.Command;

import java.util.Map;

/**
 * Created by Маша on 08.06.2017.
 */
public class HelpCommand extends Command {

    @Override
    public void execute() {
        view.showInfoMessage("Возможные команды:\n" +
                "1. add \"some text\" - добавить запись\n" +
                "2. delete recordID - удаление записи по ID\n" +
                "3. findAll - вывести все записи\n" +
                "4. findByID recordID - вывести запись по ID\n" +
                "5. help - справка");
    }

    @Override
    public Command newCommand(Map<String, Object> preparedParams) {
        return new HelpCommand();
    }
}
