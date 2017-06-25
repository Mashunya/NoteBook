package notebook.dao;

import notebook.dao.exception.ContextException;
import notebook.dao.exception.FindDAOException;
import notebook.exception.PropFileLoadException;
import notebook.exception.ResourceNotFoundException;

import java.io.File;
import java.util.Map;

/**
 * Created by Маша on 25.06.2017.
 */
public abstract class DAOFactory<C> {
    protected Map<Class, GenericDAO> DAOMap;

    public abstract C getContext() throws ContextException, PropFileLoadException, ResourceNotFoundException;

    public GenericDAO getDAO(Class entityClass) throws FindDAOException {
        GenericDAO dao = DAOMap.get(entityClass);
        if(dao != null) {
            return dao;
        } else {
            throw new FindDAOException(entityClass);
        }
    }
}
