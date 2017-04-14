package com.netcracker.veromeev.archinc.entity;

public abstract class Entity {

    private int id;

    Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
