package by.ishangulyev.application.dao.impl;

import by.ishangulyev.application.dao.ColumnName;
import by.ishangulyev.application.dao.DaoEntity;
import by.ishangulyev.application.dao.query.UserQuery;
import by.ishangulyev.application.exception.DataBaseException;
import by.ishangulyev.application.model.entity.impl.Audio;
import by.ishangulyev.application.model.entity.impl.AudioType;
import by.ishangulyev.application.model.entity.impl.Role;
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

public class DaoUser extends DaoEntity<String,User> {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<User> findAll() throws DataBaseException {
        List<User> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(UserQuery.SELECT_ALL.getValue())) {
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
    public boolean update(User entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(UserQuery.UPDATE.getValue())) {
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
    public Optional<User> getEntityById(String email) throws DataBaseException {
        Optional<User> entity = Optional.empty();

        try (PreparedStatement statement = connection.prepareStatement(UserQuery.SELECT_BY_ID.getValue())) {
            statement.setString(1, email);
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
    public boolean delete(String email) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(UserQuery.DELETE.getValue())) {
            statement.setString(1, email);
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
    public boolean create(User entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(UserQuery.INSERT.getValue())) {
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
    public void fillStatement(PreparedStatement statement, User entity) throws SQLException {

    }

    @Override
    public User getValues(ResultSet set) throws SQLException {
        User result = new User();
        result.setId(set.getLong(ColumnName.ID));
        result.setName(set.getString(ColumnName.USER_USERNAME));
        result.setDate(set.getDate(ColumnName.USER_TIME));
        result.setEmail(set.getString(ColumnName.USER_EMAIL));
        result.setPass(set.getString(ColumnName.USER_PASS));
        result.setRole(Role.valueOf(set.getString(ColumnName.USER_ROLE)));
        return result;
    }
}
