package notebook.command;

import notebook.entity.Record;
import notebook.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by Маша on 08.06.2017.
 */
public class FindAllCommand extends CommandWorkedWithNoteBook {

    private final Logger logger = LoggerFactory.getLogger(FindAllCommand.class);
    @Override
    public ModelAndView execute(Map<String, Object> params) {
        List<Record> records = noteBookService.findAll();
        if(records.size() == 0) {
            logger.warn("FindAll: не найдено ни одной записи");
            MessageListModel resultModel = new MessageListModel();
            resultModel.addMessage(new Message("Не найдено ни одной записи", MessageStatus.WARNING));
            return new ModelAndView("MessagesView", resultModel);
        } else {
            RecordListModel resultModel = new RecordListModel();
            resultModel.setRecords(records);
            return new ModelAndView("RecordListView", resultModel);
        }
    }
}
