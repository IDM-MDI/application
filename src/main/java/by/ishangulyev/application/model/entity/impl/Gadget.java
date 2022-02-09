package by.ishangulyev.application.model.entity.impl;

import by.ishangulyev.application.model.entity.Entity;

import java.math.BigDecimal;
import java.util.Base64;

public class Gadget extends Entity {
    private String name;

    private String smallDescription;
    private String bigDescription;

    private byte[] photo;
    private String photoToString;

    private BigDecimal price;

    private long cpuID;
    private long categoryID;
    private long batteryID;
    private long memoryID;
    private long videoID;
    private long audioID;

    private String cpuName;
    private String categoryName;
    private String batteryName;
    private String memoryName;
    private String videoName;
    private String audioName;

    public String getPhotoToString() {
        return photoToString;
    }

    public void setPhotoToString() {
        if(photo != null){
            photoToString = Base64.getEncoder().encodeToString(photo);
        }
    }

    public String getCpuName() {
        return cpuName;
    }

    public void setCpuName(String cpuName) {
        this.cpuName = cpuName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBatteryName() {
        return batteryName;
    }

    public void setBatteryName(String batteryName) {
        this.batteryName = batteryName;
    }

    public String getMemoryName() {
        return memoryName;
    }

    public void setMemoryName(String memoryName) {
        this.memoryName = memoryName;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

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
        return cpuID;
    }

    public void setCpyID(long cpuID) {
        this.cpuID = cpuID;
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
