package com.kekker.app.view;

import com.kekker.app.model.User;

import java.io.Serializable;
import java.util.Date;

public class CommentDto implements Serializable{

    private String text;
    private UserView owner;
    private Date creationDate;

    public CommentDto(String text, Date creationDate, User owner)
    {
        this.text = text;
        this.creationDate = creationDate;
        this.owner = new UserView(owner.getNickName(), owner.getAvatarUrl());
    }

    public CommentDto()
    {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserView getOwner() {
        return owner;
    }

    public void setOwner(UserView owner) {
        this.owner = owner;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
