package notebook.view;

import notebook.model.Message;
import notebook.model.RecordListModel;

/**
 * Created by Маша on 19.06.2017.
 */
public class RecordListView implements View<RecordListModel> {
    @Override
    public void show(RecordListModel model) {
        ConsoleView consoleView = getNewConsoleView();
        consoleView.show(model.getRecords());
        for(Message message: model.getMessages()) {
            consoleView.show(message);
        }
    }

    ConsoleView getNewConsoleView() {
        return new ConsoleView();
    }
}
