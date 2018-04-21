package com.kekker.app.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// post in our app where user can add text image etc
@Entity
@Table(name = "keks")
public class Kek implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @NotNull
    private long Id;

    @Size(max = 200)
    private String text;

    @Lob
    @Null
    private byte[] image;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "owner_nick_name")
    private User owner;

    @NotNull
    @OneToMany(mappedBy = "kek", fetch = FetchType.LAZY)
    private Set<Comment> comments;

    @NotNull
    @OneToMany(mappedBy = "kek", fetch = FetchType.LAZY)
    private Set<Reaction> reactions = new HashSet<Reaction>();


    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(Set<Reaction> reactions) {
        this.reactions = reactions;
    }
}
