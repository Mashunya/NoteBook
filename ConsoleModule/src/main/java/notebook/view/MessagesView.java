package notebook.view;

import notebook.model.Message;
import notebook.model.MessageListModel;

/**
 * Created by Маша on 19.06.2017.
 */
public class MessagesView implements View<MessageListModel> {
    @Override
    public void show(MessageListModel model) {
        ConsoleView consoleView = getNewConsoleView();
        for(Message message: model.getMessages()) {
            consoleView.show(message);
        }
    }

    ConsoleView getNewConsoleView() {
        return new ConsoleView();
    }
}
