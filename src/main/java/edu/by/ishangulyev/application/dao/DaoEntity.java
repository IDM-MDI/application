package edu.by.ishangulyev.application.dao;

import edu.by.ishangulyev.application.connection.ConnectionPool;
import edu.by.ishangulyev.application.exception.DataBaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class DaoEntity<T> implements ResultSetExecutor<T>
{
    protected Connection connection;
    protected ConnectionPool connectionPool;

    public DaoEntity() {
        connectionPool = ConnectionPool.getINSTANCE();
        try
        {
            connection = connectionPool.getConnection();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public abstract List<T> getAll() throws DataBaseException;
    public abstract boolean update(T entity);
    public abstract Optional<T> getEntityById(long id) throws DataBaseException;
    public abstract boolean delete(long id);
    public abstract boolean create(T entity);

    public void releaseConnection() {
        connectionPool.releaseConnection(connection);
    }

    public abstract void fillStatement(PreparedStatement statement, T entity) throws SQLException;

}
