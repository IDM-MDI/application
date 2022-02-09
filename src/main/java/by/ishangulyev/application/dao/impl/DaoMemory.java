package by.ishangulyev.application.dao.impl;

import by.ishangulyev.application.dao.ColumnName;
import by.ishangulyev.application.dao.DaoEntity;
import by.ishangulyev.application.dao.query.MemoryQuery;
import by.ishangulyev.application.dao.query.UserQuery;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.exception.DataBaseException;
import by.ishangulyev.application.model.entity.impl.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoMemory extends DaoEntity<Long,Memory> {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Memory> findAll() throws DaoException {
        List<Memory> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(MemoryQuery.SELECT_ALL.getValue())) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                result.add(getValues(set));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error executing query get all memory", e);
            throw new DaoException("Error executing query get all memory", e);
        } finally {
            releaseConnection();
        }
        return result;
    }

    @Override
    public boolean update(Memory entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(MemoryQuery.UPDATE.getValue())) {
            fillStatement(statement, entity);
            statement.setLong(4,entity.getId());
            result = statement.executeUpdate() > 0;
        } catch (SQLException | DaoException e) {
            logger.log(Level.WARN, "Error while updating dao", e);
            result = false;
        } finally {
            releaseConnection();
        }
        return result;
    }

    @Override
    public Optional<Memory> findEntityById(Long id) throws DaoException {
        Optional<Memory> entity = Optional.empty();

        try (PreparedStatement statement = connection.prepareStatement(MemoryQuery.SELECT_BY_ID.getValue())) {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                entity = Optional.of(getValues(set));
            }
        } catch (SQLException e) {
            logger.error("query has failed", e);
            throw new DaoException("query has failed");
        } finally {
            releaseConnection();
        }
        return entity;
    }

    @Override public List<Memory> findByCount(int count) throws DaoException {
        List<Memory> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(MemoryQuery.SELECT_BY_COUNT.getValue())) {
            statement.setInt(1, count*9);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                result.add(getValues(set));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error executing query get all category", e);
            throw new DaoException("Error executing query get all category", e);
        } finally {
            releaseConnection();
        }
        return result;
    }

    @Override
    public boolean delete(Long id) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(MemoryQuery.DELETE.getValue())) {
            statement.setLong(1, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.WARN, "", e);
            result = false;
        } finally {
            releaseConnection();
        }
        return result;
    }

    @Override
    public boolean create(Memory entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(MemoryQuery.INSERT.getValue())) {
            fillStatement(statement, entity);
            result = statement.executeUpdate() > 0;
        } catch (SQLException | DaoException e) {
            logger.log(Level.WARN, "", e);
            result = false;
        } finally {
            releaseConnection();
        }
        return result;
    }

    @Override
    public void fillStatement(PreparedStatement statement, Memory entity) throws DaoException {
        try {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSize());
            statement.setString(3, entity.getType().name());
        } catch (SQLException e) {
            logger.error("query has failed", e);
            throw new DaoException("query has failed");
        }
    }

    @Override
    public Memory getValues(ResultSet set) throws DaoException {
        Memory result = new Memory();
        try {
            result.setId(set.getLong(ColumnName.ID));
            result.setName(set.getString(ColumnName.NAME));
            result.setType(MemoryType.valueOf(set.getString(ColumnName.MEMORY_TYPE)));
            result.setSize(set.getString(ColumnName.MEMORY_SIZE));
        } catch (SQLException e) {
            logger.error("query has failed", e);
            throw new DaoException("query has failed");
        }
        return result;
    }
}
