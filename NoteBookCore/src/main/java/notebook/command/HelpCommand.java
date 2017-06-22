package notebook.command;

import notebook.model.Message;
import notebook.model.MessageListModel;
import notebook.model.MessageStatus;
import notebook.model.Model;
import notebook.model.ModelAndView;

import java.util.Map;

/**
 * Created by Маша on 08.06.2017.
 */
public class HelpCommand extends CommandWorkedWithNoteBook {

    @Override
    public ModelAndView execute(Map<String, Object> params) {
        Model resultModel = new MessageListModel();
        resultModel.addMessage(new Message("Возможные команды:\n" +
                "1. add (rec_text (required), rec_title, rec_author, rec_type, rec_deadline) - добавить запись\n" +
                "2. delete (recordID (required)) - удаление записи по ID\n" +
                "3. findAll - вывести все записи\n" +
                "4. findByID (recordID (required)) - вывести запись по ID\n" +
                "5. help - справка\n" +
                "6. about - о программе", MessageStatus.INFO));
        return new ModelAndView("MessagesView", resultModel);
    }
}
