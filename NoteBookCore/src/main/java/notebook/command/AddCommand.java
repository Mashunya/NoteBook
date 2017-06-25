package notebook.command;

import notebook.dao.exception.DAOException;
import notebook.entity.Record;
import notebook.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

/**
 * Created by Маша on 08.06.2017.
 */
public class AddCommand extends CommandWorkedWithDAO {

    private final Logger logger = LoggerFactory.getLogger(AddCommand.class);

    @Override
    public ModelAndView execute(Map<String, Object> params) {
        Record record = new Record((String)params.get("text"), (String)params.get("author"), (String)params.get("title"),
                (String)params.get("type"), (Date)params.get("deadline"));

        Message resultMessage;
        try {
            recordDAO.persist(record);
            resultMessage = new Message("Запись успешно добавлена", MessageStatus.SUCCESS);
            logger.info("Запись успешно добавлена");
        } catch(DAOException ex) {
            resultMessage = new Message(ex.getMessage(), MessageStatus.ERROR);
            logger.error(ex.getMessage(), ex);
        }
        Model model = new MessageListModel();
        model.addMessage(resultMessage);
        return new ModelAndView("MessagesView", model);
    }
}
