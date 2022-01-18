package edu.by.ishangulyev.application.dao.impl;

import edu.by.ishangulyev.application.dao.DaoEntity;
import edu.by.ishangulyev.application.dao.ResultSetExecutor;
import edu.by.ishangulyev.application.dao.query.BatteryQuery;
import edu.by.ishangulyev.application.dao.query.CategoryQuery;
import edu.by.ishangulyev.application.exception.DataBaseException;
import edu.by.ishangulyev.application.model.entity.impl.Battery;
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

public class DaoBattery extends DaoEntity<Battery> implements ResultSetExecutor<Battery>
{
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Battery> getAll() throws DataBaseException
    {
        List<Battery> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(BatteryQuery.SELECT_ALL.toString()))
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
    public boolean update(Battery entity)
    {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(BatteryQuery.UPDATE.toString()))
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
    public Optional<Battery> getEntityById(long id) throws DataBaseException
    {
        Optional<Battery> entity = Optional.empty();

        try(PreparedStatement statement = connection.prepareStatement(BatteryQuery.SELECT_BY_ID.toString()))
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
        try (PreparedStatement statement = connection.prepareStatement(BatteryQuery.DELETE.toString()))
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
    public boolean create(Battery entity)
    {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(BatteryQuery.INSERT.toString()))
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
    public void fillStatement(PreparedStatement statement, Battery entity) throws SQLException
    {

    }

    @Override
    public Battery execute(ResultSet set) throws SQLException
    {
        return null;
    }
}
