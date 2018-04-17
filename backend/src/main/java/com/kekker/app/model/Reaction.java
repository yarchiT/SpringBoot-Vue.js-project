package com.kekker.app.model;


public class Reaction {

    public int id;
    public String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public enum ReactionTypes{
        Loys,
        Dizloys
    }
}
