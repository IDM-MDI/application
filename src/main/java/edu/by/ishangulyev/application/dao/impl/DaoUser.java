package edu.by.ishangulyev.application.dao.impl;

import edu.by.ishangulyev.application.dao.DaoEntity;
import edu.by.ishangulyev.application.dao.query.UserQuery;
import edu.by.ishangulyev.application.exception.DataBaseException;
import edu.by.ishangulyev.application.model.entity.impl.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoUser  extends DaoEntity<User>
{
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<User> getAll() throws DataBaseException
    {
        List<User> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(UserQuery.SELECT_ALL.toString()))
        {
            ResultSet set = statement.executeQuery();
            while(set.next())
            {
                result.add(getValues(set));
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
    public boolean update(User entity)
    {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(UserQuery.UPDATE.toString()))
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
    public Optional<User> getEntityById(long id) throws DataBaseException
    {
        Optional<User> entity = Optional.empty();

        try(PreparedStatement statement = connection.prepareStatement(UserQuery.SELECT_BY_ID.toString()))
        {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();

            if (set.next())
            {
                entity = Optional.of(getValues(set));
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
        try (PreparedStatement statement = connection.prepareStatement(UserQuery.DELETE.toString()))
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
    public boolean create(User entity)
    {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(UserQuery.INSERT.toString()))
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
    public void fillStatement(PreparedStatement statement, User entity) throws SQLException
    {

    }

    @Override
    public User getValues(ResultSet set) throws SQLException
    {
        return null;
    }
}
