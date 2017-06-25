package notebook.dao;

import notebook.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Маша on 25.06.2017.
 */
public interface GenericDAO<T, PK> {
    void persist(T obj) throws DAOException;
    boolean delete(PK keyValue) throws DAOException;
    T findByID(PK keyValue) throws DAOException;
    List<T> findAll() throws DAOException;
}
