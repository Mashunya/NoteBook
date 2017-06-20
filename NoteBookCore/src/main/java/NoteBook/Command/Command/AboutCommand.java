package NoteBook.Command.Command;

import NoteBook.ModelAndView.Model.Message;
import NoteBook.ModelAndView.Model.MessageListModel;
import NoteBook.ModelAndView.Model.MessageStatus;
import NoteBook.ModelAndView.Model.Model;
import NoteBook.ModelAndView.ModelAndView;

import java.util.HashMap;
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
