package by.ishangulyev.application.model.entity.impl;

import by.ishangulyev.application.model.entity.Entity;

import java.sql.Date;
import java.util.Base64;

public class User extends Entity {
    private String name;
    private String email;
    private String pass;
    private Date date;
    private Role role;
    private byte[] photo;
    private String photoToString;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoToString() {
        return photoToString;
    }

    public void setPhotoToString() {
        if(photo != null){
            photoToString = Base64.getEncoder().encodeToString(photo);
        }
    }
}
