package NoteBook.Command;

import NoteBook.View.View;

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
}
