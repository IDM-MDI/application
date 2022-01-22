package by.ishangulyev.application.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetExecutor<T> {
    T getValues(ResultSet set) throws SQLException;
}
