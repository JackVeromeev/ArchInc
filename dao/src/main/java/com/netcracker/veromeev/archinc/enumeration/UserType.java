package com.netcracker.veromeev.archinc.enumeration;

public enum UserType {
    ADMIN,
    CUSTOMER,
    MANAGER,
    HUMAN_RESOURCE,
    NA;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static UserType getById(int id) {
        if (id == ADMIN.getId()) {
            return ADMIN;
        }
        if (id == CUSTOMER.getId()) {
            return CUSTOMER;
        }
        if (id == MANAGER.getId()) {
            return MANAGER;
        }
        if (id == HUMAN_RESOURCE.getId()) {
            return HUMAN_RESOURCE;
        }
        return NA;
    }
}
