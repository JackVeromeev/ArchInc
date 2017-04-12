package com.netcracker.veromeev.archinc.enumeration;

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

}
