package NoteBook.Command.Command.CommandDecorator;

import NoteBook.Command.Command.Command;
import NoteBook.ModelAndView.Model.Message;
import NoteBook.ModelAndView.Model.MessageStatus;
import NoteBook.ModelAndView.Model.Model;
import NoteBook.ModelAndView.ModelAndView;

import java.util.Map;

/**
 * Created by Маша on 19.06.2017.
 */
public class CheckOSDecorator implements Command {

    private Command command;

    public CheckOSDecorator(Command command) {
        this.command = command;
    }

    @Override
    public ModelAndView execute(Map<String, Object> params) {
        ModelAndView resultModelAndView = command.execute(params);
        Model resultModel = resultModelAndView.getModel();

        String recommendedOS = (String)params.get("Recommended_OS");

        if(recommendedOS != null) {
            if (recommendedOS.equals(getCurrentOS())) {
                resultModel.addMessage(new Message("Ваша ОС совпадает с рекомендованной", MessageStatus.INFO));
            } else {
                resultModel.addMessage(new Message("Рекомендованная версия ОС: " + recommendedOS, MessageStatus.WARNING));
            }
        } else {
            resultModel.addMessage(new Message("Глобальный параметр Recommended_OS не задан", MessageStatus.WARNING));
        }

        return resultModelAndView;
    }

    String getCurrentOS() {
        return System.getenv().get("OS");
    }
}
