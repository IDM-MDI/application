package edu.by.ishangulyev.application.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetExecutor<T>
{
    T execute(ResultSet set) throws SQLException;
}
