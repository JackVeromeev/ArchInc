package com.netcracker.veromeev.archinc.enumeration;

import com.sun.istack.internal.Nullable;

public enum UserType {
    ADMIN,
    CUSTOMER,
    MANAGER,
    HUMAN_RESOURCER;

    private static final String INIT_QUERY =
            "SELECT (id_Usertype, Type) FROM Usertype;";

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nullable
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
        if (id == HUMAN_RESOURCER.getId()) {
            return HUMAN_RESOURCER;
        }
        return null;
    }
}
