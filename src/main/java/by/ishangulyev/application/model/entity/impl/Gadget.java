package by.ishangulyev.application.model.entity.impl;

import by.ishangulyev.application.model.entity.Entity;

import java.math.BigDecimal;

public class Gadget extends Entity {
    private String name;

    private String smallDescription;
    private String bigDescription;

    private byte[] photo;

    private BigDecimal price;

    private long cpyID;
    private long categoryID;
    private long batteryID;
    private long memoryID;
    private long videoID;
    private long audioID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSmallDescription() {
        return smallDescription;
    }

    public void setSmallDescription(String smallDescription) {
        this.smallDescription = smallDescription;
    }

    public String getBigDescription() {
        return bigDescription;
    }

    public void setBigDescription(String bigDescription) {
        this.bigDescription = bigDescription;
    }

    public byte[] getMainPhoto() {
        return photo;
    }

    public void setMainPhoto(byte[] mainPhoto) {
        this.photo = mainPhoto;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getCpyID() {
        return cpyID;
    }

    public void setCpyID(long cpyID) {
        this.cpyID = cpyID;
    }

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }

    public long getBatteryID() {
        return batteryID;
    }

    public void setBatteryID(long batteryID) {
        this.batteryID = batteryID;
    }

    public long getMemoryID() {
        return memoryID;
    }

    public void setMemoryID(long memoryID) {
        this.memoryID = memoryID;
    }

    public long getVideoID() {
        return videoID;
    }

    public void setVideoID(long videoID) {
        this.videoID = videoID;
    }

    public long getAudioID() {
        return audioID;
    }

    public void setAudioID(long audioID) {
        this.audioID = audioID;
    }
}
