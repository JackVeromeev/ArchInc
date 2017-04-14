package com.netcracker.veromeev.archinc.entity;

public abstract class AbstractEntity {

    protected int id;

    public AbstractEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
