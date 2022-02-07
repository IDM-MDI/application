package by.ishangulyev.application.dao.impl;

import by.ishangulyev.application.dao.ColumnName;
import by.ishangulyev.application.dao.DaoEntity;
import by.ishangulyev.application.dao.query.BatteryQuery;
import by.ishangulyev.application.dao.query.GadgetQuery;
import by.ishangulyev.application.exception.DataBaseException;
import by.ishangulyev.application.model.entity.impl.Audio;
import by.ishangulyev.application.model.entity.impl.AudioType;
import by.ishangulyev.application.model.entity.impl.Battery;
import by.ishangulyev.application.model.entity.impl.Gadget;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoBattery extends DaoEntity<Long,Battery> {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Battery> findAll() throws DataBaseException {
        List<Battery> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(BatteryQuery.SELECT_ALL.getValue())) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                result.add(getValues(set));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error executing query get all category", e);
            throw new DataBaseException("Error executing query get all category", e);
        } finally {
            releaseConnection();
        }
        return result;
    }

    @Override
    public List<Battery> findByCount(int count) throws DataBaseException {
        count*=9;
        List<Battery> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(BatteryQuery.SELECT_BY_COUNT.getValue())) {
            statement.setInt(1, count);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                result.add(getValues(set));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error executing query get all category", e);
            throw new DataBaseException("Error executing query get all category", e);
        } finally {
            releaseConnection();
        }
        return result;
    }

    @Override
    public boolean update(Battery entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(BatteryQuery.UPDATE.getValue())) {
            fillStatement(statement, entity);
            statement.setLong(3,entity.getId());
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.WARN, "Error while updating dao", e);
            result = false;
        } finally {
            releaseConnection();
        }
        return result;
    }

    @Override
    public Optional<Battery> findEntityById(Long id) throws DataBaseException {
        Optional<Battery> entity = Optional.empty();

        try (PreparedStatement statement = connection.prepareStatement(BatteryQuery.SELECT_BY_ID.getValue())) {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                entity = Optional.of(getValues(set));
            }
        } catch (SQLException e) {
            logger.error("query has failed", e);
            throw new DataBaseException("query has failed");
        } finally {
            releaseConnection();
        }
        return entity;
    }

    @Override
    public boolean delete(Long id) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(BatteryQuery.DELETE.getValue())) {
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
    public boolean create(Battery entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(BatteryQuery.INSERT.getValue())) {
            fillStatement(statement, entity);
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
    public void fillStatement(PreparedStatement statement, Battery entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setInt(2, entity.getMah());
    }

    @Override
    public Battery getValues(ResultSet set) throws SQLException {
        Battery result = new Battery();
        result.setId(set.getLong(ColumnName.ID));
        result.setName(set.getString(ColumnName.NAME));
        result.setMah(set.getInt(ColumnName.BATTERY_MAH));
        return result;
    }
}
