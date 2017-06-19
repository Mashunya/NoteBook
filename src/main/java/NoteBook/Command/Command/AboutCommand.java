package NoteBook.Command.Command;

import NoteBook.ModelAndView.Model.Message;
import NoteBook.ModelAndView.Model.MessageListModel;
import NoteBook.ModelAndView.Model.Model;
import NoteBook.ModelAndView.ModelAndView;

import java.util.Map;

/**
 * Created by Маша on 16.06.2017.
 */
public class AboutCommand implements Command {

    private Map<String, String> globalParams;

    @Override
    public ModelAndView execute() {
        Model resultModel = new MessageListModel();

        if(globalParams == null) {
            resultModel.addMessage(new Message("Глобальные параметры не установлены", Message.WARNING));
        } else {
            String aboutProgram = "Информация о программе:\n";
            aboutProgram += ("Название программы: " + globalParams.get("Program_Name") + "\n");
            aboutProgram += ("Версия: " + globalParams.get("Version") + "\n");
            aboutProgram += ("Краткое описание: " + globalParams.get("About_Program") + "\n");
            aboutProgram += ("Автор: " + globalParams.get("Author") + "\n");
            resultModel.addMessage(new Message(aboutProgram, Message.INFO));
        }

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
