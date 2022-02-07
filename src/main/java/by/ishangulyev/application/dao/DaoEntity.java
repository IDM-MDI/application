package by.ishangulyev.application.dao;

import by.ishangulyev.application.connection.ConnectionPool;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.exception.DataBaseException;
import by.ishangulyev.application.model.entity.impl.Gadget;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class DaoEntity<U,T> implements ResultSetExecutor<T> {
    protected Connection connection;
    protected ConnectionPool connectionPool;

    public DaoEntity() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

    public abstract List<T> findAll() throws DaoException;

    public abstract boolean update(T entity);

    public abstract Optional<T> findEntityById(U id) throws DaoException;
    public abstract List<T> findByCount(int count) throws DaoException;

    public abstract boolean delete(U id);

    public abstract boolean create(T entity);

    public void releaseConnection() {
        connectionPool.releaseConnection(connection);
    }

    public abstract void fillStatement(PreparedStatement statement, T entity) throws DaoException;

}
