package edu.by.ishangulyev.application.dao.impl;

import edu.by.ishangulyev.application.dao.DaoEntity;
import edu.by.ishangulyev.application.model.entity.impl.Gadget;
import edu.by.ishangulyev.application.model.entity.impl.User;

import java.util.List;

public class DaoUser  extends DaoEntity<User>
{
    @Override
    public List<User> getAll()
    {
        return null;
    }

    @Override
    public User update(User entity)
    {
        return null;
    }

    @Override
    public User getEntityById(long id)
    {
        return null;
    }

    @Override
    public boolean delete(long id)
    {
        return false;
    }

    @Override
    public boolean create(User entity)
    {
        return false;
    }
}
