package by.ishangulyev.application.dao.impl;

import by.ishangulyev.application.dao.ColumnName;
import by.ishangulyev.application.dao.DaoEntity;
import by.ishangulyev.application.dao.query.GadgetQuery;
import by.ishangulyev.application.model.entity.impl.Audio;
import by.ishangulyev.application.model.entity.impl.AudioType;
import by.ishangulyev.application.model.entity.impl.Gadget;
import by.ishangulyev.application.exception.DataBaseException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoGadget extends DaoEntity<Long,Gadget> {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Gadget> findAll() throws DataBaseException {
        List<Gadget> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(GadgetQuery.SELECT_ALL.getValue())) {
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
    public List<Gadget> findByCount(int count) throws DataBaseException {
        count*=9;
        List<Gadget> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GadgetQuery.SELECT_BY_COUNT.getValue())) {
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
    public boolean update(Gadget entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(GadgetQuery.UPDATE.getValue())) {
            fillStatement(statement, entity);
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
    public Optional<Gadget> findEntityById(Long id) throws DataBaseException {
        Optional<Gadget> entity = Optional.empty();

        try (PreparedStatement statement = connection.prepareStatement(GadgetQuery.SELECT_BY_ID.getValue())) {
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
        try (PreparedStatement statement = connection.prepareStatement(GadgetQuery.DELETE.getValue())) {
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
    public boolean create(Gadget entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(GadgetQuery.INSERT.getValue())) {
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
    public void fillStatement(PreparedStatement statement, Gadget entity) throws SQLException {

    }

    @Override
    public Gadget getValues(ResultSet set) throws SQLException {
        Gadget result = new Gadget();
        result.setId(set.getLong(ColumnName.ID));
        result.setName(set.getString(ColumnName.NAME));
        result.setSmallDescription(set.getString(ColumnName.GADGET_SMALL_DESCRIPTION));
        result.setBigDescription(set.getString(ColumnName.GADGET_BIG_DESCRIPTION));
        result.setMainPhoto(set.getBytes(ColumnName.GADGET_PHOTO));
        result.setPrice(set.getBigDecimal(ColumnName.GADGET_PRICE));
        result.setAudioID(set.getLong(ColumnName.GADGET_AUDIO_ID));
        result.setVideoID(set.getLong(ColumnName.GADGET_VIDEO_ID));
        result.setMemoryID(set.getLong(ColumnName.GADGET_MEMORY_ID));
        result.setBatteryID(set.getLong(ColumnName.GADGET_BATTERY_ID));
        result.setCpyID(set.getLong(ColumnName.GADGET_CPU_ID));
        result.setCategoryID(set.getLong(ColumnName.GADGET_CATEGORY_ID));
        return result;
    }
}
