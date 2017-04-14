package com.netcracker.veromeev.archinc.dao;

import com.netcracker.veromeev.archinc.dao.exception.DAOException;
import com.netcracker.veromeev.archinc.entity.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {

    private static final String TABLE_NAME = "User";

    public UserDAO(Connection connection) {
        super(connection, TABLE_NAME);
    }

    @Override
    public User read(int id) throws DAOException {
        return null;
    }

    @Override
    public List<User> readAll() throws DAOException {
        return null;
    }

    @Override
    public void insert(User val) throws DAOException {

    }

    @Override
    public void update(int id, User val) throws DAOException {

    }
}
