package NoteBook.Command;

/**
 * Created by Маша on 08.06.2017.
 */
public class HelpCommand implements Command {

    @Override
    public void execute(String... params) {
        System.out.println("Возможные команды:\n" +
                "1. add \"some text\" - добавить запись\n" +
                "2. delete recordID - удаление записи по ID\n" +
                "3. findAll - вывести все записи\n" +
                "4. findByID recordID - вывести запись по ID\n" +
                "5. help - справка");
    }
}
