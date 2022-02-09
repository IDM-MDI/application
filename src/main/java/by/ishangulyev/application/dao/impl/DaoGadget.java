package by.ishangulyev.application.dao.impl;

import by.ishangulyev.application.dao.ColumnName;
import by.ishangulyev.application.dao.DaoEntity;
import by.ishangulyev.application.dao.query.*;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.model.entity.impl.*;
import by.ishangulyev.application.exception.DataBaseException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoGadget extends DaoEntity<Long,Gadget> {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Gadget> findAll() throws DaoException {
        List<Gadget> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(GadgetQuery.SELECT_ALL.getValue())) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                result.add(getValues(set));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error executing query get all gadget", e);
            throw new DaoException("Error executing query get all gadget", e);
        } finally {
            releaseConnection();
        }
        return result;
    }
    @Override
    public List<Gadget> findByCount(int count) throws DaoException {
        count*=9;
        List<Gadget> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GadgetQuery.SELECT_BY_COUNT.getValue())) {
            statement.setInt(1, count);
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
    public boolean update(Gadget entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(GadgetQuery.UPDATE.getValue())) {
            fillStatement(statement, entity);
            statement.setLong(12,entity.getId());
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
    public Optional<Gadget> findEntityById(Long id) throws DaoException {
        Optional<Gadget> entity = Optional.empty();

        try (PreparedStatement statement = connection.prepareStatement(GadgetQuery.SELECT_BY_ID.getValue())) {
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

    public List<Gadget> findAllByOrder(List<Order> orders) throws DaoException {
        List<Gadget> result = new ArrayList<>();
        try {
            for (int i = 0; i < orders.size(); i++) {
                try (PreparedStatement statement = connection.prepareStatement(GadgetQuery.SELECT_BY_ID.getValue())) {
                    statement.setLong(1,orders.get(i).getGadgetID());
                    ResultSet set = statement.executeQuery();
                    if (set.next()) {
                        result.add(getValues(set));
                    }
                }
            }
        }
         catch (SQLException e) {
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
        try (PreparedStatement statement = connection.prepareStatement(GadgetQuery.DELETE.getValue())) {
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


    public void findAllForeign(List<Audio> audioList, List<Battery> batteryList, List<Category> categoryList, List<Cpu> cpus, List<Memory> memoryList, List<Video> videoList) throws DaoException {
        try{
            try (PreparedStatement statement = connection.prepareStatement(AudioQuery.SELECT_ID_NAME.getValue())) {
            ResultSet set = statement.executeQuery();
                while (set.next()) {
                Audio audio = new Audio();
                audio.setId(set.getLong(ColumnName.ID));
                audio.setName(set.getString(ColumnName.NAME));
                audioList.add(audio);
            }
        }
            try (PreparedStatement statement = connection.prepareStatement(BatteryQuery.SELECT_ID_NAME.getValue())) {
                ResultSet set = statement.executeQuery();
                while (set.next()) {
                    Battery battery = new Battery();
                    battery.setId(set.getLong(ColumnName.ID));
                    battery.setName(set.getString(ColumnName.NAME));
                    batteryList.add(battery);
                }
            }
            try (PreparedStatement statement = connection.prepareStatement(CategoryQuery.SELECT_ID_NAME.getValue())) {
                ResultSet set = statement.executeQuery();
                while (set.next()) {
                    Category category = new Category();
                    category.setId(set.getLong(ColumnName.ID));
                    category.setName(set.getString(ColumnName.NAME));
                    categoryList.add(category);
                }
            }
            try (PreparedStatement statement = connection.prepareStatement(CpuQuery.SELECT_ID_NAME.getValue())) {
                ResultSet set = statement.executeQuery();
                while (set.next()) {
                    Cpu cpu = new Cpu();
                    cpu.setId(set.getLong(ColumnName.ID));
                    cpu.setName(set.getString(ColumnName.NAME));
                    cpus.add(cpu);
                }
            }
            try (PreparedStatement statement = connection.prepareStatement(MemoryQuery.SELECT_ID_NAME.getValue())) {
                ResultSet set = statement.executeQuery();
                while (set.next()) {
                    Memory memory = new Memory();
                    memory.setId(set.getLong(ColumnName.ID));
                    memory.setName(set.getString(ColumnName.NAME));
                    memoryList.add(memory);
                }
            }
            try (PreparedStatement statement = connection.prepareStatement(VideoQuery.SELECT_ID_NAME.getValue())) {
                ResultSet set = statement.executeQuery();
                while (set.next()) {
                    Video video = new Video();
                    video.setId(set.getLong(ColumnName.ID));
                    video.setName(set.getString(ColumnName.NAME));
                    videoList.add(video);
                }
            }
        } catch (SQLException e) {
            logger.error("query has failed", e);
            throw new DaoException("query has failed");
        } finally {
            releaseConnection();
        }
    }

    @Override
    public boolean create(Gadget entity) {
        boolean result = true;
        try (PreparedStatement statement = connection.prepareStatement(GadgetQuery.INSERT.getValue())) {
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
    public void fillStatement(PreparedStatement statement, Gadget entity) throws DaoException {
        try {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSmallDescription());
            statement.setString(3, entity.getBigDescription());
            statement.setBlob(4, new ByteArrayInputStream(entity.getMainPhoto()));
            statement.setLong(5, entity.getCpyID());
            statement.setLong(6, entity.getMemoryID());
            statement.setBigDecimal(7, entity.getPrice());
            statement.setLong(8, entity.getBatteryID());
            statement.setLong(9, entity.getVideoID());
            statement.setLong(10, entity.getAudioID());
            statement.setLong(11, entity.getCategoryID());
        } catch (SQLException e) {
            logger.error("query has failed", e);
            throw new DaoException("query has failed");
        }
    }

    @Override
    public Gadget getValues(ResultSet set) throws DaoException {
        Gadget result = new Gadget();
        try {
            result.setId(set.getLong(ColumnName.ID));
            result.setName(set.getString(ColumnName.NAME));
            result.setSmallDescription(set.getString(ColumnName.GADGET_SMALL_DESCRIPTION));
            result.setBigDescription(set.getString(ColumnName.GADGET_BIG_DESCRIPTION));
            result.setMainPhoto(set.getBytes(ColumnName.GADGET_PHOTO));
            result.setPrice(set.getBigDecimal(ColumnName.GADGET_PRICE));
            result.setCpuName(set.getString(7));
            result.setVideoName(set.getString(10));
            result.setMemoryName(set.getString(8));
            result.setBatteryName(set.getString(9));
            result.setAudioName(set.getString(11));
            result.setCategoryName(set.getString(12));
            result.setPhotoToString();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error executing query get all category", e);
            throw new DaoException("Error executing query get all category", e);
        }
        return result;
    }

}
