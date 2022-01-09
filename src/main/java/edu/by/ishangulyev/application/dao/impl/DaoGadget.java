package edu.by.ishangulyev.application.dao.impl;

import edu.by.ishangulyev.application.dao.DaoEntity;
import edu.by.ishangulyev.application.model.entity.impl.Gadget;

import java.util.List;

public class DaoGadget extends DaoEntity<Gadget>
{
    @Override
    public List<Gadget> getAll()
    {
        return null;
    }

    @Override
    public Gadget update(Gadget entity)
    {
        return null;
    }

    @Override
    public Gadget getEntityById(long id)
    {
        return null;
    }

    @Override
    public boolean delete(long id)
    {
        return false;
    }

    @Override
    public boolean create(Gadget entity)
    {
        return false;
    }
}
