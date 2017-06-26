package notebook.dao;

import notebook.PropsLoader;
import notebook.dao.exception.ContextException;
import notebook.entity.Record;
import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Маша on 26.06.2017.
 */
public class HibernateDAOFactory extends DAOFactory<Session> {
    @Override
    public Session initContext() {
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().build();

        Metadata metadata = new MetadataSources( standardRegistry )
                .addAnnotatedClass( Record.class )
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        return sessionFactory.openSession();
    }

    @Override
    public void init() throws ContextException, ResourceNotFoundException, PropFileLoadException {
        Session context = initContext();

        DAOMap = new HashMap<>();
        DAOMap.put(Record.class, new HibernateRecordDAO(context));
    }
}
