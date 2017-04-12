package com.netcracker.veromeev.archinc.entity;


import com.netcracker.veromeev.archinc.enumeration.UserType;
import com.netcracker.veromeev.archinc.util.encryption.SHA512Encryption;

public class User extends AbstractEntity {

    private String userName;
    private String password;
    private UserType userType;

    public User(Integer id, String userName, String password) {
        super(id);
        this.setUserName(userName);
        this.setPassword(password);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void encryptAndSetPassword(String openPassword) {
        setPassword(SHA512Encryption.encrypt(openPassword));
    }

    @Override
    public String toSQLSet() {
        StringBuilder builder = new StringBuilder();

        return builder.toString();
    }
}