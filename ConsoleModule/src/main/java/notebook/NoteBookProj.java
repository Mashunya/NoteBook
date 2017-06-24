package notebook;

import notebook.args_converter.ArgsConverter;
import notebook.command.Command;
import notebook.command.description.CommandDescription;
import notebook.command.description.CommandDescriptionRegistry;
import notebook.command.factory.CommandFactory;
import notebook.command.params.InputDataPreparator;
import notebook.entity.NoteBook;
import notebook.exception.IllegalCommandException;
import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;
import notebook.exception.WorkWithFileException;
import notebook.id.SimpleIDGen;
import notebook.model.Message;
import notebook.model.MessageStatus;
import notebook.model.Model;
import notebook.model.ModelAndView;
import notebook.services.NoteBookService;
import notebook.store.FileStore;
import notebook.view.ConsoleView;
import notebook.view.MessagesView;
import notebook.view.View;
import notebook.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by Маша on 08.06.2017.
 */
public class NoteBookProj {

    public static void main(String[] args) {
        ConsoleWork consoleWork = new ConsoleWork();
        List<Message> initErrorMessages = consoleWork.init();
        ModelAndView commandResult = consoleWork.workWithNotebookService(args, initErrorMessages);

        Model model = commandResult.getModel();
        ViewResolver viewResolver = new ViewResolver();
        View view = viewResolver.getView(commandResult.getViewName());
        if(view == null) {
            model.addMessage(new Message("Представление не найдено", MessageStatus.ERROR));
            view = new MessagesView();
        }
        view.show(model);
    }
}
