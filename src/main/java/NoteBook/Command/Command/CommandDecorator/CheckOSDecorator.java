package NoteBook.Command.Command.CommandDecorator;

import NoteBook.Command.Command.Command;
import NoteBook.ModelAndView.Model.Message;
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
    public ModelAndView execute() {
        ModelAndView resultModelAndView = command.execute();
        Model resultModel = resultModelAndView.getModel();

        Map<String, String> globalParams = getGlobalParams();
        if(globalParams == null) {
            resultModel.addMessage(new Message("Глобальные параметры не установлены", Message.WARNING));
            return resultModelAndView;
        }

        String recommendedOS = globalParams.get("Recommended_OS");

        if(recommendedOS != null) {
            if (recommendedOS.equals(getCurrentOS())) {
                resultModel.addMessage(new Message("Ваша ОС совпадает с рекомендованной", Message.INFO));
            } else {
                resultModel.addMessage(new Message("Рекомендованная версия ОС: " + recommendedOS, Message.WARNING));
            }
        } else {
            resultModel.addMessage(new Message("Глобальный параметр Recommended_OS не задан", Message.WARNING));
        }

        return resultModelAndView;
    }

    String getCurrentOS() {
        return System.getenv().get("OS");
    }

    @Override
    public void setGlobalParams(Map<String, String> globalParams) {
        command.setGlobalParams(globalParams);
    }

    @Override
    public Map<String, String> getGlobalParams() {
        return command.getGlobalParams();
    }
}
