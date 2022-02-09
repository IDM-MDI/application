package by.ishangulyev.application.dao.impl;

import by.ishangulyev.application.dao.ColumnName;
import by.ishangulyev.application.dao.DaoEntity;
import by.ishangulyev.application.dao.query.UserQuery;
import by.ishangulyev.application.dao.query.VideoQuery;
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

public class DaoVideo extends DaoEntity<Long,Video> {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Video> findAll() throws DaoException {
        List<Video> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(VideoQuery.SELECT_ALL.getValue())) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                result.add(getValues(set));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error executing query get all video", e);
            throw new DaoException("Error executing query get all video", e);
        } finally {
            releaseConnection();
        }
        return result;
    }

    @Override
    public boolean update(Video entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(VideoQuery.UPDATE.getValue())) {
            fillStatement(statement, entity);
            statement.setLong(6,entity.getId());
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
    public Optional<Video> findEntityById(Long id) throws DaoException {
        Optional<Video> entity = Optional.empty();

        try (PreparedStatement statement = connection.prepareStatement(VideoQuery.SELECT_BY_ID.getValue())) {
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

    @Override public List<Video> findByCount(int count) throws DaoException {
        List<Video> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(VideoQuery.SELECT_BY_COUNT.getValue())) {
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
        try (PreparedStatement statement = connection.prepareStatement(VideoQuery.DELETE.getValue())) {
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
    public boolean create(Video entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(VideoQuery.INSERT.getValue())) {
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
    public void fillStatement(PreparedStatement statement, Video entity) throws DaoException {
        try {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getResolution());
            statement.setString(3, entity.getRatio());
            statement.setInt(4, entity.getBrightness());
            statement.setString(5, entity.getType().name());
        } catch (SQLException e) {
            logger.error("query has failed", e);
            throw new DaoException("query has failed");
        }
    }

    @Override
    public Video getValues(ResultSet set) throws DaoException {
        Video result = new Video();
        try {
            result.setId(set.getLong(ColumnName.ID));
            result.setName(set.getString(ColumnName.NAME));
            result.setType(VideoType.valueOf(set.getString(ColumnName.VIDEO_TYPE)));
            result.setResolution(set.getString(ColumnName.VIDEO_RESOLUTION));
            result.setRatio(set.getString(ColumnName.VIDEO_RATIO));
            result.setBrightness(set.getInt(ColumnName.VIDEO_BRIGHTNESS));
        } catch (SQLException e) {
            logger.error("query has failed", e);
            throw new DaoException("query has failed");
        }
        return result;
    }
}
