package com.kekker.app.model;

import java.util.Date;

public class Comment {

    private int id;
    private int commentOwnerId;
    private int kekId;
    private String text;
    private Date creationDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommentOwnerId() {
        return commentOwnerId;
    }

    public void setCommentOwnerId(int commentOwnerId) {
        this.commentOwnerId = commentOwnerId;
    }

    public int getKekId() {
        return kekId;
    }

    public void setKekId(int kekId) {
        this.kekId = kekId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
