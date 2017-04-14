package com.netcracker.veromeev.archinc.dao;

import com.netcracker.veromeev.archinc.dao.exception.DAOException;
import com.netcracker.veromeev.archinc.entity.User;
import com.netcracker.veromeev.archinc.entity.util.EntityName;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {

    private static final String TABLE_NAME = "User";

    private static final String UPDATE_QUERY =
            "UPDATE User SET Name = ?, Password = ?,"
            + "id_Usertype = ? WHERE id_User = ?";
    private static final String UPDATE_EXCEPTION_MESSAGE =
            "Can't update User with id = ";

    private static final String INSERT_QUERY =
            "INSERT INTO User SET Name = ?, Password = ?, id_Usertype = ?";
    private static final String INSERT_EXCEPTION_MESSAGE =
            "Can't insert User with Name = ";

    private static final String SELECT_BY_ID_QUERY =
            "SELECT id_User, id_Usertype, Name, Password FROM User " +
                    "WHERE id_User = ? LIMIT 1;";
    private static final String SELECT_BY_ID_ESCEPTION_MESSAGE =
            "Can't find User with id = ";

    private static final String SELECT_ALL_USERS_QUERY =
            "SELECT id_User, id_Usertype, Name, Password FROM User";
    private static final String SELECT_ASS_USERS_ESCEPTION_MESSAGE =
            "Can't open all users";

    private static final String DELETE_USER_BT_ID_QUERY =
            "DELETE FROM User WHERE id_User = ?";
    private static final String DELETE_USER_BT_ID_EXCEPTION_MESSAGE =
            "Can't delete User with id = ";


    public UserDAO(Connection connection) {
        super(connection, TABLE_NAME);
    }

    @Override
    public User read(int id) throws DAOException {
        List<User> resultList = new LinkedList<>();
        executeQuery(SELECT_BY_ID_QUERY, SELECT_BY_ID_ESCEPTION_MESSAGE + id,
                resultList, EntityName.USER,
                statement -> statement.setInt(1, id));
        return resultList.get(0);
    }

    @Override
    public List<User> readAll() throws DAOException {
        List<User> resultList = new LinkedList<>();
        executeQuery(SELECT_ALL_USERS_QUERY,
                SELECT_ASS_USERS_ESCEPTION_MESSAGE,
                resultList, EntityName.USER,
                statement -> {});
        return resultList;
    }

    @Override
    public void insert(User val) throws DAOException {
        executeQuery(INSERT_QUERY, INSERT_EXCEPTION_MESSAGE + val.getName(),
                null, null,
                statement -> {
                    statement.setString(1, val.getName());
                    statement.setString(2, val.getPassword());
                    statement.setInt(3, val.getUserType().getId());
        });
    }

    @Override
    public void update(User val) throws DAOException {
        executeQuery(UPDATE_QUERY,
                UPDATE_EXCEPTION_MESSAGE + val.getId(), null,
                null,
                statement -> {
                statement.setString(1, val.getName());
                statement.setString(2, val.getPassword());
                statement.setInt(3, val.getUserType().getId());
                statement.setInt(4, val.getId());
        });
    }

    @Override
    public void delete(int id) throws DAOException {
        executeQuery(DELETE_USER_BT_ID_QUERY,
                DELETE_USER_BT_ID_EXCEPTION_MESSAGE + id,
                null, null,
                statement -> statement.setInt(1, id));
    }
}
