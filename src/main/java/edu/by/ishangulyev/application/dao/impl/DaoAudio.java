package edu.by.ishangulyev.application.dao.impl;

import edu.by.ishangulyev.application.dao.ColumnName;
import edu.by.ishangulyev.application.dao.DaoEntity;
import edu.by.ishangulyev.application.dao.ResultSetExecutor;
import edu.by.ishangulyev.application.dao.query.AudioQuery;
import edu.by.ishangulyev.application.dao.query.CategoryQuery;
import edu.by.ishangulyev.application.exception.DataBaseException;
import edu.by.ishangulyev.application.model.entity.impl.Audio;
import edu.by.ishangulyev.application.model.entity.impl.Category;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoAudio extends DaoEntity<Audio> implements ResultSetExecutor<Audio>
{
    private static final Logger logger = LogManager.getLogger();
    public DaoAudio()
    {
        super();
    }

    @Override
    public List<Audio> getAll() throws DataBaseException
    {
        List<Audio> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(AudioQuery.SELECT_ALL.toString()))
        {
            ResultSet set = statement.executeQuery();
            while(set.next())
            {
                result.add(execute(set));
            }
        }
        catch (SQLException e)
        {
            logger.log(Level.ERROR,"Error executing query get all category", e);
            throw new DataBaseException("Error executing query get all category", e);
        }
        finally
        {
            releaseConnection();
        }
        return result;
    }

    @Override
    public boolean update(Audio entity)
    {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(AudioQuery.UPDATE.toString()))
        {
            fillStatement(statement,entity);
            result = statement.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            logger.log(Level.WARN,"Error while updating dao",e);
            result = false;
        }
        finally
        {
            releaseConnection();
        }
        return result;
    }

    @Override
    public Optional<Audio> getEntityById(long id) throws DataBaseException
    {
        Optional<Audio> entity = Optional.empty();

        try(PreparedStatement statement = connection.prepareStatement(AudioQuery.SELECT_BY_ID.toString()))
        {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();

            if (set.next())
            {
                entity = Optional.of(execute(set));
            }
        } catch (SQLException e) {
            logger.error("query has failed", e);
            throw new DataBaseException("query has failed");
        }
        finally
        {
            releaseConnection();
        }
        return entity;
    }

    @Override
    public boolean delete(long id)
    {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(AudioQuery.DELETE.toString()))
        {
            statement.setLong(1,id);
            result = statement.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            logger.log(Level.WARN,"",e);
            result = false;
        }
        finally
        {
            releaseConnection();
        }
        return result;
    }

    @Override
    public boolean create(Audio entity)
    {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(AudioQuery.INSERT.toString()))
        {
            fillStatement(statement,entity);
            result = statement.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            logger.log(Level.WARN,"",e);
            result = false;
        }
        finally
        {
            releaseConnection();
        }
        return result;
    }

    @Override
    public void fillStatement(PreparedStatement statement, Audio entity) throws SQLException
    {

    }

    @Override
    public Audio execute(ResultSet set) throws SQLException
    {
        return null;
    }
}
