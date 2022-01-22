package by.ishangulyev.application.model.entity.impl;

import by.ishangulyev.application.model.entity.Entity;

public class Cpu extends Entity {
    private String name;
    private String core;
    private String frequency;
    private String bit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getBit() {
        return bit;
    }

    public void setBit(String bit) {
        this.bit = bit;
    }
}
