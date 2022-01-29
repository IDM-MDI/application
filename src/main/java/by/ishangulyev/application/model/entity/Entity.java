package by.ishangulyev.application.model.entity;

public abstract class Entity {
    protected Long id;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
