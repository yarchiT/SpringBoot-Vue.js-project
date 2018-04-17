package com.kekker.app.model;

import javax.persistence.*;
import java.util.*;


@Entity
public class User {

    private String nickName; // user's id -> must me unique
    private String email; // also unique
    private String password;
    private String first_Name;
    private String last_Name;
    private byte[] avatar;
    private String bio;
    private String gender;

    private Set<Kek> keks = new HashSet<Kek>();   // array of all written keks by user

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<Kek> getKeks() {
        return keks;
    }

    public void setKeks(Set<Kek> keks) {
        this.keks = keks;
    }
}
