package NoteBook.ModelAndView.View;

import NoteBook.ModelAndView.Model.Message;
import NoteBook.ModelAndView.Model.Model;
import NoteBook.ModelAndView.Model.RecordListModel;

/**
 * Created by Маша on 19.06.2017.
 */
public class RecordListView implements View {
    @Override
    public void show(Model model) {
        RecordListModel recordListModel = (RecordListModel)model;
        ConsoleView consoleView = getNewConsoleView();

        consoleView.show(recordListModel.getRecords());
        for(Message message: model.getMessages()) {
            consoleView.show(message);
        }
    }

    ConsoleView getNewConsoleView() {
        return new ConsoleView();
    }
}
