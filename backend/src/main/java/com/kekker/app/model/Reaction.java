package com.kekker.app.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "reactions")
public class Reaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_nick_name")
    private User owner;

    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    private Kek kek;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Kek getKek() {
        return kek;
    }

    public void setKek(Kek kek) {
        this.kek = kek;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public enum ReactionTypes{
        Loys,
        Dizloys
    }
}
