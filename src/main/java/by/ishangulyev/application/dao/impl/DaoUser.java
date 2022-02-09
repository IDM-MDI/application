package by.ishangulyev.application.dao.impl;

import by.ishangulyev.application.dao.ColumnName;
import by.ishangulyev.application.dao.DaoEntity;
import by.ishangulyev.application.dao.query.UserQuery;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.model.entity.impl.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoUser extends DaoEntity<String,User> {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<User> findAll() throws DaoException {
        List<User> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(UserQuery.SELECT_ALL.getValue())) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                result.add(getValues(set));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error executing query get all user", e);
            throw new DaoException("Error executing query get all user", e);
        } finally {
            releaseConnection();
        }
        return result;
    }

    @Override
    public boolean update(User entity) {
        boolean result = true;
        try{
            if(entity.getRole() != null){
                updateRole(entity);
            }
            if(!entity.getName().isEmpty()){
                updateUsername(entity);
            }
            if(entity.getPhoto() != null){
                updatePhoto(entity);
            }
            if(entity.getPass().isEmpty()){
                updatePassword(entity);
            }
        } catch (SQLException e) {
            result = false;
            logger.log(Level.WARN, "Error while updating dao", e);
        } finally {
            releaseConnection();
        }
        return result;
    }

    @Override
    public Optional<User> findEntityById(String email) throws DaoException {
        Optional<User> entity = Optional.empty();

        try (PreparedStatement statement = connection.prepareStatement(UserQuery.SELECT_BY_ID.getValue())) {
            statement.setString(1, email);
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

    @Override public List<User> findByCount(int count) throws DaoException {
        List<User> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(UserQuery.SELECT_BY_COUNT.getValue())) {
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
            entity.setDate(new Date(System.currentTimeMillis()));
            if(entity.getRole() == null){
                entity.setRole(Role.USER);
            }
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
    public void fillStatement(PreparedStatement statement, User entity) throws DaoException {
        try {
            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getPass());
            statement.setDate(3, entity.getDate());
            statement.setString(4,entity.getRole().name());
        } catch (SQLException e) {
            logger.error("query has failed", e);
            throw new DaoException("query has failed");
        }
    }

    @Override
    public User getValues(ResultSet set) throws DaoException {
        User result = new User();
        try {
            result.setName(set.getString(ColumnName.USER_USERNAME));
            result.setDate(set.getDate(ColumnName.USER_TIME));
            result.setEmail(set.getString(ColumnName.USER_EMAIL));
            result.setPass(set.getString(ColumnName.USER_PASS));
            result.setRole(Role.valueOf(set.getString(ColumnName.USER_ROLE)));
            result.setPhoto(set.getBytes(ColumnName.USER_PHOTO));
            result.setPhotoToString();
        } catch (SQLException e) {
            logger.error("query has failed", e);
            throw new DaoException("query has failed");
        }
        return result;
    }

    private void updatePhoto(User entity) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UserQuery.UPDATE_PHOTO.getValue())) {
            statement.setBlob(1,new ByteArrayInputStream(entity.getPhoto()));
            statement.setString(2, entity.getEmail());
            statement.executeUpdate();
        }
    }
    private void updateUsername(User entity) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(UserQuery.UPDATE_USERNAME.getValue())) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getEmail());
            statement.executeUpdate();
        }
    }
    private void updateRole(User entity) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(UserQuery.UPDATE_ROLE.getValue())) {
            statement.setString(1, entity.getRole().name());
            statement.setString(2, entity.getEmail());
            statement.executeUpdate();
        }
    }
    private boolean updatePassword(User entity) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement(UserQuery.UPDATE_PASSWORD.getValue())) {
            statement.setString(1, entity.getPass());
            statement.setString(2, entity.getEmail());
            return statement.executeUpdate() > 0;
        }
    }
}
