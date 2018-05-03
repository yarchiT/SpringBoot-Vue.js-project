package com.kekker.app.view;

import com.kekker.app.model.User;

import java.io.Serializable;

// UserView class used to return correct user data from database
public class UserView implements Serializable{

    private String avatarUrl;
    private String nickName;

    public UserView(String nickName, String avatarUrl)
    {
        this.avatarUrl = avatarUrl;
        this.nickName = nickName;
    }

    public UserView(User u)
    {
        avatarUrl = u.getAvatarUrl();
        nickName = u.getNickName();
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
