package notebook.dao;

import notebook.dao.exception.DAOException;
import notebook.entity.Record;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Маша on 26.06.2017.
 */
public class HibernateRecordDAO implements GenericDAO<Record, Integer> {

    private SessionFactory sessionFactory;

    public HibernateRecordDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void persist(Record obj) throws DAOException {
        Session session = sessionFactory.openSession();
        session.save(obj);
        session.close();
    }

    @Override
    public boolean delete(Integer keyValue) throws DAOException {
        Session session = sessionFactory.openSession();
        Record record = session.get(Record.class, keyValue);
        if(record != null) {
            session.delete(record);
        }
        session.close();
        return (record != null);
    }

    @Override
    public Record findByID(Integer keyValue) throws DAOException {
        Session session = sessionFactory.openSession();
        Record record = session.get(Record.class, keyValue);
        session.close();
        return record;
    }

    @Override
    public List<Record> findAll() throws DAOException {
        Session session = sessionFactory.openSession();
        List<Record> records = session.createQuery("FROM Record").list();
        session.close();
        return records;
    }
}
