package com.kekker.app.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// post in our app where user can add text image etc
public class Kek {

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
    @JoinColumn(name = "owner_id")
    private User owner;

    @NotNull
    @OneToMany(mappedBy = "kek", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments;

    @NotNull
    @OneToMany(mappedBy = "kek", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reaction> reactions;


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
}
