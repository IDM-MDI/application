package by.ishangulyev.application.dao;

import by.ishangulyev.application.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetExecutor<T> {
    T getValues(ResultSet set) throws DaoException;
}
