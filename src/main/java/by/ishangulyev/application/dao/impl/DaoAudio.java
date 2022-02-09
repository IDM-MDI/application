package by.ishangulyev.application.dao.impl;

import by.ishangulyev.application.dao.ColumnName;
import by.ishangulyev.application.dao.DaoEntity;
import by.ishangulyev.application.dao.query.AudioQuery;
import by.ishangulyev.application.dao.query.UserQuery;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.exception.DataBaseException;
import by.ishangulyev.application.model.entity.impl.Audio;
import by.ishangulyev.application.model.entity.impl.AudioType;
import by.ishangulyev.application.model.entity.impl.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoAudio extends DaoEntity<Long,Audio> {
    private static final Logger logger = LogManager.getLogger();

    public DaoAudio() {
        super();
    }

    @Override
    public List<Audio> findAll() throws DaoException {
        List<Audio> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(AudioQuery.SELECT_ALL.getValue())) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                result.add(getValues(set));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error executing query get all Audio", e);
            throw new DaoException("Error executing query get all Audio", e);
        } finally {
            releaseConnection();
        }
        return result;
    }

    @Override
    public boolean update(Audio entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(AudioQuery.UPDATE.getValue())) {
            fillStatement(statement, entity);
            statement.setLong(4, entity.getId());
            result = statement.executeUpdate() > 0;
        } catch (SQLException | DaoException e) {
            logger.log(Level.WARN, "Error executing query update Audio", e);
            result = false;
        } finally {
            releaseConnection();
        }
        return result;
    }

    @Override
    public Optional<Audio> findEntityById(Long id) throws DaoException {
        Optional<Audio> entity = Optional.empty();

        try (PreparedStatement statement = connection.prepareStatement(AudioQuery.SELECT_BY_ID.getValue())) {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                entity = Optional.of(getValues(set));
            }
        } catch (SQLException e) {
            logger.error("Error executing query findEntityByID Audio", e);
            throw new DaoException("Error executing query findEntityByID Audio");
        } finally {
            releaseConnection();
        }
        return entity;
    }

    @Override public List<Audio> findByCount(int count) throws DaoException {
        List<Audio> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(AudioQuery.SELECT_BY_COUNT.getValue())) {
            statement.setInt(1, count*9);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                result.add(getValues(set));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error executing query findEntityByCount Audio", e);
            throw new DaoException("Error executing query findEntityByCount Audio", e);
        } finally {
            releaseConnection();
        }
        return result;
    }

    @Override
    public boolean delete(Long id) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(AudioQuery.DELETE.getValue())) {
            statement.setLong(1, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.WARN, "Error executing query delete Audio", e);
            result = false;
        } finally {
            releaseConnection();
        }
        return result;
    }

    @Override
    public boolean create(Audio entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(AudioQuery.INSERT.getValue())) {
            fillStatement(statement, entity);
            result = statement.executeUpdate() > 0;
        } catch (DaoException | SQLException e) {
            logger.log(Level.WARN, "Error executing query create Audio", e);
            result = false;
        } finally {
            releaseConnection();
        }
        return result;
    }

    @Override
    public void fillStatement(PreparedStatement statement, Audio entity) throws DaoException {
        try {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getType().name());
            statement.setInt(3, entity.getFrequency());
        } catch (SQLException e) {
            logger.log(Level.ERROR,"Error executing query fill Statement Audio");
            throw new DaoException("",e);
        }
    }

    @Override
    public Audio getValues(ResultSet set) throws DaoException {
        Audio result = new Audio();
        AudioType type = null;
        try {
            type = AudioType.valueOf(set.getString(ColumnName.AUDIO_TYPE));
            result.setId(set.getLong(ColumnName.ID));
            result.setName(set.getString(ColumnName.NAME));
            result.setType(type);
            result.setFrequency(set.getInt(ColumnName.AUDIO_FREQUENCY));
        } catch (SQLException e) {
            logger.log(Level.ERROR,"Error executing query get Values from Result set Audio");
            throw new DaoException("Error executing query get Values from Result set Audio",e);
        }
        return result;
    }
}
