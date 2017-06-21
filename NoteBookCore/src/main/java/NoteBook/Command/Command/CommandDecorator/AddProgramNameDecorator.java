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
    public ModelAndView execute(Map<String, Object> params) {
        ModelAndView resultModelAndView = command.execute(params);

        String programName = (String)params.get("Program_Name");
        String version = (String)params.get("Version");

        if(programName != null && version != null) {
            String aboutProgram = "Программа " + programName + ", версия " + version;
            resultModelAndView.getModel().addMessage(new Message(aboutProgram, MessageStatus.INFO));
        } else {
            resultModelAndView.getModel().addMessage(new Message("Глобальные параметры Program_Name и (или) " +
                    "Version не заданы", MessageStatus.WARNING));
        }

        return resultModelAndView;
    }
}
