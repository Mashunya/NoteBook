package notebook.dao;

import notebook.dao.exception.ContextException;
import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Connection;


/**
 * Created by Маша on 26.06.2017.
 */
@RunWith(JUnit4.class)
public class MySQLDAOFactoryTest {
    @Test
    public void initDAOFactoryWithConnectionToDB() throws PropFileLoadException, ResourceNotFoundException, ContextException {
        //given
        DAOFactory<Connection> factory = new MySQLDAOFactory();

        //when
        factory.initContext();

        //then
        //work without exception
    }
}