package com.netcracker.veromeev.archinc.dao;

import com.netcracker.veromeev.archinc.dao.exception.DAOException;
import com.netcracker.veromeev.archinc.entity.User;

import java.sql.Connection;
import java.util.ArrayList;

public class UserDAO extends AbstractDAO<User> {

    UserDAO(Connection connection, String tableName) {
        super(connection, tableName);
    }

    @Override
    public ArrayList<User> readAll() throws DAOException {
        return null;
    }

    @Override
    public User read(int id) throws DAOException {
        return null;
    }
}
