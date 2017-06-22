package notebook.command;

import notebook.exception.SaveRecordsException;
import notebook.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Маша on 08.06.2017.
 */
public class DeleteCommand extends CommandWorkedWithNoteBook {

    private final Logger logger = LoggerFactory.getLogger(DeleteCommand.class);

    @Override
    public ModelAndView execute(Map<String, Object> params) {
        Message resultMessage;
        try {
            int recordID = (int)params.get("recordID");
            if(noteBookService.deleteRecord(recordID)) {
                resultMessage = new Message("Запись c ID: " + recordID + " успешно удалена", MessageStatus.SUCCESS);
                logger.info("Запись c ID: " + recordID + " успешно удалена");
            } else {
                resultMessage = new Message("Запись c ID: " + recordID + " не найдена", MessageStatus.WARNING);
                logger.warn("DeleteRecord: запись c ID: " + recordID + " не найдена");
            }
        } catch (SaveRecordsException ex) {
            logger.error(ex.getMessage(), ex);
            resultMessage = new Message(ex.getMessage(), MessageStatus.ERROR);
        }
        Model model = new MessageListModel();
        model.addMessage(resultMessage);
        return new ModelAndView("MessagesView", model);
    }
}
