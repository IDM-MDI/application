package by.ishangulyev.application.model.entity.impl;

import by.ishangulyev.application.model.entity.Entity;

public class Memory extends Entity {
    private String name;
    private String size;
    private MemoryType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public MemoryType getType() {
        return type;
    }

    public void setType(MemoryType type) {
        this.type = type;
    }
}
