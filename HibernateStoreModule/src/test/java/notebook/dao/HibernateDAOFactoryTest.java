package notebook.dao;

import notebook.dao.exception.ContextException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by Маша on 26.06.2017.
 */
@RunWith(JUnit4.class)
public class HibernateDAOFactoryTest {

    @Test(expected = ContextException.class)
    public void initContextDBNOtExist() throws ContextException {
        //given
        HibernateDAOFactory factory = new HibernateDAOFactory();

        //when
        factory.initContext();

        //then
        //work without exceptions
    }
}