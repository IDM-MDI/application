package by.ishangulyev.application.dao.impl;

import by.ishangulyev.application.dao.ColumnName;
import by.ishangulyev.application.dao.DaoEntity;
import by.ishangulyev.application.dao.query.AudioQuery;
import by.ishangulyev.application.exception.DataBaseException;
import by.ishangulyev.application.model.entity.impl.Audio;
import by.ishangulyev.application.model.entity.impl.AudioType;
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
    public List<Audio> findAll() throws DataBaseException {
        List<Audio> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(AudioQuery.SELECT_ALL.getValue())) {
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
    public boolean update(Audio entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(AudioQuery.UPDATE.getValue())) {
            fillStatement(statement, entity);
            statement.setLong(4, entity.getId());
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
    public Optional<Audio> getEntityById(Long id) throws DataBaseException {
        Optional<Audio> entity = Optional.empty();

        try (PreparedStatement statement = connection.prepareStatement(AudioQuery.SELECT_BY_ID.getValue())) {
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
        try (PreparedStatement statement = connection.prepareStatement(AudioQuery.DELETE.getValue())) {
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
    public boolean create(Audio entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(AudioQuery.INSERT.getValue())) {
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
    public void fillStatement(PreparedStatement statement, Audio entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getType().name());
        statement.setInt(3, entity.getFrequency());
    }

    @Override
    public Audio getValues(ResultSet set) throws SQLException {
        Audio result = new Audio();
        AudioType type = AudioType.valueOf(set.getString(ColumnName.AUDIO_TYPE));
        result.setId(set.getLong(ColumnName.ID));
        result.setName(set.getString(ColumnName.NAME));
        result.setType(type);
        result.setFrequency(set.getInt(ColumnName.AUDIO_FREQUENCY));
        return result;
    }
}