package notebook.dao;

import notebook.dao.exception.DAOException;
import notebook.entity.Record;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Маша on 26.06.2017.
 */
public class HibernateRecordDAO implements GenericDAO<Record, Integer> {

    private Session session;

    public HibernateRecordDAO(Session session) {
        this.session = session;
    }


    @Override
    public void persist(Record obj) throws DAOException {
        session.save(obj);
    }

    @Override
    public boolean delete(Integer keyValue) throws DAOException {
        Record record = session.get(Record.class, keyValue);
        if(record != null) {
            session.delete(record);
            return true;
        }
        return false;
    }

    @Override
    public Record findByID(Integer keyValue) throws DAOException {
        return session.get(Record.class, keyValue);
    }

    @Override
    public List<Record> findAll() throws DAOException {
        return session.createQuery("FROM Record").list();
    }
}
