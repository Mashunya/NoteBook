package NoteBook.Command.Command.CommandDecorator;

import NoteBook.Command.Command.Command;
import NoteBook.ModelAndView.Model.Message;
import NoteBook.ModelAndView.Model.MessageStatus;
import NoteBook.ModelAndView.ModelAndView;

import java.util.Map;

/**
 * Created by Маша on 19.06.2017.
 */
public class AddProgramNameDecorator implements Command {

    private Command command;

    public AddProgramNameDecorator(Command command) {
        this.command = command;
    }

    @Override
    public ModelAndView execute() {
        ModelAndView resultModelAndView = command.execute();

        Map<String, String> globalParams = getGlobalParams();
        if(globalParams == null) {
            resultModelAndView.getModel().addMessage(new Message("Глобальные параметры не установлены", MessageStatus.WARNING));
            return resultModelAndView;
        }

        String programName = globalParams.get("Program_Name");
        String version = globalParams.get("Version");

        if(programName != null && version != null) {
            String aboutProgram = "Программа " + programName + ", версия " + version;
            resultModelAndView.getModel().addMessage(new Message(aboutProgram, MessageStatus.INFO));
        } else {
            resultModelAndView.getModel().addMessage(new Message("Глобальные параметры Program_Name и (или) " +
                    "Version не заданы", MessageStatus.WARNING));
        }

        return resultModelAndView;
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
