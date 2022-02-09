package by.ishangulyev.application.dao.impl;

import by.ishangulyev.application.dao.ColumnName;
import by.ishangulyev.application.dao.DaoEntity;
import by.ishangulyev.application.dao.query.OrderQuery;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.model.entity.impl.Order;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoOrder extends DaoEntity<Long,Order> {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(OrderQuery.SELECT_ALL.getValue())) {
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
    public List<Order> findAllByCart(long id) throws DaoException {
        List<Order> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(OrderQuery.SELECT_BY_CART.getValue())) {
            statement.setLong(1,id);
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
    public boolean update(Order entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(OrderQuery.UPDATE.getValue())) {
            fillStatement(statement, entity);
            statement.setLong(3,entity.getId());
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
    public Optional<Order> findEntityById(Long id) throws DaoException {
        Optional<Order> entity = Optional.empty();

        try (PreparedStatement statement = connection.prepareStatement(OrderQuery.SELECT_BY_ID.getValue())) {
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

    @Override public List<Order> findByCount(int count) throws DaoException {
        List<Order> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(OrderQuery.SELECT_BY_COUNT.getValue())) {
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
        try (PreparedStatement statement = connection.prepareStatement(OrderQuery.DELETE.getValue())) {
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
    public boolean deleteByGadget(long gadgetId,long userCart) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(OrderQuery.DELETE_BY_GADGET.getValue())) {
            statement.setLong(1, userCart);
            statement.setLong(2, gadgetId);
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
    public boolean create(Order entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(OrderQuery.INSERT.getValue())) {
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
    public void fillStatement(PreparedStatement statement, Order entity) throws DaoException {
        try {
            statement.setLong(1,entity.getCartID());
            statement.setLong(2,entity.getGadgetID());
        } catch (SQLException e) {
            logger.error("query has failed", e); // TODO: 2/8/2022
            throw new DaoException("query has failed");
        }
    }

    @Override
    public Order getValues(ResultSet set) throws DaoException {
        Order result = new Order();
        try {
            result.setId(set.getLong(ColumnName.ID));
            result.setCartID(set.getLong(ColumnName.ORDER_CART_ID));
            result.setGadgetID(set.getLong(ColumnName.ORDER_GADGET_ID));
        } catch (SQLException e) {
            logger.error("query has failed", e);
            throw new DaoException("query has failed");
        }
        return result;
    }
}
