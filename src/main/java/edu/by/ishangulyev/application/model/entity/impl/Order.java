package edu.by.ishangulyev.application.model.entity.impl;

import edu.by.ishangulyev.application.model.entity.Entity;

public class Order extends Entity
{
    private long cartID;
    private long gadgetID;

    public long getCartID()
    {
        return cartID;
    }

    public void setCartID(long cartID)
    {
        this.cartID = cartID;
    }

    public long getGadgetID()
    {
        return gadgetID;
    }

    public void setGadgetID(long gadgetID)
    {
        this.gadgetID = gadgetID;
    }
}
