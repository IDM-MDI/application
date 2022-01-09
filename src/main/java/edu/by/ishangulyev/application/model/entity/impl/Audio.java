package edu.by.ishangulyev.application.model.entity.impl;

import edu.by.ishangulyev.application.model.entity.Entity;

public class Audio extends Entity
{
    private String name;
    private AudioType type;
    private int frequency;

    public AudioType getType()
    {
        return type;
    }

    public int getFrequency()
    {
        return frequency;
    }

    public String getName()
    {
        return name;
    }

    public void setFrequency(int frequency)
    {
        this.frequency = frequency;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setType(AudioType type)
    {
        this.type = type;
    }
}
