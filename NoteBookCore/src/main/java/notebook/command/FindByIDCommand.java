package notebook.command;

import notebook.dao.exception.DAOException;
import notebook.entity.Record;
import notebook.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Маша on 08.06.2017.
 */
public class FindByIDCommand extends CommandWorkedWithDAO {

    private final Logger logger = LoggerFactory.getLogger(FindByIDCommand.class);

    @Override
    public ModelAndView execute(Map<String, Object> params) {
        int recordID = (int) params.get("recordID");
        try {
            Record record = recordDAO.findByID(recordID);
            if (record != null) {
                RecordModel resultModel = new RecordModel();
                resultModel.setRecord(record);
                return new ModelAndView("RecordView", resultModel);
            } else {
                logger.warn("FindByID: запись c ID: " + recordID + " не найдена");
                MessageListModel resultModel = new MessageListModel();
                resultModel.addMessage(new Message("Запись c ID: " + recordID + " не найдена", MessageStatus.WARNING));
                return new ModelAndView("MessagesView", resultModel);
            }
        } catch(DAOException ex) {
            logger.error(ex.getMessage(), ex);
            MessageListModel resultModel = new MessageListModel();
            resultModel.addMessage(new Message(ex.getMessage(), MessageStatus.ERROR));
            return new ModelAndView("MessagesView", resultModel);
        }
    }
}
