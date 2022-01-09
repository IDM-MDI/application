package edu.by.ishangulyev.application.model.entity.impl;

import edu.by.ishangulyev.application.model.entity.Entity;

public class Battery extends Entity
{
    private String name;
    private int mah;

    public String getName()
    {
        return name;
    }

    public int getMah()
    {
        return mah;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setMah(int mah)
    {
        this.mah = mah;
    }
}
