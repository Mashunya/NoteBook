package notebook.view;

import notebook.model.Message;
import notebook.model.RecordModel;

/**
 * Created by Маша on 19.06.2017.
 */
public class RecordView implements View<RecordModel> {
    @Override
    public void show(RecordModel model) {
        ConsoleView consoleView = getNewConsoleView();
        consoleView.show(model.getRecord());
        for(Message message: model.getMessages()) {
            consoleView.show(message);
        }
    }

    ConsoleView getNewConsoleView() {
        return new ConsoleView();
    }
}
