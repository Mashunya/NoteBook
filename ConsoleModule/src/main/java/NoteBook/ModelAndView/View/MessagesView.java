package NoteBook.ModelAndView.View;

import NoteBook.ModelAndView.Model.Message;
import NoteBook.ModelAndView.Model.Model;

/**
 * Created by Маша on 19.06.2017.
 */
public class MessagesView implements View {
    @Override
    public void show(Model model) {
        ConsoleView consoleView = getNewConsoleView();
        for(Message message: model.getMessages()) {
            consoleView.show(message);
        }
    }

    ConsoleView getNewConsoleView() {
        return new ConsoleView();
    }
}
