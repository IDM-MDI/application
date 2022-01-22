package by.ishangulyev.application.dao.impl;

import by.ishangulyev.application.dao.ColumnName;
import by.ishangulyev.application.dao.DaoEntity;
import by.ishangulyev.application.dao.query.VideoQuery;
import by.ishangulyev.application.exception.DataBaseException;
import by.ishangulyev.application.model.entity.impl.Audio;
import by.ishangulyev.application.model.entity.impl.AudioType;
import by.ishangulyev.application.model.entity.impl.Video;
import by.ishangulyev.application.model.entity.impl.VideoType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoVideo extends DaoEntity<Video> {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Video> findAll() throws DataBaseException {
        List<Video> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(VideoQuery.SELECT_ALL.getValue())) {
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
    public boolean update(Video entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(VideoQuery.UPDATE.getValue())) {
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
    public Optional<Video> getEntityById(long id) throws DataBaseException {
        Optional<Video> entity = Optional.empty();

        try (PreparedStatement statement = connection.prepareStatement(VideoQuery.SELECT_BY_ID.getValue())) {
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
    public boolean delete(long id) {
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
        } catch (SQLException e) {
            logger.log(Level.WARN, "", e);
            result = false;
        } finally {
            releaseConnection();
        }
        return result;
    }

    @Override
    public void fillStatement(PreparedStatement statement, Video entity) throws SQLException {

    }

    @Override
    public Video getValues(ResultSet set) throws SQLException {
        Video result = new Video();
        result.setId(set.getLong(ColumnName.ID));
        result.setName(set.getString(ColumnName.NAME));
        result.setType(VideoType.valueOf(set.getString(ColumnName.VIDEO_TYPE)));
        result.setResolution(set.getString(ColumnName.VIDEO_RESOLUTION));
        result.setRatio(set.getString(ColumnName.VIDEO_RATIO));
        result.setBrightness(set.getInt(ColumnName.VIDEO_BRIGHTNESS));
        return result;
    }
}
