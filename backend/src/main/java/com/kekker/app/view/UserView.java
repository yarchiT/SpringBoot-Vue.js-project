package com.kekker.app.view;

import java.io.Serializable;

public class UserView implements Serializable{

    private byte[] avatar;
    private String nickName;

    public UserView(String nickName, byte[] avatar)
    {
        this.avatar = avatar;
        this.nickName = nickName;
    }

    public UserView()
    {

    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
