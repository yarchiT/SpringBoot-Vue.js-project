package com.kekker.app.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "reaction")
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String type;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "kek_id", nullable = false)
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

    public enum ReactionTypes{
        Loys,
        Dizloys
    }
}
