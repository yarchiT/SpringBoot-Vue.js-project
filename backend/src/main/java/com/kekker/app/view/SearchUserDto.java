package com.kekker.app.view;

import com.kekker.app.model.User;

import java.io.Serializable;

public class SearchUserDto implements Serializable {
    private String nickName;
    private String avatarUrl;
    private String firstName;
    private String lastName;
    private String bio;

    public SearchUserDto()
    {

    }

    public SearchUserDto(User user)
    {
        this.nickName = user.getNickName();
        this.avatarUrl = user.getAvatarUrl();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.bio = user.getBio();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
