package notebook.command;

import notebook.model.Message;
import notebook.model.MessageListModel;
import notebook.model.MessageStatus;
import notebook.model.Model;
import notebook.model.ModelAndView;

import java.util.Map;

/**
 * Created by Маша on 16.06.2017.
 */
public class AboutCommand implements Command {

    @Override
    public ModelAndView execute(Map<String, Object> params) {
        Model resultModel = new MessageListModel();

        String aboutProgram = "Информация о программе:\n";
        aboutProgram += ("Название программы: " + params.get("Program_Name") + "\n");
        aboutProgram += ("Версия: " + params.get("Version") + "\n");
        aboutProgram += ("Краткое описание: " + params.get("About_Program") + "\n");
        aboutProgram += ("Автор: " + params.get("Author") + "\n");
        resultModel.addMessage(new Message(aboutProgram, MessageStatus.INFO));

        return new ModelAndView("MessagesView", resultModel);
    }
}
