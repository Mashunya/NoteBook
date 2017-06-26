package notebook.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by Маша on 26.06.2017.
 */
@RunWith(JUnit4.class)
public class HibernateDAOFactoryTest {
    @Test
    public void initContext() {
        //given
        HibernateDAOFactory factory = new HibernateDAOFactory();

        //when
        factory.initContext();

        //then
        //work without exceptions
    }
}