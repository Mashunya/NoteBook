package notebook.dao;

import com.ibatis.common.jdbc.ScriptRunner;
import notebook.PropsLoader;
import notebook.dao.exception.DAOException;
import notebook.entity.Record;
import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Маша on 26.06.2017.
 */
@RunWith(JUnit4.class)
public class HibernateRecordDAOTest {

    private static Session session;
    private static String url;
    private Transaction curTransaction;

    @BeforeClass
    public static void generateTestDB() throws PropFileLoadException, ResourceNotFoundException, SQLException, IOException {
        PropsLoader propsLoader = new PropsLoader("hibernate.properties");
        Map<String, String> connectionProps = propsLoader.loadAllProps();
        url = "jdbc:mysql://localhost/?user=" + connectionProps.get("hibernate.connection.username") +
                "&password=" + connectionProps.get("hibernate.connection.password");
        Connection connection = DriverManager.getConnection(url);

        ScriptRunner scriptRunner = new ScriptRunner(connection, false, true);
        Reader reader = new FileReader(System.getProperty("user.dir") + "/src/test/resources/createDB.sql");
        scriptRunner.runScript(reader);
        connection.close();

        session = new HibernateDAOFactory().initContext();
    }

    @Before
    public void beginTransaction() throws SQLException {
        curTransaction = session.beginTransaction();
    }

    @Test
    public void persistTest() throws DAOException {
        //given
        GenericDAO<Record, Integer> recordDAO = new HibernateRecordDAO(session);
        Calendar calendar = new GregorianCalendar();
        calendar.set(2017, Calendar.AUGUST, 30);
        Date deadline = calendar.getTime();
        Record record = new Record("test_text", "test_author", "test_title", "test_type", deadline);

        //when
        recordDAO.persist(record);

        //then
        assertNotNull(record.getRecordID());
    }

    @Test
    public void deleteTest() throws DAOException {
        //given
        GenericDAO<Record, Integer> recordDAO = new HibernateRecordDAO(session);
        assertNotNull(recordDAO.findByID(1));

        //when
        recordDAO.delete(1);

        //then
        assertNull(recordDAO.findByID(1));
    }

    @Test
    public void findByIDTest() throws DAOException {
        //given
        GenericDAO<Record, Integer> recordDAO = new HibernateRecordDAO(session);

        //when
        Record existRecord = recordDAO.findByID(1);
        Record notExistRecord = recordDAO.findByID(100);

        //then
        assertNotNull(existRecord);
        assertNull(notExistRecord);
    }

    @Test
    public void findAll() throws DAOException {
        //given
        GenericDAO<Record, Integer> recordDAO = new HibernateRecordDAO(session);

        //when
        List<Record> records = recordDAO.findAll();

        //then
        assertEquals(3, records.size());
    }

    @After
    public void rollbackTransaction() throws SQLException {
        curTransaction.rollback();
    }

    @AfterClass
    public static void dropTestDB() throws PropFileLoadException, ResourceNotFoundException, SQLException, IOException {
        Connection connection = DriverManager.getConnection(url);
        ScriptRunner scriptRunner = new ScriptRunner(connection, false, true);
        Reader reader = new FileReader(System.getProperty("user.dir") + "/src/test/resources/dropDB.sql");
        scriptRunner.runScript(reader);
        connection.close();

        session.close();
    }
}