package by.ishangulyev.application.dao.impl;

import by.ishangulyev.application.dao.ColumnName;
import by.ishangulyev.application.dao.DaoEntity;
import by.ishangulyev.application.dao.query.OrderQuery;
import by.ishangulyev.application.exception.DataBaseException;
import by.ishangulyev.application.model.entity.impl.Audio;
import by.ishangulyev.application.model.entity.impl.AudioType;
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
    public List<Order> findAll() throws DataBaseException {
        List<Order> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(OrderQuery.SELECT_ALL.getValue())) {
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
    public boolean update(Order entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(OrderQuery.UPDATE.getValue())) {
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
    public Optional<Order> findEntityById(Long id) throws DataBaseException {
        Optional<Order> entity = Optional.empty();

        try (PreparedStatement statement = connection.prepareStatement(OrderQuery.SELECT_BY_ID.getValue())) {
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

    @Override
    public boolean create(Order entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(OrderQuery.INSERT.getValue())) {
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
    public void fillStatement(PreparedStatement statement, Order entity) throws SQLException {

    }

    @Override
    public Order getValues(ResultSet set) throws SQLException {
        Order result = new Order();
        result.setId(set.getLong(ColumnName.ID));
        result.setCartID(set.getLong(ColumnName.ORDER_CART_ID));
        result.setGadgetID(set.getLong(ColumnName.ORDER_GADGET_ID));
        return result;
    }
}
