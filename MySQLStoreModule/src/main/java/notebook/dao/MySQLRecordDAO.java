package notebook.dao;

import notebook.dao.exception.DAOException;
import notebook.dao.exception.ExecuteException;
import notebook.dao.exception.ParseResultException;
import notebook.entity.Record;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Маша on 25.06.2017.
 */
public class MySQLRecordDAO implements GenericDAO<Record, Integer> {

    private Connection connection;

    public MySQLRecordDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void persist(Record obj) throws DAOException {

        String sql = "INSERT INTO notebook(recordText, author, title, recordType, deadline, createdDate, updatedDate) " +
                "VALUES ('" + obj.getRecordText() + "', '" + obj.getAuthor() + "', '" + obj.getTitle() + "', '" + obj.getType() + "', '" +
                convertDate(obj.getDeadlineDate()) + "', '" + convertDate(obj.getCreatedDate()) + "', '" + convertDate(obj.getUpdatedDate()) + "')";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                obj.setRecordID(resultSet.getInt(1));
            }
        } catch(SQLException ex) {
            throw new ExecuteException(ex);
        } finally {
            closeExecuteQuery(resultSet, statement);
        }
    }

    private String convertDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    @Override
    public boolean delete(Integer keyValue) throws DAOException {
        String sql = "DELETE FROM notebook WHERE recordID = " + keyValue;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            int deletedCount = statement.executeUpdate(sql);
            return deletedCount != 0;
        } catch(SQLException ex) {
            throw new ExecuteException(ex);
        } finally {
            closeExecuteQuery(null, statement);
        }
    }

    @Override
    public Record findByID(Integer keyValue) throws DAOException {
        String sql = "SELECT * FROM notebook WHERE recordID = " + keyValue;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            List<Record> records = parseResultSet(resultSet);
            return records.isEmpty() ? null : records.get(0);
        } catch(SQLException ex) {
            throw new ExecuteException(ex);
        } finally {
            closeExecuteQuery(resultSet, statement);
        }
    }

    @Override
    public List<Record> findAll() throws DAOException {
        String sql = "SELECT * FROM notebook";
        List<Record> records;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            records = parseResultSet(resultSet);
            return records;
        } catch(SQLException ex) {
            throw new ExecuteException(ex);
        } finally {
            closeExecuteQuery(resultSet, statement);
        }
    }

    private void closeExecuteQuery(ResultSet resultSet, Statement statement) throws ExecuteException {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if(statement != null) {
                statement.close();
            }
        } catch(SQLException ex) {
            throw new ExecuteException(ex);
        }
    }

    private List<Record> parseResultSet(ResultSet resultSet) throws ParseResultException {
        try {
            List<Record> records = new ArrayList<>();
            while(resultSet.next()) {
                Record record = new Record(resultSet.getString("recordText"), resultSet.getString("author"),
                        resultSet.getString("title"), resultSet.getString("recordType"),
                        resultSet.getDate("deadline"));
                record.setRecordID(resultSet.getInt("recordID"));
                record.setCreatedDate(resultSet.getDate("createdDate"));
                record.setUpdatedDate(resultSet.getDate("updatedDate"));
                records.add(record);
            }
            return records;
        } catch (SQLException ex) {
            throw new ParseResultException(ex);
        }
    }
}
