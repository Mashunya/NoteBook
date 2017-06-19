package NoteBook.Command.Command;

import NoteBook.ModelAndView.Model.Message;
import NoteBook.ModelAndView.Model.MessageListModel;
import NoteBook.ModelAndView.Model.Model;
import NoteBook.ModelAndView.ModelAndView;

import java.util.Map;

/**
 * Created by Маша on 08.06.2017.
 */
public class HelpCommand extends CommandWorkedWithNoteBook {

    private Map<String, String> globalParams;

    @Override
    public ModelAndView execute() {
        Model resultModel = new MessageListModel();
        resultModel.addMessage(new Message("Возможные команды:\n" +
                "1. add (rec_text (required), rec_title, rec_author, rec_type) - добавить запись\n" +
                "2. delete (recordID (required)) - удаление записи по ID\n" +
                "3. findAll - вывести все записи\n" +
                "4. findByID (recordID (required)) - вывести запись по ID\n" +
                "5. help - справка\n" +
                "6. about - о программе", Message.INFO));
        return new ModelAndView("MessagesView", resultModel);
    }

    @Override
    public Map<String, String> getGlobalParams() {
        return globalParams;
    }

    public void setGlobalParams(Map<String, String> globalParams) {
        this.globalParams = globalParams;
    }
}
