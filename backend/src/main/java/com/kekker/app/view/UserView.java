package com.kekker.app.view;

import java.io.Serializable;

public class UserView implements Serializable{

    private String avatarUrl;
    private String nickName;

    public UserView(String nickName, String avatarUrl)
    {
        this.avatarUrl = avatarUrl;
        this.nickName = nickName;
    }

    public UserView()
    {

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
