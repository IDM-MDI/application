package by.ishangulyev.application.model.entity.impl;

import by.ishangulyev.application.model.entity.Entity;

import java.util.Objects;

public class Audio extends Entity {
    private String name;
    private AudioType type;
    private int frequency;

    public AudioType getType() {
        return type;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getName() {
        return name;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(AudioType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Audio{").
                append("id=").append(id).
                append(", name='").append(name).
                append(", type=").append(type).
                append(", frequency=").append(frequency).
                append("}");

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Audio audio = (Audio) o;
        return frequency == audio.frequency && name.equals(audio.name) && type == audio.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, frequency);
    }
}
