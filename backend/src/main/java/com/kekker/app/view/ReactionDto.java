package com.kekker.app.view;

import com.kekker.app.model.User;

import java.io.Serializable;

public class ReactionDto implements Serializable{
    private String type;
    private UserView owner;

    public ReactionDto(String type, User owner)
    {
        this.type = type;
        this.owner = new UserView(owner.getNickName(), owner.getAvatarUrl());
    }

    public ReactionDto()
    {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserView getOwner() {
        return owner;
    }

    public void setOwner(UserView owner) {
        this.owner = owner;
    }
}
