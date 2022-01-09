package edu.by.ishangulyev.application.dao;

import edu.by.ishangulyev.application.connection.ConnectionPool;

import java.sql.Connection;
import java.util.List;

public abstract class DaoEntity<T>
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

    public abstract List<T> getAll();
    public abstract T update(T entity);
    public abstract T getEntityById(long id);
    public abstract boolean delete(long id);
    public abstract boolean create(T entity);

    public void releaseConnection() {
        connectionPool.releaseConnection(connection);
    }


}
