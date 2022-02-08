package by.ishangulyev.application.dao.impl;

import by.ishangulyev.application.dao.ColumnName;
import by.ishangulyev.application.dao.DaoEntity;
import by.ishangulyev.application.dao.query.CartQuery;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.exception.DataBaseException;
import by.ishangulyev.application.model.entity.impl.Audio;
import by.ishangulyev.application.model.entity.impl.AudioType;
import by.ishangulyev.application.model.entity.impl.Cart;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoCart extends DaoEntity<Long,Cart> {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Cart> findAll() throws DaoException {
        List<Cart> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(CartQuery.SELECT_ALL.getValue())) {
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
    public boolean update(Cart entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(CartQuery.UPDATE.getValue())) {
            fillStatement(statement, entity);
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
    public Optional<Cart> findEntityById(Long id) throws DaoException {
        Optional<Cart> entity = Optional.empty();

        try (PreparedStatement statement = connection.prepareStatement(CartQuery.SELECT_BY_ID.getValue())) {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                entity = Optional.of(getValues(set));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error executing query get all category", e);
            throw new DaoException("Error executing query get all category", e);
        } finally {
            releaseConnection();
        }
        return entity;
    }

    @Override public List<Cart> findByCount(int count) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(CartQuery.DELETE.getValue())) {
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
    public boolean deleteByEmail(String email) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(CartQuery.DELETE_BY_EMAIL.getValue())) {
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
    public boolean create(Cart entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(CartQuery.INSERT.getValue())) {
            statement.setString(1, entity.getUserID());
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
    public void fillStatement(PreparedStatement statement, Cart entity) throws DaoException {
        try {
            statement.setString(1, entity.getUserID());
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error executing query get all category", e);
            throw new DaoException("Error executing query get all category", e);
        }
    }

    @Override
    public Cart getValues(ResultSet set) throws DaoException {
        Cart result = new Cart();
        try {
            result.setId(set.getLong(ColumnName.ID));
            result.setUserID(set.getString(ColumnName.CART_USERID));
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error executing query get all category", e);
            throw new DaoException("Error executing query get all category", e);
        }
        return result;
    }
}
