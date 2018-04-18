package com.kekker.app.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @NotNull
    private long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "kek_id", nullable = false)
    private Kek kek;

    @NotNull
    @Size(max = 150)
    private String text;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Kek getKek() {
        return kek;
    }

    public void setKek(Kek kek) {
        this.kek = kek;
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
