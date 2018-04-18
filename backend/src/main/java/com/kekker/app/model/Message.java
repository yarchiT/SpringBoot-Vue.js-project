package com.kekker.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @NotNull
    private long id;

//todo: change message mapping
    private int userFromId;
    private int userToId;

    @Temporal(TemporalType.DATE)
    private Date creationDAte;
    private String text;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
