package com.kekker.app.model;

import java.util.Date;

public class Message {

    private int id;
    private int userFromId;
    private int userToId;
    private Date creationDAte;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserFromId() {
        return userFromId;
    }

    public void setUserFromId(int userFromId) {
        this.userFromId = userFromId;
    }

    public int getUserToId() {
        return userToId;
    }

    public void setUserToId(int userToId) {
        this.userToId = userToId;
    }

    public Date getCreationDAte() {
        return creationDAte;
    }

    public void setCreationDAte(Date creationDAte) {
        this.creationDAte = creationDAte;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
