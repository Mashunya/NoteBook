package notebook.view;

import notebook.entity.Record;
import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;
import notebook.GlobalParamsExtractor;
import notebook.model.Message;
import notebook.model.MessageStatus;
import notebook.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Маша on 11.06.2017.
 */
public class ConsoleView {

    private Logger logger = LoggerFactory.getLogger(ConsoleView.class);

    public void show(String viewName, Model model) {
        ViewResolver viewResolver = new ViewResolver();
        View view = viewResolver.getView(viewName);
        if(view == null) {
            logger.error("Представление не найдено");
            model.addMessage(new Message("Представление не найдено", MessageStatus.ERROR));
            view = new MessagesView();
        }
        view.show(model);
    }

    public void show(Message message) {
        printInConsole(message.getStatus().name() + ": " + message.getMessage());
    }

    public void show(Record record) {
        DateFormat formatter = getDateFormatter();

        printInConsole("ID: " + record.getRecordID() + "\n" +
                "Title: " + record.getTitle() + "\n" +
                "Text: " + record.getRecordText() + "\n" +
                "Author: " + record.getAuthor() + "\n" +
                "Type: " + record.getType() + "\n" +
                "DeadlineDate: " + formatter.format(record.getDeadlineDate()) + "\n" +
                "CreatedDate: " + formatter.format(record.getCreatedDate()) + "\n" +
                "UpdateDate: " + formatter.format(record.getUpdatedDate()) + "\n");
    }

    public void show(List<Record> records) {
        for(Record record: records) {
            show(record);
        }
    }

    void printInConsole(String text) {
        System.out.println(text);
    }

    DateFormat getDateFormatter() {
        String dateFormat;
        try {
            dateFormat = GlobalParamsExtractor.getProperty("Date_Format");
        } catch(PropFileLoadException | ResourceNotFoundException ex) {
            dateFormat = "dd.MM.yyyy";
            logger.error(ex.getMessage(), ex);
        }
        return new SimpleDateFormat(dateFormat);
    }
}
