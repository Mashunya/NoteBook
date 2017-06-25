package notebook.command;

import notebook.dao.GenericDAO;
import notebook.entity.Record;

/**
 * Created by Маша on 08.06.2017.
 */
public abstract class CommandWorkedWithDAO implements Command {

    protected GenericDAO<Record, Integer> recordDAO;

    public void setRecordDAO(GenericDAO<Record, Integer> recordDAO) {
        this.recordDAO = recordDAO;
    }
}
