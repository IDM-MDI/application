package edu.by.ishangulyev.application.model.entity.impl;

import edu.by.ishangulyev.application.model.entity.Entity;

import java.sql.Date;

public class User extends Entity
{
    private String name;
    private String email;
    private String pass;
    private Date date;
    private Role role;
}
