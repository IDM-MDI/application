package edu.by.ishangulyev.application.dao.impl;

import edu.by.ishangulyev.application.dao.ColumnName;
import edu.by.ishangulyev.application.dao.DaoEntity;
import edu.by.ishangulyev.application.dao.query.CategoryQuery;
import edu.by.ishangulyev.application.exception.DataBaseException;
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

public class DaoCategory extends DaoEntity<Category>
{
    private static final Logger logger = LogManager.getLogger();
    private final ColumnName column = ColumnName.CATEGORY;

    public DaoCategory()
    {
        super();
    }

    @Override
    public List<Category> getAll() throws DataBaseException
    {
        List<Category> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(CategoryQuery.SELECT_ALL.toString()))
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
    public boolean update(Category entity)
    {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(CategoryQuery.UPDATE.toString()))
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
    public Optional<Category> getEntityById(long id) throws DataBaseException
    {
        Optional<Category> entity = Optional.empty();

        try(PreparedStatement statement = connection.prepareStatement(CategoryQuery.SELECT_BY_ID.toString()))
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
        try (PreparedStatement statement = connection.prepareStatement(CategoryQuery.DELETE.toString()))
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
    public boolean create(Category entity)
    {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(CategoryQuery.INSERT.toString()))
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
    public Category getValues(ResultSet set) throws SQLException
    {
        Category result = new Category();
        result.setId(set.getLong(column.getId()));
        result.setName(set.getString(column.getName()));
        return result;
    }

    @Override
    public void fillStatement(PreparedStatement statement, Category entity) throws SQLException
    {
        statement.setLong(1,entity.getId());
        statement.setString(2,entity.getName());
    }
}
