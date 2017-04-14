package com.netcracker.veromeev.archinc.entity;


import com.netcracker.veromeev.archinc.enumeration.UserType;
import com.netcracker.veromeev.archinc.util.encryption.SHA512Encryption;

public class User extends Entity {

    private String name;
    private String password;
    private UserType userType;

    public User(int id, UserType userType, String name, String password) {
        super(id);
        this.name = name;
        this.password = password;
        this.setUserType(userType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void encryptAndSetPassword(String openPassword) {
        this.password = SHA512Encryption.encrypt(openPassword);
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}