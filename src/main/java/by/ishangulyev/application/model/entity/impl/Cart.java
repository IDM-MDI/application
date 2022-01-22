package by.ishangulyev.application.model.entity.impl;

import by.ishangulyev.application.model.entity.Entity;

public class Cart extends Entity {
    private long userID;

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

}
