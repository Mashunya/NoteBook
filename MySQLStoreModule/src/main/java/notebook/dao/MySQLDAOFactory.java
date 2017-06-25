package notebook.dao;

import notebook.PropsLoader;
import notebook.dao.DAOFactory;
import notebook.dao.GenericDAO;
import notebook.dao.MySQLRecordDAO;
import notebook.dao.exception.ContextException;
import notebook.dao.exception.DAOException;
import notebook.dao.exception.FindDAOException;
import notebook.entity.Record;
import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Маша on 25.06.2017.
 */
public class MySQLDAOFactory extends DAOFactory<Connection> {
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    public MySQLDAOFactory() throws ContextException, ResourceNotFoundException, PropFileLoadException {
        Connection context = getContext();
        DAOMap = new HashMap<>();
        DAOMap.put(Record.class, new MySQLRecordDAO(context));
    }

    @Override
    public Connection getContext() throws ContextException, PropFileLoadException, ResourceNotFoundException {
        try {
            Class.forName(DRIVER);
            PropsLoader propsLoader = new PropsLoader("connection.properties");
            Map<String, String> props = propsLoader.loadAllProps();
            return DriverManager.getConnection(props.get("URL"), props.get("LOGIN"), props.get("PASSWORD"));
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ContextException(ex);
        }
    }
}
