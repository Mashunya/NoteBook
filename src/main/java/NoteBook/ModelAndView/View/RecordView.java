package NoteBook.ModelAndView.View;

import NoteBook.ModelAndView.Model.Message;
import NoteBook.ModelAndView.Model.Model;
import NoteBook.ModelAndView.Model.RecordModel;

/**
 * Created by Маша on 19.06.2017.
 */
public class RecordView implements View {
    @Override
    public void show(Model model) {
        RecordModel recordModel = (RecordModel)model;
        ConsoleView consoleView = new ConsoleView();

        consoleView.show(recordModel.getRecord());
        for(Message message: model.getMessages()) {
            consoleView.show(message);
        }
    }
}
