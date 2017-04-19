package com.netcracker.veromeev.archinc.entity;


import com.netcracker.veromeev.archinc.enumeration.UserType;
import com.netcracker.veromeev.archinc.util.encryption.SHA512Encryption;

import java.util.Objects;

public class User extends Entity {

    private String login;
    private String password;
    private UserType userType;

    public User(int id, UserType userType, String login, String password) {
        super(id);
        this.login = login;
        this.password = password;
        this.setUserType(userType);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(getId());
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", userType=").append(userType);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                userType == user.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, userType);
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}